package com.mcquaids.actions.equipment;

import java.util.List;
import java.util.Map;

import com.mcquaids.model.EquipmentQueryDTO;
import com.opensymphony.xwork2.ActionSupport;

 public class SearchEquipmentByEquipmentNumberAction extends BaseEquipmentAction {

	private static final long serialVersionUID = 1L;

	private List<EquipmentQueryDTO> equipmentDTOs;

	

	/**
	* 
	*/
	public SearchEquipmentByEquipmentNumberAction() { 
		super();
	}

	public String execute() {
		try {
				equipmentDTOs = equipmentService.queryEquipmentByEquipmentNUmber(this.equipmentNumber );
				if (equipmentService.getErrors() != null) {
			        // Check if there are any errors returned from the service
			        if (!equipmentService.getErrors().isEmpty()) {
			            for (Map.Entry<String, String> entry : equipmentService.getErrors().entrySet()) {
			                addActionError(entry.getValue());
			            }
						return "input";  
			        }
				}    
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionError("An Unknown Error has occurred. (" + ex.getMessage() + ")");
			return "Exception";
		}
		
		return ActionSupport.SUCCESS;
	}
	


	

	/**
	 * @return the equipmentDTOs
	 */
	public List<EquipmentQueryDTO> getEquipmentDTOs() {
		return equipmentDTOs;
	}

	/**
	 * @param equipmentDTOs the equipmentDTOs to set
	 */
	public void setEquipmentDTOs(List<EquipmentQueryDTO> equipmentDTOs) {
		this.equipmentDTOs = equipmentDTOs;
	}

}
