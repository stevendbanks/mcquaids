package com.mcquaids.service;

import java.io.InputStream;
import java.util.Collections;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

public class GoogleCalendarClientFactory {

    private static Calendar connection;

    public static synchronized Calendar getConnection() {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    private static Calendar createConnection() {
        try {
            JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            InputStream in = GoogleCalendarClientFactory.class
                    .getResourceAsStream("/google/service-account.json");

            if (in == null) {
                throw new RuntimeException("Could not load /google/service-account.json from classpath");
            }

            GoogleCredentials googleCreds = GoogleCredentials
                    .fromStream(in)
                    .createScoped(Collections.singletonList("https://www.googleapis.com/auth/calendar"));

            ServiceAccountCredentials credentials = (ServiceAccountCredentials) googleCreds;

            return new Calendar.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    jsonFactory,
                    new HttpCredentialsAdapter(credentials)
            )
            .setApplicationName("Dispatch System")
            .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Google Calendar client", e);
        }
    }
}