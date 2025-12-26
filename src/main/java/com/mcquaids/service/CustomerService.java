package com.mcquaids.service;

import java.util.List;

import com.mcquaids.dao.UserDAO;
import com.mcquaids.dao.interfaces.IUserDAO;
import com.mcquaids.model.Customer;

public class CustomerService  {

	private IUserDAO userDAO;

	/**
	* 
	*/
	public CustomerService() {
		userDAO = new UserDAO();
	}

	public Customer saveNewCustomer(Customer customer) {
		userDAO.addCustomer(customer);
		return customer;
	}
	
	public void addCustomer(Customer customer) {
		userDAO.addCustomer(customer); 
		
	}

	public Customer edit(String pUserID) {
		return userDAO.findByCustomerID(pUserID); 
	}

	public Customer save(Customer pCustomer) {
		
		return userDAO.saveCustomer(pCustomer);
	}	
	
	public List<Customer> findByCustomerName(String pCustomerName) {
		return userDAO.findByCustomerName(pCustomerName);
	}
	
	

	public List<Customer> queryCustomers(String userID, String customerName, String phone, String email,
			String street, String city, String province, String country, String postalCode) {

		return userDAO.queryCustomers(userID, customerName, phone, email, street, city, province, country, postalCode);
		
		
	}



}