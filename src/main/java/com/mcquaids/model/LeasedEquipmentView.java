package com.mcquaids.model;

import java.util.Date;
import java.util.Map;

import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.utils.JsonUtils;

public class LeasedEquipmentView {

    private String leaseID;
    private String equipmentNumber;
    private Date dateAddedToLease;
    private Date dateRemovedFromLease;

    private String specialNotes;  // from lease_equipment
    private String notes;         // from equipment

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

    protected Map<String, String> properties;

    private String availabilityStatusCode;
    private String availabilityStatusText;

    private String conditionStatusCode;
    private String conditionStatusText;

    private String maintenanceStatusCode;
    private String maintenanceStatusText;

    private String cleaningStatusCode;
    private String cleaningStatusText;

    private String bookingStatusCode;
    private String bookingStatusCodeText;

    private CodeValues codeValues = new CodeValues();

    // --- Core property access ---

    public String getPropertyValue(String key) {
        if (properties == null) {
            return "";
        }
        return properties.getOrDefault(key, "");
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getPropertiesAsJson() {
        return JsonUtils.toJson(this.properties);
    }

    // --- Trailer-friendly methods ---

    public boolean isInsulated() {
        String val = getPropertyValue("insulated");
        return val.equalsIgnoreCase("true");
    }

    public String getColour() {
        return getPropertyValue("colour");
    }

    public String getAxel() {
        Map<String, String> x = null;
        try {
            x = codeValues.getAxelTypes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x != null ? x.get(getPropertyValue("axel")) : null;
    }

    public String getLength() {
        return getPropertyValue("length");
    }

    public String getDoorType() {
        Map<String, String> x = null;
        try {
            x = codeValues.getDoortypes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x != null ? x.get(getPropertyValue("doorType")) : null;
    }

    public String getDoorLocation() {
        Map<String, String> x = null;
        try {
            x = codeValues.getDoorLocations();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x != null ? x.get(getPropertyValue("doorLocation")) : null;
    }

    public String getFloor() {
        Map<String, String> x = null;
        try {
            x = codeValues.getFloorTypes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x != null ? x.get(getPropertyValue("floor")) : null;
    }

    public boolean isTieDown() {
        String val = getPropertyValue("tieDown");
        return val.equalsIgnoreCase("true");
    }

    // --- Forklift-friendly methods ---

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

    public String getSize() {
        return getPropertyValue("size");
    }

    public String getFuelType() {
        return getPropertyValue("fuelType");
    }

    public String getForkliftFloor() {
        return getPropertyValue("floor");
    }

    // --- Container-friendly methods ---

    public String getContainerSize() {
        Map<String, String> x = null;
        try {
            x = codeValues.getContainerSizes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x != null ? x.get(getPropertyValue("containerSize")) : null;
    }

    public String getCapacity() {
        return getPropertyValue("capacity");
    }

    public String getWeightLimit() {
        return getPropertyValue("weightLimit");
    }

    public String getContainerDoors() {
        Map<String, String> x = null;
        try {
            x = codeValues.getContainerDoors();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x != null ? x.get(getPropertyValue("containerDoors")) : null;
    }

    // --- Getters/setters for scalar fields ---

    public String getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(String leaseID) {
        this.leaseID = leaseID;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Date getDateAddedToLease() {
        return dateAddedToLease;
    }

    public void setDateAddedToLease(Date dateAddedToLease) {
        this.dateAddedToLease = dateAddedToLease;
    }

    public Date getDateRemovedFromLease() {
        return dateRemovedFromLease;
    }

    public void setDateRemovedFromLease(Date dateRemovedFromLease) {
        this.dateRemovedFromLease = dateRemovedFromLease;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentTypeText() {
        return equipmentTypeText;
    }

    public void setEquipmentTypeText(String equipmentTypeText) {
        this.equipmentTypeText = equipmentTypeText;
    }

    public String getEquipmentSubType() {
        return equipmentSubType;
    }

    public void setEquipmentSubType(String equipmentSubType) {
        this.equipmentSubType = equipmentSubType;
    }

    public String getEquipmentSubTypeText() {
        return equipmentSubTypeText;
    }

    public void setEquipmentSubTypeText(String equipmentSubTypeText) {
        this.equipmentSubTypeText = equipmentSubTypeText;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(Date manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getAvailabilityStatusCode() {
        return availabilityStatusCode;
    }

    public void setAvailabilityStatusCode(String availabilityStatusCode) {
        this.availabilityStatusCode = availabilityStatusCode;
    }

    public String getAvailabilityStatusText() {
        return availabilityStatusText;
    }

    public void setAvailabilityStatusText(String availabilityStatusText) {
        this.availabilityStatusText = availabilityStatusText;
    }

    public String getConditionStatusCode() {
        return conditionStatusCode;
    }

    public void setConditionStatusCode(String conditionStatusCode) {
        this.conditionStatusCode = conditionStatusCode;
    }

    public String getConditionStatusText() {
        return conditionStatusText;
    }

    public void setConditionStatusText(String conditionStatusText) {
        this.conditionStatusText = conditionStatusText;
    }

    public String getMaintenanceStatusCode() {
        return maintenanceStatusCode;
    }

    public void setMaintenanceStatusCode(String maintenanceStatusCode) {
        this.maintenanceStatusCode = maintenanceStatusCode;
    }

    public String getMaintenanceStatusText() {
        return maintenanceStatusText;
    }

    public void setMaintenanceStatusText(String maintenanceStatusText) {
        this.maintenanceStatusText = maintenanceStatusText;
    }

    public String getCleaningStatusCode() {
        return cleaningStatusCode;
    }

    public void setCleaningStatusCode(String cleaningStatusCode) {
        this.cleaningStatusCode = cleaningStatusCode;
    }

    public String getCleaningStatusText() {
        return cleaningStatusText;
    }

    public void setCleaningStatusText(String cleaningStatusText) {
        this.cleaningStatusText = cleaningStatusText;
    }

    public String getBookingStatusCode() {
        return bookingStatusCode;
    }

    public void setBookingStatusCode(String bookingStatusCode) {
        this.bookingStatusCode = bookingStatusCode;
    }

    public String getBookingStatusCodeText() {
        return bookingStatusCodeText;
    }

    public void setBookingStatusCodeText(String bookingStatusCodeText) {
        this.bookingStatusCodeText = bookingStatusCodeText;
    }

    public CodeValues getCodeValues() {
        return codeValues;
    }

    public void setCodeValues(CodeValues codeValues) {
        this.codeValues = codeValues;
    }
}