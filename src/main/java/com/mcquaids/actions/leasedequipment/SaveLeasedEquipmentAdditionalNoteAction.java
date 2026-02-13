package com.mcquaids.actions.leasedequipment;

import com.mcquaids.actions.equipment.BaseEquipmentAction;

public class SaveLeasedEquipmentAdditionalNoteAction extends BaseEquipmentAction {

	private static final long serialVersionUID = 1L;
	
	
	private String title = "Edit Leased Equipment";
	

	public SaveLeasedEquipmentAdditionalNoteAction() {
		super();
	}

	 
	public String execute() {
		
		System.out.println(leasedEquipmentView.getNotes());
		
		
		if (!leaseService.updateLeasedEquipmentAdditionalNote(leaseID, equipmentNumber, leasedEquipmentView.getNotes())) {  
			addActionError("Leased Equipment could not be updated");
			return "input";
		}
 
	    addActionMessage("Note was Added/Updated successfully");
		return "success";
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
