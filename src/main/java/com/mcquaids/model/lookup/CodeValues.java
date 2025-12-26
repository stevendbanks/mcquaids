/**
 * 
 */
package com.mcquaids.model.lookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcquaids.service.CodeValueService;

/**
 * 
 */
public class CodeValues { // extends CodeTypes {
	
	private static HashMap<String, String> provinces = new HashMap<String, String>();	
	private static HashMap<String, String> equipmentTypes = new HashMap<String, String>();	
	private static HashMap<String, String> trailerTypes = new HashMap<String, String>();	
	private static HashMap<String, String> flatbedTypes = new HashMap<String, String>();	
	private static HashMap<String, String> forkliftTypes = new HashMap<String, String>();	

	private static HashMap<String, String> availabilityStatuses = new HashMap<String, String>();	
	private static HashMap<String, String> conditionStatuses = new HashMap<String, String>();	
	private static HashMap<String, String> maintenanceStatuses = new HashMap<String, String>();	
	private static HashMap<String, String> cleaningStatuses = new HashMap<String, String>();	
	private static HashMap<String, String> leaseStatuses = new HashMap<String, String>();	
	private static HashMap<String, String> leaseTerminationReasonCodes = new HashMap<String, String>();	

	private static HashMap<String, String> trailerLengths = new HashMap<String, String>();	
	
	private static HashMap<String, String> doorLocations = new HashMap<String, String>();	
	private static HashMap<String, String> doorTypes = new HashMap<String, String>();	
	
	private static HashMap<String, String> floorTypes = new HashMap<String, String>();	
	private static HashMap<String, String> axelTypes = new HashMap<String, String>();	
	
	
	private static HashMap<String, String> containerTypes = new HashMap<String, String>();	
	private static HashMap<String, String> containerSizes = new HashMap<String, String>();	
	private static HashMap<String, String> containerDoors = new HashMap<String, String>();	
	
	private static HashMap<String, String> reservationStatuses = new HashMap<String, String>();	
	
	
	
	

	
	private static  HashMap<String, HashMap<String, String>> masterMap = new HashMap<>();
	private HashMap<String, String> equipmentSubTypes = new HashMap<String, String>();		
	

	static {
		CodeValueService codeValueService = new CodeValueService();
		
		queryCodeValues(codeValueService, reservationStatuses, "Reservation Status"); // Looks up the Codetype table  EnglishDescription name in DB

		
		queryCodeValues(codeValueService, provinces, "Province");
		queryCodeTypes(codeValueService, equipmentTypes, "yes");
		queryCodeValues(codeValueService, trailerTypes, "Trailer");
		queryCodeValues(codeValueService, flatbedTypes, "Flatbed");
		queryCodeValues(codeValueService, forkliftTypes, "Forklift");
//		queryCodeValues(codeValueService, containerTypes, "Container");
		queryCodeValues(codeValueService, containerTypes, "Container");
		queryCodeValues(codeValueService, containerSizes, "Container Sizes");
		queryCodeValues(codeValueService, containerDoors, "container Doors");

		queryCodeValues(codeValueService, availabilityStatuses, "Availability Status");
		queryCodeValues(codeValueService, conditionStatuses, "Condition Status");
		queryCodeValues(codeValueService, maintenanceStatuses, "Maintenance Status");
		queryCodeValues(codeValueService, cleaningStatuses, "Cleaning Status");
		queryCodeValues(codeValueService, leaseStatuses, "Lease Status");
		queryCodeValues(codeValueService, trailerLengths, "Trailer Lengths");
		queryCodeValues(codeValueService, doorLocations, "Door Locations");
		queryCodeValues(codeValueService, doorTypes, "Door Types");

		queryCodeValues(codeValueService, floorTypes, "Floor Types");
		queryCodeValues(codeValueService, axelTypes, "Axel Types");

		
		
		queryCodeValues(codeValueService, leaseTerminationReasonCodes, "Lease Termination Reason Code");
		
		
		
		
		
		
		// These values are put into a masterMap so that the correct Select values are displayed in the equipment subform.
		masterMap.put("trailerTypes", trailerTypes);
		masterMap.put("flatbedTypes", flatbedTypes);
		masterMap.put("forkliftTypes", forkliftTypes);
		masterMap.put("containerTypes", containerTypes);
    }

	/**
	 * @return the leaseTerminationReasonCodes
	 */
	public  HashMap<String, String> getLeaseTerminationReasonCodes() {
		return leaseTerminationReasonCodes;
	}


	/**
	 * @param codeValueService
	 * @param hashmap
	 * @param pCodeTypeDescription - The CodeType English Description.
	 */
	private static void queryCodeValues(CodeValueService codeValueService, HashMap<String, String> hashmap, String pCodeTypeDescription) {
		List<CodeValue> x = codeValueService.findCodeValues(pCodeTypeDescription);
		for (CodeValue cv : x) {
			hashmap.put(cv.getCodeValue(), cv.getEnglishDescription());
		}
	}
	
	
	private static void queryCodeTypes(CodeValueService codeValueService,  HashMap<String, String> hashmap, String isTypeOfEquipment) {
		
		List<CodeValue> x = codeValueService.findCodeTypes(isTypeOfEquipment);
		for (CodeValue cv : x) {
			hashmap.put(cv.getCodeValue(), cv.getEnglishDescription());
		}
	}



	/**
	 * 
	 */
	public CodeValues() {
		super();
	}


    public Map<String, String> getProvinces() {
        return provinces;
    }	
	

	/**
	 * @return the bookingStatuses
	 */
	public  HashMap<String, String> getAvailabilityStatuses() {
		return availabilityStatuses;
	}
    
    
    /**
	 * @return the conditionStatuses
	 */
	public  HashMap<String, String> getConditionStatuses() {
		return conditionStatuses;
	}

	  /**
		 * @return the conditionStatuses
		 */
		public  HashMap<String, String> getMaintenanceStatuses() {
			return maintenanceStatuses;
		}


	/**
	 * @return the cleaningStatuses
	 */
	public  HashMap<String, String> getCleaningStatuses() {
		return cleaningStatuses;
	}


	/**
	 * @return the bookingStatuses
	 */
	public  HashMap<String, String> getLeaseStatuses() {
		return leaseStatuses;
	}





	public Map<String, String> getEquipmentTypes() {
        return equipmentTypes;
    }	
    

    public Map<String, String> getFlatbedTypes() {
        return flatbedTypes;
    }	
    

    public Map<String, String> getTrailerTypes() {
        return trailerTypes;
    }	
    
    public Map<String, String> getForkliftTypes() {
        return forkliftTypes;
    }	
 
    public Map<String, String> getContainerTypes() {
        return containerTypes;
    }	  
    
    public Map<String, String> getContainerSizes() {
        return containerSizes;
    }	  
 
    public Map<String, String> getContainerDoors() {
        return containerDoors;
    }	  
    
    
    public Map<String, String> getTrailerLengths() {
        return trailerLengths;
    }	     
    
    public Map<String, String> getDoorLocations() {
        return doorLocations;
    }	      


    public Map<String, String> getDoortypes() {
        return doorTypes;
    }	      

    public Map<String, String> getFloorTypes() {
        return floorTypes;
    }	      

    public Map<String, String> getAxelTypes() {
        return axelTypes;
    }	      

     
    public Map<String, String> getReservationStatuses() {
        return reservationStatuses;
    }	
    
    

	/**
	 * @return the equipmentSubTypes
	 */
	public HashMap<String, String> getEquipmentSubTypes() {
		return equipmentSubTypes;
	}


	/**
	 * @param equipmentSubTypes the equipmentSubTypes to set
	 */
	public void setEquipmentSubTypes(int pEquipmentTypeCode) {

		switch(pEquipmentTypeCode) {
		  case 1002:
			  this.equipmentSubTypes = masterMap.get("trailerTypes");
		    break;
		  case 1003:
			  this.equipmentSubTypes = masterMap.get("flatbedTypes");
		    break;
		  case 1004:
			  this.equipmentSubTypes = masterMap.get("containerTypes");
		    break;
		  case 1005:
			  this.equipmentSubTypes = masterMap.get("forkliftTypes");
		    break;
		  default:
		    // code block
		}

	}

    
    public Map<String, String> getEquipmentSubtypes() {
        return this.equipmentSubTypes;
    }
    
	/**
	 * @param equipmentSubTypes the equipmentSubTypes to set
	 * @return 
	 */
	public HashMap<String, String> getEquipmentSubTypes(int pEquipmentTypeCode) {

		switch(pEquipmentTypeCode) {
		  case 1002:
			  return masterMap.get("trailerTypes");
		  case 1003:
			  return masterMap.get("flatbedTypes");
		  case 1004:
			  return masterMap.get("containerTypes");
		  case 1005:
			  return masterMap.get("forkliftTypes");
		  default:
		    return null;
		}

	}


	public String getEquipmentSubtypeAsString(String x) {
		return equipmentSubTypes.get(x);
	}    
    
	


 

}
