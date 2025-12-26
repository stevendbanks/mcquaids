package com.mcquaids.model;

public class EquipmentCategory {
    private int categoryId;
    private int equipmentType;
    private String equipmentSubType;
    private String properties;
    private String haulingEquipment;

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentSubType() {
        return equipmentSubType;
    }

    public void setEquipmentSubType(String equipmentSubType) {
        this.equipmentSubType = equipmentSubType;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getHaulingEquipment() {
        return haulingEquipment;
    }

    public void setHaulingEquipment(String haulingEquipment) {
        this.haulingEquipment = haulingEquipment;
    }
}
