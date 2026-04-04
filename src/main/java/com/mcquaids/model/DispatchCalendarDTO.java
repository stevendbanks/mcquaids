package com.mcquaids.model;


import java.time.ZonedDateTime;

public class DispatchCalendarDTO {

    public Long dispatchActionId;
	public Integer reservationId;
    public String customerName;
    public String customerEmail;

    public String equipmentType;
    public String equipmentSubType;
    public Integer equipmentNumber;

    public String fromAddress;
    public String toAddress;

    public ZonedDateTime start;
    public ZonedDateTime end;

    public String notes;

	/**
	 * @return the dispatchActionId
	 */
	public Long getDispatchActionId() {
		return dispatchActionId;
	}

	/**
	 * @param dispatchActionId the dispatchActionId to set
	 */
	public void setDispatchActionId(Long dispatchActionId) {
		this.dispatchActionId = dispatchActionId;
	}

	/**
	 * @return the reservationId
	 */
	public Integer getReservationId() {
		return reservationId;
	}

	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the equipmentType
	 */
	public String getEquipmentType() {
		return equipmentType;
	}

	/**
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
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
	 * @return the equipmentNumber
	 */
	public Integer getEquipmentNumber() {
		return equipmentNumber;
	}

	/**
	 * @param equipmentNumber the equipmentNumber to set
	 */
	public void setEquipmentNumber(Integer equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * @return the start
	 */
	public ZonedDateTime getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(ZonedDateTime start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public ZonedDateTime getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(ZonedDateTime end) {
		this.end = end;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
}