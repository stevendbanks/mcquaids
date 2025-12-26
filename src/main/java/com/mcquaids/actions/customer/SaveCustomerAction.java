package com.mcquaids.actions.customer;

import java.io.Serializable;

import com.mcquaids.model.Constants;
import com.mcquaids.model.Customer;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.CustomerService;


	public class SaveCustomerAction	extends BaseCustomerAction  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title = "Edit Customer";
	

	
	private Customer customer;


	public String execute() {
		codeValues = new CodeValues();
		System.out.println("Hello " + customer.getFirstName());
		CustomerService customerService = new CustomerService();
		System.out.println("START");
		try {
			if (saveActionType.equals(Constants.SAVE_ACTION_TYPE_ADD_NEW)) {
				customer = customerService.saveNewCustomer(customer);
			} else {
				customer = customerService.save(customer);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		System.out.println("END");
		return "success";

	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	
}
