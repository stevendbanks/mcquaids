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
		System.out.println("SDBANKS->reservationID=" + reservationID);
		if (actionType.equals("CREATE")) {
				reservation = reservationService.createReservation(reservation);
		} else {
			  	reservationService.updateReservation(reservation);
		}
		
		reservationID = reservation.getReservationID();   //  the edit Reservation action uses this ID, not the one in the reservation
		return Action.SUCCESS;
	}


	public String getLeaseID() {
		return leaseID;
	}


	public void setLeaseID(String leaseID) {
		this.leaseID = leaseID;
	}
	
	

	
}
