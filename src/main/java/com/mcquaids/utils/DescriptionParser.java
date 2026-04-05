package com.mcquaids.utils;

import com.mcquaids.model.ParsedDescription;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptionParser {

    private static final Pattern FROM_PATTERN =
            Pattern.compile("From:\\s*(.*)", Pattern.CASE_INSENSITIVE);

    private static final Pattern TO_PATTERN =
            Pattern.compile("To:\\s*(.*)", Pattern.CASE_INSENSITIVE);

    private static final Pattern NOTES_PATTERN =
            Pattern.compile("Notes:\\s*(.*)", Pattern.CASE_INSENSITIVE);

    public static ParsedDescription parse(String text) {
        if (text == null || text.isBlank()) {
            return new ParsedDescription(null, null, null);
        }

        String from = extract(text, FROM_PATTERN);
        String to = extract(text, TO_PATTERN);
        String notes = extract(text, NOTES_PATTERN);

        return new ParsedDescription(from, to, notes);
    }

    private static String extract(String text, Pattern pattern) {
        Matcher m = pattern.matcher(text);
        return m.find() ? m.group(1).trim() : null;
    }
}