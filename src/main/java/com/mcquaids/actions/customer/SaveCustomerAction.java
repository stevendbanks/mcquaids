package com.mcquaids.actions.customer;

import java.io.Serializable;

import com.mcquaids.model.Constants;
import com.mcquaids.model.Customer;
import com.mcquaids.service.CustomerService;

public class SaveCustomerAction extends BaseCustomerAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title = "Edit Customer";

	private Customer customer;

	// NEW: JSON string containing reservation params
	private String returnParams;

	// NEW: final redirect URL for Struts
	private String redirectUrl;

	public String execute() {
		System.out.println("SaveCustomerAction.execute()");
	    CustomerService customerService = new CustomerService();

	    try {
	        if (Constants.SAVE_ACTION_TYPE_ADD_NEW.equals(saveActionType)) {
	    		System.out.println("SaveCustomerAction.execute().Savenew-" + customer.getFullName());

	        	customer = customerService.saveNewCustomer(customer);
	        } else {
	    		System.out.println("SaveCustomerAction.execute().save-" + customer.getFullName());

	            customer = customerService.save(customer);
	        }
	    } catch (Throwable t) {
	        t.printStackTrace();
	        return ERROR;
	    }

	    // This is all you need now!
	    if ("true".equals(getIsModal())) {
	    	System.out.println("SaveCustomerAction.execute().save-modal_success");
	        return "modal_success"; // Returns the tiny JS snippet to update the parent
	    }
    	System.out.println("SaveCustomerAction.execute().SUCCESS");

	    return SUCCESS; // Standard behavior for the standalone management page
	}




	// Getters / setters

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getReturnParams() {
		return returnParams;
	}

	public void setReturnParams(String returnParams) {
		this.returnParams = returnParams;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}