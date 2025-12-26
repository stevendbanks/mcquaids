package com.mcquaids.actions.equipment;

import java.io.Serializable;
import java.util.Map;

import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.EquipmentService;


 public class SaveEquipmentAction	extends BaseEquipmentAction  implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String equipmentSaveActionType;



	public SaveEquipmentAction() {
		super();
	}	
	
	
	public String execute() {
//		System.out.println("SDBANKS-Entered SaveEquipmentAction");
		codeValues = new CodeValues();
		codeValues.setEquipmentSubTypes(equipment.getEquipmentType());
		
//		Map<String, Object> x = equipment.getProperties();
//        // Iterate and print each key-value pair
//		System.out.println("SDB-properties=");
//        // Iterate and print each key-value pair with proper type handling
//        for (Map.Entry<String, Object> entry : x.entrySet()) {
//            Object value = entry.getValue();
//            if (value instanceof String) {
//                System.out.println("Key: " + entry.getKey() + ", Value: " + value);
//            } else if (value instanceof String[]) {
//                String[] valueArray = (String[]) value;
//                System.out.println("Key: " + entry.getKey() + ", Value: " + String.join(", ", valueArray));
//            } else {
//                System.out.println("Key: " + entry.getKey() + ", Value: " + value.toString());
//            }
//        }		

		
		EquipmentService equipmentService = new EquipmentService();
		if (equipmentSaveActionType.equals("SaveNew")) {
			equipmentService.saveNewEquipment(equipment);
		} else {
			equipmentService.updateEquipment(equipment);
		}

		editable = true;
		setJSPTitle("Edit");
	    addActionMessage("Equipment Asset was was Added/Updated successfully");
		return redirectBasedOnEquipmentType(equipment.getEquipmentType());

	}

	/**
	 * @return the trailerSaveActionType
	 */
	public String getEquipmentSaveActionType() {
		return equipmentSaveActionType;
	}
	/**
	 * @param trailerSaveActionType the trailerSaveActionType to set
	 */
	public void setEquipmentSaveActionType(String trailerSaveActionType) {
		this.equipmentSaveActionType = trailerSaveActionType;
	}

	

	
}
