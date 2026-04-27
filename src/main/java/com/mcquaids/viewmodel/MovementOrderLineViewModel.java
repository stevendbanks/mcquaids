package com.mcquaids.viewmodel;

import java.time.LocalDateTime;

public class MovementOrderLineViewModel {

    // Equipment
    private Integer equipmentNumber;
    private String equipmentType;
    private String equipmentSubType;

    // Origin (FROM)
    private String fromLocationType;
    private String fromYardName;
    private String fromStreet;
    private String fromCity;
    private String fromProvince;
    private String fromCountry;

    // Destination (TO) — from header
    private String targetLocationType;
    private String targetYardName;   // ✔ yard name, not ID
    private String targetStreet;
    private String targetCity;
    private String targetProvince;
    private String targetPostal;
    private String targetCountry;

    // Dispatch
    private Long dispatchId;
    private String dispatchStatus;
    private String driverName;
    private LocalDateTime scheduledDateTime;
    private LocalDateTime startedDateTime;
    private LocalDateTime completedDateTime;

    // Line
    private String lineStatus;

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
	 * @return the fromLocationType
	 */
	public String getFromLocationType() {
		return fromLocationType;
	}

	/**
	 * @param fromLocationType the fromLocationType to set
	 */
	public void setFromLocationType(String fromLocationType) {
		this.fromLocationType = fromLocationType;
	}

	/**
	 * @return the fromYardName
	 */
	public String getFromYardName() {
		return fromYardName;
	}

	/**
	 * @param fromYardName the fromYardName to set
	 */
	public void setFromYardName(String fromYardName) {
		this.fromYardName = fromYardName;
	}

	/**
	 * @return the fromStreet
	 */
	public String getFromStreet() {
		return fromStreet;
	}

	/**
	 * @param fromStreet the fromStreet to set
	 */
	public void setFromStreet(String fromStreet) {
		this.fromStreet = fromStreet;
	}

	/**
	 * @return the fromCity
	 */
	public String getFromCity() {
		return fromCity;
	}

	/**
	 * @param fromCity the fromCity to set
	 */
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	/**
	 * @return the fromProvince
	 */
	public String getFromProvince() {
		return fromProvince;
	}

	/**
	 * @param fromProvince the fromProvince to set
	 */
	public void setFromProvince(String fromProvince) {
		this.fromProvince = fromProvince;
	}

	/**
	 * @return the fromCountry
	 */
	public String getFromCountry() {
		return fromCountry;
	}

	/**
	 * @param fromCountry the fromCountry to set
	 */
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	/**
	 * @return the targetLocationType
	 */
	public String getTargetLocationType() {
		return targetLocationType;
	}

	/**
	 * @param targetLocationType the targetLocationType to set
	 */
	public void setTargetLocationType(String targetLocationType) {
		this.targetLocationType = targetLocationType;
	}

	/**
	 * @return the targetYardName
	 */
	public String getTargetYardName() {
		return targetYardName;
	}

	/**
	 * @param targetYardName the targetYardName to set
	 */
	public void setTargetYardName(String targetYardName) {
		this.targetYardName = targetYardName;
	}

	/**
	 * @return the targetStreet
	 */
	public String getTargetStreet() {
		return targetStreet;
	}

	/**
	 * @param targetStreet the targetStreet to set
	 */
	public void setTargetStreet(String targetStreet) {
		this.targetStreet = targetStreet;
	}

	/**
	 * @return the targetCity
	 */
	public String getTargetCity() {
		return targetCity;
	}

	/**
	 * @param targetCity the targetCity to set
	 */
	public void setTargetCity(String targetCity) {
		this.targetCity = targetCity;
	}

	/**
	 * @return the targetProvince
	 */
	public String getTargetProvince() {
		return targetProvince;
	}

	/**
	 * @param targetProvince the targetProvince to set
	 */
	public void setTargetProvince(String targetProvince) {
		this.targetProvince = targetProvince;
	}

	/**
	 * @return the targetPostal
	 */
	public String getTargetPostal() {
		return targetPostal;
	}

	/**
	 * @param targetPostal the targetPostal to set
	 */
	public void setTargetPostal(String targetPostal) {
		this.targetPostal = targetPostal;
	}

	/**
	 * @return the targetCountry
	 */
	public String getTargetCountry() {
		return targetCountry;
	}

	/**
	 * @param targetCountry the targetCountry to set
	 */
	public void setTargetCountry(String targetCountry) {
		this.targetCountry = targetCountry;
	}

	/**
	 * @return the dispatchId
	 */
	public Long getDispatchId() {
		return dispatchId;
	}

	/**
	 * @param dispatchId the dispatchId to set
	 */
	public void setDispatchId(Long dispatchId) {
		this.dispatchId = dispatchId;
	}

	/**
	 * @return the dispatchStatus
	 */
	public String getDispatchStatus() {
		return dispatchStatus;
	}

	/**
	 * @param dispatchStatus the dispatchStatus to set
	 */
	public void setDispatchStatus(String dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}

	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * @return the scheduledDateTime
	 */
	public LocalDateTime getScheduledDateTime() {
		return scheduledDateTime;
	}

	/**
	 * @param scheduledDateTime the scheduledDateTime to set
	 */
	public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}

	/**
	 * @return the startedDateTime
	 */
	public LocalDateTime getStartedDateTime() {
		return startedDateTime;
	}

	/**
	 * @param startedDateTime the startedDateTime to set
	 */
	public void setStartedDateTime(LocalDateTime startedDateTime) {
		this.startedDateTime = startedDateTime;
	}

	/**
	 * @return the completedDateTime
	 */
	public LocalDateTime getCompletedDateTime() {
		return completedDateTime;
	}

	/**
	 * @param completedDateTime the completedDateTime to set
	 */
	public void setCompletedDateTime(LocalDateTime completedDateTime) {
		this.completedDateTime = completedDateTime;
	}

	/**
	 * @return the lineStatus
	 */
	public String getLineStatus() {
		return lineStatus;
	}

	/**
	 * @param lineStatus the lineStatus to set
	 */
	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}


    
    
    
}
