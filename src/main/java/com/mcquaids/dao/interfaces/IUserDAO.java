package com.mcquaids.dao.interfaces;

import java.util.List;

import com.mcquaids.model.Customer;

public interface IUserDAO {

  Customer saveNewCustomer(Customer pCustomer);
  
  Customer saveCustomer(Customer pCustomer);

  Customer findByCustomerID(String pCustomerID);  
  
  List<Customer> findByPhoneNumber(String pPhoneNumber);  

  List<Customer> findByCustomerName(String pCustomerName);  
  
  List<Customer> findByemail(String pEmail);  

void addCustomer(Customer customer);

List<Customer> queryCustomers(String userID, String customerName, String phone, String email,
		String street, String city, String province, String country, String postalCode);  
  
  

}
