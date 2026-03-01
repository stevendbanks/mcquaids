package com.mcquaids.model;


import java.util.Date;

public class EquipmentEvent {

    private int eventId;
    private int equipmentNumber;

    private String eventType;        // e.g., PICKUP, DROPOFF, INSPECTED, DAMAGE_DISCOVERED, MAINTENANCE
    private Date eventDateTime;

    // Optional FROM location (movement events only)
    private String fromStreet;
    private String fromCity;
    private String fromProvince;
    private String fromPostal;
    private String fromCountry;

    // Optional TO location (movement events only)
    private String toStreet;
    private String toCity;
    private String toProvince;
    private String toPostal;
    private String toCountry;

    private Integer reservationId;   // nullable
    private String notes;

    // ----- Getters and Setters -----

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(int equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Date eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getFromStreet() {
        return fromStreet;
    }

    public void setFromStreet(String fromStreet) {
        this.fromStreet = fromStreet;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getFromProvince() {
        return fromProvince;
    }

    public void setFromProvince(String fromProvince) {
        this.fromProvince = fromProvince;
    }

    public String getFromPostal() {
        return fromPostal;
    }

    public void setFromPostal(String fromPostal) {
        this.fromPostal = fromPostal;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getToStreet() {
        return toStreet;
    }

    public void setToStreet(String toStreet) {
        this.toStreet = toStreet;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToProvince() {
        return toProvince;
    }

    public void setToProvince(String toProvince) {
        this.toProvince = toProvince;
    }

    public String getToPostal() {
        return toPostal;
    }

    public void setToPostal(String toPostal) {
        this.toPostal = toPostal;
    }

    public String getToCountry() {
        return toCountry;
    }

    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
