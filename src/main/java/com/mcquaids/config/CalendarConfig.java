package com.mcquaids.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CalendarConfig {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = CalendarConfig.class.getResourceAsStream("/calendar-mapping.properties")) {

            if (in == null) {
                throw new RuntimeException("calendar-mapping.properties not found on classpath");
            }

            props.load(in);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load calendar-mapping.properties", e);
        }
    }

    public static String getCalendarId(String key) {
        return props.getProperty(key);
    }
}