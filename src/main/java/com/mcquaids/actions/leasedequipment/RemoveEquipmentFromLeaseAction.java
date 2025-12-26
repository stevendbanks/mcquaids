package com.mcquaids.actions.leasedequipment;

import com.mcquaids.actions.equipment.BaseEquipmentAction;

public class RemoveEquipmentFromLeaseAction extends BaseEquipmentAction {
//public class EditLeasedEquipmentAction	extends ActionSupport implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	
	private String title = "Update Leased Equipment";
	

	public RemoveEquipmentFromLeaseAction() {
		super();
	}

	
	public String execute() {
		
		if (!leaseService.updateLeasedEquipment(leasedEquipment)) {
			addActionError("Leased Equipment could not be updated");
			return "input";
		}

		return "success";
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
