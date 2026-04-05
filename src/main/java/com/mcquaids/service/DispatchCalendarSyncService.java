package com.mcquaids.service;

import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Channel;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.mcquaids.dao.DispatchActionDAO;
import com.mcquaids.model.Address;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.ParsedDescription;
import com.mcquaids.model.ParsedSummary;
import com.mcquaids.utils.DescriptionParser;
import com.mcquaids.utils.SummaryParser;

public class DispatchCalendarSyncService {

    private Calendar client = GoogleCalendarClientFactory.getConnection();
    private DispatchActionDAO dao;;
    private Map<String, String> syncTokenStore = new HashMap<>();
    
    private final Map<String, Instant> lastProcessedEvent = new ConcurrentHashMap<>();
    
public DispatchCalendarSyncService() {
    JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
    this.dao = new DispatchActionDAO(jdbcTemplate);
	
}


public void registerAllWatches() {
    Set<String> calendarIds = loadUniqueCalendarIds();

    System.out.println("Registering watches for " + calendarIds.size() + " unique calendars");

    for (String calendarId : calendarIds) {
        registerWatch(calendarId);
    }
}

public Set<String> loadUniqueCalendarIds() {
    Properties props = new Properties();

    try (InputStream in = getClass().getClassLoader()
            .getResourceAsStream("calendar-mapping.properties")) {

        props.load(in);

    } catch (Exception e) {
        throw new RuntimeException("Failed to load calendar-mapping.properties", e);
    }

    Set<String> uniqueCalendarIds = new HashSet<>();

    for (String key : props.stringPropertyNames()) {
        if (key.startsWith("calendar.") && !key.endsWith(".name")) {
            uniqueCalendarIds.add(props.getProperty(key));
        }
    }

    return uniqueCalendarIds;
}



private void registerWatch(String calendarId) {
    try {
        Calendar client = GoogleCalendarClientFactory.getConnection();

        Channel channel = new Channel()
                .setId(UUID.randomUUID().toString())
                .setType("web_hook")
                .setAddress("https://newschasers.ca/mcquaids/google/calendar/webhook")
                .setToken(calendarId); // token = calendarId

        Channel response = client.events().watch(calendarId, channel).execute();

        System.out.println("Watch created for calendar: " + calendarId);
        System.out.println("Channel ID: " + response.getId());
        System.out.println("Resource ID: " + response.getResourceId());
        System.out.println("Expiration: " + response.getExpiration());

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Failed to register watch for calendar: " + calendarId);
    }
}

public void handleUpdatedEvent(String calendarId, String eventId) {
	
	String key = calendarId + ":" + eventId;
	Instant now = Instant.now();
	Instant last = lastProcessedEvent.get(key);

	// Ignore repeated updates within 2 seconds
	if (last != null && Duration.between(last, now).toMillis() < 2000) {
	    System.out.println("Duplicate event update ignored for " + key);
	    return;
	}

	lastProcessedEvent.put(key, now);	
	
    System.out.println("\n=== handleUpdatedEvent() CALLED ===");
    System.out.println("Calendar ID: " + calendarId);
    System.out.println("Event ID: " + eventId);

    try {
        // Fetch event from Google
        Event event = client.events().get(calendarId, eventId).execute();
        System.out.println("Fetched Google Event:");
        System.out.println("  Summary: " + event.getSummary());
        System.out.println("  Description: " + event.getDescription());
        System.out.println("  Status: " + event.getStatus());
        System.out.println("  Start: " + event.getStart());
        System.out.println("  End: " + event.getEnd());

        // Extract dispatch_action_id
        String dispatchActionID = null;
        if (event.getExtendedProperties() != null &&
            event.getExtendedProperties().getPrivate() != null) {

            dispatchActionID = event.getExtendedProperties()
                         .getPrivate()
                         .get("dispatch_action_id");
        }

        System.out.println("Extended Property dispatch_action_id: " + dispatchActionID);

        if (dispatchActionID == null) {
            System.out.println("No dispatch_action_id found → skipping sync.");
            return;
        }

        // Validate ID
        Long dispatchActionId;
        try {
            dispatchActionId = Long.valueOf(dispatchActionID);
        } catch (NumberFormatException e) {
            System.err.println("Invalid dispatch_action_id in Google event: " + dispatchActionID);
            return;
        }

        System.out.println("Resolved dispatchActionId: " + dispatchActionId);

        // Load dispatch action
        DispatchAction action = dao.getByDispatchActionID(dispatchActionId);
        if (action == null) {
            System.err.println("No DispatchAction found for ID: " + dispatchActionId);
            return;
        }

        // Log current DB state
        System.out.println("Loaded DispatchAction:");
        System.out.println("  dispatchActionId: " + action.getDispatchActionId());
        System.out.println("  reservationID: " + action.getReservationID());
        System.out.println("  equipmentNumber: " + action.getEquipmentNumber());
        System.out.println("  actionType: " + action.getActionType());
        System.out.println("  googleEventId: " + action.getGoogleEventId());
        System.out.println("  googleCalendarId: " + action.getGoogleCalendarId());
        System.out.println("  lastCalendarSyncAt: " + action.getLastCalendarSyncAt());
        System.out.println("  status: " + action.getStatus());
        System.out.println("  fromAddress: " + action.getFromAddress());
        System.out.println("  toAddress: " + action.getToAddress());
        System.out.println("  scheduledDateTime: " + action.getScheduledDateTime());
        System.out.println("  driverId: " + action.getDriverId());
        System.out.println("  notes: " + action.getNotes());
        System.out.println("  reservationLineItemID: " + action.getReservationLineItemID());

        // Perform sync operations
        System.out.println("Syncing date/time...");
        syncDateTime(action, event);

        System.out.println("Syncing calendar move...");
        syncCalendarMove(action, calendarId);

        System.out.println("Syncing summary...");
        syncSummary(action, event.getSummary());

        System.out.println("Syncing description...");
        syncDescription(action, event.getDescription());

        // Update DB
        dao.updateFromCalendarSync(action);
        System.out.println("DispatchAction updated successfully.");
        System.out.println("=== handleUpdatedEvent() COMPLETE ===\n");

    } catch (Exception e) {
        System.err.println("ERROR in handleUpdatedEvent:");
        e.printStackTrace();
    }
}

    public void handleDeletedEvent(String eventId) {
        try {
            dao.clearCalendarLinkageByEventId(eventId);
            System.out.println("Cleared calendar linkage for Google event: " + eventId);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private void syncDateTime(DispatchAction action, Event event) {

        // Case 1: Timed event (has full date+time)
        if (event.getStart() != null && event.getStart().getDateTime() != null) {

            // Google gives you milliseconds since epoch in UTC
            Instant instant = Instant.ofEpochMilli(event.getStart().getDateTime().getValue());

            // Convert to LocalDateTime in your system zone (or Halifax if you prefer)
            LocalDateTime dateTime = instant
                    .atZone(ZoneId.of("America/Halifax"))
                    .toLocalDateTime();

            action.setScheduledDateTime(dateTime);
            return;
        }

        // Case 2: All-day event (Google gives only a date, no time)
        if (event.getStart() != null && event.getStart().getDate() != null) {

            // Parse the date
            LocalDate date = LocalDate.parse(event.getStart().getDate().toStringRfc3339());

            // Convert to LocalDateTime at midnight
            LocalDateTime dateTime = date.atStartOfDay();

            action.setScheduledDateTime(dateTime);
        }
    }

    private void syncCalendarMove(DispatchAction action, String newCalendarId) {
        if (!newCalendarId.equals(action.getGoogleCalendarId())) {
            action.setGoogleCalendarId(newCalendarId);
        }
    }

    private void syncSummary(DispatchAction action, String summary) {
        ParsedSummary parsed = SummaryParser.parse(summary);
        if (parsed.getActionType() != null) action.setActionType(parsed.getActionType());
//        if (parsed.getDriver() != null) action.setAssignedDriver(parsed.getDriverId());
    }

    private void syncDescription(DispatchAction action, String description) {
        ParsedDescription parsed = DescriptionParser.parse(description);

        if (parsed.getFromAddress() != null) {
            action.setFromAddress(new Address(parsed.getFromAddress(), null, null, null, null));
        }

        if (parsed.getToAddress() != null) {
            action.setToAddress(new Address(parsed.getToAddress(), null, null, null, null));
        }

        if (parsed.getNotes() != null) {
            action.setNotes(parsed.getNotes());
        }
    }


    public void handleCalendarChange(String calendarId) {
        try {
            Calendar client = GoogleCalendarClientFactory.getConnection();

            // 1. Load last sync token for this calendar
            String syncToken = syncTokenStore.get(calendarId);

            Calendar.Events.List request;

            if (syncToken == null) {
                // First time: full sync
                request = client.events().list(calendarId)
                        .setSingleEvents(true)
                        .setOrderBy("startTime");
            } else {
                // Incremental sync
                request = client.events().list(calendarId)
                        .setSyncToken(syncToken);
            }

            Events events = request.execute();

            // 2. Process changed events
            if (events.getItems() != null) {
                for (Event event : events.getItems()) {
                    if (event.getStatus().equals("cancelled")) {
                        handleDeletedEvent(event.getId());
                    } else {
                        handleUpdatedEvent(calendarId, event.getId());
                    }
                }
            }

            // 3. Save new sync token
            if (events.getNextSyncToken() != null) {
                syncTokenStore.put(calendarId, events.getNextSyncToken());
            }

        } catch (GoogleJsonResponseException e) {
            // If sync token is invalid, Google requires a full resync
            if (e.getStatusCode() == 410) {
                syncTokenStore.remove(calendarId);
                handleCalendarChange(calendarId); // retry with full sync
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}