package com.mcquaids.model;

import java.util.Date;

import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.utils.PropertyHydrator;

public class Reservation {

    private Integer reservationID;

    private String customerID;
    private Customer customer;
    
    private String reservationStatusCode;
    private Date startDate;
    private Date endDate;
    private String instructions;
    private String leaseID;
    private Date dateCreated;
    private Date dateUpdated;
    
 // Delivery Address Fields
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryProvince;
    private String deliveryPostal;
    private String deliveryCountry;
    private Boolean deliverySameAsCustomer;

    public Integer getReservationID() {
        return this.reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }
    
    public String getReservationIDAsDisplay() {
        return "R" + String.format("%05d", this.reservationID);
    }

    public String getReservationStatusText() {
    	return  CodeValues.getKeyValue("reservationStatus", reservationStatusCode);
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

	/**
	 * @return the deliveryStreet
	 */
	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	/**
	 * @param deliveryStreet the deliveryStreet to set
	 */
	public void setDeliveryStreet(String deliveryStreet) {
		this.deliveryStreet = deliveryStreet;
	}

	/**
	 * @return the deliveryCity
	 */
	public String getDeliveryCity() {
		return deliveryCity;
	}

	/**
	 * @param deliveryCity the deliveryCity to set
	 */
	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	/**
	 * @return the deliveryProvince
	 */
	public String getDeliveryProvince() {
		return deliveryProvince;
	}

	/**
	 * @param deliveryProvince the deliveryProvince to set
	 */
	public void setDeliveryProvince(String deliveryProvince) {
		this.deliveryProvince = deliveryProvince;
	}

	/**
	 * @return the deliveryPostal
	 */
	public String getDeliveryPostal() {
		return deliveryPostal;
	}

	/**
	 * @param deliveryPostal the deliveryPostal to set
	 */
	public void setDeliveryPostal(String deliveryPostal) {
		this.deliveryPostal = deliveryPostal;
	}

	/**
	 * @return the deliveryCountry
	 */
	public String getDeliveryCountry() {
		return deliveryCountry;
	}

	/**
	 * @param deliveryCountry the deliveryCountry to set
	 */
	public void setDeliveryCountry(String deliveryCountry) {
		this.deliveryCountry = deliveryCountry;
	}

	/**
	 * @return the deliverySameAsCustomer
	 */
	public Boolean getDeliverySameAsCustomer() {
		return deliverySameAsCustomer;
	}

	/**
	 * @param deliverySameAsCustomer the deliverySameAsCustomer to set
	 */
	public void setDeliverySameAsCustomer(Boolean deliverySameAsCustomer) {
		this.deliverySameAsCustomer = deliverySameAsCustomer;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String notes) {
        this.instructions = notes;
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
				+ reservationStatusCode + ", startDate=" + startDate + ", endDate=" + endDate + ", instructions=" + instructions
				+ ", leaseID=" + leaseID + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + "]";
	}
    
    
}