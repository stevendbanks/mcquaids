package com.mcquaids.actions.customer;

import java.util.ArrayList;
import java.util.List;

import com.mcquaids.model.Customer;
import com.mcquaids.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<Customer> customers = new ArrayList<>();
	private CustomerService customerService = new CustomerService();

	// Search fields
	private String phoneNumber;
	private String customerID;
	private String customerName;
	private String email;

	// Caller parameters
	private String caller;
	private String reservationId;

	public String execute() {
		try {
			customers = customerService.queryCustomers(customerID, customerName, phoneNumber, email, null, null, null,
					null, null);

			if (customers == null || customers.isEmpty()) {
				addActionError("No matching customers found.");
				return INPUT;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			addActionError("An unknown error occurred: " + ex.getMessage());
			return INPUT;
		}

		return SUCCESS;
	}

	@Override
	public void validate() {
		if (isEmpty(customerID) && isEmpty(customerName) && isEmpty(phoneNumber) && isEmpty(email)) {

			addActionError("Returning all customers because no filters were applied.");
		}
	}

	private boolean isEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	// Getters and setters...

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getCaller() {
		return caller;
	}

	public String getReservationId() {
		return reservationId;
	}
}