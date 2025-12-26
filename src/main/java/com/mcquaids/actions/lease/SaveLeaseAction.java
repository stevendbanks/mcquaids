package com.mcquaids.actions.lease;

import java.io.Serializable;


 public class SaveLeaseAction	extends BaseLeaseAction  implements Serializable {


	private static final long serialVersionUID = 1L;


	public SaveLeaseAction() {
		super();
		title = "Edit Lease";	
	}
	
	
	public String execute() {
		System.out.println("SDB-000->SaveLeaseAction().execute()-> saveActiontype="+ saveActionType);
		if (saveActionType.equals("saveNewLease")) {
			leaseService.createLease(lease);
		} else {
			 leaseService.updateLease(lease);
		}

		super.leaseID = lease.getLeaseID();   // Need this in order for the chained action to work.
		return "success";

	}

}
