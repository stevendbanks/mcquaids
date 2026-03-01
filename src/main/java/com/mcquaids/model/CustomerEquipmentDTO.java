package com.mcquaids.model;

import java.util.Date;


	public class CustomerEquipmentDTO {

	    private int reservationID;
	    private String customerID;
	    private Date startDate;
	    private Date endDate;
	    private String reservationStatusCode;

	    private String reservationStatusCodeText;
	    
	    private int reservationLineItem;
	    private int equipmentNumber;
	    private String equipmentNotes;

	    private int equipmentType;
	    private String equipmentTypeText;        // NEW: from lkp_equipmenttypes

	    private String equipmentSubType;
	    private String equipmentSubTypeText;     // NEW: from subtype lookup tables



	
	/**
	 * @return the reservationID
	 */
	public int getReservationID() {
		return reservationID;
	}
	/**
	 * @param reservationID the reservationID to set
	 */
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the reservationStatusCode
	 */
	public String getReservationStatusCode() {
		return reservationStatusCode;
	}
	/**
	 * @param reservationStatusCode the reservationStatusCode to set
	 */
	public void setReservationStatusCode(String reservationStatusCode) {
		this.reservationStatusCode = reservationStatusCode;
	}
	public String getReservationStatusCodeText() {
		return reservationStatusCodeText;
	}
	public void setReservationStatusCodeText(String reservationStatusCodeText) {
		this.reservationStatusCodeText = reservationStatusCodeText;
	}
	/**
	 * @return the reservationLineItem
	 */
	public int getReservationLineItem() {
		return reservationLineItem;
	}
	/**
	 * @param reservationLineItem the reservationLineItem to set
	 */
	public void setReservationLineItem(int reservationLineItem) {
		this.reservationLineItem = reservationLineItem;
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
	/**
	 * @return the equipmentNotes
	 */
	public String getEquipmentNotes() {
		return equipmentNotes;
	}
	/**
	 * @param equipmentNotes the equipmentNotes to set
	 */
	public void setEquipmentNotes(String equipmentDescription) {
		this.equipmentNotes = equipmentDescription;
	}
	/**
	 * @return the equipmentType
	 */
	public int getEquipmentType() {
		return equipmentType;
	}
	/**
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(int equipmentType) {
		this.equipmentType = equipmentType;
	}
	/**
	 * @return the equipmentTypeText
	 */
	public String getEquipmentTypeText() {
		return equipmentTypeText;
	}
	/**
	 * @param equipmentTypeText the equipmentTypeText to set
	 */
	public void setEquipmentTypeText(String equipmentTypeText) {
		this.equipmentTypeText = equipmentTypeText;
	}
	/**
	 * @return the equipmentSubType
	 */
	public String getEquipmentSubType() {
		return equipmentSubType;
	}
	/**
	 * @param equipmentSubType the equipmentSubType to set
	 */
	public void setEquipmentSubType(String equipmentSubType) {
		this.equipmentSubType = equipmentSubType;
	}
	/**
	 * @return the equipmentSubTypeText
	 */
	public String getEquipmentSubTypeText() {
		return equipmentSubTypeText;
	}
	/**
	 * @param equipmentSubTypeText the equipmentSubTypeText to set
	 */
	public void setEquipmentSubTypeText(String equipmentSubTypeText) {
		this.equipmentSubTypeText = equipmentSubTypeText;
	}


    
}