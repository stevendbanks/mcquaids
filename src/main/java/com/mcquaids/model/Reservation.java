package com.mcquaids.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.mcquaids.model.lookup.CodeValues;

public class Reservation {

    private Integer reservationID;

    private String customerID;
    private Customer customer;
    
    private String reservationStatusCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String instructions;
    private String leaseID;
    private Date dateCreated;
    private Date dateUpdated;
    
 // Delivery Address Fields
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryProvince;
    private String deliveryPostalCode;
    private String deliveryCountry;
    private Boolean deliverySameAsCustomer;
    
 // Secondary Delivery Address
    private String secondaryStreet;
    private String secondaryCity;
    private String secondaryProvince;
    private String secondaryPostalCode;
    private String secondaryCountry;
<<<<<<< HEAD
    private LocalDateTime secondaryDeliveryDate;    
=======
    private Date secondaryDeliveryDate;    
>>>>>>> origin/main
    
    // Additional Person (MVP fields)
    private String additionalPersonName;
    private String additionalPersonPhone;
    private String additionalPersonEmail;

    
    

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

<<<<<<< HEAD
	
	
	
	/**
	 * @return the startDate
	 */
	public LocalDateTime getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDateTime getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

=======
>>>>>>> origin/main
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
	 * @return the deliveryPostalCode
	 */
	public String getDeliveryPostalCode() {
		return deliveryPostalCode;
	}

	/**
	 * @param deliveryPostalCode the deliveryPostalCode to set
	 */
	public void setDeliveryPostalCode(String deliveryPostal) {
		this.deliveryPostalCode = deliveryPostal;
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

	/**
	 * @return the secondaryStreet
	 */
	public String getSecondaryStreet() {
		return secondaryStreet;
	}

	/**
	 * @param secondaryStreet the secondaryStreet to set
	 */
	public void setSecondaryStreet(String secondaryStreet) {
		this.secondaryStreet = secondaryStreet;
	}

	/**
	 * @return the secondaryCity
	 */
	public String getSecondaryCity() {
		return secondaryCity;
	}

	/**
	 * @param secondaryCity the secondaryCity to set
	 */
	public void setSecondaryCity(String secondaryCity) {
		this.secondaryCity = secondaryCity;
	}

	/**
	 * @return the secondaryProvince
	 */
	public String getSecondaryProvince() {
		return secondaryProvince;
	}

	/**
	 * @param secondaryProvince the secondaryProvince to set
	 */
	public void setSecondaryProvince(String secondaryProvince) {
		this.secondaryProvince = secondaryProvince;
	}

	/**
	 * @return the secondaryPostalCode
	 */
	public String getSecondaryPostalCode() {
		return secondaryPostalCode;
	}

	/**
	 * @param secondaryPostalCode the secondaryPostalCode to set
	 */
	public void setSecondaryPostalCode(String secondaryPostalCode) {
		this.secondaryPostalCode = secondaryPostalCode;
	}

	/**
	 * @return the secondaryCountry
	 */
	public String getSecondaryCountry() {
		return secondaryCountry;
	}

	/**
	 * @param secondaryCountry the secondaryCountry to set
	 */
	public void setSecondaryCountry(String secondaryCountry) {
		this.secondaryCountry = secondaryCountry;
	}

<<<<<<< HEAD


	/**
	 * @return the secondaryDeliveryDate
	 */
	public LocalDateTime getSecondaryDeliveryDate() {
=======
	/**
	 * @return the secondaryDeliveryDate
	 */
	public Date getSecondaryDeliveryDate() {
>>>>>>> origin/main
		return secondaryDeliveryDate;
	}

	/**
	 * @param secondaryDeliveryDate the secondaryDeliveryDate to set
	 */
<<<<<<< HEAD
	public void setSecondaryDeliveryDate(LocalDateTime secondaryDeliveryDate) {
=======
	public void setSecondaryDeliveryDate(Date secondaryDeliveryDate) {
>>>>>>> origin/main
		this.secondaryDeliveryDate = secondaryDeliveryDate;
	}

	/**
	 * @return the additionalPersonName
	 */
	public String getAdditionalPersonName() {
		return additionalPersonName;
	}

	/**
	 * @param additionalPersonName the additionalPersonName to set
	 */
	public void setAdditionalPersonName(String additionalPersonName) {
		this.additionalPersonName = additionalPersonName;
	}

	/**
	 * @return the additionalPersonPhone
	 */
	public String getAdditionalPersonPhone() {
		return additionalPersonPhone;
	}

	/**
	 * @param additionalPersonPhone the additionalPersonPhone to set
	 */
	public void setAdditionalPersonPhone(String additionalPersonPhone) {
		this.additionalPersonPhone = additionalPersonPhone;
	}

	/**
	 * @return the additionalPersonEmail
	 */
	public String getAdditionalPersonEmail() {
		return additionalPersonEmail;
	}

	/**
	 * @param additionalPersonEmail the additionalPersonEmail to set
	 */
	public void setAdditionalPersonEmail(String additionalPersonEmail) {
		this.additionalPersonEmail = additionalPersonEmail;
	}

	public String getReservationStatusCode() {
        return reservationStatusCode;
    }

    public void setReservationStatusCode(String reservationStatusCode) {
        this.reservationStatusCode = reservationStatusCode;
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



<<<<<<< HEAD
    public Address getDeliveryAddress() {
        if (deliveryStreet == null &&
            deliveryCity == null &&
            deliveryProvince == null &&
            deliveryCountry == null &&
            deliveryPostalCode == null) {
            return null;
        }

        Address address = new Address();
        address.setStreet(deliveryStreet);
        address.setCity(deliveryCity);
        address.setProvince(deliveryProvince);
        address.setCountry(deliveryCountry);
        address.setPostalCode(deliveryPostalCode);
        return address;
    }
	
	public Address getSecondaryDeliveryAddress() {
	    if (secondaryStreet == null &&
	        secondaryCity == null &&
	        secondaryProvince == null &&
	        secondaryCountry == null &&
	        secondaryPostalCode == null) {
	        return null;
	    }

	    Address address = new Address();
	    address.setStreet(secondaryStreet);
	    address.setCity(secondaryCity);
	    address.setProvince(secondaryProvince);
	    address.setCountry(secondaryCountry);
	    address.setPostalCode(secondaryPostalCode);
	    return address;
=======
	public Address getDeliveryAddress() {
		Address address = new Address();
		address.setStreet(deliveryStreet);
		address.setCity(deliveryCity);
		address.setProvince(deliveryProvince);
		address.setCountry(deliveryCountry);
		address.setPostalCode(deliveryPostalCode);
		return address;
	}
	
	public Address getSecondaryDeliveryAddress() {
		Address address = new Address();
		address.setStreet(secondaryStreet);
		address.setCity(secondaryCity);
		address.setProvince(secondaryProvince);
		address.setCountry(secondaryCountry);
		address.setPostalCode(secondaryPostalCode);
		return address;
>>>>>>> origin/main
	}

	@Override
	public String toString() {
		return "Reservation [reservationID=" + reservationID + ", customerID=" + customerID + ", customer=" + customer
				+ ", reservationStatusCode=" + reservationStatusCode + ", startDate=" + startDate + ", endDate="
				+ endDate + ", instructions=" + instructions + ", leaseID=" + leaseID + ", dateCreated=" + dateCreated
				+ ", dateUpdated=" + dateUpdated + ", deliveryStreet=" + deliveryStreet + ", deliveryCity="
				+ deliveryCity + ", deliveryProvince=" + deliveryProvince + ", deliveryPostalCode=" + deliveryPostalCode
				+ ", deliveryCountry=" + deliveryCountry + ", deliverySameAsCustomer=" + deliverySameAsCustomer
				+ ", secondaryStreet=" + secondaryStreet + ", secondaryCity=" + secondaryCity + ", secondaryProvince="
				+ secondaryProvince + ", secondaryPostalCode=" + secondaryPostalCode + ", secondaryCountry="
				+ secondaryCountry + ", secondaryDeliveryDate=" + secondaryDeliveryDate + "]";
	}
	
	
    
    
}