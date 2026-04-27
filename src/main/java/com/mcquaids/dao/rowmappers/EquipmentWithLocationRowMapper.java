package com.mcquaids.dao.rowmappers;

import com.mcquaids.model.EquipmentWithLocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentWithLocationRowMapper implements RowMapper<EquipmentWithLocation> {

    @Override
    public EquipmentWithLocation mapRow(ResultSet rs, int rowNum) throws SQLException {

    
        EquipmentWithLocation e = new EquipmentWithLocation();
        
        try {

        e.setEquipmentNumber(rs.getInt("EquipmentNumber"));
        e.setEquipmentType(rs.getInt("EquipmentType"));
        e.setEquipmentTypeText(rs.getString("EquipmentTypeText"));
        e.setEquipmentSubType(rs.getString("EquipmentSubType"));
        e.setEquipmentSubTypeText(rs.getString("EquipmentSubTypeText")); 

        e.setSerialNumber(rs.getString("SerialNumber"));
        e.setManufacturer(rs.getString("Manufacturer"));
        e.setManufacturedDate(rs.getString("ManufacturedDate"));
        e.setPurchasePrice(rs.getString("PurchasePrice"));
        e.setPurchaseDate(rs.getString("PurchaseDate"));
        e.setSpecialNotes(rs.getString("SpecialNotes"));

        e.setInspectionDate(rs.getString("InspectionDate"));
        e.setProperties(rs.getString("Properties"));

        e.setAvailable(rs.getBoolean("Available"));
        e.setConditionStatusCode(rs.getString("ConditionStatusCode"));
        e.setMaintenanceStatusCode(rs.getString("MaintenanceStatusCode"));
        e.setCleaningStatusCode(rs.getString("CleaningStatusCode"));
        e.setDerivedAvailabilityStatus(rs.getString("DerivedAvailabilityStatus"));

        e.setLocationType(rs.getString("LocationType"));
        e.setStreet(rs.getString("Street"));
        e.setCity(rs.getString("City"));
        e.setProvince(rs.getString("Province"));
        e.setPostal(rs.getString("Postal"));
        e.setCountry(rs.getString("Country"));
        e.setYardId(rs.getLong("YardID"));

        e.setHistoryStart(rs.getString("HistoryStart"));
        e.setHistoryEnd(rs.getString("HistoryEnd"));

        e.setLocationReservationId(rs.getLong("LocationReservationID"));
        e.setLocationActionId(rs.getLong("LocationActionID"));
        e.setLocationActionType(rs.getString("LocationActionType"));
        e.setLocationNotes(rs.getString("LocationNotes"));
        } catch(Exception ex) {
        	ex.printStackTrace();
        }

        return e;
    }
}
