package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.Equipment;
import com.mcquaids.utils.JsonUtils;

public class EquipmentRowMapper implements RowMapper<Equipment> {

    protected Equipment equipment = new Equipment();

	
	@Override
    public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            equipment.setEquipmentNumber(rs.getString("EquipmentNumber"));
            equipment.setEquipmentType(rs.getInt("EquipmentType"));
            equipment.setEquipmentSubType(rs.getString("EquipmentSubType"));
            equipment.setEquipmentSubTypeText(rs.getString("equipmentSubTypeText"));
            equipment.setSerialNumber(rs.getString("SerialNumber"));
            equipment.setManufacturer(rs.getString("Manufacturer"));

            // Handle null values and convert java.sql.Date to java.util.Date
            java.sql.Date manufacturedDate = rs.getDate("ManufacturedDate");
            if (manufacturedDate != null) {
                equipment.setManufacturedDate(new Date(manufacturedDate.getTime()));
            }

            java.sql.Date purchaseDate = rs.getDate("PurchaseDate");
            if (purchaseDate != null) {
                equipment.setPurchaseDate(new Date(purchaseDate.getTime()));
            }

            equipment.setPurchasePrice(rs.getDouble("PurchasePrice"));
            equipment.setSpecialNotes(rs.getString("SpecialNotes"));

         // Handle null values and convert java.sql.Date to java.util.Date
             java.sql.Date inspectionDate = rs.getDate("InspectionDate");
             if (inspectionDate != null) {
                 equipment.setInspectionDate(new Date(inspectionDate.getTime()));
             }

            equipment.setAvailabilityStatusCode(rs.getString("AvailabilityStatusCode"));
            equipment.setConditionStatusCode(rs.getString("ConditionStatusCode"));
            equipment.setMaintenanceStatusCode(rs.getString("MaintenanceStatusCode"));
            equipment.setCleaningStatusCode(rs.getString("CleaningStatusCode"));
            equipment.setBookingStatusCode(rs.getString("BookingStatusCode"));

//             Convert JSON string to Map if needed
             String propertiesJson = rs.getString("Properties");
             if (propertiesJson != null) {
                 equipment.setProperties(JsonUtils.setPropertiesFromJson(propertiesJson)); 
             } else {
                 equipment.setProperties(new HashMap<>());
             }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return equipment;
    }
    

	/**
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}


	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}    
    
    
    
}
