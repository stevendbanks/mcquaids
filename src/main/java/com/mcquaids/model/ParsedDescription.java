package com.mcquaids.model;

public class ParsedDescription {

    private final String fromAddress;
    private final String toAddress;
    private final String notes;

    public ParsedDescription(String fromAddress, String toAddress, String notes) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.notes = notes;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getNotes() {
        return notes;
    }
}