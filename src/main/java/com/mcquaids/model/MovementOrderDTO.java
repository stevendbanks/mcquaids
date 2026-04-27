package com.mcquaids.model;

public class MovementOrderDTO {

    private Long movementOrderId;
    private String displayOrderNumber;

    private Integer equipmentNumber;
    private String equipmentTypeText;
    private String equipmentSubTypeText;

    private String movementTypeText;

    private String fromLocationDisplay;
    private String toLocationDisplay;

    private String driverName;
    private String status;

    private String requestedAtFormatted;

	/**
	 * @return the movementOrderId
	 */
	public Long getMovementOrderId() {
		return movementOrderId;
	}

	/**
	 * @param movementOrderId the movementOrderId to set
	 */
	public void setMovementOrderId(Long movementOrderId) {
		this.movementOrderId = movementOrderId;
	}

	/**
	 * @return the displayOrderNumber
	 */
	public String getDisplayOrderNumber() {
		return displayOrderNumber;
	}

	/**
	 * @param displayOrderNumber the displayOrderNumber to set
	 */
	public void setDisplayOrderNumber(String displayOrderNumber) {
		this.displayOrderNumber = displayOrderNumber;
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

	/**
	 * @return the movementTypeText
	 */
	public String getMovementTypeText() {
		return movementTypeText;
	}

	/**
	 * @param movementTypeText the movementTypeText to set
	 */
	public void setMovementTypeText(String movementTypeText) {
		this.movementTypeText = movementTypeText;
	}

	/**
	 * @return the fromLocationDisplay
	 */
	public String getFromLocationDisplay() {
		return fromLocationDisplay;
	}

	/**
	 * @param fromLocationDisplay the fromLocationDisplay to set
	 */
	public void setFromLocationDisplay(String fromLocationDisplay) {
		this.fromLocationDisplay = fromLocationDisplay;
	}

	/**
	 * @return the toLocationDisplay
	 */
	public String getToLocationDisplay() {
		return toLocationDisplay;
	}

	/**
	 * @param toLocationDisplay the toLocationDisplay to set
	 */
	public void setToLocationDisplay(String toLocationDisplay) {
		this.toLocationDisplay = toLocationDisplay;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the requestedAtFormatted
	 */
	public String getRequestedAtFormatted() {
		return requestedAtFormatted;
	}

	/**
	 * @param requestedAtFormatted the requestedAtFormatted to set
	 */
	public void setRequestedAtFormatted(String requestedAtFormatted) {
		this.requestedAtFormatted = requestedAtFormatted;
	}

	public String getEquipmentDisplay() {
	    if (equipmentNumber == null) {
	        return "";
	    }

	    String type = equipmentTypeText;
	    String subType = equipmentSubTypeText;

	    if (type != null && !type.isEmpty() && subType != null && !subType.isEmpty()) {
	        return equipmentNumber + " (" + type + " – " + subType + ")";
	    }

	    if (type != null && !type.isEmpty()) {
	        return equipmentNumber + " (" + type + ")";
	    }

	    return String.valueOf(equipmentNumber);
	}

    
    
}
