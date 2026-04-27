package com.mcquaids.model;

import java.time.LocalDateTime;

public class MovementOrderEventLog {

    private long eventId;
    private long movementOrderId;
    private Long movementOrderLineId; // nullable
    private String eventType;
    private LocalDateTime eventDateTime;
    private String performedBy;
    private String notes;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getMovementOrderId() {
        return movementOrderId;
    }

    public void setMovementOrderId(long movementOrderId) {
        this.movementOrderId = movementOrderId;
    }

    public Long getMovementOrderLineId() {
        return movementOrderLineId;
    }

    public void setMovementOrderLineId(Long movementOrderLineId) {
        this.movementOrderLineId = movementOrderLineId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
