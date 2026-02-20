package com.mcquaids.actions.equipment;

import com.mcquaids.model.lookup.CodeValues;

public class EditEquipmentAction extends BaseEquipmentAction {

	private static final long serialVersionUID = 1L;
	
		public EditEquipmentAction() {
		super();
	}

	
	public String execute() {
		System.out.println("SDB->equipmentNumber="+ equipmentNumber);
		equipment = equipmentService.edit(equipmentNumber);
		if (null == equipment) {
			addActionError("Equipment Number (" + equipmentNumber + ") Not Found");
			return "success";
		}
		
		codeValues = new CodeValues();
		codeValues.setEquipmentSubTypes(equipment.getEquipmentType());
		this.equipmentTypeText =  CodeValues.getKeyValue("equipmentType", Integer.toString(equipment.getEquipmentType()));
		
		title = setJSPTitle("Edit");
		

		editable = true;

		return redirectBasedOnEquipmentType(equipment.getEquipmentType());
		
	}


	
	
	
}
