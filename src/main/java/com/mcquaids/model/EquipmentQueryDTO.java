package com.mcquaids.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import com.mcquaids.utils.JsonUtils;

public class EquipmentQueryDTO extends Equipment {

    private static final long serialVersionUID = 1L;

    private String equipmentTypeText;
    private String derivedAvailabilityStatus;
    private String conditionStatusText;
    private String maintenanceStatusText;
    private String cleaningStatusText;
    private String leasedEquipmentNotes;

    public String getEquipmentTypeText() {
        return equipmentTypeText;
    }

    public void setEquipmentTypeText(String equipmentTypeText) {
        this.equipmentTypeText = equipmentTypeText;
    }

    /**
	 * @return the derivedAvailabilityStatus
	 */
	public String getDerivedAvailabilityStatus() {
		return derivedAvailabilityStatus;
	}

	/**
	 * @param derivedAvailabilityStatus the derivedAvailabilityStatus to set
	 */
	public void setDerivedAvailabilityStatus(String derivedAvailabilityStatus) {
		this.derivedAvailabilityStatus = derivedAvailabilityStatus;
	}

	public String getConditionStatusText() {
        return conditionStatusText;
    }

    public void setConditionStatusText(String conditionStatusText) {
        this.conditionStatusText = conditionStatusText;
    }

    public String getMaintenanceStatusText() {
        return maintenanceStatusText;
    }

    public void setMaintenanceStatusText(String maintenanceStatusText) {
        this.maintenanceStatusText = maintenanceStatusText;
    }

    public String getCleaningStatusText() {
        return cleaningStatusText;
    }

    public void setCleaningStatusText(String cleaningStatusText) {
        this.cleaningStatusText = cleaningStatusText;
    }

    public String getLeasedEquipmentNotes() {
        return leasedEquipmentNotes;
    }

    public void setLeasedEquipmentNotes(String leasedEquipmentNotes) {
        this.leasedEquipmentNotes = leasedEquipmentNotes;
    }

    public static EquipmentQueryDTO fromResultSet(ResultSet rs) throws SQLException {
        EquipmentQueryDTO dto = new EquipmentQueryDTO();

        dto.setEquipmentNumber(rs.getString("EquipmentNumber"));
        dto.setEquipmentType(rs.getInt("EquipmentType"));
        dto.setEquipmentSubType(rs.getString("EquipmentSubType"));
        dto.setSerialNumber(rs.getString("SerialNumber"));
        dto.setManufacturer(rs.getString("Manufacturer"));

        dto.setConditionStatusText(rs.getString("conditionStatusText"));
        dto.setMaintenanceStatusText(rs.getString("maintenanceStatusText"));
        dto.setCleaningStatusText(rs.getString("cleaningStatusText"));

        dto.setEquipmentTypeText(rs.getString("equipmentTypeText"));
        dto.setEquipmentSubTypeText(rs.getString("equipmentSubTypeText"));
        
        dto.setDerivedAvailabilityStatus(rs.getString("DerivedAvailabilityStatus"));

        java.sql.Date manufacturedDate = rs.getDate("ManufacturedDate");
        if (manufacturedDate != null) {
            dto.setManufacturedDate(new Date(manufacturedDate.getTime()));
        }

        java.sql.Date purchaseDate = rs.getDate("PurchaseDate");
        if (purchaseDate != null) {
            dto.setPurchaseDate(new Date(purchaseDate.getTime()));
        }

        dto.setPurchasePrice(rs.getDouble("PurchasePrice"));
        dto.setSpecialNotes(rs.getString("SpecialNotes"));

        java.sql.Date inspectionDate = rs.getDate("InspectionDate");
        if (inspectionDate != null) {
            dto.setInspectionDate(new Date(inspectionDate.getTime()));
        }

        dto.setConditionStatusCode(rs.getString("ConditionStatusCode"));
        dto.setMaintenanceStatusCode(rs.getString("MaintenanceStatusCode"));
        dto.setCleaningStatusCode(rs.getString("CleaningStatusCode"));

//        dto.setLeasedEquipmentNotes(rs.getString("leasedEquipmentNotes"));

        String propertiesJson = rs.getString("Properties");
        if (propertiesJson != null) {
            dto.setProperties(JsonUtils.setPropertiesFromJson(propertiesJson));
        } else {
            dto.setProperties(new HashMap<>());
        }

        dto.setAvailable(rs.getBoolean("Available"));
        dto.setSafetyStatusCode(rs.getString("SafetyStatusCode"));

        long yardId = rs.getLong("PreferredYardID");
        dto.setPreferredYardId(rs.wasNull() ? null : yardId);

        return dto;
    }
}
