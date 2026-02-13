package com.mcquaids.actions.reservation;

import com.opensymphony.xwork2.Action;

 public class EditReservationAction extends BaseReservationAction {

	private static final long serialVersionUID = 1L;
	
	private String leaseID;
	
	private  Integer reservationID;
	
	/**
	 * @return the reservationID
	 */
	public Integer getReservationID() {
		return reservationID;
	}


	/**
	 * @param reservationID the reservationID to set
	 */
	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public EditReservationAction() {
		super();
	}

	
	public String execute() {
		System.out.println("Entered EditReservationAction->reservationID=" + reservationID);
		
		try {
		super.reservation =    reservationService.getReservation(reservationID);
		pageTitle = "Reservation #" + reservation.getReservationIDAsDisplay();

		super.reservationLineItemsDTO = reservationService.getReservationLineItems(reservationID);
		
		if (reservedEquipmentID == null) {
			// do nothing
		} else {
		  super.reservedEquipment = equipmentService.findEquipment(reservedEquipmentID);
		}
		
		} catch (Exception ex) {
			ex.printStackTrace();
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
