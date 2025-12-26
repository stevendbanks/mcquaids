/**
 * 
 */
package com.mcquaids.model;

/**
 * 
 */
public class Employee extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String employeeID;
	
	private String password;

	
	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
