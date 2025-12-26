package com.mcquaids.actions.equipment;

import java.util.HashMap;
import java.util.Map;

import com.mcquaids.model.Constants;
import com.mcquaids.model.Equipment;
import com.mcquaids.model.EquipmentQueryDTO;
import com.mcquaids.model.LeasedEquipment;
import com.mcquaids.model.LeasedEquipmentView;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.EquipmentService;
import com.mcquaids.service.LeaseService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseEquipmentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected CodeValues codeValues;

	protected Map<String, String> equipmentSubTypes = new HashMap<String, String>();		
	
	protected Equipment equipment;
	
	protected EquipmentQueryDTO equipmentQueryDTO;

	protected LeasedEquipment leasedEquipment;
	
	protected LeasedEquipmentView leasedEquipmentView;
	
	protected String leaseID;
	
	protected String equipmentNumber;
	
	protected Integer equipmentType;
	
	protected String equipmentTypeText = "Equipment";

	protected String actionTypeText = "Edit";

	protected String SaveActionType;
	
	protected String title;

	
	protected boolean editable = false;


	
	
	protected EquipmentService equipmentService = new EquipmentService();
	protected LeaseService leaseService = new LeaseService();

	protected Map<String, String> errors = new HashMap<>();	
	
	/**
	 * 
	 */
	public BaseEquipmentAction() {
		codeValues = new CodeValues();
	}
	
	
	/**
	 * 
	 */
	public BaseEquipmentAction(String x) {
		//  Just putting this here until I clean up code.
	}
	
	/**
	 * 
	 */
	public void setEquipmentSubtypeSelect(Integer pEquipmentSubtype) {
		codeValues = new CodeValues();
		codeValues.setEquipmentSubTypes(pEquipmentSubtype );
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
	 * @param errors the errors to set
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}



	public Map<String, String> getErrors() {
		return errors;
	}


	/**
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}


	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}


	/**
	 * @return the equipmentType
	 */
	public Integer getEquipmentType() {
		return equipmentType;
	}


	/**
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}


	/**
	 * @return the equipmentSubTypes
	 */
	public Map<String, String> getEquipmentSubTypes() {
		return equipmentSubTypes;
	}


	/**
	 * @param equipmentSubTypes the equipmentSubTypes to set
	 */
	public void setEquipmentSubTypes(Map<String, String> equipmentSubTypes) {
		this.equipmentSubTypes = equipmentSubTypes;
	}


	/**
	 * @return the leasedEquipment
	 */
	public LeasedEquipment getLeasedEquipment() {
		return leasedEquipment;
	}


	/**
	 * @param leasedEquipment the leasedEquipment to set
	 */
	public void setLeasedEquipment(LeasedEquipment leasedEquipment) {
		this.leasedEquipment = leasedEquipment;
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
	public void setLeaseID(String leaseID) {
		this.leaseID = leaseID;
	}


	/**
	 * @return the equipmentNumber
	 */
	public String getEquipmentNumber() {
		return equipmentNumber;
	}


	/**
	 * @return the equipmentQueryDTO
	 */
	public EquipmentQueryDTO getEquipmentQueryDTO() {
		return equipmentQueryDTO;
	}


	/**
	 * @param equipmentQueryDTO the equipmentQueryDTO to set
	 */
	public void setEquipmentQueryDTO(EquipmentQueryDTO equipmentQueryDTO) {
		this.equipmentQueryDTO = equipmentQueryDTO;
	}


	/**
	 * @param equipmentNumber the equipmentNumber to set
	 */
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}


	/**
	 * @return the leasedEquipmentView
	 */
	public LeasedEquipmentView getLeasedEquipmentView() {
		return leasedEquipmentView;
	}


	/**
	 * @param leasedEquipmentView the leasedEquipmentView to set
	 */
	public void setLeasedEquipmentView(LeasedEquipmentView leasedEquipmentView) {
		this.leasedEquipmentView = leasedEquipmentView;
	}



	/**
	 * @return the equipmentTypeText
	 */
	public String getEquipmentTypeText() {
		return equipmentTypeText;
	}


	/**
	 * @return the saveActionType
	 */
	public String getSaveActionType() {
		return SaveActionType;
	}


	/**
	 * @param saveActionType the saveActionType to set
	 */
	public void setSaveActionType(String saveActionType) {
		SaveActionType = saveActionType;
	}


	/**
	 * @param equipmentTypeText the equipmentTypeText to set
	 */
	public void setEquipmentTypeText(String equipmentType) {
		this.equipmentTypeText = equipmentType;
	}


	/**
	 * @return the actionTypeText
	 */
	public String getActionTypeText() {
		return actionTypeText;
	}


	/**
	 * @param actionTypeText the actionTypeText to set
	 */
	public void setActionTypeText(String actionTypeText) {
		this.actionTypeText = actionTypeText;
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

	public boolean isEditable() {
	    return editable;
	}

	public void setEditable(boolean editable) {
	    this.editable = editable;
	}		
	

	protected String setJSPTitle(String titlePrefix) {
		switch(this.equipment.getEquipmentType() ) {
		  case 1002:
			  equipmentTypeText= titlePrefix + " Trailer";
		    break;
		  case 1003:
			  equipmentTypeText= titlePrefix + " Flatbed";
		    break;
		  case 1004:
			  equipmentTypeText= titlePrefix + " Container";
		    break;
		  case 1005:
			  equipmentTypeText= titlePrefix + " Forklift";
		    break;
		  default:
			  equipmentTypeText= titlePrefix + " Equipment";
		}
		return equipmentTypeText;
	
	}


	protected String redirectBasedOnEquipmentType(int equipmentType) {
		switch (equipmentType) {
		  case Constants.TRAILER:
				return "success-trailer";
		  case Constants.FLATBED:
			  return "success-flatbed";
		  case Constants.CONTAINER:
				return "success-container";
		  case Constants.FORKLIFT:
			  System.out.println("SDBANKS- Returning success=success-forklift");
			  return "success-forklift";
		  default:
			  return "error";
		}
	}	


	

}
