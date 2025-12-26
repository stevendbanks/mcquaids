package com.mcquaids.actions;
import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
public class LoginAction extends ActionSupport{	
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password; 
//	private boolean valid = true;
	

 
	public String getEmail() { 
		return email;  
	}
 
	public void setEmail(String uname) {
		this.email = uname;
	}
	  
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String pass) {
		this.password = pass;
	}
 
	public String execute()
	{

		   if (email.equals("steven.banks@gmail.com") && password.equals("ssrtb")) {
			      return SUCCESS;
		    } else {
		      addActionError("Invalid email or password");
		      return INPUT;
		    }
	}
	


	
	public void validate(){

		if (!hasActionErrors()) {
	    if (StringUtils.isBlank(email) ) {
	        addFieldError("email", "Email Address is required.");
	    }

	    if (StringUtils.isBlank(password)) {
	        addFieldError("password", "Password is required.");
	    }
	   
		}
	    




	}

	
	
	
}