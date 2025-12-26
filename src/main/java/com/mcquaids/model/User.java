package com.mcquaids.model;

import java.io.Serializable;

public class User implements Serializable {

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected String userID;

protected String firstName;

protected String lastName;

protected String street;

protected String city;

protected String province;

protected String country;

protected String postalCode;

protected String phone;

protected String email;




/**
 * 
 */
public User() {

}





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
public User(String userID, String firstName, String lastName, String street, String city, String province,
		String country, String postalCode, String phone, String email) {
	super();
	this.userID = userID;
	this.firstName = firstName;
	this.lastName = lastName;
	this.street = street;
	this.city = city;
	this.province = province;
	this.country = country;
	this.postalCode = postalCode;
	this.phone = phone;
	this.email = email;
}






/**
 * @return the id
 */
public String getUserID() {
	return userID;
}

/**
 * @param id the id to set
 */
public void setUserID(String id) {
	this.userID = id;
}

/**
 * @return the firstName
 */
public String getFirstName() {
	return firstName;
}

/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

/**
 * @return the lastName
 */
public String getLastName() {
	return lastName;
}

/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}



/**
 * @return the city
 */
public String getCity() {
	return city;
}

/**
 * @param city the city to set
 */
public void setCity(String city) {
	this.city = city;
}

/**
 * @return the provinceState
 */
public String getProvince() {
	return province;
}

/**
 * @param provinceState the provinceState to set
 */
public void setProvince(String provinceState) {
	this.province = provinceState;
}

/**
 * @return the country
 */
public String getCountry() {
	return country;
}

/**
 * @param country the country to set
 */
public void setCountry(String country) {
	this.country = country;
}

/**


/**
 * @return the email
 */
public String getEmail() {
	return email;
}

/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}

/**
 * @return the phone
 */
public String getPhone() {
	return phone;
}

/**
 * @return the street
 */
public String getStreet() {
	return street;
}

/**
 * @param street the street to set
 */
public void setStreet(String street) {
	this.street = street;
}

/**
 * @param phone the phone to set
 */
public void setPhone(String phone) {
	this.phone = phone;
}

public String getPostalCode() {
	return postalCode;
}

public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}


}
