package com.mcquaids.actions.reservation;

import com.mcquaids.model.Customer;
import com.mcquaids.model.Reservation;
import com.opensymphony.xwork2.Action;

 public class CreateReservationAction extends BaseReservationAction {

	private static final long serialVersionUID = 1L;
	
	private String leaseID;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public CreateReservationAction() {
		super();
	}

	
	public String execute() {
		System.out.println("SDBANKS- Entered execute.  customerID="  + reservation.getCustomerID() );
		pageTitle = "Create Reservation";
		reservation.setReservationStatusCode("1001-01");  // Set the Reservation to DRAFT
		
        // Enrich customer object if customerID is present
        if (reservation.getCustomerID() != null) {
            Customer fullCustomer = customerService.edit(reservation.getCustomerID().toString());   // Just in read-only 
            System.out.println(fullCustomer.toString());
            reservation.setCustomer(fullCustomer);		
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
