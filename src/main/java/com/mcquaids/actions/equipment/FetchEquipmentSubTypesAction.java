package com.mcquaids.actions.equipment;

import com.opensymphony.xwork2.ActionSupport;

public class FetchEquipmentSubTypesAction extends BaseEquipmentAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FetchEquipmentSubTypesAction() {
		super();
	}
	
	public String execute() {
		equipmentSubTypes = codeValues.getEquipmentSubTypes(this.equipmentType);
		codeValues.setEquipmentSubTypes(this.equipmentType);
		return ActionSupport.SUCCESS;
	}


	
	
}