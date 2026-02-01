package com.mcquaids.actions.reservation.lineItems;

import java.util.HashMap;
import java.util.Map;

import com.mcquaids.model.Equipment;
import com.mcquaids.model.ReservationLineItem;
import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.EquipmentService;
import com.mcquaids.service.ReservationService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseReservationManagementAction extends ActionSupport {

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
	
	protected int equipmentQty;
	protected String  equipmentNotes;
	
	

	protected String actionTypeText = "Reserve";
	
	protected String reservationID;
	
	protected int reservationLineItemID;
	
	protected ReservationLineItem reservationLineItem;
	protected ReservationLineItemDTO  reservationLineItemDTO;


	
	protected EquipmentService equipmentService = new EquipmentService();
	protected ReservationService reservationService = new ReservationService();

	protected Map<String, String> errors = new HashMap<>();

	
	/**
	 * 
	 */
	public BaseReservationManagementAction() {
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






	/**
	 * @return the reservationLineItemID
	 */
	public int getReservationLineItemID() {
		return reservationLineItemID;
	}






	/**
	 * @param reservationLineItemID the reservationLineItemID to set
	 */
	public void setReservationLineItemID(int reservationLineItemID) {
		this.reservationLineItemID = reservationLineItemID;
	}






	/**
	 * @return the equipmentType
	 */
	public Integer getEquipmentType() {
		return equipmentType;
	}






	/**
	 * @return the reservationLineItem
	 */
	public ReservationLineItem getReservationLineItem() {
		return reservationLineItem;
	}






	/**
	 * @param reservationLineItem the reservationLineItem to set
	 */
	public void setReservationLineItem(ReservationLineItem reservationLineItem) {
		this.reservationLineItem = reservationLineItem;
	}






	/**
	 * @return the reservationLineItemDTO
	 */
	public ReservationLineItemDTO getReservationLineItemDTO() {
		return reservationLineItemDTO;
	}






	/**
	 * @param reservationLineItemDTO the reservationLineItemDTO to set
	 */
	public void setReservationLineItemDTO(ReservationLineItemDTO reservationLineItemDTO) {
		this.reservationLineItemDTO = reservationLineItemDTO;
	}






	/**
	 * @return the equipmentQty
	 */
	public int getEquipmentQty() {
		return equipmentQty;
	}






	/**
	 * @param equipmentQty the equipmentQty to set
	 */
	public void setEquipmentQty(int equipmentQty) {
		this.equipmentQty = equipmentQty;
	}






	/**
	 * @return the equipmentNotes
	 */
	public String getEquipmentNotes() {
		return equipmentNotes;
	}






	/**
	 * @param equipmentNotes the equipmentNotes to set
	 */
	public void setEquipmentNotes(String equipmentNotes) {
		this.equipmentNotes = equipmentNotes;
	}

		
	

}
