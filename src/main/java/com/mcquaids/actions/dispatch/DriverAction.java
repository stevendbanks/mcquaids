package com.mcquaids.actions.dispatch;

import com.mcquaids.model.DispatchAction;
import com.mcquaids.service.DispatchActionService;
import com.mcquaids.service.DriverActionService;
import com.opensymphony.xwork2.ActionSupport;

public class DriverAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DispatchActionService dispatchActionService = new DispatchActionService();
	private DriverActionService driverActionService = new DriverActionService();

	private Long id;
	private String token;
	private String notes;

	private DispatchAction action;
	private Long actionId;

	// ------------------------------------------------------------
	// Setters for Struts parameter injection
	// ------------------------------------------------------------
	public void setId(Long id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	// ------------------------------------------------------------
	// Getters for JSP access (ValueStack)
	// ------------------------------------------------------------
	public DispatchAction getAction() {
		return action;
	}

	public String getToken() {
		return token;
	}

	public Long getActionId() {
		return actionId;
	}


	// ------------------------------------------------------------
	// SHOW ACTION PAGE
	// ------------------------------------------------------------
	@Override
	public String execute() throws Exception {

		action = dispatchActionService.getActionById(id);
		
		
		
		System.out.println(action.toString());

		driverActionService.validateToken(id, token);
		String error = driverActionService.getError();

		if (error != null && !error.isEmpty()) {
		    addActionError(error);
		    return "error";
		}


		return "show";
	}

	// ------------------------------------------------------------
	// COMPLETE ACTION
	// ------------------------------------------------------------
	public String complete() throws Exception {
		
	    driverActionService.validateToken(id, token);
	    String error = driverActionService.getError();

		if (error != null && !error.isEmpty()) {
		    addActionError(error);
		    return "error";
		}

	    
	    
	    driverActionService.completeAction(id, notes);
	    this.actionId = id;
	    return "completed";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
}