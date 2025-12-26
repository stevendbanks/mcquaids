package com.mcquaids.actions.user;

import org.apache.commons.lang3.StringUtils;

public class RegisterAction extends BaseUserAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String email;
	private String password; 
	private String passwordConfirmation;


	public String execute() throws Exception {
		return SUCCESS;
    }

	public String input() throws Exception {

		return INPUT;
    }	
	
	public void validate(){
	    if (StringUtils.isEmpty(firstname) ) {
	        addFieldError("firstname", "Given name is required.");
	    }

	    if (StringUtils.isEmpty(lastname)) {
	        addFieldError("lastname", "Last name is required.");
	    }

       if (StringUtils.isEmpty(email)) {
	        addFieldError("email", "Email is required.");
	    }

	}

	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}





}
