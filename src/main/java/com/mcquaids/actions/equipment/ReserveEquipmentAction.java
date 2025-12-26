package com.mcquaids.actions.equipment;

import com.mcquaids.model.lookup.CodeValues;

public class ReserveEquipmentAction extends BaseEquipmentAction {

	private static final long serialVersionUID = 1L;
	
		public ReserveEquipmentAction() {
		super();
	}

	
	public String execute() {
		equipment = equipmentService.edit(equipmentNumber);
		if (null == equipment) {
			addActionError("Equipment Number (" + equipmentNumber + ") Not Found");
			return "success";
		}
		
		codeValues = new CodeValues();
		codeValues.setEquipmentSubTypes(equipment.getEquipmentType());
		
		title = setJSPTitle("Edit");
		

		editable = false;

		return redirectBasedOnEquipmentType(equipment.getEquipmentType());
		
	}


	
	
	
}
