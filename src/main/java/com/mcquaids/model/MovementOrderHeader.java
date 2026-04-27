package com.mcquaids.model;

import java.time.LocalDateTime;

import com.mcquaids.model.enums.MovementOrderStatus;

public class MovementOrderHeader {

    // -------------------------
    // ENUMS
    // -------------------------

    public enum Priority {
        NORMAL,
        URGENT
    }

    public enum MovementType {
        REPOSITION,
        INSPECTION,
        CLEANING,
        MAINTENANCE,
        CUSTOMER_REQUEST,
        SWAP,
        BULK
    }

    public enum TargetLocationType {
        ON_PREMISE,
        CUSTOMER_SITE
    }

    // -------------------------
    // FIELDS
    // -------------------------

    private long movementOrderId;

    private String requestedBy;
    private LocalDateTime requestedDate;

    private Priority priority = Priority.NORMAL;
    private MovementType movementType;

    private String reasonCode;
    private String notes;

    private MovementOrderStatus status;

    // -------------------------
    // TARGET LOCATION (DESTINATION)
    // -------------------------

    private TargetLocationType targetLocationType;

    private Long targetYardId;       // if ON_PREMISE
    private String targetName;         // if ON_PREMISE

    private String targetStreet;     // if CUSTOMER_SITE
    private String targetCity;
    private String targetProvince;
    private String targetPostal;
    private String targetCountry;

    // -------------------------
    // GETTERS / SETTERS
    // -------------------------

    public long getMovementOrderId() {
        return movementOrderId;
    }

    public void setMovementOrderId(long movementOrderId) {
        this.movementOrderId = movementOrderId;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LocalDateTime getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDateTime requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
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

    public MovementOrderStatus getStatus() {
        return status;
    }

    public void setStatus(MovementOrderStatus status) {
        this.status = status;
    }

    public TargetLocationType getTargetLocationType() {
        return targetLocationType;
    }

    public void setTargetLocationType(TargetLocationType targetLocationType) {
        this.targetLocationType = targetLocationType;
    }

    public Long getTargetYardId() {
        return targetYardId;
    }

    public void setTargetYardId(Long targetYardId) {
        this.targetYardId = targetYardId;
    }

    /**
	 * @return the targetName
	 */
	public String getTargetName() {
		return targetName;
	}

	/**
	 * @param string the targetName to set
	 */
	public void setTargetName(String string) {
		this.targetName = string;
	}

	public String getTargetStreet() {
        return targetStreet;
    }

    public void setTargetStreet(String targetStreet) {
        this.targetStreet = targetStreet;
    }

    public String getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(String targetCity) {
        this.targetCity = targetCity;
    }

    public String getTargetProvince() {
        return targetProvince;
    }

    public void setTargetProvince(String targetProvince) {
        this.targetProvince = targetProvince;
    }

    public String getTargetPostal() {
        return targetPostal;
    }

    public void setTargetPostal(String targetPostal) {
        this.targetPostal = targetPostal;
    }

    public String getTargetCountry() {
        return targetCountry;
    }

    public void setTargetCountry(String targetCountry) {
        this.targetCountry = targetCountry;
    }
}
