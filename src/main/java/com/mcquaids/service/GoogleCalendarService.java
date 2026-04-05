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

    public String pushReservationToCalendar(DispatchCalendarDTO dto) throws Exception {

        String key = "calendar." + dto.equipmentType
                .toLowerCase()
                .replace(" ", "_")
                .replace("-", "_");
        
       
//        key = "calendar.shunt_truck";
        System.out.println("Pushing to this calendar=" + key);

        String calendarId = CalendarConfig.getCalendarId(key);
try {
        
        if (calendarId == null) {
            throw new RuntimeException("No calendar configured for equipment type: " + dto.equipmentType);
        }

        Event event = new Event()
                .setSummary("Dispatch: " + dto.equipmentType + " - " + dto.equipmentSubType + " {#" + dto.equipmentNumber + "} ");
//                .setLocation(dto.fromAddress);
   
        // Put this here so that if the event is moved to a different calendar, we can find out what was changed on out system
        event.setExtendedProperties(
        	    new Event.ExtendedProperties()
        	        .setPrivate(Collections.singletonMap("dispatch_action_id", String.valueOf(dto.dispatchActionId)))
        	);
        

        StringBuilder desc = new StringBuilder();
        desc.append("Reservation #: ").append(dto.reservationId).append("\n");
        desc.append("Customer: ").append(dto.customerName).append("\n");
        desc.append("Email: ").append(dto.customerEmail).append("\n\n");
        desc.append("From: ").append(dto.fromAddress).append("\n");
        desc.append("To: ").append(dto.toAddress).append("\n");
        desc.append("Notes: ").append(dto.notes).append("\n");

        event.setDescription(desc.toString());

        event.setStart(new EventDateTime()
                .setDateTime(new DateTime(dto.start.toInstant().toEpochMilli()))
                .setTimeZone(dto.start.getZone().getId()));

        event.setEnd(new EventDateTime()
                .setDateTime(new DateTime(dto.end.toInstant().toEpochMilli()))
                .setTimeZone(dto.end.getZone().getId()));

        Event created = calendarClient.events().insert(calendarId, event).execute();
        
        // Store linkage
        dispatchActionDao.updateCalendarLinkage(
            dto.dispatchActionId,
            created.getId(),
            calendarId,
            Instant.now()
        );
        
        return created.getHtmlLink();        
} catch (Exception ex) {
	ex.printStackTrace();
}
     return null; 

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