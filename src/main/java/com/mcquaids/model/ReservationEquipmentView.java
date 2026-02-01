package com.mcquaids.model;

import java.util.Date;
import java.util.Map;

public class ReservationEquipmentView {

    // reservation_equipment fields
    private int reservationLineItem;
    private String reservationID;
    private String equipmentType;
    private String equipmentSubType;
    private int quantity;
    private String notes;
    private Map<String, Object> properties;
    private Date dateAdded;

    // reservation fields
    private String customerID;
    private String reservationStatusCode;
    private Date startDate;
    private Date endDate;
    private String leaseID;

    // customer fields
    private String customerNotes;
    private Date customerCreatedDateTime;
    private String customerCreatedUserID;

    // user fields
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String province;
    private String country;

    // equipment text fields
    private String equipmentTypeText;
    private String equipmentSubTypeText;

    // reservation status description
    private String reservationStatusDescription;

    // Getters and setters...

    public int getReservationLineItem() {
        return reservationLineItem;
    }

    public void setReservationLineItem(int reservationEquipmentID) {
        this.reservationLineItem = reservationEquipmentID;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getReservationStatusCode() {
        return reservationStatusCode;
    }

    public void setReservationStatusCode(String reservationStatusCode) {
        this.reservationStatusCode = reservationStatusCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(String leaseID) {
        this.leaseID = leaseID;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public Date getCustomerCreatedDateTime() {
        return customerCreatedDateTime;
    }

    public void setCustomerCreatedDateTime(Date customerCreatedDateTime) {
        this.customerCreatedDateTime = customerCreatedDateTime;
    }

    public String getCustomerCreatedUserID() {
        return customerCreatedUserID;
    }

    public void setCustomerCreatedUserID(String customerCreatedUserID) {
        this.customerCreatedUserID = customerCreatedUserID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEquipmentTypeText() {
        return equipmentTypeText;
    }

    public void setEquipmentTypeText(String equipmentTypeText) {
        this.equipmentTypeText = equipmentTypeText;
    }

    public String getEquipmentSubTypeText() {
        return equipmentSubTypeText;
    }

    public void setEquipmentSubTypeText(String equipmentSubTypeText) {
        this.equipmentSubTypeText = equipmentSubTypeText;
    }

    public String getReservationStatusDescription() {
        return reservationStatusDescription;
    }

    public void setReservationStatusDescription(String reservationStatusDescription) {
        this.reservationStatusDescription = reservationStatusDescription;
    }
}