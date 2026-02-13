/**
 * 
 */
package com.mcquaids.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import com.mcquaids.utils.JsonUtils;

/**
 * 
 */
public class EquipmentQueryDTO  extends Equipment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
 
	private String equipmentTypeText;
	private String availabilityStatusText;
	private String conditionStatusText;
	private String maintenanceStatusText;
	private String cleaningStatusText;
//	private String leaseStatusText;
    private String bookingStatusCodeText;
    private String leasedEquipmentNotes;
	/**
	 * @return the equipmentTypeText
	 */
	public String getEquipmentTypeText() {
		return equipmentTypeText;
	}
	/**
	 * @param equipmentTypeText the equipmentTypeText to set
	 */
	public void setEquipmentTypeText(String equipmentTypeText) {
		this.equipmentTypeText = equipmentTypeText;
	}
	/**
	 * @return the availabilityStatusText
	 */
	public String getAvailabilityStatusText() {
		return availabilityStatusText;
	}
	/**
	 * @param availabilityStatusText the availabilityStatusText to set
	 */
	public void setAvailabilityStatusText(String availabilityStatusText) {
		this.availabilityStatusText = availabilityStatusText;
	}
	/**
	 * @return the conditionStatusText
	 */
	public String getConditionStatusText() {
		return conditionStatusText;
	}
	/**
	 * @param conditionStatusText the conditionStatusText to set
	 */
	public void setConditionStatusText(String conditionStatusText) {
		this.conditionStatusText = conditionStatusText;
	}
	/**
	 * @return the maintenanceStatusText
	 */
	public String getMaintenanceStatusText() {
		return maintenanceStatusText;
	}
	/**
	 * @param maintenanceStatusText the maintenanceStatusText to set
	 */
	public void setMaintenanceStatusText(String maintenanceStatusText) {
		this.maintenanceStatusText = maintenanceStatusText;
	}
	/**
	 * @return the cleaningStatusTest
	 */
	public String getCleaningStatusText() {
		return cleaningStatusText;
	}
	/**
	 * @param cleaningStatusTest the cleaningStatusTest to set
	 */
	public void setCleaningStatusText(String cleaningStatusTest) {
		this.cleaningStatusText = cleaningStatusTest;
	}
//	/**
//	 * @return the leaseStatusText
//	 */
//	public String getLeaseStatusText() {
//		return leaseStatusText;
//	}
//	/**
//	 * @param leaseStatusText the leaseStatusText to set
//	 */
//	public void setLeaseStatusText(String leaseStatusText) {
//		this.leaseStatusText = leaseStatusText;
//	}
	/**
	 * @return the bookingStatusCodeText
	 */
	public String getBookingStatusCodeText() {
		return bookingStatusCodeText;
	}
	/**
	 * @param bookingStatusCodeText the bookingStatusCodeText to set
	 */
	public void setBookingStatusCodeText(String bookingStatusCodeText) {
		this.bookingStatusCodeText = bookingStatusCodeText;
	}
	/**
	 * @return the leasedEquipmentNotes
	 */
	public String getLeasedEquipmentNotes() {
		return leasedEquipmentNotes;
	}
	/**
	 * @param leasedEquipmentNotes the leasedEquipmentNotes to set
	 */
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

	    dto.setAvailabilityStatusText(rs.getString("AvailabilityStatusText"));
	    dto.setConditionStatusText(rs.getString("ConditionStatusText"));
	    dto.setMaintenanceStatusText(rs.getString("MaintenanceStatusText"));
	    dto.setEquipmentTypeText(rs.getString("EquipmentTypeText"));
	    dto.setEquipmentSubTypeText(rs.getString("EquipmentSubTypeText"));

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

	    dto.setAvailabilityStatusCode(rs.getString("AvailabilityStatusCode"));
	    dto.setConditionStatusCode(rs.getString("ConditionStatusCode"));
	    dto.setMaintenanceStatusCode(rs.getString("MaintenanceStatusCode"));
	    dto.setCleaningStatusCode(rs.getString("CleaningStatusCode"));
	    dto.setBookingStatusCode(rs.getString("BookingStatusCode"));

	    dto.setLeasedEquipmentNotes(rs.getString("leasedEquipmentNotes"));

	    String propertiesJson = rs.getString("Properties");
	    if (propertiesJson != null) {
	        dto.setProperties(JsonUtils.setPropertiesFromJson(propertiesJson));
	    } else {
	        dto.setProperties(new HashMap<>());
	    }

	    return dto;
	}	
	
	
}
