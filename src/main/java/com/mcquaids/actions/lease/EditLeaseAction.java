package com.mcquaids.actions.lease;

import java.util.List;

import com.mcquaids.model.LeasedEquipmentView;

public class EditLeaseAction extends BaseLeaseAction {


	private static final long serialVersionUID = 1L;
	private  List<LeasedEquipmentView> leasedEquipmentView;
	
	
	


	public EditLeaseAction() {
		super();
		super.title = "Lease #" + leaseID;
	}

	
	/**
	 * Finds a Lease and the equipment that are assigned to the lease.
	 */
	public String execute() {
		System.out.println("SDBANKS-Entered EditLeaseAction");
		lease = leaseService.getLease(leaseID);
		setLeasedEquipmentView(leaseService.getLeasedEquipmentViewByLeaseID(leaseID));
		super.title = "Lease #" + leaseID;
		super.saveActionType = "editLease";
		if (null == lease) {;
			addActionError("LeaseID (" + leaseID + ") Not Found");
		} else {
			
		}
		return "success";
	}


	/**
	 * @return the leasedEquipmentView
	 */
	public List<LeasedEquipmentView> getLeasedEquipmentView() {
		return leasedEquipmentView;
	}


	/**
	 * @param leasedEquipmentView the leasedEquipmentView to set
	 */
	public void setLeasedEquipmentView(List<LeasedEquipmentView> leasedEquipmentView) {
		this.leasedEquipmentView = leasedEquipmentView;
	}




	
	
}
