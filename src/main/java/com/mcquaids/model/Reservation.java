package com.mcquaids.model;

import java.util.Date;

public class Reservation {

    private String reservationID;

    private String customerID;
    private Customer customer;
    
    private String reservationStatusCode;
    private Date startDate;
    private Date endDate;
    private String notes;
    private String leaseID;
    private Date dateCreated;
    private Date dateUpdated;

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getReservationStatusCode() {
        return reservationStatusCode;
    }

    public void setReservationStatusCode(String reservationStatusCode) {
        this.reservationStatusCode = reservationStatusCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(String leaseID) {
        this.leaseID = leaseID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

	@Override
	public String toString() {
		return "Reservation [reservationID=" + reservationID + ", customerID=" + customerID + ", reservationStatusCode="
				+ reservationStatusCode + ", startDate=" + startDate + ", endDate=" + endDate + ", notes=" + notes
				+ ", leaseID=" + leaseID + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + "]";
	}
    
    
}