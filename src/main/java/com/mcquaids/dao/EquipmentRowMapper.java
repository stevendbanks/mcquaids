package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.Equipment;
import com.mcquaids.utils.JsonUtils;

public class EquipmentRowMapper implements RowMapper<Equipment> {

    @Override
    public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {

        Equipment equipment = new Equipment();

        equipment.setEquipmentNumber(rs.getString("EquipmentNumber"));
        equipment.setEquipmentType(rs.getInt("EquipmentType"));
        equipment.setEquipmentSubType(rs.getString("EquipmentSubType"));

        // Read-only lookup field
        try {
            equipment.setEquipmentSubTypeText(rs.getString("equipmentSubTypeText"));
        } catch (SQLException ignore) {}

        equipment.setSerialNumber(rs.getString("SerialNumber"));
        equipment.setManufacturer(rs.getString("Manufacturer"));

        // Dates
        java.sql.Date manufacturedDate = rs.getDate("ManufacturedDate");
        if (manufacturedDate != null) {
            equipment.setManufacturedDate(new Date(manufacturedDate.getTime()));
        }

        java.sql.Date purchaseDate = rs.getDate("PurchaseDate");
        if (purchaseDate != null) {
            equipment.setPurchaseDate(new Date(purchaseDate.getTime()));
        }

        java.sql.Date inspectionDate = rs.getDate("InspectionDate");
        if (inspectionDate != null) {
            equipment.setInspectionDate(new Date(inspectionDate.getTime()));
        }

        equipment.setPurchasePrice(rs.getDouble("PurchasePrice"));
        equipment.setSpecialNotes(rs.getString("SpecialNotes"));

        equipment.setAvailable(rs.getBoolean("Available"));
        equipment.setConditionStatusCode(rs.getString("ConditionStatusCode"));
        equipment.setMaintenanceStatusCode(rs.getString("MaintenanceStatusCode"));
        equipment.setCleaningStatusCode(rs.getString("CleaningStatusCode"));

        // JSON properties
        String propertiesJson = rs.getString("Properties");
        if (propertiesJson != null) {
            equipment.setProperties(JsonUtils.setPropertiesFromJson(propertiesJson));
        } else {
            equipment.setProperties(new HashMap<>());
        }

        // NEW FIELDS
        try {
            equipment.setAvailable(rs.getBoolean("Available"));
        } catch (SQLException ignore) {}

        try {
            equipment.setSafetyStatusCode(rs.getString("SafetyStatusCode"));
        } catch (SQLException ignore) {}

        try {
            long yardId = rs.getLong("PreferredYardID");
            equipment.setPreferredYardId(rs.wasNull() ? null : yardId);
        } catch (SQLException ignore) {}

        return equipment;
    }
}

