package com.mcquaids.actions.customer;

import com.mcquaids.service.CustomerService;

 public class EditCustomerAction extends BaseCustomerAction {
//	public class EditAction	extends ActionSupport implements Serializable, ModelDriven<Customer> {

	private static final long serialVersionUID = 1L;
	
	
	private String title = "Edit Customer";
	
	private String userID;


	public EditCustomerAction() {
		super();
	}

	
	public String execute() {
//		codeValues = new CodeValues();
		CustomerService customerService = new CustomerService();
		customer = customerService.edit(userID);

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
	
}
