package com.mcquaids.service.webhooks.google;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mcquaids.service.DispatchCalendarSyncService;

public class CalendarWatchStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== CalendarWatchStartupListener: Registering Google Calendar watches ===");

        try {
            DispatchCalendarSyncService service = new DispatchCalendarSyncService();
            service.registerAllWatches();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to register Google Calendar watch channels");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Optional: clean up channels on shutdown
    }
}