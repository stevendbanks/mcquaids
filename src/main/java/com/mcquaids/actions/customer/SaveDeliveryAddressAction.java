package com.mcquaids.actions.customer;

import java.util.Collection;

import com.mcquaids.model.Reservation;
import com.mcquaids.service.CustomerService;
import com.mcquaids.service.ReservationService;

public class SaveDeliveryAddressAction extends BaseCustomerAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Incoming JSON fields
	private Integer reservationID;
	private String deliveryAddressSource;

	private String street;
	private String city;
	private String province;
	private String postalCode;
	private String country;

	// Services
	private ReservationService reservationService = new ReservationService();
	private CustomerService customerService;


	@Override
	public String execute() {

		try {
			// Load reservation
			Reservation r = reservationService.getReservation(reservationID);

			if (r == null) {
				addActionError("Reservation not found");
				return SUCCESS;
			}

			
			
			r.setDeliverySameAsCustomer("CUSTOMER".equals(deliveryAddressSource));
			
			System.out.println("setDeliverySameAsCustomer=" + r.getDeliverySameAsCustomer());

			// Save delivery address (same for CUSTOMER or ALTERNATIVE)
			r.setDeliveryStreet(street);
			r.setDeliveryCity(city);
			r.setDeliveryProvince(province);
			r.setDeliveryPostalCode(postalCode);
			r.setDeliveryCountry(country);

			reservationService.updateReservation(r);


			addActionMessage("Delivery address saved");
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Unable to save delivery address.  Please Try again");
			return SUCCESS;
		}
	}



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



	/**
	 * @return the deliveryAddressSource
	 */
	public String getDeliveryAddressSource() {
		return deliveryAddressSource;
	}



	/**
	 * @param deliveryAddressSource the deliveryAddressSource to set
	 */
	public void setDeliveryAddressSource(String deliveryAddressSource) {
		this.deliveryAddressSource = deliveryAddressSource;
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
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}



	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
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
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}



	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	/**
	 * @return the customerService
	 */
	public CustomerService getCustomerService() {
		return customerService;
	}



	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	

	


}