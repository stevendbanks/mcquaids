package com.mcquaids.actions.reservation;

<<<<<<< HEAD
import java.time.LocalDate;

import com.mcquaids.model.Customer;
=======
import com.mcquaids.model.Customer;
import com.mcquaids.model.Reservation;
>>>>>>> origin/main
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
	    actionType = "CREATE";
<<<<<<< HEAD
	    
=======
>>>>>>> origin/main
		
        // Enrich customer object if customerID is present
        if (reservation.getCustomerID() != null) {
            Customer fullCustomer = customerService.edit(reservation.getCustomerID().toString());   // Just in read-only 
            System.out.println(fullCustomer.toString());
            reservation.setCustomer(fullCustomer);		
<<<<<<< HEAD

        } else {
            reservation.setStartDate(LocalDate.now().atTime(12, 0));
            reservation.setEndDate(LocalDate.now().atTime(15, 0));        	
=======
>>>>>>> origin/main
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
