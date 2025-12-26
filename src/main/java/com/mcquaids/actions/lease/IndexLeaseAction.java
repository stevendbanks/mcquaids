package com.mcquaids.actions.lease;

import com.opensymphony.xwork2.Action;

 public class IndexLeaseAction extends BaseLeaseAction {


	private static final long serialVersionUID = 1L;
	

	public IndexLeaseAction() {
		super();
		title = "Search For Lease(s)";
	}

	
	public String execute() {
		
		return Action.SUCCESS;
	}

	
}
