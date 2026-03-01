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

    // ---------------------------------------------------------------------
    // Find the open (active) location interval
    // ---------------------------------------------------------------------
    public EquipmentLocationHistory findOpenLocation(int equipmentNumber) {

        String sql =
            "SELECT LocationHistoryID, EquipmentNumber, Street, City, Province, " +
            "Postal, Country, LocationType, StartDateTime, EndDateTime, " +
            "ReservationID, Notes " +
            "FROM equipment_location_history " +
            "WHERE EquipmentNumber = ? " +
            "AND EndDateTime IS NULL " +
            "LIMIT 1";

        List<EquipmentLocationHistory> results =
            jdbcTemplate.query(sql, new EquipmentLocationHistoryRowMapper(), equipmentNumber);

        return results.isEmpty() ? null : results.get(0);
    }

    // ---------------------------------------------------------------------
    // Update an existing location interval (used to close intervals)
    // ---------------------------------------------------------------------
    public void update(EquipmentLocationHistory history) {

        String sql =
            "UPDATE equipment_location_history SET " +
            "Street = ?, City = ?, Province = ?, Postal = ?, Country = ?, " +
            "LocationType = ?, StartDateTime = ?, EndDateTime = ?, " +
            "ReservationID = ?, Notes = ? " +
            "WHERE LocationHistoryID = ?";

        jdbcTemplate.update(sql,
            history.getStreet(),
            history.getCity(),
            history.getProvince(),
            history.getPostal(),
            history.getCountry(),
            history.getLocationType(),
            history.getStartDateTime(),
            history.getEndDateTime(),
            history.getReservationId(),
            history.getNotes(),
            history.getLocationHistoryID()
        );
    }

    // ---------------------------------------------------------------------
    // Insert new location interval (service provides timestamps)
    // ---------------------------------------------------------------------
    public void insertLocationHistory(EquipmentLocationHistory history) {

        String sql =
            "INSERT INTO equipment_location_history " +
            "(EquipmentNumber, Street, City, Province, Postal, Country, " +
            "LocationType, StartDateTime, EndDateTime, ReservationID, Notes) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
            history.getEquipmentNumber(),
            history.getStreet(),
            history.getCity(),
            history.getProvince(),
            history.getPostal(),
            history.getCountry(),
            history.getLocationType(),
            history.getStartDateTime(),
            history.getEndDateTime(),
            history.getReservationId(),
            history.getNotes()
        );
    }

    // ---------------------------------------------------------------------
    // Full history for equipment
    // ---------------------------------------------------------------------
    public List<EquipmentLocationHistory> findHistoryForEquipment(int equipmentNumber) {

        String sql =
            "SELECT LocationHistoryID, EquipmentNumber, Street, City, Province, " +
            "Postal, Country, LocationType, StartDateTime, EndDateTime, " +
            "ReservationID, Notes " +
            "FROM equipment_location_history " +
            "WHERE EquipmentNumber = ? " +
            "ORDER BY StartDateTime DESC";

        return jdbcTemplate.query(sql,
            new EquipmentLocationHistoryRowMapper(),
            equipmentNumber
        );
    }
    
    public List<EquipmentLocationHistory> findHistoryForReservation(int reservationId) {

        String sql =
            "SELECT LocationHistoryID, EquipmentNumber, Street, City, Province, " +
            "Postal, Country, LocationType, StartDateTime, EndDateTime, " +
            "ReservationID, Notes " +
            "FROM equipment_location_history " +
            "WHERE ReservationID = ? " +
            "ORDER BY StartDateTime DESC";

        return jdbcTemplate.query(sql,
            new EquipmentLocationHistoryRowMapper(),
            reservationId
        );
    }    
    

    // ---------------------------------------------------------------------
    // RowMapper
    // ---------------------------------------------------------------------
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

            int reservationId = rs.getInt("ReservationID");
            history.setReservationId(rs.wasNull() ? null : reservationId);

            history.setNotes(rs.getString("Notes"));

            return history;
        }
    }
}