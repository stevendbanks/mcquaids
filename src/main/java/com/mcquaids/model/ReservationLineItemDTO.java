package com.mcquaids.model;

import java.util.Map;

import com.mcquaids.utils.JsonUtils;

public class ReservationLineItemDTO extends ReservationLineItem {

    private String equipmentTypeText;
    private String equipmentSubTypeText;

    // Optional UI fields
    private String availabilityStatusText;
    private String equipmentNotes;
    private String serialNumber;
    private String manufacturer;
    
    
    private Map<String, String> equipmentProperties;
   

    public String getEquipmentTypeText() {
        return equipmentTypeText;
    }

    public void setEquipmentTypeText(String equipmentTypeText) {
        this.equipmentTypeText = equipmentTypeText;
    }

    public String getEquipmentSubTypeText() {
        return equipmentSubTypeText;
    }

    public void setEquipmentSubTypeText(String equipmentSubTypeText) {
        this.equipmentSubTypeText = equipmentSubTypeText;
    }

    public String getAvailabilityStatusText() {
        return availabilityStatusText;
    }

    public void setAvailabilityStatusText(String availabilityStatusText) {
        this.availabilityStatusText = availabilityStatusText;
    }

    public String getEquipmentNotes() {
        return equipmentNotes;
    }

    public void setEquipmentNotes(String equipmentNotes) {
        this.equipmentNotes = equipmentNotes;
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
    
    public Map<String, String> getEquipmentProperties() {
        return equipmentProperties;
    }
    
    public String getEquipmentPropertiesAsJson() {
        return JsonUtils.toJson(equipmentProperties);
    }    

    public void setEquipmentProperties(Map<String, String> equipmentProperties) {
        this.equipmentProperties = equipmentProperties;
    }    
    

    @Override
    public String toString() {
        return "ReservationLineItemDTO{" +
                "equipmentTypeText='" + equipmentTypeText + '\'' +
                ", equipmentSubTypeText='" + equipmentSubTypeText + '\'' +
                ", availabilityStatusText='" + availabilityStatusText + '\'' +
                ", equipmentNotes='" + equipmentNotes + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                "} " + super.toString();
    }
}