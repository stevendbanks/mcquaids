/**
 * 
 */
package com.mcquaids.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import com.mcquaids.model.interfaces.IEquipment;
import com.mcquaids.utils.DateStringUtil;
import com.mcquaids.utils.JsonUtils;

/**
 * 
 */
public class Equipment implements Serializable, IEquipment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String equipmentNumber;
	protected int equipmentType;
	protected String equipmentSubType;
	private String equipmentSubTypeText; // From CodeValue Table.  Is only for READONLY
	protected String serialNumber;
	protected String manufacturer;
	protected Date manufacturedDate; // Used to calculate the age if required.
	protected Date purchaseDate;
	protected Double purchasePrice;
	protected String specialNotes;
	protected Date inspectionDate;
	protected String availabilityStatusCode;
	protected String conditionStatusCode;
	protected String maintenanceStatusCode;
	protected String cleaningStatusCode;
	protected String bookingStatusCode;


	protected Map<String, String> properties;
	
	
	protected DateStringUtil dateStringUtil = new DateStringUtil();

	/**
	 * 
	 */
	public Equipment() {
		super();
	}

	/**
	 * 
	 */
	public Equipment(Equipment pEquipment) {
		super();
		
		try {
		this.equipmentNumber = pEquipment.getEquipmentNumber();
		this.equipmentType = pEquipment.getEquipmentType();
		this.equipmentSubType = pEquipment.getEquipmentSubType();
		this.serialNumber = pEquipment.getSerialNumber();
		this.manufacturer = pEquipment.getManufacturer();
		 // Convert to Java date with null check
		this.manufacturedDate = (pEquipment.getManufacturedDate() != null) ? new java.util.Date(pEquipment.getManufacturedDate().getTime()) : null;
		
		
		this.specialNotes = pEquipment.getSpecialNotes();

		
		
		 // Convert to Java date with null check
		this.inspectionDate = (pEquipment.getInspectionDate() != null) ? new java.util.Date(pEquipment.getInspectionDate().getTime()) : null;

		// Convert to Java date with null check
		this.purchaseDate = (pEquipment.getPurchaseDate() != null) ? new java.util.Date(pEquipment.getPurchaseDate().getTime()) : null;

		this.purchasePrice = pEquipment.getPurchasePrice();
		this.availabilityStatusCode = pEquipment.getAvailabilityStatusCode();
		this.conditionStatusCode = pEquipment.getConditionStatusCode();
		this.maintenanceStatusCode = pEquipment.getMaintenanceStatusCode();
		this.cleaningStatusCode = pEquipment.getCleaningStatusCode();
		this.bookingStatusCode = pEquipment.getBookingStatusCode();
		
		
//		// Assuming equipment.properties is a JSON string
//		String jsonString = pEquipment.getProperties();
//		JSONObject jsonObject = new JSONObject(jsonString);
//		Map<String, Object> propertiesMap = new HashMap<>();
//
//		Iterator<String> keys = jsonObject.keys();
//		while (keys.hasNext()) {
//		    String key = keys.next();
//		    propertiesMap.put(key, jsonObject.get(key));
//		}
		
		// Set the parsed map back to equipment.properties
//		propertiesTest = (propertiesMap);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
				
	}

	public String getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
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
	public void setSpecialNotes(String notes) {
		this.specialNotes = notes;
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
	 * @return the manufacturedDate
	 */
	public Date getManufacturedDate() {
		return manufacturedDate;
	}

	/**
	 * @return the manufacturedDate
	 */
	public String getManufacturedDateAsString() {
		return dateStringUtil.dateToString(manufacturedDate);
	}

	/**
	 * @return the manufacturedDate
	 */
	public void setManufacturedDateAsString(String mdate) {
		manufacturedDate = dateStringUtil.stringToDate(mdate);
	}

	/**
	 * @return the manufacturedDate
	 */
	public void setInspectionDateAsString(String idate) {
		inspectionDate = dateStringUtil.stringToDate(idate);
	}

	/**
	 * @return the manufacturedDate
	 */
	public String getInspectionDateAsString() {
		return dateStringUtil.dateToString(inspectionDate);
	}

	/**
	 * @return the manufacturedDate
	 */
	public String getPurchaseDateAsString() {
		return dateStringUtil.dateToString(purchaseDate);
	}

	/**
	 * @return the manufacturedDate
	 */
	public void setPurchaseDateAsString(String mdate) {
		purchaseDate = dateStringUtil.stringToDate(mdate);
	}

	/**
	 * @param manufacturedDate the manufacturedDate to set
	 */
	public void setManufacturedDate(Date manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	@Override
	public Integer getAge() {
		if (null == manufacturedDate) {
			return null;
		}

		LocalDate now = LocalDate.now(); // Today's date

		LocalDate manDate = manufacturedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Period period = Period.between(manDate, now);
		return period.getYears();

	}

	/**
	 * @return the equipmentTypeText
	 */
	public int getEquipmentType() {
		return equipmentType;
	}

	/**
	 * @param equipmentTypeText the equipmentTypeText to set
	 */
	public void setEquipmentType(int equipmentType) {
		this.equipmentType = equipmentType;
	}

	/**
	 * @return the inspectionDate
	 */
	public Date getInspectionDate() {
		return inspectionDate;
	}

	/**
	 * @param inspectionDate the inspectionDate to set
	 */
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
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
	 * @return the availabilityStatusCode
	 */
	public String getAvailabilityStatusCode() {
		return availabilityStatusCode;
	}

	/**
	 * @param availabilityStatusCode the availabilityStatusCode to set
	 */
	public void setAvailabilityStatusCode(String availabilityStatusCode) {
		this.availabilityStatusCode = availabilityStatusCode;
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
	 * @return the bookingStatusCode
	 */
	public String getBookingStatusCode() {
		return bookingStatusCode;
	}

	/**
	 * @param bookingStatusCode the bookingStatusCode to set
	 */
	public void setBookingStatusCode(String bookingStatusCode) {
		this.bookingStatusCode = bookingStatusCode;
	}

	/**
	 * @return the dateStringUtil
	 */
	public DateStringUtil getDateStringUtil() {
		return dateStringUtil;
	}

	/**
	 * @param dateStringUtil the dateStringUtil to set
	 */
	public void setDateStringUtil(DateStringUtil dateStringUtil) {
		this.dateStringUtil = dateStringUtil;
	}

	/**
	 * @return the purchasePrice
	 */
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the equipmentSubTypeDescription
	 */
	public String getEquipmentSubTypeText() {
		return equipmentSubTypeText;
	}

	/**
	 * @param equipmentSubTypeDescription the equipmentSubTypeDescription to set
	 */
	public void setEquipmentSubTypeText(String equipmentSubTypeText) {
		this.equipmentSubTypeText = equipmentSubTypeText;
	}

	@Override
	public String toString() {
		return "Equipment [equipmentNumber=" + equipmentNumber + ", equipmentTypeText=" + equipmentType
				+ ", equipmentSubType=" + equipmentSubType + ", equipmentSubTypeDescription="
				+ equipmentSubTypeText + ", serialNumber=" + serialNumber + ", manufacturer=" + manufacturer
				+ ", manufacturedDate=" + manufacturedDate + ", purchaseDate=" + purchaseDate + ", purchasePrice="
				+ purchasePrice + ", specialNotes=" + specialNotes + ", inspectionDate=" + inspectionDate
				+ ", availabilityStatusCode=" + availabilityStatusCode + ", conditionStatusCode=" + conditionStatusCode
				+ ", maintenanceStatusCode=" + maintenanceStatusCode + ", cleaningStatusCode=" + cleaningStatusCode
				+ ", bookingStatusCode=" + bookingStatusCode + ", properties=" + getPropertiesAsJson() + ", dateStringUtil="
				+ dateStringUtil + "]";
	}

	/**
	 * @return the propertiesTest
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * @param propertiesTest the propertiesTest to set
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public String getPropertiesAsJson() {
		 return JsonUtils.toJson(this.properties);

	}

	

	
	
}
