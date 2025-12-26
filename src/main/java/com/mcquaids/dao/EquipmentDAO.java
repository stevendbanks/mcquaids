/**
 * 
 */
package com.mcquaids.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.model.Constants;
import com.mcquaids.model.Equipment;
import com.mcquaids.model.EquipmentQueryDTO;
import com.mcquaids.model.Flatbed;
import com.mcquaids.model.Forklift;
import com.mcquaids.model.ShippingContainer;
import com.mcquaids.model.Trailer;

/**
 * 
 */
public class EquipmentDAO {

	private JdbcTemplate jdbcTemplate;

	public EquipmentDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Integer getAge() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Equipment> findByEquipmentLeessedBy(String pCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addEquipment(Equipment equipment) {
		System.out.println(equipment.toString());
		try {
		String sql = "INSERT INTO equipment (EquipmentNumber, EquipmentType, equipmentSubType, SerialNumber, Manufacturer, ManufacturedDate, PurchaseDate, PurchasePrice, SpecialNotes, InspectionDate, Properties, AvailabilityStatusCode, ConditionStatusCode, MaintenanceStatusCode, CleaningStatusCode, BookingStatusCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, equipment.getEquipmentNumber(), equipment.getEquipmentType(),
				equipment.getEquipmentSubType(), equipment.getSerialNumber(), equipment.getManufacturer(),
				equipment.getManufacturedDate(), equipment.getPurchaseDate(), equipment.getPurchasePrice(),
				equipment.getSpecialNotes(), equipment.getInspectionDate(), equipment.getPropertiesAsJson(),
				equipment.getAvailabilityStatusCode(), equipment.getConditionStatusCode(),
				equipment.getMaintenanceStatusCode(), equipment.getCleaningStatusCode(),
				equipment.getBookingStatusCode());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Equipment findByEquipmentNumber(String pEquipmentNumber) {
	    String sql = "SELECT equip.*, cv.EnglishDescription AS equipmentSubTypeText FROM equipment equip INNER JOIN codevalue cv ON equip.EquipmentSubType = cv.CodeValue WHERE equip.EquipmentNumber = ?";
	    return jdbcTemplate.queryForObject(sql, new EquipmentRowMapper(), pEquipmentNumber);
	}


	// Update
	public void updateEquipment(Equipment equipment) {
		String sql = "UPDATE equipment SET EquipmentType = ?, equipmentSubType = ?, SerialNumber = ?, Manufacturer = ?, ManufacturedDate = ?, PurchaseDate = ?, PurchasePrice = ?, SpecialNotes = ?, InspectionDate = ?, Properties = ?, AvailabilityStatusCode = ?, ConditionStatusCode = ?, MaintenanceStatusCode = ?, CleaningStatusCode = ?, BookingStatusCode = ? WHERE EquipmentNumber = ?";
		jdbcTemplate.update(sql, equipment.getEquipmentType(), equipment.getEquipmentSubType(),
				equipment.getSerialNumber(), equipment.getManufacturer(), equipment.getManufacturedDate(),
				equipment.getPurchaseDate(), equipment.getPurchasePrice(), equipment.getSpecialNotes(),
				equipment.getInspectionDate(), equipment.getPropertiesAsJson(), equipment.getAvailabilityStatusCode(),
				equipment.getConditionStatusCode(), equipment.getMaintenanceStatusCode(),
				equipment.getCleaningStatusCode(), equipment.getBookingStatusCode(), equipment.getEquipmentNumber());
	}

	// Delete
	public void deleteEquipment(String equipmentNumber) {
		String sql = "DELETE FROM equipment WHERE EquipmentNumber = ?";
		jdbcTemplate.update(sql, equipmentNumber);
	}

	public List<EquipmentQueryDTO> queryEquipment(Integer pEquipmentType, String pEquipmentSubType,
			String pAvailabilityStatusCode, String pCconditionStatusCode, String pMaintenanceStatusCode,
			String pCleaningStatusCode, String pBookingStatusCode) {
		
		

		
		StringBuilder sql = new StringBuilder(
			"SELECT * FROM qryequipmentdetails WHERE 1 =1 ");

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		if (null != pEquipmentType) {
			sql.append(" AND EquipmentType = :equipmentType");
			parameters.addValue("equipmentType", pEquipmentType);
		}
		
		if ((!StringUtils.isAllEmpty(pEquipmentSubType)) && (!pEquipmentSubType.startsWith("All"))) {
			sql.append(" AND  EquipmentSubType = :equipmentSubType");
			parameters.addValue("equipmentSubType", pEquipmentSubType);
		}
		
		if ((!StringUtils.isAllEmpty(pAvailabilityStatusCode)) && (!pAvailabilityStatusCode.startsWith("All"))) {
			sql.append(" AND  AvailabilityStatusCode = :AvailabilityStatusCode");
			parameters.addValue("AvailabilityStatusCode", pAvailabilityStatusCode);
		}
		
		if ((!StringUtils.isAllEmpty(pCconditionStatusCode)) && (!pCconditionStatusCode.startsWith("All"))) {
			sql.append(" AND  ConditionStatusCode = :ConditionStatusCode");
			parameters.addValue("ConditionStatusCode", pCconditionStatusCode);
		}

		if ((!StringUtils.isAllEmpty(pMaintenanceStatusCode)) && (!pMaintenanceStatusCode.startsWith("All"))) {
			sql.append(" AND  MaintenanceStatusCode = :MaintenanceStatusCode");
			parameters.addValue("MaintenanceStatusCode", pMaintenanceStatusCode);
		}

		if ((!StringUtils.isAllEmpty(pCleaningStatusCode)) && (!pCleaningStatusCode.startsWith("All"))) {
			sql.append(" AND  MaintenanceStatusCode = :CleaningStatusCode");
			parameters.addValue("CleaningStatusCode", pCleaningStatusCode);
		}

		if ((!StringUtils.isAllEmpty(pBookingStatusCode)) && (!pBookingStatusCode.startsWith("All"))) {
			sql.append(" AND  MaintenanceStatusCode = :BookingStatusCode");
			parameters.addValue("BookingStatusCode", pBookingStatusCode);
		}

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());

	  List<EquipmentQueryDTO> x = null;
	
		try {
	      x = namedParameterJdbcTemplate.query(sql.toString(), parameters, new EquipmentQueryDTORowMapper());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return x;
		
	}
	
	
	public List<EquipmentQueryDTO> queryEquipmentByEquipmentNUmber(String pEquipmentNumber) {
		
		
		StringBuilder sql = new StringBuilder(
		" SELECT * from qryEquipmentDetails " +
		" WHERE 1 = 1 ");

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		if (StringUtils.isNotEmpty(pEquipmentNumber)) {
			sql.append(" AND EquipmentNumber = :EquipmentNumber");
			parameters.addValue("EquipmentNumber", pEquipmentNumber);
		}
		
		System.out.println(sql.toString());
		
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		
		
		List<EquipmentQueryDTO> x = null;
		try {
		      x = namedParameterJdbcTemplate.query(sql.toString(), parameters, new EquipmentQueryDTORowMapper());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return x;		 
	}	
	
	
	public List<Map<String, Object>> queryEquipmentisAvailable(Integer pEquipmentType, String pEquipmentSubType,
			String pCconditionStatusCode, String pMaintenanceStatusCode) {

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());


		MapSqlParameterSource parameters = new MapSqlParameterSource();		
		
		
		StringBuilder sql = new StringBuilder(
				"SELECT equipmentTypeText, equipmentSubTypeText, count(*) as numberAvailable FROM qryequipmentdetails WHERE AvailabilityStatusCode = '"
						+ Constants.EQUIPMENT_AVAILABLE_TO_LEASE + "'");


		if (null != pEquipmentType) {
			sql.append(" AND EquipmentType = :equipmentType");
			parameters.addValue("equipmentType", pEquipmentType);
		}

		if ((!StringUtils.isAllEmpty(pEquipmentSubType)) && (!pEquipmentSubType.startsWith("All"))) {
			sql.append(" AND  EquipmentSubType = :equipmentSubType");
			parameters.addValue("equipmentSubType", pEquipmentSubType);
		}
		
		sql.append(" GROUP BY equipmentTypeText, equipmentSubTypeText");

		return namedParameterJdbcTemplate.queryForList(sql.toString(), parameters);

	}
	
	
	
    public List<Map<String, Object>> getEquipmentReport() {
        String sql = "CALL GetEquipmentReport()";
        return jdbcTemplate.queryForList(sql);
    }
	

    public List<Map<String, Object>> getEquipmentInspectionReport(int daysToExpiry) {
        String sql = "CALL ListEquipmentPastOrUpcomingAnniversary(:days)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("days", daysToExpiry);
        
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());  

        return namedParameterJdbcTemplate.queryForList(sql, params);
    }
	

// ***************************************************
// *  Helper methods to Save specific types of equipment.
// ********************************************************
	public int saveTrailer(Trailer pTrailer) {
		Equipment equipment = pTrailer;
//		equipment.setProperties(pTrailer.setFieldsToJson(pTrailer));
		updateEquipment(equipment);
		return 0;
	}

	public int saveNewTrailer(Equipment pEquipment) {

		addEquipment(pEquipment);
		return 0;
	}

	public int saveForklift(Forklift pForklift) {
		Equipment equipment = pForklift;
//		equipment.setProperties(pForklift.setFieldsToJson(pForklift));
		updateEquipment(equipment);
		return 0;
	}

	public int saveNewForklift(Forklift pForklift) {
		Equipment equipment = pForklift;
//		equipment.setProperties(pForklift.setFieldsToJson(pForklift));
		addEquipment(equipment);
		return 0;
	}

	public int saveFlatbed(Flatbed pFlatbed) {
		Equipment equipment = pFlatbed;
//		equipment.setProperties(pFlatbed.setFieldsToJson(pFlatbed));
		updateEquipment(equipment);
		return 0;
	}

	public int saveNewFlatbed(Flatbed pFlatbed) {
		Equipment equipment = pFlatbed;
//		equipment.setProperties(pFlatbed.setFieldsToJson(pFlatbed));
		addEquipment(equipment);
		return 0;
	}

	public int saveContainer(ShippingContainer pContainer) {
		Equipment equipment = pContainer;
//		equipment.setProperties(pContainer.setFieldsToJson(pContainer));
		updateEquipment(equipment);
		return 0;
	}

	public int saveNewContainer(ShippingContainer pContainer) {
		Equipment equipment = pContainer;
//		equipment.setProperties(pContainer.setFieldsToJson(pContainer));
		System.out.println("saveNewContainer() equipment =" + equipment.toString());
		addEquipment(equipment);
		return 0;
	}

}
