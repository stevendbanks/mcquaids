package com.mcquaids.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.CustomerDAO;
import com.mcquaids.dao.LeaseDAO;
import com.mcquaids.dao.LeaseEquipmentDAO;
import com.mcquaids.dao.UserDAO;
import com.mcquaids.model.Customer;
import com.mcquaids.model.CustomerEquipmentDTO;

public class CustomerService  {

	private UserDAO userDAO;
	private CustomerDAO customerDAO;

	/**
	* 
	*/
	public CustomerService() {
		userDAO = new UserDAO();
		customerDAO = new CustomerDAO();
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
	
	 public List<CustomerEquipmentDTO> getEquipmentForCustomer(String customerID) {
	        if (customerID == null || customerID.trim().isEmpty()) {
	            return new ArrayList<>();
	        }

	        try {
	            return customerDAO.getEquipmentForCustomer(customerID);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return new ArrayList<>();
	        }
	    }
	



}