package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.EquipmentQueryDTO;
import com.mcquaids.utils.JsonUtils;

public class EquipmentQueryDTORowMapper implements RowMapper<EquipmentQueryDTO> {

  
	public EquipmentQueryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        EquipmentQueryDTO equipmentQueryDTO = new EquipmentQueryDTO();



        // Then, map the additional fields in EquipmentQueryDTO
        try {
        	equipmentQueryDTO.setEquipmentNumber(rs.getString("EquipmentNumber"));
        	equipmentQueryDTO.setEquipmentType(rs.getInt("EquipmentType"));
        	equipmentQueryDTO.setEquipmentSubType(rs.getString("EquipmentSubType"));
        	equipmentQueryDTO.setSerialNumber(rs.getString("SerialNumber"));
        	equipmentQueryDTO.setManufacturer(rs.getString("Manufacturer"));

            equipmentQueryDTO.setAvailabilityStatusText(rs.getString("AvailabilityStatusText"));
            equipmentQueryDTO.setConditionStatusText(rs.getString("ConditionStatusText"));
            equipmentQueryDTO.setMaintenanceStatusText(rs.getString("MaintenanceStatusText"));
            equipmentQueryDTO.setEquipmentTypeText(rs.getString("EquipmentTypeText"));
            equipmentQueryDTO.setEquipmentSubTypeText(rs.getString("EquipmentSubTypeText"));
//            equipmentQueryDTO.setBookingStatusCodeText(rs.getString("BookingStatusCodeText"));
        	
        	 
        	
        	
            // Handle null values and convert java.sql.Date to java.util.Date
            // Handle null values and convert java.sql.Date to java.util.Date
            java.sql.Date manufacturedDate = rs.getDate("ManufacturedDate");
            if (manufacturedDate != null) {
            	equipmentQueryDTO.setManufacturedDate(new Date(manufacturedDate.getTime()));
            }

            java.sql.Date purchaseDate = rs.getDate("PurchaseDate");
            if (purchaseDate != null) {
            	equipmentQueryDTO.setPurchaseDate(new Date(purchaseDate.getTime()));
            }

            equipmentQueryDTO.setPurchasePrice(rs.getDouble("PurchasePrice"));
            equipmentQueryDTO.setSpecialNotes(rs.getString("SpecialNotes"));

         // Handle null values and convert java.sql.Date to java.util.Date
             java.sql.Date inspectionDate = rs.getDate("InspectionDate");
             if (inspectionDate != null) {
            	 equipmentQueryDTO.setInspectionDate(new Date(inspectionDate.getTime()));
             }

             equipmentQueryDTO.setAvailabilityStatusCode(rs.getString("AvailabilityStatusCode"));
             equipmentQueryDTO.setConditionStatusCode(rs.getString("ConditionStatusCode"));
             equipmentQueryDTO.setMaintenanceStatusCode(rs.getString("MaintenanceStatusCode"));
             equipmentQueryDTO.setCleaningStatusCode(rs.getString("CleaningStatusCode"));
             equipmentQueryDTO.setBookingStatusCode(rs.getString("BookingStatusCode"));
             
             equipmentQueryDTO.setLeasedEquipmentNotes(rs.getString("leasedEquipmentNotes"));
             
//             Convert JSON string to Map if needed
             String propertiesJson = rs.getString("Properties");
             if (propertiesJson != null) {
            	 equipmentQueryDTO.setProperties(JsonUtils.setPropertiesFromJson(propertiesJson)); 
             } else {
            	 equipmentQueryDTO.setProperties(new HashMap<>());
             }
        	
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return equipmentQueryDTO;
    }
}
