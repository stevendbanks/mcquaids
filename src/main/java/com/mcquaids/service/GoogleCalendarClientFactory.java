package com.mcquaids.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
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
            GsonFactory jsonFactory = GsonFactory.getDefaultInstance();

            // Read environment variable
            String keyPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

            if (keyPath == null || keyPath.isEmpty()) {
                throw new RuntimeException(
                    "Environment variable GOOGLE_APPLICATION_CREDENTIALS is not set."
                );
            }

            // Validate file exists
            if (!Files.exists(Paths.get(keyPath))) {
                throw new RuntimeException(
                    "Google service account file not found at: " + keyPath
                );
            }

            // Load credentials from external file
            InputStream in = new FileInputStream(keyPath);

            GoogleCredentials googleCreds = GoogleCredentials
                    .fromStream(in)
                    .createScoped(Collections.singletonList(
                            "https://www.googleapis.com/auth/calendar"
                    ));

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
