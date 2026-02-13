package com.mcquaids.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.LeaseDAO;
import com.mcquaids.dao.LeaseEquipmentDAO;
import com.mcquaids.model.Constants;
import com.mcquaids.model.Equipment;
import com.mcquaids.model.Lease;
import com.mcquaids.model.LeasedEquipment;
import com.mcquaids.model.LeaseQueryDTO;
import com.mcquaids.model.LeasedEquipmentView;
import com.mcquaids.model.lookup.CodeValue;

public class LeaseService {
    private LeaseDAO leaseDAO;
    private LeaseEquipmentDAO leaseEquipmentDAO;
    private EquipmentService equipmentService = new EquipmentService();
    private CodeValueService codeValueService = new CodeValueService();
    
    private String errorMessage;

    public LeaseService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.leaseDAO = new LeaseDAO(jdbcTemplate);
        this.leaseEquipmentDAO = new LeaseEquipmentDAO(jdbcTemplate);
    }

    // Create Lease
    public void createLease(Lease lease) {
        leaseDAO.createLease(lease);
    }

    // Get Lease by LeaseID
    public Lease getLease(String pLeaseID) {
    	System.out.println("SDB leaseService=leaseID=" + pLeaseID);
        return leaseDAO.getLease(pLeaseID);
    }

    // Update Lease
    public void updateLease(Lease lease) {
        leaseDAO.updateLease(lease);
    }

    // Delete Lease
    public void deleteLease(String pLeaseID, String pCustomerID) {
        leaseDAO.deleteLease(pLeaseID, pCustomerID);
    }

    // Get all LeasedEquipment by LeaseID
    public List<LeasedEquipment> getAllLeasedEquipmentByLeaseId(String pLeaseID) {
        return leaseEquipmentDAO.findAllLeasedEquipmentByLeaseID(pLeaseID);
    }
    
    
    public  List<LeasedEquipmentView>  getLeasedEquipmentViewByLeaseID (String pLeaseID) {
        return leaseEquipmentDAO.getLeasedEquipmentViewByLeaseID(pLeaseID);
    }
    
    

    // Get all Leases for customerID
    public List<Lease> getAllLeasesForCustomerId(String pCustomerID) {
        return leaseDAO.getAllLeasesForCustomerID(pCustomerID);
    }
    
    
    // Add LeasedEquipment to a Lease
    public LeasedEquipmentView addEquipmentToLease(String leaseID, Integer equipmentNumber, String notes) {
    	
        try {
        	//  Check if the piece of equipment is available for Lease.
        	Equipment equipment = equipmentService.edit(equipmentNumber);
        	if (null == equipment) { 
        		this.errorMessage = "Error- Equipment Number {"  + equipmentNumber  +"} does not exist";
        		return null;
        	}
        	
        	if (!equipment.getAvailabilityStatusCode().equals(Constants.EQUIPMENT_AVAILABLE_TO_LEASE)) { 
        		CodeValue cv = codeValueService.findCodeValue(Constants.EQUIPMENT_AVAILABILTY_CODE_TYPES, equipment.getAvailabilityStatusCode());
        		this.errorMessage = "Error- Equipment Number {"  + equipmentNumber  +"} is not available to Lease. Availability Status Code = " + cv.getEnglishDescription();
        		return null;
        	}
        	
        	
        	leaseEquipmentDAO.createLeasedEquipment(leaseID, equipmentNumber, notes );
        	
        	// Set the equipment availability as rented
        	equipment.setAvailabilityStatusCode(Constants.EQUIPMENT_AVAILABILITY_RENTED);
        	equipmentService.updateEquipment(equipment);
        	
			LeasedEquipmentView leasedEquipmentView = leaseEquipmentDAO.editLeasedEquipmentView(leaseID, equipmentNumber);
			return leasedEquipmentView;
		} catch (DuplicateKeyException e) {
			errorMessage = "Error- Equipment is already on this Lease {leaseID=" + leaseID + "; equipmentNumber=" + equipmentNumber + "}";
		}
		return null;
    }
    

	public LeasedEquipmentView editLeasedEquipment(String pLeaseID, Integer pEquipmentNumber) {
		
		LeasedEquipmentView x =  leaseEquipmentDAO.editLeasedEquipmentView(pLeaseID, pEquipmentNumber); 
		if (null == x) {
			System.out.println("LeasedEquipmentView = NULL");
		}
		return x;
	}    

	public LeasedEquipmentView getLeasedEquipmentDetails(String pEquipmentNumber) {
		
		LeasedEquipmentView x =  leaseEquipmentDAO.getLeasedEquipmentDetails(pEquipmentNumber);
		if (null == x) {
			System.out.println("LeasedEquipmentView = NULL");
		}
		return x;
	}    
	
	
	
	
	public boolean updateLeasedEquipment(LeasedEquipment pLeasedEquipment) {
		 System.out.println("ENTERED LeaseService.updateLeasedEquipment()");
		/// System.out.println(pLeasedEquipment.toString());
		 System.out.println("ENTERED LeaseService.updateLeasedEquipment() xxx");
		 return leaseEquipmentDAO.updateLeasedEquipment(pLeasedEquipment);
	}  	
	
    

    // Remove LeasedEquipment from a Lease
    public void removeEquipmentFromLease(String pLeaseID, String equipmentNumber) {
        leaseEquipmentDAO.deleteLeasedEquipment(pLeaseID, equipmentNumber);
    }

	
	public List<LeaseQueryDTO> queryLease(String pLeaseID,String pCustomerID,
			String pLeaseStatusCode) {	
		List<LeaseQueryDTO> leaseQueryDTOs = leaseDAO.getLeaseDetails(pLeaseID, pCustomerID );
		
		for (LeaseQueryDTO leaseQueryDTO : leaseQueryDTOs) {
		    System.out.println(leaseQueryDTO.getLeaseStatusDescription());
		}
		
		return leaseQueryDTOs;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean updateLeasedEquipmentAdditionalNote(String leaseID, Integer equipmentNumber, String notes) {
		 return leaseEquipmentDAO.updateLeasedEquipmentAdditionalNote(leaseID, equipmentNumber, notes); 
	}




	
	
}
