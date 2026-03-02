package com.mcquaids.actions.customer;

import java.util.ArrayList;
import java.util.List;

import com.mcquaids.model.Customer;
import com.mcquaids.service.CustomerService;

public class SearchAction extends BaseCustomerAction {

	private static final long serialVersionUID = 1L;

	private List<Customer> customers = new ArrayList<>();
	private CustomerService customerService = new CustomerService();

	// Search fields
	private String phoneNumber;
	private String customerID;
	private String customerName;
	private String email;
	private String businessName;

	// Caller parameters
//	private String reservationId;

	public String execute() {
		System.out.println("SearchAction.java Entered");
		try {
			customers = customerService.queryCustomers(businessName, customerID, customerName, phoneNumber, email, null, null, null,
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
		if ( isEmpty(businessName) && isEmpty(customerID) && isEmpty(customerName) && isEmpty(phoneNumber) && isEmpty(email)) {

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


	/**
	 * @return the businessName
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * @param businessName the businessName to set
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

}