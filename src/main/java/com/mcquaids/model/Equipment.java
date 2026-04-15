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

public class Equipment implements Serializable, IEquipment {

    private static final long serialVersionUID = 1L;

    protected String equipmentNumber;
    protected int equipmentType;
    protected String equipmentSubType;
    private String equipmentSubTypeText; // Read-only from CodeValue
    protected String serialNumber;
    protected String manufacturer;
    protected Date manufacturedDate;
    protected Date purchaseDate;
    protected Double purchasePrice;
    protected String specialNotes;
    protected Date inspectionDate;

    // REMOVED: availabilityStatusCode
    protected boolean available;

    protected String conditionStatusCode;
    protected String maintenanceStatusCode;
    protected String cleaningStatusCode;

    // REMOVED: bookingStatusCode

    protected String safetyStatusCode;
    protected Long preferredYardId;

    protected Map<String, String> properties;

    protected DateStringUtil dateStringUtil = new DateStringUtil();

    public Equipment() {}

    public Equipment(Equipment pEquipment) {
        try {
            this.equipmentNumber = pEquipment.getEquipmentNumber();
            this.equipmentType = pEquipment.getEquipmentType();
            this.equipmentSubType = pEquipment.getEquipmentSubType();
            this.serialNumber = pEquipment.getSerialNumber();
            this.manufacturer = pEquipment.getManufacturer();

            this.manufacturedDate = (pEquipment.getManufacturedDate() != null)
                    ? new Date(pEquipment.getManufacturedDate().getTime()) : null;

            this.specialNotes = pEquipment.getSpecialNotes();

            this.inspectionDate = (pEquipment.getInspectionDate() != null)
                    ? new Date(pEquipment.getInspectionDate().getTime()) : null;

            this.purchaseDate = (pEquipment.getPurchaseDate() != null)
                    ? new Date(pEquipment.getPurchaseDate().getTime()) : null;

            this.purchasePrice = pEquipment.getPurchasePrice();

            // REMOVED: availabilityStatusCode
            this.conditionStatusCode = pEquipment.getConditionStatusCode();
            this.maintenanceStatusCode = pEquipment.getMaintenanceStatusCode();
            this.cleaningStatusCode = pEquipment.getCleaningStatusCode();

            // REMOVED: bookingStatusCode

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

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String notes) {
        this.specialNotes = notes;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getManufacturedDate() {
        return manufacturedDate;
    }

    public String getManufacturedDateAsString() {
        return dateStringUtil.dateToString(manufacturedDate);
    }

    public void setManufacturedDateAsString(String mdate) {
        manufacturedDate = dateStringUtil.stringToDate(mdate);
    }

    public void setInspectionDateAsString(String idate) {
        inspectionDate = dateStringUtil.stringToDate(idate);
    }

    public String getInspectionDateAsString() {
        return dateStringUtil.dateToString(inspectionDate);
    }

    public String getPurchaseDateAsString() {
        return dateStringUtil.dateToString(purchaseDate);
    }

    public void setPurchaseDateAsString(String mdate) {
        purchaseDate = dateStringUtil.stringToDate(mdate);
    }

    public void setManufacturedDate(Date manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    @Override
    public Integer getAge() {
        if (manufacturedDate == null) return null;

        LocalDate now = LocalDate.now();
        LocalDate manDate = manufacturedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Period.between(manDate, now).getYears();
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getEquipmentSubType() {
        return equipmentSubType;
    }

    public void setEquipmentSubType(String equipmentSubType) {
        this.equipmentSubType = equipmentSubType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // REMOVED: getAvailabilityStatusCode()
    // REMOVED: setAvailabilityStatusCode()

    public String getConditionStatusCode() {
        return conditionStatusCode;
    }

    public void setConditionStatusCode(String conditionStatusCode) {
        this.conditionStatusCode = conditionStatusCode;
    }

    public String getMaintenanceStatusCode() {
        return maintenanceStatusCode;
    }

    public void setMaintenanceStatusCode(String maintenanceStatusCode) {
        this.maintenanceStatusCode = maintenanceStatusCode;
    }

    public String getCleaningStatusCode() {
        return cleaningStatusCode;
    }

    public void setCleaningStatusCode(String cleaningStatusCode) {
        this.cleaningStatusCode = cleaningStatusCode;
    }

    // REMOVED: getBookingStatusCode()
    // REMOVED: setBookingStatusCode()

    public String getSafetyStatusCode() {
        return safetyStatusCode;
    }

    public void setSafetyStatusCode(String safetyStatusCode) {
        this.safetyStatusCode = safetyStatusCode;
    }

    public DateStringUtil getDateStringUtil() {
        return dateStringUtil;
    }

    public void setDateStringUtil(DateStringUtil dateStringUtil) {
        this.dateStringUtil = dateStringUtil;
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

    public Long getPreferredYardId() {
        return preferredYardId;
    }

    public void setPreferredYardId(Long preferredYardId) {
        this.preferredYardId = preferredYardId;
    }

    public String getEquipmentSubTypeText() {
        return equipmentSubTypeText;
    }

    public void setEquipmentSubTypeText(String equipmentSubTypeText) {
        this.equipmentSubTypeText = equipmentSubTypeText;
    }

    @Override
    public String toString() {
        return "Equipment [" +
                "equipmentNumber=" + equipmentNumber +
                ", equipmentType=" + equipmentType +
                ", equipmentSubType=" + equipmentSubType +
                ", equipmentSubTypeText=" + equipmentSubTypeText +
                ", serialNumber=" + serialNumber +
                ", manufacturer=" + manufacturer +
                ", manufacturedDate=" + manufacturedDate +
                ", purchaseDate=" + purchaseDate +
                ", purchasePrice=" + purchasePrice +
                ", specialNotes=" + specialNotes +
                ", inspectionDate=" + inspectionDate +
                ", conditionStatusCode=" + conditionStatusCode +
                ", maintenanceStatusCode=" + maintenanceStatusCode +
                ", cleaningStatusCode=" + cleaningStatusCode +
                ", available=" + available +
                ", safetyStatusCode=" + safetyStatusCode +
                ", properties=" + getPropertiesAsJson() +
                "]";
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
}
