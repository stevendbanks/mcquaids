package com.mcquaids.actions.customer;

import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

import com.mcquaids.model.Constants;
import com.mcquaids.model.Customer;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.CustomerService;
import com.mcquaids.utils.JsonUtils;

public class SaveCustomerAction extends BaseCustomerAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title = "Edit Customer";

	private Customer customer;

	// NEW: JSON string containing reservation params
	private String returnParams;

	// NEW: final redirect URL for Struts
	private String redirectUrl;

	public String execute() {

		System.out.println("SaveCustomerAction -> returnParams=" + returnParams);

		codeValues = new CodeValues();
		CustomerService customerService = new CustomerService();

		try {
			if (saveActionType.equals(Constants.SAVE_ACTION_TYPE_ADD_NEW)) {
				customer = customerService.saveNewCustomer(customer);
			} else {
				customer = customerService.save(customer);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

		// NEW: If returnParams exists, this came from reservation workflow
		if (returnParams != null && !returnParams.isEmpty()) {
			return buildReservationRedirect();
		}

		return "success";
	}

	// NEW: Build redirect URL back to reservation workflow
	private String buildReservationRedirect() {

		// Parse JSON into a map using your utility class
		Map<String, String> params = JsonUtils.setPropertiesFromJson(returnParams);

		// Inject the newly created customer
		params.put("reservation.customerID", String.valueOf(customer.getUserID()));
		params.put("fromSelector", "true");

		// Determine edit vs create
		boolean hasReservationId = params.containsKey("reservation.reservationID")
				&& params.get("reservation.reservationID") != null
				&& !params.get("reservation.reservationID").isEmpty();

		String base;
		if (hasReservationId) {
			base = "/reservation/edit-reservation?";
		} else {
			base = "/reservation/create?";
		}

		// Rebuild query string
		String query = params.entrySet().stream().map(e -> encode(e.getKey()) + "=" + encode(e.getValue()))
				.collect(Collectors.joining("&"));

		redirectUrl = base + query;

		
		System.out.println("SDBANKS->reservationRedirect="+ redirectUrl);
		return "reservationRedirect";
	}

	// NEW: URL encoding helper
	private String encode(String value) {
		try {
			return URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8.toString());
		} catch (Exception e) {
			return "";
		}
	}

	// Getters / setters

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getReturnParams() {
		return returnParams;
	}

	public void setReturnParams(String returnParams) {
		this.returnParams = returnParams;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}