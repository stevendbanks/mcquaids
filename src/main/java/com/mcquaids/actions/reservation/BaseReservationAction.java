package com.mcquaids.actions.reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcquaids.model.Equipment;
import com.mcquaids.model.EquipmentQueryDTO;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.model.ReservationViewDTO;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.EquipmentService;
import com.mcquaids.service.LeaseService;
import com.mcquaids.service.ReservationService;
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
	
	protected String pageTitle;
	
	protected Integer reservationID;
	protected Integer reservedEquipmentID;
	
	protected EquipmentQueryDTO reservedEquipment;
	
	protected String customerID;

	protected List<ReservationViewDTO> reservationViewDTO;



	protected List<ReservationLineItemDTO> reservationLineItemsDTO;
	

	
	protected EquipmentService equipmentService = new EquipmentService();
	protected ReservationService reservationService = new ReservationService();
	protected LeaseService leaseService = new LeaseService();

	protected String actionType;
	protected Map<String, String> errors = new HashMap<>();

	protected Reservation reservation;

	
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
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}






	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
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
	 * @return the reservedEquipment
	 */
	public EquipmentQueryDTO getReservedEquipment() {
		return reservedEquipment;
	}






	/**
	 * @param reservedEquipment the reservedEquipment to set
	 */
	public void setReservedEquipment(EquipmentQueryDTO reservedEquipment) {
		this.reservedEquipment = reservedEquipment;
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
	public Integer getReservationID() {
		return reservationID;
	}


	/**
	 * @param reservationID the reservationID to set
	 */
	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}






	/**
	 * @return the reservedEquipmentID
	 */
	public Integer getReservedEquipmentID() {
		return reservedEquipmentID;
	}






	/**
	 * @param reservedEquipmentID the reservedEquipmentID to set
	 */
	public void setReservedEquipmentID(Integer reservedEquipmentID) {
		this.reservedEquipmentID = reservedEquipmentID;
	}






	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}






	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}






	/**
	 * @return the reservationViewDTO
	 */
	public List<ReservationViewDTO> getReservationViewDTO() {
		return reservationViewDTO;
	}






	/**
	 * @param reservationViewDTO the reservationViewDTO to set
	 */
	public void setReservationViewDTO(List<ReservationViewDTO> reservationViewDTO) {
		this.reservationViewDTO = reservationViewDTO;
	}






	/**
	 * @return the leaseService
	 */
	public LeaseService getLeaseService() {
		return leaseService;
	}






	/**
	 * @param leaseService the leaseService to set
	 */
	public void setLeaseService(LeaseService leaseService) {
		this.leaseService = leaseService;
	}






	/**
	 * @return the reservation
	 */
	public Reservation getReservation() {
		return reservation;
	}






	/**
	 * @param reservation the reservation to set
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}






	/**
	 * @return the equipmentType
	 */
	public Integer getEquipmentType() {
		return equipmentType;
	}






	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}






	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}






	/**
	 * @return the reservationLineItemsDTO
	 */
	public List<ReservationLineItemDTO> getReservationLineItemsDTO() {
		return reservationLineItemsDTO;
	}






	/**
	 * @param reservationLineItemsDTO the reservationLineItemsDTO to set
	 */
	public void setReservationLineItemsDTO(List<ReservationLineItemDTO> reservationLineItemsDTO) {
		this.reservationLineItemsDTO = reservationLineItemsDTO;
	}




		
	

}
