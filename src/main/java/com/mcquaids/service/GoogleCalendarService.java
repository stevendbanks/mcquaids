package com.mcquaids.service;

import java.time.Instant;
import java.util.Collections;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.mcquaids.config.CalendarConfig;
import com.mcquaids.dao.DispatchActionDAO;
import com.mcquaids.model.DispatchCalendarDTO;

public class GoogleCalendarService {

    private final Calendar calendarClient;
    private final DispatchActionDAO dispatchActionDao;

    public GoogleCalendarService() {
        this.calendarClient = GoogleCalendarClientFactory.getConnection();
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.dispatchActionDao = new DispatchActionDAO(jdbcTemplate);
    }

    public String pushDispatchToCalendar(DispatchCalendarDTO dto) throws Exception {

        // --------------------------------------------
        // 1. Determine calendar key
        // --------------------------------------------
        String calendarKey;

        if (dto.getEquipmentType() != null && !dto.getEquipmentType().isEmpty()) {
            calendarKey = "calendar." + dto.getEquipmentType()
                    .toLowerCase()
                    .replace(" ", "_")
                    .replace("-", "_");
        } else {
            // Movement Orders may not have equipmentType
            calendarKey = "calendar.default";
        }

        System.out.println("Pushing to calendar key = " + calendarKey);

        String calendarId = CalendarConfig.getCalendarId(calendarKey);
        if (calendarId == null) {
            throw new RuntimeException("No calendar configured for key: " + calendarKey);
        }

        try {
            // --------------------------------------------
            // 2. Build Google Calendar Event
            // --------------------------------------------
            Event event = new Event()
                    .setSummary(dto.getEventTitle())
                    .setDescription(dto.getEventDescription());

            // Extended properties allow us to track the dispatch action
            event.setExtendedProperties(
                    new Event.ExtendedProperties()
                            .setPrivate(Collections.singletonMap(
                                    "dispatch_action_id",
                                    String.valueOf(dto.getDispatchActionId())
                            ))
            );

            // --------------------------------------------
            // 3. Set start/end times
            // --------------------------------------------
            event.setStart(new EventDateTime()
                    .setDateTime(new DateTime(dto.getStart().toInstant().toEpochMilli()))
                    .setTimeZone(dto.getStart().getZone().getId()));

            event.setEnd(new EventDateTime()
                    .setDateTime(new DateTime(dto.getEnd().toInstant().toEpochMilli()))
                    .setTimeZone(dto.getEnd().getZone().getId()));

            // --------------------------------------------
            // 4. Insert event into Google Calendar
            // --------------------------------------------
            Event created = calendarClient.events()
                    .insert(calendarId, event)
                    .execute();

            // --------------------------------------------
            // 5. Store linkage in DB
            // --------------------------------------------
            dispatchActionDao.updateCalendarLinkage(
                    dto.getDispatchActionId(),
                    created.getId(),
                    calendarId,
                    Instant.now()
            );

            return created.getHtmlLink();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failed to push event to Google Calendar: " + ex.getMessage(), ex);
        }
    }
 
    
    public void deleteEvent(String calendarId, String eventId) throws Exception {

        if (calendarId == null || eventId == null) {
            throw new IllegalArgumentException("Calendar ID and Event ID are required to delete an event.");
        }

        try {
            calendarClient
                .events()
                .delete(calendarId, eventId)
                .execute();

        } catch (GoogleJsonResponseException gjre) {
            // 410 Gone = event already deleted on Google side
            if (gjre.getStatusCode() == 410) {
                System.out.println("Event already deleted on Google Calendar. Treating as success.");
                return;
            }

            // Other Google API errors should still bubble up
            throw new Exception("Failed to delete Google Calendar event: " + gjre.getMessage(), gjre);

        } catch (Exception e) {
            throw new Exception("Failed to delete Google Calendar event: " + e.getMessage(), e);
        }
    }
    
    
}