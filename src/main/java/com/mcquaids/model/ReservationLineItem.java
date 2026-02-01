package com.mcquaids.model;

import java.util.Date;
import java.util.Map;

import com.mcquaids.utils.JsonUtils;

public class ReservationLineItem {

    private int reservationLineItemID;
    private String reservationID;
    private int equipmentNumber;    
    private String equipmentType;
    private String equipmentSubType;
    private int quantity;
    private String notes;
    private Map<String, String> properties;
    private Date dateAdded;

    public int getReservationLineItemID() {
        return reservationLineItemID;
    }

    public void setReservationLineItemID(int reservationLineItemID) {
        this.reservationLineItemID = reservationLineItemID;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    /**
	 * @return the equipmentNumber
	 */
	public int getEquipmentNumber() {
		return equipmentNumber;
	}

	/**
	 * @param equipmentNumber the equipmentNumber to set
	 */
	public void setEquipmentNumber(int equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
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

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
    
    public String getPropertiesJson() {
        return JsonUtils.toJson(properties);
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

	@Override
	public String toString() {
		return "ReservationLineItem [reservationLineItemID=" + reservationLineItemID + ", reservationID="
				+ reservationID + ", equipmentNumber=" + equipmentNumber + ", equipmentType=" + equipmentType
				+ ", equipmentSubType=" + equipmentSubType + ", quantity=" + quantity + ", notes=" + notes
				+ ", properties=" + properties + ", dateAdded=" + dateAdded + "]";
	}
    
    
    
}