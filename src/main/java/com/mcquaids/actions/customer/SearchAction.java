package com.mcquaids.actions.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcquaids.model.Customer;
import com.mcquaids.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;

// public class SearchAction extends BaseCustomerAction {
	
	public class SearchAction	extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<Customer> customers = new ArrayList<>();

	private CustomerService customerService;
	
	String phoneNumber = null;;
	String customerID = null;;
	String customerName = null;
	String email = null;
	String street = null;
	String city = null;
	String province = null;
	String Country = null;
	String postalCode = null;
	

//	public SearchAction(ICustomerService customerService) {
//		super();
//		this.customerService = customerService;
//	}
	
    /**
 * 
 */
public SearchAction() {
	super();
	customerService = new CustomerService();
}	
	
	public String execute() {
		try {
            
			customers = customerService.queryCustomers(customerID, customerName, phoneNumber, email, street, city, province, Country, postalCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}
	
	



	protected Map<String, String> errors = new HashMap<>();
    
    public Map<String, String> getErrors() {
        return errors; 
    }	
	

//	public void validate() {
//		if (null == phoneNumber && null == customerID  && null == customerName && null == email) {
//			errors.put("validation", "You must Enter a value in one of the search fields.");
//		}
//	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String pCustomerID) {
		this.customerID = pCustomerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
