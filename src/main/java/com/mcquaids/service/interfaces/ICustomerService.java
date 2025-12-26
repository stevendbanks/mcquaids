/**
 * 
 */
package com.mcquaids.service.interfaces;

import java.util.List;

import com.mcquaids.model.Customer;

/**
 * 
 */
public interface ICustomerService {
	

	  Customer createBlankCustomer();
	
	  Customer saveNewCustomer(Customer pCustomer);
	  
	  Customer editCustomer(String pCustomerID);

	  Customer saveCustomer(Customer pCustomer);
	  
	  List<Customer> findByPhoneNumber(String pPhoneNumber);  

	  List<Customer> findByCustomerID(String pCustomerID);  

	  List<Customer> findByCustomerName(String pCustomerName);  
	  
	  List<Customer> findByemail(String pEmail);  
	  
	  Customer getBySessionId(String pSessionId);


}
