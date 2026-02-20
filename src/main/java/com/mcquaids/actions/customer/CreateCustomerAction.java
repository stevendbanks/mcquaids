package com.mcquaids.actions.customer;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mcquaids.model.Constants;
import com.mcquaids.model.Customer;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.utils.JsonUtils;

public class CreateCustomerAction extends BaseCustomerAction {

	private static final long serialVersionUID = 1L;

	private String customerID;

	private String title = "Create New Customer";

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Customer customer = new Customer();

	private Map<String, String> params = new HashMap<>();

	private String returnParams;

	public CreateCustomerAction() {
		super();
	}


	@Override
	public String execute() {

	    System.out.println("SDBANKS WAS HERE");

	    HttpServletRequest request = ServletActionContext.getRequest();
	    String queryString = request.getQueryString();

	    if (queryString != null && !queryString.isEmpty()) {

	        Map<String, String> paramMap = new HashMap<>();

	        for (String pair : queryString.split("&")) {
	            String[] kv = pair.split("=", 2);
	            if (kv.length == 2) {
	                paramMap.put(
	                    URLDecoder.decode(kv[0], StandardCharsets.UTF_8),
	                    URLDecoder.decode(kv[1], StandardCharsets.UTF_8)
	                );
	            }
	        }

	        this.setReturnParams(JsonUtils.toJson(paramMap));
	        System.out.println("SDBANKS returnParams = " + this.returnParams);
	    }

	    saveActionType = Constants.SAVE_ACTION_TYPE_ADD_NEW;

	    customer.setProvince("PE");
	    customer.setCountry("Canada");

	    return "success";
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public void setCustomerID(String pCustomerID) {
		this.customerID = pCustomerID;
	}

	/**
	 * @return the codeValues
	 */
	public CodeValues getCodeValues() {
		return codeValues;
	}

	/**
	 * @param codeValues the codeValues to set
	 */
	public void setCodeValues(CodeValues codeValues) {
		this.codeValues = codeValues;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReturnParams() {
		return returnParams;
	}

	public void setReturnParams(String returnParams) {
		this.returnParams = returnParams;
	}



}
