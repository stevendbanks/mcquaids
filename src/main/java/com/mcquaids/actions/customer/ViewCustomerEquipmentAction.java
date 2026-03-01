package com.mcquaids.actions.customer;

import java.util.List;

import com.mcquaids.model.CustomerEquipmentDTO;
import com.mcquaids.service.CustomerService;

 public class ViewCustomerEquipmentAction extends BaseCustomerAction {

	private static final long serialVersionUID = 1L;
	
	
	private String title = "View Customer Equipment";
	
	private String userID;
	
	private String customerID;


	private List<CustomerEquipmentDTO> customerEquipmentDTO;
	
	


	public ViewCustomerEquipmentAction() {
		super();
	}

	
	public String execute() {

		CustomerService customerService = new CustomerService();
		
		// Temporary compatibility layer during migration
		if ((userID == null || userID.isEmpty()) && customerID != null && !customerID.isEmpty()) {
		    userID = customerID;
		}		
		
		customerEquipmentDTO  = customerService.getEquipmentForCustomer(userID);
		

		addActionMessage("Customer Equipment");
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


	/**
	 * @return the customerEquipmentDTO
	 */
	public List<CustomerEquipmentDTO> getCustomerEquipmentDTO() {
		return customerEquipmentDTO;
	}


	/**
	 * @param customerEquipmentDTO the customerEquipmentDTO to set
	 */
	public void setCustomerEquipmentDTO(List<CustomerEquipmentDTO> customerEquipmentDTO) {
		this.customerEquipmentDTO = customerEquipmentDTO;
	}

	
}
