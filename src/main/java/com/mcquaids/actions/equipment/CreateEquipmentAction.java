package com.mcquaids.actions.equipment;

import com.mcquaids.model.Equipment;

 public class CreateEquipmentAction extends BaseEquipmentAction {

	private static final long serialVersionUID = 1L;
	
	private String equipmentSaveActionType = "SaveNew";

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public CreateEquipmentAction() {
		super();
	}

	
	public String execute() {
		
		equipment = new Equipment();
		setEquipmentSubtypeSelect(equipmentType);
		try {
			equipment.setEquipmentType(equipmentType);
			title = setJSPTitle("Add");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		editable = true;
		return redirectBasedOnEquipmentType(equipmentType);

	}

	

	/**
	 * @return the trailerSaveActionType
	 */
	public String getEquipmentSaveActionType() {
		return equipmentSaveActionType;
	}


	/**
	 * @param trailerSaveActionType the trailerSaveActionType to set
	 */
	public void setEquipmentSaveActionType(String equipmentSaveActionType) {
		this.equipmentSaveActionType = equipmentSaveActionType;
	}


	
}
