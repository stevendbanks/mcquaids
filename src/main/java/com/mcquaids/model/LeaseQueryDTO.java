/**
 * 
 */
package com.mcquaids.model;

/**
 * 
 */
public class LeaseQueryDTO extends Lease {
	
	private String leaseStatusDescription;
	private String leaseTerminationDescription;
	
	private Customer customer;

	
	/**
	 * @return the leaseStatusDescription
	 */
	public String getLeaseStatusDescription() {
		return leaseStatusDescription;
	}
	/**
	 * @param leaseStatusDescription the leaseStatusDescription to set
	 */
	public void setLeaseStatusDescription(String leaseStatusDescription) {
		this.leaseStatusDescription = leaseStatusDescription;
	}
	/**
	 * @return the leaseTerminationDescription
	 */
	public String getLeaseTerminationDescription() {
		return leaseTerminationDescription;
	}
	/**
	 * @param leaseTerminationDescription the leaseTerminationDescription to set
	 */
	public void setLeaseTerminationDescription(String leaseTerminationDescription) {
		this.leaseTerminationDescription = leaseTerminationDescription;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
