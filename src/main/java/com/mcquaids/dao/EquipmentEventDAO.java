package com.mcquaids.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.model.EquipmentEvent;

public class EquipmentEventDAO {

    private final JdbcTemplate jdbcTemplate;

    public EquipmentEventDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ------------------------------------------------------------
    // INSERT
    // ------------------------------------------------------------
    public void insert(EquipmentEvent event) {

        final String sql =
            "INSERT INTO equipment_event (" +
            "EquipmentNumber, EventType, EventDateTime, " +
            "FromStreet, FromCity, FromProvince, FromPostal, FromCountry, " +
            "ToStreet, ToCity, ToProvince, ToPostal, ToCountry, " +
            "ReservationID, Notes" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, event.getEquipmentNumber());
            ps.setString(2, event.getEventType());
            ps.setTimestamp(3, new Timestamp(event.getEventDateTime().getTime()));

            // FROM location
            ps.setString(4, event.getFromStreet());
            ps.setString(5, event.getFromCity());
            ps.setString(6, event.getFromProvince());
            ps.setString(7, event.getFromPostal());
            ps.setString(8, event.getFromCountry());

            // TO location
            ps.setString(9, event.getToStreet());
            ps.setString(10, event.getToCity());
            ps.setString(11, event.getToProvince());
            ps.setString(12, event.getToPostal());
            ps.setString(13, event.getToCountry());

            // Reservation ID (nullable)
            if (event.getReservationId() != null) {
                ps.setInt(14, event.getReservationId());
            } else {
                ps.setNull(14, Types.INTEGER);
            }

            ps.setString(15, event.getNotes());

            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            event.setEventId(keyHolder.getKey().intValue());
        }
    }

    // ------------------------------------------------------------
    // QUERY BY EQUIPMENT
    // ------------------------------------------------------------
    public List<EquipmentEvent> findByEquipmentNumber(int equipmentNumber) {
        final String sql =
            "SELECT * FROM equipment_event " +
            "WHERE EquipmentNumber = ? " +
            "ORDER BY EventDateTime DESC";

        return jdbcTemplate.query(sql, rowMapper, equipmentNumber);
    }

    // ------------------------------------------------------------
    // QUERY BY RESERVATION
    // ------------------------------------------------------------
    public List<EquipmentEvent> findByReservationId(int reservationId) {
        final String sql =
            "SELECT * FROM equipment_event " +
            "WHERE ReservationID = ? " +
            "ORDER BY EventDateTime";

        return jdbcTemplate.query(sql, rowMapper, reservationId);
    }

    // ------------------------------------------------------------
    // RECENT EVENTS
    // ------------------------------------------------------------
    public List<EquipmentEvent> findRecent(int limit) {
        final String sql =
            "SELECT * FROM equipment_event " +
            "ORDER BY EventDateTime DESC LIMIT ?";

        return jdbcTemplate.query(sql, rowMapper, limit);
    }

    // ------------------------------------------------------------
    // ROW MAPPER
    // ------------------------------------------------------------
    private final RowMapper<EquipmentEvent> rowMapper = (rs, rowNum) -> {
        EquipmentEvent event = new EquipmentEvent();

        event.setEventId(rs.getInt("EventID"));
        event.setEquipmentNumber(rs.getInt("EquipmentNumber"));
        event.setEventType(rs.getString("EventType"));
        event.setEventDateTime(rs.getTimestamp("EventDateTime"));

        event.setFromStreet(rs.getString("FromStreet"));
        event.setFromCity(rs.getString("FromCity"));
        event.setFromProvince(rs.getString("FromProvince"));
        event.setFromPostal(rs.getString("FromPostal"));
        event.setFromCountry(rs.getString("FromCountry"));

        event.setToStreet(rs.getString("ToStreet"));
        event.setToCity(rs.getString("ToCity"));
        event.setToProvince(rs.getString("ToProvince"));
        event.setToPostal(rs.getString("ToPostal"));
        event.setToCountry(rs.getString("ToCountry"));

        int reservationId = rs.getInt("ReservationID");
        event.setReservationId(rs.wasNull() ? null : reservationId);

        event.setNotes(rs.getString("Notes"));

        return event;
    };
}