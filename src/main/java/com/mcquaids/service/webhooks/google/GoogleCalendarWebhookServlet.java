package com.mcquaids.service.webhooks.google;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcquaids.service.DispatchCalendarSyncService;

public class GoogleCalendarWebhookServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DispatchCalendarSyncService syncService;
	

    @Override
    public void init() throws ServletException {
        System.out.println("GoogleCalendarWebhookServlet.init() CALLED");
        syncService = new DispatchCalendarSyncService();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("GoogleCalendarWebhookServlet.doGet() ENTERED");
        resp.getWriter().write("Webhook endpoint is alive");
    }
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("GoogleCalendarWebhookServlet.doPost() ENTERED");
        
        String channelId = req.getHeader("X-Goog-Channel-ID");
        String resourceId = req.getHeader("X-Goog-Resource-ID");
        String resourceState = req.getHeader("X-Goog-Resource-State");
        String calendarId = req.getHeader("X-Goog-Channel-Token");

        System.out.println("=== GOOGLE WEBHOOK RECEIVED ===");
        System.out.println("Channel-ID: " + channelId);
        System.out.println("Resource-ID: " + resourceId);
        System.out.println("Resource-State: " + resourceState);
        System.out.println("Channel-Token (calendarId): " + calendarId);


        // SYNC ping
        if ("sync".equals(resourceState)) {
            resp.setStatus(200);
            return;
        }

        // Deleted event (Google sends {"id":"xyz"})
        if ("deleted".equals(resourceState)) {
            // Google does NOT send event data except for deletes
            String body = req.getReader().lines().collect(Collectors.joining());
            System.out.println("Body: " + body);
        	String eventId = extractEventId(body);
            syncService.handleDeletedEvent(eventId);
            resp.setStatus(200);
            return;
        }

        // For created/updated events, Google sends NO BODY
        // You must fetch changes using incremental sync
        syncService.handleCalendarChange(calendarId);

        resp.setStatus(200);
    }

    private String extractEventId(String body) {
        // Google sends JSON like: {"id":"abc123"}
        int idx = body.indexOf("\"id\"");
        if (idx == -1) return null;
        int start = body.indexOf(":", idx) + 2;
        int end = body.indexOf("\"", start);
        return body.substring(start, end);
    }
}