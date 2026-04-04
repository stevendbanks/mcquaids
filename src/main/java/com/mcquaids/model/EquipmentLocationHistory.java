package com.mcquaids.model;

import java.time.LocalDateTime;

public class EquipmentLocationHistory {

    private Long locationHistoryID;
    
    private Integer reservationId; // nullable
    private Long actionId;     // nullable
    private String actionType; // nullable
    
    
    private Integer equipmentNumber;

    private Long yardID;

    
    private String street;
    private String city;
    private String province;
    private String postal;
    private String country;

    private String locationType;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    private String notes;

    // Getters and setters

    public Long getLocationHistoryID() {
        return locationHistoryID;
    }

    public void setLocationHistoryID(Long locationHistoryID) {
        this.locationHistoryID = locationHistoryID;
    }

    public Integer getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    
    
    /**
	 * @return the yardID
	 */
	public Long getYardID() {
		return yardID;
	}

	/**
	 * @param yardID the yardID to set
	 */
	public void setYardID(Long yardID) {
		this.yardID = yardID;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
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
    
    public Address toAddress() {
        Address address = new Address();
        address.setStreet(this.street);
        address.setCity(this.city);
        address.setProvince(this.province);
        address.setPostalCode(this.postal);
        address.setCountry(this.country);
        return address;
    }
}