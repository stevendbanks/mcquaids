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

	private static HashMap<String, String> moveTypes = new HashMap<String, String>();	

	private static HashMap<String, String> movementStatuses = new HashMap<String, String>();	
	
	

	
	private static  HashMap<String, HashMap<String, String>> masterMap = new HashMap<>();
	private HashMap<String, String> equipmentSubTypes = new HashMap<String, String>();		
	

	static {
		CodeValueService codeValueService = new CodeValueService();
		
		queryLookupTable(codeValueService, reservationStatuses, "lkp_reservationstatus"); // Looks up the Codetype table  EnglishDescription name in DB

		
		queryLookupTable(codeValueService, provinces, "lkp_provinces");
		queryLookupTable(codeValueService, equipmentTypes, "lkp_equipmenttypes");
		queryLookupTable(codeValueService, trailerTypes, "lkp_trailersubtypes");
		queryLookupTable(codeValueService, flatbedTypes, "lkp_flatbedsubtypes");
		queryLookupTable(codeValueService, forkliftTypes, "lkp_forkliftsubtypes");

		queryLookupTable(codeValueService, containerTypes, "lkp_containersubtypes");
		queryLookupTable(codeValueService, containerSizes, "lkp_containersizes");
		queryLookupTable(codeValueService, containerDoors, "lkp_containerdoors");

		queryLookupTable(codeValueService, availabilityStatuses, "lkp_availabilitystatuses");
		queryLookupTable(codeValueService, conditionStatuses, "lkp_conditionstatuses");
		queryLookupTable(codeValueService, maintenanceStatuses, "lkp_maintenancestatuses");
		queryLookupTable(codeValueService, cleaningStatuses, "lkp_cleaningstatuses");
		queryLookupTable(codeValueService, leaseStatuses, "lkp_leasestatuses");
		queryLookupTable(codeValueService, trailerLengths, "lkp_trailerlengths");
		queryLookupTable(codeValueService, doorLocations, "lkp_doorlocations");
		queryLookupTable(codeValueService, doorTypes, "lkp_doortypes");
		queryLookupTable(codeValueService, moveTypes, "lkp_movetypes");

		movementStatuses.put("NEW", "New");
		movementStatuses.put("CONFIRMED", "Confirmed");
		movementStatuses.put("IN_PROGRESS", "In Progress");
		movementStatuses.put("COMPLETED", "Completed");
		movementStatuses.put("CANCELLED", "Cancelled");

		
		queryLookupTable(codeValueService, floorTypes, "lkp_floortypes");
		queryLookupTable(codeValueService, axelTypes, "lkp_axeltypes");

		
		
		
		queryLookupTable(codeValueService, leaseTerminationReasonCodes, "lkp_leaseterminationreasoncodes");
		
		
		
		
		
		
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

	
	private static void queryLookupTable(CodeValueService codeValueService,  HashMap<String, String> hashmap, String pCodeTypeTableName) {
		
		List<CodeValue> x = codeValueService.queryLookupTable(pCodeTypeTableName);
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
    
    public Map<String, String> getMoveTypes() {
        return moveTypes;
    }	


    public Map<String, String> getMovementStatuses() {
        return movementStatuses;
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
		  case 2:
			  this.equipmentSubTypes = masterMap.get("trailerTypes");
		    break;
		  case 3:
			  this.equipmentSubTypes = masterMap.get("flatbedTypes");
		    break;
		  case 4:
			  this.equipmentSubTypes = masterMap.get("containerTypes");
		    break;
		  case 5:
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
		  case 2:
			  return masterMap.get("trailerTypes");
		  case 3:
			  return masterMap.get("flatbedTypes");
		  case 4:
			  return masterMap.get("containerTypes");
		  case 5:
			  return masterMap.get("forkliftTypes");
		  default:
		    return null;
		}

	}


	public String getEquipmentSubtypeAsString(String x) {
		return equipmentSubTypes.get(x);
	}    
    
	
	public static String getKeyValue(String key, String value) {
	    Map<String, String> map = null;

	    switch (key) {
	        case "province":
	            map = provinces;
	            break;
	        case "equipmentType":
	            map = equipmentTypes;
	            break;
	        case "trailerType":
	            map = trailerTypes;
	            break;
	        case "flatbedType":
	            map = flatbedTypes;
	            break;
	        case "forkliftType":
	            map = forkliftTypes;
	            break;

	        case "availabilityStatus":
	            map = availabilityStatuses;
	            break;
	        case "conditionStatus":
	            map = conditionStatuses;
	            break;
	        case "maintenanceStatus":
	            map = maintenanceStatuses;
	            break;
	        case "cleaningStatus":
	            map = cleaningStatuses;
	            break;
	        case "leaseStatus":
	            map = leaseStatuses;
	            break;
	        case "reservationStatus":
	            map = reservationStatuses;
	            break;

	        // ⭐ NEW CASES — these match your JSON keys
	        case "axel":
	            map = axelTypes;
	            break;
	        case "doorType":
	            map = doorTypes;
	            break;
	        case "doorLocation":
	            map = doorLocations;
	            break;
	        case "floor":
	            map = floorTypes;
	            break;

	        case "moveType":
	            map = moveTypes;
	            break;
	            
	        case "movementStatus":
	            map = movementStatuses;
	            break;

	            
	            
	        default:
	            return value;
	    }

	    if (map == null) return value;
	    return map.getOrDefault(value, value);
	}


	public  String getEquipmentSubTypeText(int equipmentTypeValue, String equipmentSubTypeValue) {
		HashMap<String, String> x = getEquipmentSubTypes(equipmentTypeValue);
		
		return x.get(equipmentSubTypeValue);
	}

 

}
