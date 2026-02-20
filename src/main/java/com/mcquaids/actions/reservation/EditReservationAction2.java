package com.mcquaids.actions.reservation;

import com.mcquaids.model.Customer;
import com.opensymphony.xwork2.Action;

 public class EditReservationAction2 extends BaseReservationAction {

	private static final long serialVersionUID = 1L;
	
	private String leaseID;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public EditReservationAction2() {
		super();
	}

	
	public String execute() {

	    try {
	    	if (null == reservationID) {
	    		reservationID = reservation.getReservationID();
	    	}
	    	
	    	
	    	// In case we go from the Chained SaveReservation
	    	if (reservationID != null && reservation.getReservationID() == null) {
	    	    reservation.setReservationID(reservationID);
	    	}

	        // Detect if returning from Customer Search (or any selector)
	        boolean comingFromSelector =
	            reservation != null &&
	            reservation.getCustomerID() != null;

	        if (!comingFromSelector && reservationID != null) {

	            // Normal load from DB
	            super.reservation = reservationService.getReservation(reservationID);
	            pageTitle = "Reservation #" + reservation.getReservationIDAsDisplay();



	            if (reservedEquipmentID != null) {
	                super.reservedEquipment = equipmentService.findEquipment(reservedEquipmentID);
	            }

	        } else {

	            // Returning from selector OR new reservation
	            if (reservationID == null ) {
	                pageTitle = "New Reservation";
	            } else {
	                pageTitle = "Reservation #" + reservationID + " (unsaved changes)";
		            super.reservationLineItemsDTO = reservationService.getReservationLineItems(reservationID);	        // Get the line items    

	            }
	            
	            // Enrich customer object if customerID is present
	            if (reservation.getCustomerID() != null) {
	                Customer fullCustomer = customerService.edit(reservation.getCustomerID().toString());   // Just in read-only 
	                reservation.setCustomer(fullCustomer);
	            }
	            

	            // Do NOT load from DB — Struts has already populated the reservation object
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
