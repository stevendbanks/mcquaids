package com.mcquaids.actions.leasedequipment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mcquaids.actions.equipment.BaseEquipmentAction;
import com.mcquaids.model.EquipmentQueryDTO;
import com.mcquaids.model.LeasedEquipmentView;

 public class AddEquipmentToLeaseAction extends BaseEquipmentAction {

	private static final long serialVersionUID = 1L;
	private List<EquipmentQueryDTO> equipments = new ArrayList<>();

	private String equipmentNumber;
	private Integer equipmentType;
	private String equipmentSubType;
	
	private String availabilityStatusCode;
	private String  conditionStatusCode;
	private String  maintenanceStatusCode;
	private String  cleaningStatusCode;
	private String  bookingStatusCode;
	
	private String leaseID;
	
	private String notes;
	private LeasedEquipmentView leasedEquipmentView;
	

	/**
	* 
	*/
	public AddEquipmentToLeaseAction() { 
		super();
	}

	public String execute() {
		System.out.println("AddEquipmentToLease {equipmentNumber=" + equipmentNumber + ",leaseID=" + leaseID);
		try {
			if (!equipmentNumber.equals("")) {
				leasedEquipmentView = leaseService.addEquipmentToLease(leaseID, equipmentNumber, notes);
				
				if (StringUtils.isNotBlank(leaseService.getErrorMessage())) {
					addActionError(leaseService.getErrorMessage());
					return "input";
				}
				
				if (null == leasedEquipmentView) {
					addActionError("Equipment Number (" + equipmentNumber + ") Not Found");
					return "input";
			    } else {
			    	System.out.println(leasedEquipmentView.toString());
			    }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionError("An Unknown Error has occurred. (" + ex.getMessage() + ")");
			return "input";
		}
		
		return "success";
	}
	


	/**
	 * @return List of Equipment
	 */
	public List<EquipmentQueryDTO> getEquipments() {
		return equipments;
	}

	/**
	 * @param Equipment the trailers to set
	 */
	public void setEquipments(List<EquipmentQueryDTO> equipments) {
		this.equipments = equipments;
	}

	/**
	 * @return the Equipment Number
	 */
	public String getEquipmentNumber() {
			return equipmentNumber;
	}

	/**
	 * @param equipmentNumber to set
	 */
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	
	
	/**
	 * @return the trailerType
	 */
	public Integer getEquipmentType() {
		return equipmentType;
	}

	/**
	 * @param trailerType the trailerType to set
	 */
	public void setEquipmentType(String pEquipmentType) {
		this.equipmentType = stringToInteger(pEquipmentType);

	}

	private Integer stringToInteger(String pEquipmentType) {
		if (pEquipmentType.equals("")) {
			return null;
		} else {
		   return Integer.parseInt(pEquipmentType);
		}
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
	 * @return the availabilityStatusCode
	 */
	public String getAvailabilityStatusCode() {
		return availabilityStatusCode;
	}

	/**
	 * @param availabilityStatusCode the availabilityStatusCode to set
	 */
	public void setAvailabilityStatusCode(String availabilityStatusCode) {
		this.availabilityStatusCode = availabilityStatusCode;
	}

	/**
	 * @return the conditionStatusCode
	 */
	public String getConditionStatusCode() {
		return conditionStatusCode;
	}

	/**
	 * @param conditionStatusCode the conditionStatusCode to set
	 */
	public void setConditionStatusCode(String conditionStatusCode) {
		this.conditionStatusCode = conditionStatusCode;
	}

	/**
	 * @return the maintenanceStatusCode
	 */
	public String getMaintenanceStatusCode() {
		return maintenanceStatusCode;
	}

	/**
	 * @param maintenanceStatusCode the maintenanceStatusCode to set
	 */
	public void setMaintenanceStatusCode(String maintenanceStatusCode) {
		this.maintenanceStatusCode = maintenanceStatusCode;
	}

	/**
	 * @return the cleaningStatusCode
	 */
	public String getCleaningStatusCode() {
		return cleaningStatusCode;
	}

	/**
	 * @param cleaningStatusCode the cleaningStatusCode to set
	 */
	public void setCleaningStatusCode(String cleaningStatusCode) {
		this.cleaningStatusCode = cleaningStatusCode;
	}

	/**
	 * @return the bookingStatusCode
	 */
	public String getBookingStatusCode() {
		return bookingStatusCode;
	}

	/**
	 * @param bookingStatusCode the bookingStatusCode to set
	 */
	public void setBookingStatusCode(String bookingStatusCode) {
		this.bookingStatusCode = bookingStatusCode;
	}

	/**
	 * @param equipmentTypeText the equipmentTypeText to set
	 */
	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
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
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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

	@Override
	public void validate() {
		super.validate();
		if ( (null == equipmentNumber) && (null == equipmentType) && (null == equipmentSubType)) {
			 addActionError("Returning all Equipment as there were no filters applied.");
			 System.out.println("SDB-Error occurred validating SearchEquipmentAction");
		}
	}
}
