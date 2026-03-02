package com.mcquaids.actions.customer;

import org.apache.struts2.ServletActionContext;

import com.mcquaids.service.CustomerService;

 public class EditCustomerAction extends BaseCustomerAction {

	private static final long serialVersionUID = 1L;
	
	
	private String title = "Edit Customer";
	
	private String userID;
	
	private String customerID;
	
	private String returnParams;
	



	public EditCustomerAction() {
		super();
	}

	
	public String execute() {
//		codeValues = new CodeValues();
		CustomerService customerService = new CustomerService();
		
		// Temporary compatibility layer during migration
		if ((userID == null || userID.isEmpty()) && customerID != null && !customerID.isEmpty()) {
		    userID = customerID;
		}		
		
		customer = customerService.edit(userID);
		
		System.out.println(customer.toString());

		System.out.println("SDBANKS-Executing EditCustomerAction");
        // Check if the JS passed the isModal flag
        if ("true".equals(isModal)) {
            return "modal"; // Matches <result name="modal">
        }
		
		
		
		return "success";
	}


	/**
	 * @return the customerID
	 */
	public String getUserID() {
		return userID;
	}


	/**
	 * @param customerID the customerID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}




	
}
