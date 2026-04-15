package com.mcquaids.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.model.Equipment;
import com.mcquaids.model.EquipmentQueryDTO;
import com.mcquaids.model.Flatbed;
import com.mcquaids.model.Forklift;
import com.mcquaids.model.ShippingContainer;
import com.mcquaids.model.Trailer;

public class EquipmentDAO {

    private JdbcTemplate jdbcTemplate;

    public EquipmentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getAge() {
        return null;
    }

    public List<Equipment> findByEquipmentLeessedBy(String pCustomer) {
        return null;
    }

    // ------------------------------------------------------------
    // INSERT (cleaned: removed AvailabilityStatusCode + BookingStatusCode)
    // ------------------------------------------------------------
    public void addEquipment(Equipment equipment) {
        try {
            String sql = "INSERT INTO equipment (" +
                    "EquipmentNumber, EquipmentType, EquipmentSubType, SerialNumber, Manufacturer, " +
                    "ManufacturedDate, PurchaseDate, PurchasePrice, SpecialNotes, InspectionDate, Properties, " +
                    "ConditionStatusCode, MaintenanceStatusCode, CleaningStatusCode, Available, SafetyStatusCode, PreferredYardID" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(sql,
                equipment.getEquipmentNumber(),
                equipment.getEquipmentType(),
                equipment.getEquipmentSubType(),
                equipment.getSerialNumber(),
                equipment.getManufacturer(),
                equipment.getManufacturedDate(),
                equipment.getPurchaseDate(),
                equipment.getPurchasePrice(),
                equipment.getSpecialNotes(),
                equipment.getInspectionDate(),
                equipment.getPropertiesAsJson(),
                equipment.getConditionStatusCode(),
                equipment.getMaintenanceStatusCode(),
                equipment.getCleaningStatusCode(),
                equipment.isAvailable(),
                equipment.getSafetyStatusCode(),
                equipment.getPreferredYardId()
            );

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Equipment findByEquipmentNumber(Integer pEquipmentNumber) {
        String sql = "SELECT equip.*, cv.EnglishDescription AS equipmentSubTypeText " +
                     "FROM equipment equip " +
                     "INNER JOIN codevalue cv ON equip.EquipmentSubType = cv.CodeValue " +
                     "WHERE equip.EquipmentNumber = ?";
        return jdbcTemplate.queryForObject(sql, new EquipmentRowMapper(), pEquipmentNumber);
    }

    // ------------------------------------------------------------
    // UPDATE (cleaned: removed AvailabilityStatusCode + BookingStatusCode)
    // ------------------------------------------------------------
    public void updateEquipment(Equipment equipment) {
        String sql = "UPDATE equipment SET " +
                "EquipmentType=?, EquipmentSubType=?, SerialNumber=?, Manufacturer=?, " +
                "ManufacturedDate=?, PurchaseDate=?, PurchasePrice=?, SpecialNotes=?, " +
                "InspectionDate=?, Properties=?, ConditionStatusCode=?, MaintenanceStatusCode=?, " +
                "CleaningStatusCode=?, Available=?, SafetyStatusCode=?, PreferredYardID=? " +
                "WHERE EquipmentNumber=?";

        jdbcTemplate.update(sql,
            equipment.getEquipmentType(),
            equipment.getEquipmentSubType(),
            equipment.getSerialNumber(),
            equipment.getManufacturer(),
            equipment.getManufacturedDate(),
            equipment.getPurchaseDate(),
            equipment.getPurchasePrice(),
            equipment.getSpecialNotes(),
            equipment.getInspectionDate(),
            equipment.getPropertiesAsJson(),
            equipment.getConditionStatusCode(),
            equipment.getMaintenanceStatusCode(),
            equipment.getCleaningStatusCode(),
            equipment.isAvailable(),
            equipment.getSafetyStatusCode(),
            equipment.getPreferredYardId(),
            equipment.getEquipmentNumber()
        );
    }

    public void deleteEquipment(String equipmentNumber) {
        String sql = "DELETE FROM equipment WHERE EquipmentNumber = ?";
        jdbcTemplate.update(sql, equipmentNumber);
    }

    // ------------------------------------------------------------
    // QUERY (cleaned: removed AvailabilityStatusCode + BookingStatusCode)
    // ------------------------------------------------------------
    public List<EquipmentQueryDTO> queryEquipment(
            Integer pEquipmentType,
            String pEquipmentSubType,
            String pDerivedAvailability,
            String pConditionStatusCode,
            String pMaintenanceStatusCode,
            String pCleaningStatusCode) {

        StringBuilder sql = new StringBuilder(
            "SELECT * FROM qryequipmentdetails WHERE 1 = 1");

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        if (pEquipmentType != null) {
            sql.append(" AND EquipmentType = :equipmentType");
            parameters.addValue("equipmentType", pEquipmentType);
        }

        if (StringUtils.isNotBlank(pEquipmentSubType) && !pEquipmentSubType.startsWith("All")) {
            sql.append(" AND EquipmentSubType = :equipmentSubType");
            parameters.addValue("equipmentSubType", pEquipmentSubType);
        }
        
        if (StringUtils.isNotBlank(pDerivedAvailability) && !"All".equals(pDerivedAvailability)) {
            sql.append(" AND DerivedAvailabilityStatus = :derivedAvailability");
            parameters.addValue("derivedAvailability", pDerivedAvailability);
        }
        

        if (StringUtils.isNotBlank(pConditionStatusCode) && !pConditionStatusCode.startsWith("All")) {
            sql.append(" AND ConditionStatusCode = :ConditionStatusCode");
            parameters.addValue("ConditionStatusCode", pConditionStatusCode);
        }

        if (StringUtils.isNotBlank(pMaintenanceStatusCode) && !pMaintenanceStatusCode.startsWith("All")) {
            sql.append(" AND MaintenanceStatusCode = :MaintenanceStatusCode");
            parameters.addValue("MaintenanceStatusCode", pMaintenanceStatusCode);
        }

        if (StringUtils.isNotBlank(pCleaningStatusCode) && !pCleaningStatusCode.startsWith("All")) {
            sql.append(" AND CleaningStatusCode = :CleaningStatusCode");
            parameters.addValue("CleaningStatusCode", pCleaningStatusCode);
        }

        NamedParameterJdbcTemplate named = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        try {
            return named.query(sql.toString(), parameters, new EquipmentQueryDTORowMapper());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<EquipmentQueryDTO> queryEquipmentByEquipmentNUmber(Integer equipmentNumber) {

        StringBuilder sql = new StringBuilder(
            "SELECT * FROM qryEquipmentDetails WHERE 1 = 1");

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        if (equipmentNumber != null) {
            sql.append(" AND EquipmentNumber = :EquipmentNumber");
            parameters.addValue("EquipmentNumber", equipmentNumber);
        }

        NamedParameterJdbcTemplate named =
                new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        try {
            return named.query(sql.toString(), parameters, new EquipmentQueryDTORowMapper());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // ------------------------------------------------------------
    // Availability summary (cleaned: removed AvailabilityStatusCode)
    // ------------------------------------------------------------
    public List<Map<String, Object>> queryEquipmentisAvailable(
            Integer pEquipmentType,
            String pEquipmentSubType,
            String pConditionStatusCode,
            String pMaintenanceStatusCode) {

        NamedParameterJdbcTemplate named =
                new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        StringBuilder sql = new StringBuilder(
            "SELECT equipmentTypeText, equipmentSubTypeText, count(*) AS numberAvailable " +
            "FROM qryequipmentdetails WHERE Available = 1");

        if (pEquipmentType != null) {
            sql.append(" AND EquipmentType = :equipmentType");
            parameters.addValue("equipmentType", pEquipmentType);
        }

        if (StringUtils.isNotBlank(pEquipmentSubType) && !pEquipmentSubType.startsWith("All")) {
            sql.append(" AND EquipmentSubType = :equipmentSubType");
            parameters.addValue("equipmentSubType", pEquipmentSubType);
        }

        sql.append(" GROUP BY equipmentTypeText, equipmentSubTypeText");

        return named.queryForList(sql.toString(), parameters);
    }

    public List<Map<String, Object>> getEquipmentReport() {
        String sql = "CALL GetEquipmentReport()";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getEquipmentInspectionReport(int daysToExpiry) {
        String sql = "CALL ListEquipmentPastOrUpcomingAnniversary(:days)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("days", daysToExpiry);

        NamedParameterJdbcTemplate named =
                new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        return named.queryForList(sql, params);
    }

    // ------------------------------------------------------------
    // Save helpers
    // ------------------------------------------------------------
    public int saveTrailer(Trailer pTrailer) {
        updateEquipment(pTrailer);
        return 0;
    }

    public int saveNewTrailer(Equipment pEquipment) {
        addEquipment(pEquipment);
        return 0;
    }

    public int saveForklift(Forklift pForklift) {
        updateEquipment(pForklift);
        return 0;
    }

    public int saveNewForklift(Forklift pForklift) {
        addEquipment(pForklift);
        return 0;
    }

    public int saveFlatbed(Flatbed pFlatbed) {
        updateEquipment(pFlatbed);
        return 0;
    }

    public int saveNewFlatbed(Flatbed pFlatbed) {
        addEquipment(pFlatbed);
        return 0;
    }

    public int saveContainer(ShippingContainer pContainer) {
        updateEquipment(pContainer);
        return 0;
    }

    public int saveNewContainer(ShippingContainer pContainer) {
        addEquipment(pContainer);
        return 0;
    }

    public EquipmentQueryDTO findEquipment(Integer reservedEquipmentID) {

        StringBuilder sql = new StringBuilder(
            "SELECT * FROM qryEquipmentDetails WHERE 1 = 1");

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        if (reservedEquipmentID != null) {
            sql.append(" AND EquipmentNumber = :reservedEquipmentID");
            parameters.addValue("reservedEquipmentID", reservedEquipmentID);
        }

        NamedParameterJdbcTemplate named =
                new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        try {
            return named.queryForObject(sql.toString(), parameters, new EquipmentQueryDTORowMapper());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Long getPreferredYardId(Integer equipmentNumber) {

        String sql = "SELECT PreferredYardID FROM equipment WHERE EquipmentNumber = ?";

        List<Long> results = jdbcTemplate.query(
            sql,
            ps -> ps.setInt(1, equipmentNumber),
            (rs, rowNum) -> {
                Long yardId = rs.getLong("PreferredYardID");
                return rs.wasNull() ? null : yardId;
            }
        );

        return results.isEmpty() ? null : results.get(0);
    }
}
