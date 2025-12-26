package com.mcquaids.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.mcquaids.model.lookup.CodeValues;

public class LeasedEquipmentView {

    private String leaseID;
    private String equipmentNumber;
    private Date dateAddedToLease;
    private Date dateRemovedFromLease;

    private String specialNotes;  // from the lease_Equipment table
    private String notes;         //  From the Equipment Table
    
    private String equipmentType;
    private String equipmentTypeText;

    private String equipmentSubType;
    private String equipmentSubTypeText;

    
    private String serialNumber;
    private String manufacturer;
    private Date   manufacturedDate;
    private Double purchasePrice;
    private Date   purchaseDate;
    private Date   inspectionDate;

	protected Map<String, Object> properties;

    private String availabilityStatusCode;
    private String availabilityStatusText;

    private String conditionStatusCode;
    private String conditionStatusText;
    
    private String maintenanceStatusCode;
    private String maintenanceStatusText;
    
    private String cleaningStatusCode;
    private String cleaningStatusTest;
    
    private String bookingStatusCode;
    private String bookingStatusCodeText;
    
    private CodeValues codeValues = new CodeValues();
    
    
	/**
	 * @return the leaseID
	 */
	public String getLeaseID() {
		return leaseID;
	}
	/**
	 * @param leaseID the leaseID to set
	 */
	public void setLeaseID(String leaseID) {
		this.leaseID = leaseID;
	}
	/**
	 * @return the equipmentNumber
	 */
	public String getEquipmentNumber() {
		return equipmentNumber;
	}
	/**
	 * @param equipmentNumber the equipmentNumber to set
	 */
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	/**
	 * @return the dateAddedToLease
	 */
	public Date getDateAddedToLease() {
		return dateAddedToLease;
	}
	/**
	 * @param dateAddedToLease the dateAddedToLease to set
	 */
	public void setDateAddedToLease(Date dateAddedToLease) {
		this.dateAddedToLease = dateAddedToLease;
	}
	/**
	 * @return the dateRemovedFromLease
	 */
	public Date getDateRemovedFromLease() {
		return dateRemovedFromLease;
	}
	/**
	 * @param dateRemovedFromLease the dateRemovedFromLease to set
	 */
	public void setDateRemovedFromLease(Date dateRemovedFromLease) {
		this.dateRemovedFromLease = dateRemovedFromLease;
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
	/**
	 * @return the equipmentTypeText
	 */
	public String getEquipmentType() {
		return equipmentType;
	}
	/**
	 * @param equipmentTypeText the equipmentTypeText to set
	 */
	public void setEquipmentType(String equipmentType) {
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
	public Date getManufacturedDate() {
		return manufacturedDate;
	}
	/**
	 * @param manufacturedDate the manufacturedDate to set
	 */
	public void setManufacturedDate(Date manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
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
	 * @return the availabilityStatusText
	 */
	public String getAvailabilityStatusText() {
		return availabilityStatusText;
	}
	/**
	 * @param availabilityStatusText the availabilityStatusText to set
	 */
	public void setAvailabilityStatusText(String availabilityStatusText) {
		this.availabilityStatusText = availabilityStatusText;
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
	 * @return the conditionStatusText
	 */
	public String getConditionStatusText() {
		return conditionStatusText;
	}
	/**
	 * @param conditionStatusText the conditionStatusText to set
	 */
	public void setConditionStatusText(String conditionStatusText) {
		this.conditionStatusText = conditionStatusText;
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
	 * @return the maintenanceStatusText
	 */
	public String getMaintenanceStatusText() {
		return maintenanceStatusText;
	}
	/**
	 * @param maintenanceStatusText the maintenanceStatusText to set
	 */
	public void setMaintenanceStatusText(String maintenanceStatusText) {
		this.maintenanceStatusText = maintenanceStatusText;
	}
	/**
	 * @return the mleaningStatusCode
	 */
	public String getCleaningStatusCode() {
		return cleaningStatusCode;
	}
	/**
	 * @param mleaningStatusCode the mleaningStatusCode to set
	 */
	public void setCleaningStatusCode(String mleaningStatusCode) {
		this.cleaningStatusCode = mleaningStatusCode;
	}
	/**
	 * @return the cleaningStatusTest
	 */
	public String getCleaningStatusTest() {
		return cleaningStatusTest;
	}
	/**
	 * @param cleaningStatusTest the cleaningStatusTest to set
	 */
	public void setCleaningStatusTest(String cleaningStatusTest) {
		this.cleaningStatusTest = cleaningStatusTest;
	}
	/**
	 * @return the mookingStatusCode
	 */
	public String getBookingStatusCode() {
		return bookingStatusCode;
	}
	/**
	 * @param mookingStatusCode the mookingStatusCode to set
	 */
	public void setBookingStatusCode(String mookingStatusCode) {
		this.bookingStatusCode = mookingStatusCode;
	}
	/**
	 * @return the bookingStatusCodeText
	 */
	public String getBookingStatusCodeText() {
		return bookingStatusCodeText;
	}
	/**
	 * @param bookingStatusCodeText the bookingStatusCodeText to set
	 */
	public void setBookingStatusCodeText(String bookingStatusCodeText) {
		this.bookingStatusCodeText = bookingStatusCodeText;
	}
	
	public boolean isInsulated() {
	    String val = getPropertyValue("insulated");
	    return val != null && val.equalsIgnoreCase("true");	    
	}

	public String getColour() {
	    return getPropertyValue("colour");
	}

	public String getAxel() {
		Map<String, String> x  = null;
		try {
			x = codeValues.getAxelTypes();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x.get(getPropertyValue("axel"));		
	}

	public String getLength() {
	    return getPropertyValue("length");
	}

	public String getDoorType() {
		Map<String, String> x  = null;
		try {
			x = codeValues.getDoortypes();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x.get(getPropertyValue("doorType"));		

	}

	public String getDoorLocation() {
		Map<String, String> x  = null;
		try {
			x = codeValues.getDoorLocations();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x.get(getPropertyValue("doorLocation"));

	}

	public String getFloor() {
		Map<String, String> x  = null;
		try {
			x = codeValues.getFloorTypes();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x.get(getPropertyValue("floor"));		
	}

	// Boolean-valued attribute
	public boolean isTieDown() {
	    String val = getPropertyValue("tieDown");
	    return val != null && val.equalsIgnoreCase("true");
	}


/* Forklift Friendly Methods */
	
	public String getLoadCapacity() {
	    return getPropertyValue("LoadCapacity");
	}	
	
	public String getMaximumForkHeight() {
	    return getPropertyValue("maximumForkHeight");
	}		
	
	public String getFreeLift() {
	    return getPropertyValue("freeLift");
	}		


	public String getOverallWidth() {
	    return getPropertyValue("overallWidth");
	}			

	public String getMastLoweredHeight() {
	    return getPropertyValue("mastLoweredHeight");
	}		

	public String getMastExtendedHeight() {
	    return getPropertyValue("mastExtendedHeight");
	}		
	
	public String getOverheadGuardHeight() {
	    return getPropertyValue("overheadGuardHeight");
	}		

	/* Forklift Friendly Methods */
//XXXX
	public String getSize() {
	    return getPropertyValue("size");
	}		
	
	public String getFuelType() {
	    return getPropertyValue("fuelType");
	}		


	public String getForkliftFloor() {
	    return getPropertyValue("floor");
	}			

//XXXXXX
	/* Container Friendly Methods */
	public String getContainerSize() {
		Map<String, String> x  = null;
		try {
			x = codeValues.getContainerSizes();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x.get(getPropertyValue("containerSize"));		
	}	
	
	public String getCapacity() {
	    return getPropertyValue("capacity");
	}			
	
	public String getWeightLimit() {
	    return getPropertyValue("weightLimit");
	}	
	
	public String getContainerDoors() {
		Map<String, String> x  = null;
		try {
			x = codeValues.getContainerDoors();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x.get(getPropertyValue("containerDoors"));		
	}		
	
	
	
	public String getPropertyValue(String key) {
	    Object value = properties.get(key);
	    if (value instanceof List) {
	        List<?> list = (List<?>) value;
	        return list.isEmpty() ? "" : String.valueOf(list.get(0));
	    }
	    if (value instanceof String[]) {
	        String[] arr = (String[]) value;
	        return arr.length > 0 ?    arr[0] : "";
	    }
	    return  value != null ? value.toString() : "";
	}
	
	/**
	 * @return the propertiesTest
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * @param propertiesTest the propertiesTest to set
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	public String getPropertiesAsJson() {
	    Map<String, Object> cleanedMap = new HashMap<>();
	    for (Map.Entry<String, Object> entry : this.properties.entrySet()) {
	        Object value = entry.getValue();
	        if (value instanceof java.util.List) {
	            java.util.List<?> list = (java.util.List<?>) value;
	            if (list.size() == 1) {
	                cleanedMap.put(entry.getKey(), list.get(0));
	            } else {
	                cleanedMap.put(entry.getKey(), list);
	            }
	        } else {
	            cleanedMap.put(entry.getKey(), value);
	        }
	    }

	    JSONObject json = new JSONObject(cleanedMap);
	    return json.toString();
	}
	
	
	
	@Override
	public String toString() {
		return "LeasedEquipmentView [leaseID=" + leaseID + ", equipmentNumber=" + equipmentNumber
				+ ", dateAddedToLease=" + dateAddedToLease + ", dateRemovedFromLease=" + dateRemovedFromLease
				+ ", specialNotes=" + specialNotes + ", notes=" + notes + ", equipmentTypeText=" + equipmentType
				+ ", equipmentTypeText=" + equipmentTypeText + ", EquipmentSubType=" + equipmentSubType
				+ ", equipmentSubTypeText=" + equipmentSubTypeText + ", serialNumber=" + serialNumber
				+ ", manufacturer=" + manufacturer + ", manufacturedDate=" + manufacturedDate + ", purchasePrice="
				+ purchasePrice + ", purchaseDate=" + purchaseDate + ", inspectionDate=" + inspectionDate
				+ ", properties=" + properties + ", availabilityStatusCode=" + availabilityStatusCode
				+ ", availabilityStatusText=" + availabilityStatusText + ", conditionStatusCode=" + conditionStatusCode
				+ ", conditionStatusText=" + conditionStatusText + ", maintenanceStatusCode=" + maintenanceStatusCode
				+ ", maintenanceStatusText=" + maintenanceStatusText + ", cleaningStatusCode=" + cleaningStatusCode
				+ ", cleaningStatusTest=" + cleaningStatusTest + ", bookingStatusCode=" + bookingStatusCode
				+ ", bookingStatusCodeText=" + bookingStatusCodeText + "]";
	}

	
	
    // getters and setters for each field
}
