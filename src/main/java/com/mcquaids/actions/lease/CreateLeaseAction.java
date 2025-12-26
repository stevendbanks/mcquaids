package com.mcquaids.actions.lease;

public class CreateLeaseAction	extends BaseLeaseAction {

	private static final long serialVersionUID = 1L;
	


	public CreateLeaseAction() {
		super();
		title = "Create New Lease";
		super.saveActionType = "saveNewLease";
	}

	
	public String execute() {
		return "success";
	}

	
}
