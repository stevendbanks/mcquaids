package com.mcquaids.actions.reservation;

import com.opensymphony.xwork2.Action;

 public class SearchReservationAction extends BaseReservationAction {

	private static final long serialVersionUID = 1L;
	

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public SearchReservationAction() {
		super();
	}

	
	public String execute() {
		
		System.out.println(customerID);
		try {
			reservationViewDTO  = reservationService.findReservationsByCriteria(reservationID, customerID);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
}
