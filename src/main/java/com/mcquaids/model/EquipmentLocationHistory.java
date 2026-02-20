package com.mcquaids.model;

public class EquipmentLocationHistory {

    private int locationHistoryID;
    private int equipmentNumber;

    private String street;
    private String city;
    private String province;
    private String postal;
    private String country;

    private String locationType;

    private java.util.Date startDateTime;
    private java.util.Date endDateTime;

    private Integer reservationId; // nullable
    private String notes;

    // Getters and setters

    public int getLocationHistoryID() {
        return locationHistoryID;
    }

    public void setLocationHistoryID(int locationHistoryID) {
        this.locationHistoryID = locationHistoryID;
    }

    public int getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(int equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public java.util.Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(java.util.Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public java.util.Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(java.util.Date endDateTime) {
        this.endDateTime = endDateTime;
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