package com.mcquaids.actions.reservation;

import java.util.HashMap;
import java.util.Map;

import com.mcquaids.model.Equipment;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.EquipmentService;
import com.mcquaids.service.LeaseService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseReservationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected CodeValues codeValues;

	
	protected Equipment equipment;
	protected String equipmentTypeText;

	protected String equipmentNumber;
	protected Integer equipmentType;
	protected String equipmentSubType;
	
	

	protected String actionTypeText = "Reserve";
	
	protected String reservationID;


	
	protected EquipmentService equipmentService = new EquipmentService();
	protected LeaseService leaseService = new LeaseService();

	protected Map<String, String> errors = new HashMap<>();

	
	/**
	 * 
	 */
	public BaseReservationAction() {
		codeValues = new CodeValues();
	}
	
	

	

	
	/**
	 * 
	 */
	public void setEquipmentSubtypeSelect(Integer pEquipmentSubtype) {
		codeValues = new CodeValues();
		codeValues.setEquipmentSubTypes(pEquipmentSubtype );
	}
	
	
	/**
	 * @return the EquipmentSubType
	 */
	public String getEquipmentSubType() {
		return equipmentSubType;
	}

	/**
	 * @param flatbedTrailerType the flatbedTrailerType to set
	 */
	public void setEquipmentSubType(String equipmentSubType) {
		if (null == equipmentSubType || equipmentSubType.startsWith("All types")) {
			this.equipmentSubType = null;
		} else {
			this.equipmentSubType = equipmentSubType;
		}
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
	 * @return the equipmentNumber
	 */
	public String getEquipmentNumber() {
		return equipmentNumber;
	}


	/**
	 * @param equipmentNumber the equipmentNumber to set
	 */
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}



	/**
	 * @return the trailerType
	 */
	public String getEquipmentTypeText() {
		return equipmentTypeText;
	}

	/**
	 * @param trailerType the trailerType to set
	 */
	public void setEquipmentTypeText(String pEquipmentType) {
		this.equipmentType = stringToInteger(pEquipmentType);

	}
	
	/**
	 * @param equipmentTypeText the equipmentTypeText to set
	 */
	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}	
	

	private Integer stringToInteger(String pEquipmentType) {
		if (pEquipmentType.equals("")) {
			return null;
		} else {
		   return Integer.parseInt(pEquipmentType);
		}
	}
		




	/**


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
	 * @return the reservationID
	 */
	public String getReservationID() {
		return reservationID;
	}






	/**
	 * @param reservationID the reservationID to set
	 */
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

		
	

}
