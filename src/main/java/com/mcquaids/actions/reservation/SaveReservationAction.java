package com.mcquaids.actions.reservation;

import com.mcquaids.model.Reservation;
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
		System.out.println("SDBANKS->SaveReservationAction-  actionType=" + actionType);
		System.out.println(reservation.toString());

	    if (actionType.equals("CREATE")) {
	        Reservation created = reservationService.createReservation(reservation);

	        // Set BOTH the nested and top-level IDs
	        reservation.setReservationID(created.getReservationID());
	        reservationID = created.getReservationID();
	        

	    } else {
	        reservationService.updateReservation(reservation);
	    }

	    return Action.SUCCESS;
	}


	public String getLeaseID() {
		return leaseID;
	}


	public void setLeaseID(String leaseID) {
		this.leaseID = leaseID;
	}
	
	

	
}
