/**
 * 
 */
package com.mcquaids.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 */
public class Customer  extends User implements Serializable  {

    private String notes;
	private Timestamp createdDateTime;
	private String setCreatedUserID;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param userID
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param province
	 * @param country
	 * @param postalCode
	 * @param phone
	 * @param email
	 */
	public Customer(String userID, String firstName, String lastName, String street, String city, String province,
			String country, String postalCode, String phone, String email) {
		super(userID, firstName, lastName, street, city, province, country, postalCode, phone, email);


	}
	
	public Customer(User user) {
		this.userID = user.getUserID();
		this.firstName= user.getFirstName();
		this.lastName = user.getLastName();
		this.businessName = user.getBusinessName();
		this.street = user.getStreet();
		this.city = user.getCity();
		this.country = user.getCountry();
		this.postalCode = user.getPostalCode();
		this.province = user.getProvince();
		this.phone = user.getPhone();
		this.email = user.getEmail();
	}	
	

	/**
	 * 
	 */
	public Customer() {
		super();
	}
	
	public String getCustomerID() {
		return userID;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setCreatedDateTime(Timestamp pCreatedDateTime) {
		this.createdDateTime= pCreatedDateTime;
		
	}

	/**
	 * @return the setCreatedUserID
	 */
	public String getSetCreatedUserID() {
		return setCreatedUserID;
	}
	
	public void setCreatedUserID(String userID) {
		this.setCreatedUserID= userID;
	}

	
	/**
	 * @return the createdDateTime
	 */
	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * @param setCreatedUserID the setCreatedUserID to set
	 */
	public void setSetCreatedUserID(String setCreatedUserID) {
		this.setCreatedUserID = setCreatedUserID;
	}


	public String getFullName() {
	    String base = this.lastName + ", " + this.firstName;

	    if (this.businessName != null && !this.businessName.trim().isEmpty()) {
	        return base + " (" + this.businessName + ")";
	    }

	    return base;
	}

   @Override
public String toString() {
	return "Customer [notes=" + notes + ", createdDateTime=" + createdDateTime + ", setCreatedUserID="
			+ setCreatedUserID + ", userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName
			+ ", businessName=" + businessName + ", street=" + street + ", city=" + city + ", province=" + province
			+ ", country=" + country + ", postalCode=" + postalCode + ", phone=" + phone + ", email=" + email
			+ ", getFullName()=" + getFullName() + "]";
}










}
