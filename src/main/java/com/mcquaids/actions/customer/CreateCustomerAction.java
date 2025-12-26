package com.mcquaids.actions.customer;

import com.mcquaids.model.Constants;
import com.mcquaids.model.Customer;
import com.mcquaids.model.lookup.CodeValues;

 public class CreateCustomerAction extends BaseCustomerAction {
	 
	private static final long serialVersionUID = 1L;
	
	private String customerID;

	private String title = "Create New Customer";


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Customer customer = new Customer();


	public CreateCustomerAction() {
		super();
	}

	
	public String execute() {
        saveActionType = Constants.SAVE_ACTION_TYPE_ADD_NEW;
		customer.setProvince("PE");
		customer.setCountry("Canada");
//		codeValues = new CodeValues();
		return "success";
	}


	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String pCustomerID) {
		this.customerID = pCustomerID;
	}


	/**
	 * @return the codeValues
	 */
	public CodeValues getCodeValues() {
		return codeValues;
	}


	/**
	 * @param codeValues the codeValues to set
	 */
	public void setCodeValues(CodeValues codeValues) {
		this.codeValues = codeValues;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	
}
