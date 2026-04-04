package com.mcquaids.utils;

import com.mcquaids.model.DispatchActionType;
import com.mcquaids.model.ParsedSummary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SummaryParser {

    private static final Pattern ACTION_TYPE_PATTERN =
            Pattern.compile("\\[(.*?)\\]");   // e.g. [Pickup]

    private static final Pattern DRIVER_NAME_PATTERN =
            Pattern.compile("Driver:\\s*(.+)", Pattern.CASE_INSENSITIVE);

    private static final Pattern DRIVER_ID_PATTERN =
            Pattern.compile("DriverID:\\s*(\\d+)", Pattern.CASE_INSENSITIVE);

    public static ParsedSummary parse(String summary) {
        if (summary == null || summary.isBlank()) {
            return new ParsedSummary(null, null, null);
        }

        DispatchActionType actionType = parseActionType(summary);
        Long driverId = parseDriverId(summary);
        String driverName = parseDriverName(summary);

        return new ParsedSummary(actionType, driverId, driverName);
    }

    private static DispatchActionType parseActionType(String summary) {
        Matcher m = ACTION_TYPE_PATTERN.matcher(summary);
        if (m.find()) {
            try {
                return DispatchActionType.valueOf(m.group(1).toUpperCase());
            } catch (Exception ignored) {}
        }
        return null;
    }

    private static Long parseDriverId(String summary) {
        Matcher m = DRIVER_ID_PATTERN.matcher(summary);
        if (m.find()) {
            return Long.valueOf(m.group(1));
        }
        return null;
    }

    private static String parseDriverName(String summary) {
        Matcher m = DRIVER_NAME_PATTERN.matcher(summary);
        if (m.find()) {
            return m.group(1).trim();
        }
        return null;
    }
}