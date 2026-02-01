package com.mcquaids.model;
public class ReservationLineItemDTO extends ReservationLineItem {

    private String equipmentTypeText;
    private String equipmentSubTypeText;

    // getters and setters...

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

	@Override
	public String toString() {
		return "ReservationLineItemDTO [equipmentTypeText=" + equipmentTypeText + ", equipmentSubTypeText="
				+ equipmentSubTypeText + ", toString()=" + super.toString() + "]";
	}




    
    
    
    
}