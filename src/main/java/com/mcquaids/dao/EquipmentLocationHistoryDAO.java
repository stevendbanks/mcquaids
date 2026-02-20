package com.mcquaids.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.EquipmentLocationHistory;

public class EquipmentLocationHistoryDAO {

    private final JdbcTemplate jdbcTemplate;

    public EquipmentLocationHistoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Close the current active location entry for this equipment.
     */
    public void closeCurrentLocation(int equipmentNumber) {

        String sql = "UPDATE equipment_location_history "
                   + "SET EndDateTime = NOW() "
                   + "WHERE EquipmentNumber = ? "
                   + "AND EndDateTime IS NULL";

        jdbcTemplate.update(sql, equipmentNumber);
    }

    /**
     * Insert a new location history entry.
     */
    public void insertLocationHistory(EquipmentLocationHistory history) {

        String sql = "INSERT INTO equipment_location_history "
                   + "(EquipmentNumber, Street, City, Province, Postal, Country, "
                   + "LocationType, StartDateTime, ReservationID, Notes) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?)";

        jdbcTemplate.update(sql,
                history.getEquipmentNumber(),
                history.getStreet(),
                history.getCity(),
                history.getProvince(),
                history.getPostal(),
                history.getCountry(),
                history.getLocationType(),
                history.getReservationId(),
                history.getNotes()
        );
    }

    /**
     * Get the current (active) location for this equipment.
     */
    public EquipmentLocationHistory getCurrentLocation(int equipmentNumber) {

        String sql = "SELECT LocationHistoryID, EquipmentNumber, Street, City, Province, "
                   + "Postal, Country, LocationType, StartDateTime, EndDateTime, "
                   + "ReservationID, Notes "
                   + "FROM equipment_location_history "
                   + "WHERE EquipmentNumber = ? "
                   + "AND EndDateTime IS NULL "
                   + "LIMIT 1";

        List<EquipmentLocationHistory> results = jdbcTemplate.query(sql,
                new EquipmentLocationHistoryRowMapper(),
                equipmentNumber
        );

        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * Get full movement history for this equipment.
     */
    public List<EquipmentLocationHistory> getHistory(int equipmentNumber) {

        String sql = "SELECT LocationHistoryID, EquipmentNumber, Street, City, Province, "
                   + "Postal, Country, LocationType, StartDateTime, EndDateTime, "
                   + "ReservationID, Notes "
                   + "FROM equipment_location_history "
                   + "WHERE EquipmentNumber = ? "
                   + "ORDER BY StartDateTime DESC";

        return jdbcTemplate.query(sql,
                new EquipmentLocationHistoryRowMapper(),
                equipmentNumber
        );
    }

    /**
     * RowMapper for EquipmentLocationHistory.
     */
    private static class EquipmentLocationHistoryRowMapper
            implements RowMapper<EquipmentLocationHistory> {

        @Override
        public EquipmentLocationHistory mapRow(ResultSet rs, int rowNum) throws SQLException {

            EquipmentLocationHistory history = new EquipmentLocationHistory();

            history.setLocationHistoryID(rs.getInt("LocationHistoryID"));
            history.setEquipmentNumber(rs.getInt("EquipmentNumber"));
            history.setStreet(rs.getString("Street"));
            history.setCity(rs.getString("City"));
            history.setProvince(rs.getString("Province"));
            history.setPostal(rs.getString("Postal"));
            history.setCountry(rs.getString("Country"));
            history.setLocationType(rs.getString("LocationType"));
            history.setStartDateTime(rs.getTimestamp("StartDateTime"));
            history.setEndDateTime(rs.getTimestamp("EndDateTime"));
            history.setReservationId(rs.getInt("ReservationID"));
            history.setNotes(rs.getString("Notes"));

            return history;
        }
    }
}