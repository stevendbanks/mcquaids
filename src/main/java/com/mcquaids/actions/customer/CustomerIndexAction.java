package com.mcquaids.actions.customer;

public class CustomerIndexAction extends BaseCustomerAction  {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String execute() {
        if ("true".equals(isModal)) {
            return "modal"; // Matches <result name="modal">
        }
        return SUCCESS; // Matches <result name="success">
    }
}