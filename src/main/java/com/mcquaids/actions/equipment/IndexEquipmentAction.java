package com.mcquaids.actions.equipment;

import com.mcquaids.model.Constants;
import com.opensymphony.xwork2.Action;

 public class IndexEquipmentAction extends BaseEquipmentAction {
//	public class IndexAction  extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String title = "Search For Equipment";
	
	private String caller;
	
	private Integer reservationId;
	
	private Integer reservationLineItemID;
	

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


	/**
	 * @return the caller
	 */
	public String getCaller() {
		return caller;
	}


	/**
	 * @param caller the caller to set
	 */
	public void setCaller(String caller) {
		this.caller = caller;
	}


	/**
	 * @return the reservationId
	 */
	public Integer getReservationId() {
		return reservationId;
	}


	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(Integer reservationID) {
		this.reservationId = reservationID;
	}


	public Integer getReservationLineItemID() {
		return reservationLineItemID;
	}


	public void setReservationLineItemID(Integer reservationLineItemID) {
		this.reservationLineItemID = reservationLineItemID;
	}


	
}
