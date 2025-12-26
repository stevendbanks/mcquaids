package com.mcquaids.actions.lease;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcquaids.model.Lease;
import com.mcquaids.model.LeaseQueryDTO;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.LeaseService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseLeaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected LeaseService leaseService = new LeaseService();
	
	protected CodeValues codeValues;
	
	protected Map<String, String> errors = new HashMap<>();	
	
	protected Lease lease;
	
	protected List<LeaseQueryDTO> leaseQueryDTOs = new ArrayList<>();
	
	protected String saveActionType;
	
	protected 	String leaseID;
	
	protected String title;
	
	
	
	
	

	public BaseLeaseAction() {
		codeValues = new CodeValues();
	}
	
	
	
	/**
	 * @return the lease
	 */
	public Lease getLease() {
		return lease;
	}

	/**
	 * @param lease the lease to set
	 */
	public void setLease(Lease lease) {
		this.lease = lease;
	}



	/**
	 * @return the codeValues
	 */
	public CodeValues getCodeValues() {
		return codeValues;
	}

	/**
	 * @param codeValues the codeValues to set
	 */
	public void setCodeValues(CodeValues codeValues) {
		this.codeValues = codeValues;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the leaseID
	 */
	public String getLeaseID() {
		return leaseID;
	}


	/**
	 * @param leaseID the leaseID to set
	 */
	public void setLeaseID(String pLeaseID) {
		this.leaseID = pLeaseID;
	}



	/**
	 * @return the leases
	 */
	public List<LeaseQueryDTO> getLeaseQueryDTOs() {
		return leaseQueryDTOs;
	}



	/**
	 * @param leases the leases to set
	 */
	public void setLeaseQueryDTOs(List<LeaseQueryDTO> leases) {
		this.leaseQueryDTOs = leases;
	}



	/**
	 * @return the saveActionType
	 */
	public String getSaveActionType() {
		return saveActionType;
	}



	/**
	 * @param saveActionType the saveActionType to set
	 */
	public void setSaveActionType(String saveActionType) {
		this.saveActionType = saveActionType;
	}



	/**
	 * @return the errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}



	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}


	

}
