package com.mcquaids.actions.leasedequipment;

import com.mcquaids.actions.equipment.BaseEquipmentAction;

public class EditLeasedEquipmentAction extends BaseEquipmentAction {


	private static final long serialVersionUID = 1L;
	
	
	private String title = "Leased Equipment Details";
	

	public EditLeasedEquipmentAction() {
		super();
		super.editable = false;
	}

	
	public String execute() {
      System.out.println("SDB->EditLeasedEquipmentAction.execute() entered");
		System.out.println("leaseID=" + leaseID);
		
		try {
			if (null == leaseService) {	
				System.out.println("leaseService = NULL");
			}
			

			
		leasedEquipmentView = leaseService.editLeasedEquipment(leaseID, equipmentNumber);
		
		if (null == leasedEquipmentView) {
			addActionError("Selected Equipment Number is not part of this lease");
			return "input";
		}

		} catch (Exception ex ) {
			ex.printStackTrace();
		}
		
		System.out.println(leasedEquipmentView.toString());
		return "success";
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
