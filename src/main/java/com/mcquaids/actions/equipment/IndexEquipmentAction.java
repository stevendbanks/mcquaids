package com.mcquaids.actions.equipment;

import com.mcquaids.model.Constants;
import com.opensymphony.xwork2.Action;

 public class IndexEquipmentAction extends BaseEquipmentAction {
//	public class IndexAction  extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String title = "Search For Equipment";
	

	public IndexEquipmentAction() {
		super();
	}

	
	public String execute() {
		
		return Action.SUCCESS;
	}
	
	
	public String trailerSearch() {
		equipmentType = Constants.TRAILER;
		return execute();
	}
	
	public String flatbedSearch() {
		equipmentType = Constants.FLATBED;
		return execute();
	}
	

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	
}
