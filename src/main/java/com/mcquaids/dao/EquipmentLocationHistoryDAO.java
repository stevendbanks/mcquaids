package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
            "SELECT LocationHistoryID, EquipmentNumber, YardID, Street, City, Province, " +
            "Postal, Country, LocationType, StartDateTime, EndDateTime, " +
            "ReservationID, Notes, ActionID, ActionType " +
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
            "YardID = ?, Street = ?, City = ?, Province = ?, Postal = ?, Country = ?, " +
            "LocationType = ?, StartDateTime = ?, EndDateTime = ?, " +
            "ReservationID = ?, Notes = ?, ActionID = ?, ActionType = ? " +
            "WHERE LocationHistoryID = ?";

        jdbcTemplate.update(sql,
            history.getYardID(),
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
            history.getActionId(),
            history.getActionType(),
            history.getLocationHistoryID()
        );
    }

    // ---------------------------------------------------------------------
    // Insert new location interval (service provides timestamps)
    // ---------------------------------------------------------------------
    public void insertLocationHistory(EquipmentLocationHistory history) {

        String sql =
            "INSERT INTO equipment_location_history " +
            "(EquipmentNumber, YardID, Street, City, Province, Postal, Country, " +
            "LocationType, StartDateTime, EndDateTime, ReservationID, Notes, ActionID, ActionType) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
            history.getEquipmentNumber(),
            history.getYardID(),
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
            history.getActionId(),
            history.getActionType()
        );
    }

    // ---------------------------------------------------------------------
    // Full history for equipment
    // ---------------------------------------------------------------------
    public List<EquipmentLocationHistory> findHistoryForEquipment(int equipmentNumber) {

        String sql =
            "SELECT LocationHistoryID, EquipmentNumber, YardID, Street, City, Province, " +
            "Postal, Country, LocationType, StartDateTime, EndDateTime, " +
            "ReservationID, Notes, ActionID, ActionType " +
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
            "SELECT LocationHistoryID, EquipmentNumber, YardID, Street, City, Province, " +
            "Postal, Country, LocationType, StartDateTime, EndDateTime, " +
            "ReservationID, Notes, ActionID, ActionType " +
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

            history.setLocationHistoryID(rs.getLong("LocationHistoryID"));
            history.setEquipmentNumber(rs.getInt("EquipmentNumber"));

            Long yardId = rs.getLong("YardID");
            history.setYardID(rs.wasNull() ? null : yardId);

            history.setStreet(rs.getString("Street"));
            history.setCity(rs.getString("City"));
            history.setProvince(rs.getString("Province"));
            history.setPostal(rs.getString("Postal"));
            history.setCountry(rs.getString("Country"));
            history.setLocationType(rs.getString("LocationType"));

            history.setStartDateTime(rs.getObject("StartDateTime", LocalDateTime.class));
            history.setEndDateTime(rs.getObject("EndDateTime", LocalDateTime.class));

            int reservationId = rs.getInt("ReservationID");
            history.setReservationId(rs.wasNull() ? null : reservationId);

            history.setNotes(rs.getString("Notes"));

            Long actionId = rs.getLong("ActionID");
            history.setActionId(rs.wasNull() ? null : actionId);

            history.setActionType(rs.getString("ActionType"));

            return history;
        }
    }
}
