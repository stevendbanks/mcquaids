package com.mcquaids.model;

import java.time.ZonedDateTime;

public class DispatchCalendarDTO {

    private Long dispatchActionId;

    // Reservation-specific (nullable for Movement Orders)
    private Integer reservationId;
    private String customerName;
    private String customerEmail;
    private String equipmentType;
    private String equipmentSubType;

    // Common fields
    private Integer equipmentNumber;
    private String fromAddress;
    private String toAddress;

    private ZonedDateTime start;
    private ZonedDateTime end;

    private String notes;

    // NEW — identifies Reservation vs Movement Order
    private DispatchSourceType sourceType;

    // NEW — unified calendar event fields
    private String eventTitle;
    private String eventDescription;

    // ------------------------------------------------------------
    // Getters / Setters
    // ------------------------------------------------------------

    public Long getDispatchActionId() {
        return dispatchActionId;
    }

    public void setDispatchActionId(Long dispatchActionId) {
        this.dispatchActionId = dispatchActionId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentSubType() {
        return equipmentSubType;
    }

    public void setEquipmentSubType(String equipmentSubType) {
        this.equipmentSubType = equipmentSubType;
    }

    public Integer getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public DispatchSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(DispatchSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
