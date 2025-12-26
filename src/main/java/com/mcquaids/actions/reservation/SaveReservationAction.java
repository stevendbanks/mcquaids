package com.mcquaids.actions.reservation;

import com.opensymphony.xwork2.Action;

 public class SaveReservationAction extends BaseReservationAction {

	private static final long serialVersionUID = 1L;
	
	private String leaseID;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public SaveReservationAction() {
		super();
	}

	
	public String execute() {
		return Action.SUCCESS;
	}


	public String getLeaseID() {
		return leaseID;
	}


	public void setLeaseID(String leaseID) {
		this.leaseID = leaseID;
	}
	
	

	
}
