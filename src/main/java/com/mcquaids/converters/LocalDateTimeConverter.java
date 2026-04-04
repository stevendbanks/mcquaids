package com.mcquaids.converters;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LocalDateTimeConverter extends DefaultTypeConverter {

    // Choose a format that matches your UI or form inputs
    private static final DateTimeFormatter FORMATTER =
//            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public Object convertValue(Map<String, Object> context, Object value, Class toType) {

        // String → LocalDateTime
        if (toType == LocalDateTime.class) {
            String[] params = (String[]) value;
            if (params[0] == null || params[0].trim().isEmpty()) {
                return null;
            }
            return LocalDateTime.parse(params[0], FORMATTER);
        }

        // LocalDateTime → String
        if (toType == String.class && value instanceof LocalDateTime) {
            return ((LocalDateTime) value).format(FORMATTER);
        }

        return null;
    }
}