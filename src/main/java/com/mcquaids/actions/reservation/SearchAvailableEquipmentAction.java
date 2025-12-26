package com.mcquaids.actions.reservation;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class SearchAvailableEquipmentAction extends BaseReservationAction {

	private static final long serialVersionUID = 1L;
//	private List<EquipmentQueryDTO> equipments = new ArrayList<>();

	List<Map<String, Object>> equipmentThatisAvailable;

	private String availabilityStatusCode;
	private String maintenanceStatusCode;

	/**
	* 
	*/
	public SearchAvailableEquipmentAction() {
		super();
	}

	public String execute() {
		try {
			equipmentThatisAvailable = equipmentService.queryEquipmentisAvailable(this.equipmentType, equipmentSubType,
					availabilityStatusCode, maintenanceStatusCode);
			if (equipmentService.getErrors() != null) {
				if (!equipmentService.getErrors().isEmpty()) {
					for (Map.Entry<String, String> entry : equipmentService.getErrors().entrySet()) {
						addActionError(entry.getValue());
					}
					return "input";
				}
			}

			if (equipmentThatisAvailable.size() < 1) {
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

	public List<Map<String, Object>> getEquipmentThatisAvailable() {
		return equipmentThatisAvailable;
	}

	public void setEquipmentThatisAvailable(List<Map<String, Object>> equipmentThatisAvailable) {
		this.equipmentThatisAvailable = equipmentThatisAvailable;
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

	@Override
	public void validate() {
		super.validate();
		if ((null == equipmentNumber) && (null == equipmentType) && (null == equipmentSubType)) {
			addActionError("Returning all Equipment as there were no filters applied.");
			System.out.println("SDB-Error occurred validating SearchEquipmentAction");
		}
	}
}
