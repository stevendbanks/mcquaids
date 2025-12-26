package com.mcquaids.actions.customer;

import com.mcquaids.model.Customer;
import com.mcquaids.model.lookup.CodeValues;
import com.opensymphony.xwork2.ActionSupport;

public class BaseCustomerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected CodeValues codeValues = new CodeValues();
	
	protected Customer customer;
	
	protected String saveActionType;
	
	/**
	 * 
	 */
	public BaseCustomerAction() {
		
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


	/**
	 * @return the saveActionType
	 */
	public String getSaveActionType() {
		return saveActionType;
	}


	/**
	 * @param saveActionType the saveActionType to set
	 */
	public void setSaveActionType(String saveActionType) {
		this.saveActionType = saveActionType;
	}


	

}
