package com.mcquaids.model;

public class EquipmentWithLocation {

    private int equipmentNumber;
    private int equipmentType;
    private String equipmentTypeText;
    private String equipmentSubType;
    private String equipmentSubTypeText;

    private String serialNumber;
    private String manufacturer;
    private String manufacturedDate;
    private String purchasePrice;
    private String purchaseDate;
    private String specialNotes;

    private String inspectionDate;
    private String properties;

    private boolean available;
    private String conditionStatusCode;
    private String maintenanceStatusCode;
    private String cleaningStatusCode;
    private String derivedAvailabilityStatus;

    // Location fields
    private String locationType;
    private String street;
    private String city;
    private String province;
    private String postal;
    private String country;
    private Long yardId;

    private String historyStart;
    private String historyEnd;

    private Long locationReservationId;
    private Long locationActionId;
    private String locationActionType;
    private String locationNotes;
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
	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * @return the manufacturedDate
	 */
	public String getManufacturedDate() {
		return manufacturedDate;
	}
	/**
	 * @param manufacturedDate the manufacturedDate to set
	 */
	public void setManufacturedDate(String manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}
	/**
	 * @return the purchasePrice
	 */
	public String getPurchasePrice() {
		return purchasePrice;
	}
	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	/**
	 * @return the purchaseDate
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	/**
	 * @return the specialNotes
	 */
	public String getSpecialNotes() {
		return specialNotes;
	}
	/**
	 * @param specialNotes the specialNotes to set
	 */
	public void setSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
	}
	/**
	 * @return the inspectionDate
	 */
	public String getInspectionDate() {
		return inspectionDate;
	}
	/**
	 * @param inspectionDate the inspectionDate to set
	 */
	public void setInspectionDate(String inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	/**
	 * @return the properties
	 */
	public String getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(String properties) {
		this.properties = properties;
	}
	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	/**
	 * @return the conditionStatusCode
	 */
	public String getConditionStatusCode() {
		return conditionStatusCode;
	}
	/**
	 * @param conditionStatusCode the conditionStatusCode to set
	 */
	public void setConditionStatusCode(String conditionStatusCode) {
		this.conditionStatusCode = conditionStatusCode;
	}
	/**
	 * @return the maintenanceStatusCode
	 */
	public String getMaintenanceStatusCode() {
		return maintenanceStatusCode;
	}
	/**
	 * @param maintenanceStatusCode the maintenanceStatusCode to set
	 */
	public void setMaintenanceStatusCode(String maintenanceStatusCode) {
		this.maintenanceStatusCode = maintenanceStatusCode;
	}
	/**
	 * @return the cleaningStatusCode
	 */
	public String getCleaningStatusCode() {
		return cleaningStatusCode;
	}
	/**
	 * @param cleaningStatusCode the cleaningStatusCode to set
	 */
	public void setCleaningStatusCode(String cleaningStatusCode) {
		this.cleaningStatusCode = cleaningStatusCode;
	}
	/**
	 * @return the derivedAvailabilityStatus
	 */
	public String getDerivedAvailabilityStatus() {
		return derivedAvailabilityStatus;
	}
	/**
	 * @param derivedAvailabilityStatus the derivedAvailabilityStatus to set
	 */
	public void setDerivedAvailabilityStatus(String derivedAvailabilityStatus) {
		this.derivedAvailabilityStatus = derivedAvailabilityStatus;
	}
	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}
	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the postal
	 */
	public String getPostal() {
		return postal;
	}
	/**
	 * @param postal the postal to set
	 */
	public void setPostal(String postal) {
		this.postal = postal;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the yardId
	 */
	public Long getYardId() {
		return yardId;
	}
	/**
	 * @param yardId the yardId to set
	 */
	public void setYardId(Long yardId) {
		this.yardId = yardId;
	}
	/**
	 * @return the historyStart
	 */
	public String getHistoryStart() {
		return historyStart;
	}
	/**
	 * @param historyStart the historyStart to set
	 */
	public void setHistoryStart(String historyStart) {
		this.historyStart = historyStart;
	}
	/**
	 * @return the historyEnd
	 */
	public String getHistoryEnd() {
		return historyEnd;
	}
	/**
	 * @param historyEnd the historyEnd to set
	 */
	public void setHistoryEnd(String historyEnd) {
		this.historyEnd = historyEnd;
	}
	/**
	 * @return the locationReservationId
	 */
	public Long getLocationReservationId() {
		return locationReservationId;
	}
	/**
	 * @param locationReservationId the locationReservationId to set
	 */
	public void setLocationReservationId(Long locationReservationId) {
		this.locationReservationId = locationReservationId;
	}
	/**
	 * @return the locationActionId
	 */
	public Long getLocationActionId() {
		return locationActionId;
	}
	/**
	 * @param locationActionId the locationActionId to set
	 */
	public void setLocationActionId(Long locationActionId) {
		this.locationActionId = locationActionId;
	}
	/**
	 * @return the locationActionType
	 */
	public String getLocationActionType() {
		return locationActionType;
	}
	/**
	 * @param locationActionType the locationActionType to set
	 */
	public void setLocationActionType(String locationActionType) {
		this.locationActionType = locationActionType;
	}
	/**
	 * @return the locationNotes
	 */
	public String getLocationNotes() {
		return locationNotes;
	}
	/**
	 * @param locationNotes the locationNotes to set
	 */
	public void setLocationNotes(String locationNotes) {
		this.locationNotes = locationNotes;
	}
	@Override
	public String toString() {
		return "EquipmentWithLocation [equipmentNumber=" + equipmentNumber + ", equipmentType=" + equipmentType
				+ ", equipmentTypeText=" + equipmentTypeText + ", equipmentSubType=" + equipmentSubType
				+ ", equipmentSubTypeText=" + equipmentSubTypeText + ", serialNumber=" + serialNumber
				+ ", manufacturer=" + manufacturer + ", manufacturedDate=" + manufacturedDate + ", purchasePrice="
				+ purchasePrice + ", purchaseDate=" + purchaseDate + ", specialNotes=" + specialNotes
				+ ", inspectionDate=" + inspectionDate + ", properties=" + properties + ", available=" + available
				+ ", conditionStatusCode=" + conditionStatusCode + ", maintenanceStatusCode=" + maintenanceStatusCode
				+ ", cleaningStatusCode=" + cleaningStatusCode + ", derivedAvailabilityStatus="
				+ derivedAvailabilityStatus + ", locationType=" + locationType + ", street=" + street + ", city=" + city
				+ ", province=" + province + ", postal=" + postal + ", country=" + country + ", yardId=" + yardId
				+ ", historyStart=" + historyStart + ", historyEnd=" + historyEnd + ", locationReservationId="
				+ locationReservationId + ", locationActionId=" + locationActionId + ", locationActionType="
				+ locationActionType + ", locationNotes=" + locationNotes + "]";
	}

    
    
}
