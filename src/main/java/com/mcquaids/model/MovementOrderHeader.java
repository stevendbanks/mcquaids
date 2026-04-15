package com.mcquaids.model;

import java.time.LocalDateTime;

public class MovementOrderHeader {
    private Long movementOrderID;
    private String requestedBy;
    private LocalDateTime requestedDateTime;
    private String priority;
    private String movementType;
    private String reasonCode;
    private String notes;
    private String status;

    public Long getMovementOrderID() {
        return movementOrderID;
    }

    public void setMovementOrderID(Long movementOrderID) {
        this.movementOrderID = movementOrderID;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LocalDateTime getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(LocalDateTime requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}