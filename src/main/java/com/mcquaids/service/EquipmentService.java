package com.mcquaids.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.EquipmentDAO;
import com.mcquaids.model.Equipment;
import com.mcquaids.model.EquipmentQueryDTO;
import com.mcquaids.model.Flatbed;
import com.mcquaids.model.Forklift;
import com.mcquaids.model.ShippingContainer;
import com.mcquaids.utils.PropertyHydrator;

public class EquipmentService {
    private EquipmentDAO equipmentDAO;

	private Map<String, String> errors = new HashMap<>();	
	


    public EquipmentService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.equipmentDAO = new EquipmentDAO(jdbcTemplate);
    }

	public Equipment edit(Integer pEquipmentNumber) {
   	    Equipment equipment = equipmentDAO.findByEquipmentNumber(pEquipmentNumber);
   	    return equipment;
   	    
	}

	public List<EquipmentQueryDTO> queryEquipmentByEquipmentNUmber(Integer equipmentNumber) {
			return equipmentDAO.queryEquipmentByEquipmentNUmber(equipmentNumber);  
   }

	public List<EquipmentQueryDTO> queryEquipment(Integer pEquipmentType, String pEquipmentSubType, String availabilityStatusCode,
			String conditionStatusCode, String maintenanceStatusCode, String cleaningStatusCode, String bookingStatusCode) {
 
	    if (pEquipmentType == null) {
   	        errors.put("Equipment Type","Equipment Type is a required Field");
   	        return null;
   	   } 

	    return equipmentDAO.queryEquipment(pEquipmentType, pEquipmentSubType, availabilityStatusCode, conditionStatusCode, maintenanceStatusCode, cleaningStatusCode, bookingStatusCode);
  
	
	}
	
	
	public List<Map<String, Object>> queryEquipmentisAvailable(Integer pEquipmentType, String pEquipmentSubType,
			String pCconditionStatusCode, String pMaintenanceStatusCode) {
		
	    if (pEquipmentType == null) {
   	        errors.put("Equipment Type","Equipment Type is a required Field");
   	        return null;
   	   } 

	   return equipmentDAO.queryEquipmentisAvailable(pEquipmentType, pEquipmentSubType, pCconditionStatusCode, pMaintenanceStatusCode);         
		
	}
	
	
	
    public List<Map<String, Object>> getEquipmentReport() {	
    	return equipmentDAO.getEquipmentReport();
    }
	
    public List<Map<String, Object>> getEquipmentInspectionReport(int daysToExpiry) {	
    	return equipmentDAO.getEquipmentInspectionReport(daysToExpiry);
    }    
    

	public void updateEquipment(Equipment pEquipment) {
		equipmentDAO.updateEquipment(pEquipment);
	}	
	
	public void saveNewEquipment(Equipment pEquipment) {
		equipmentDAO.addEquipment(pEquipment);
	}	

	public int saveForklift(Forklift pForklift) {
		return equipmentDAO.saveForklift(pForklift);

	}	

	
	public int saveNewForklift(Forklift pForklift) {
		return equipmentDAO.saveNewForklift(pForklift);
	}	
	
	public int saveFlatbed(Flatbed pFlatbed) {
		return equipmentDAO.saveFlatbed(pFlatbed);

	}	

	public int saveNewFlatbed(Flatbed pFlatbed) {
		return equipmentDAO.saveNewFlatbed(pFlatbed);
	}	


	public int saveContainer(ShippingContainer pContainer) {
		return equipmentDAO.saveContainer(pContainer);

	}	

	public int saveNewContainer(ShippingContainer pContainer) {
		return equipmentDAO.saveNewContainer(pContainer);
	}	

	/**
	 * @return the errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public EquipmentQueryDTO findEquipment(Integer reservedEquipmentID) {
	    EquipmentQueryDTO x = equipmentDAO.findEquipment(reservedEquipmentID);
        x.setProperties(PropertyHydrator.hydrateFromJson(x.getPropertiesAsJson()));
		return x;
	}

	public Long getPreferredYardId(Integer equipmentNumber) {
		return equipmentDAO.getPreferredYardId(equipmentNumber);
	}
	
	
	
    
}
