package com.mcquaids.actions.lease;

public class SearchLeaseAction extends BaseLeaseAction {

	private static final long serialVersionUID = 1L;
	private String customerID;
	private String leaseStatusCode;

	/**
	 * @return the leaseStatusCode
	 */
	public String getLeaseStatusCode() {
		return leaseStatusCode;
	}

	/**
	 * @param leaseStatusCode the leaseStatusCode to set
	 */
	public void setLeaseStatusCode(String leaseStatusCode) {
		this.leaseStatusCode = leaseStatusCode;
	}

	/**
	* 
	*/
	public SearchLeaseAction() { 
		super();
	}

	public String execute() {
		try {
		leaseQueryDTOs = leaseService.queryLease(this.leaseID, this.customerID, this.leaseStatusCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "success";
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
	

		

}
