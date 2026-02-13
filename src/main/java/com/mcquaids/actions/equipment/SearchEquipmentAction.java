package com.mcquaids.actions.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mcquaids.model.EquipmentQueryDTO;
import com.opensymphony.xwork2.ActionSupport;

 public class SearchEquipmentAction extends BaseEquipmentAction {

	private static final long serialVersionUID = 1L;
//	private List<Equipment> equipments = new ArrayList<>();
	private List<EquipmentQueryDTO> equipments = new ArrayList<>();

	private Integer equipmentType;
	private String equipmentSubType;
	
	private String availabilityStatusCode;
	private String  conditionStatusCode;
	private String  maintenanceStatusCode;
	private String  cleaningStatusCode;
	private String  bookingStatusCode;
	

	/**
	* 
	*/
	public SearchEquipmentAction() { 
		super();
	}

	public String execute() {
		try {
			if ((null == equipmentNumber)   || equipmentNumber.equals("")) {
				equipments = equipmentService.queryEquipment(this.equipmentType, equipmentSubType, availabilityStatusCode, conditionStatusCode, maintenanceStatusCode, cleaningStatusCode, bookingStatusCode );
				if (equipmentService.getErrors() != null) {
			        // Check if there are any errors returned from the service
			        if (!equipmentService.getErrors().isEmpty()) {
			            for (Map.Entry<String, String> entry : equipmentService.getErrors().entrySet()) {
			                addActionError(entry.getValue());
			            }
						return "input";  
				    }
				} 
			} else {
				equipments = equipmentService.queryEquipmentByEquipmentNUmber(equipmentNumber); 
			}
			
			if (equipments.size() <1 ) {
				addActionError("Equipment Not Found.  Check Filters");
				return "input";
				
			}
				
//		        // Use forEach method with a lambda expression
//		        equipments.forEach(equipment -> System.out.println(equipment.toString()));
		
		} catch (Throwable ex) {
			ex.printStackTrace();
			addActionError("An Unknown Error has occurred. (" + ex.getMessage() + ")");
			return "Exception";
		}
		
		return ActionSupport.SUCCESS;
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
	 * @return the equipments
	 */
	public List<EquipmentQueryDTO> getEquipments() {
		return equipments;
	}

	/**
	 * @param equipments the equipments to set
	 */
	public void setEquipments(List<EquipmentQueryDTO> equipments) {
		this.equipments = equipments;
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
