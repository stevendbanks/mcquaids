package com.mcquaids.dao;

import com.mcquaids.model.Address;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchActionStatus;
import com.mcquaids.model.DispatchActionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class DispatchActionDAO {

    private final JdbcTemplate jdbcTemplate;

    public DispatchActionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ------------------------------------------------------------
    // INSERT
    // ------------------------------------------------------------
    public DispatchAction insert(DispatchAction action) {

        String sql =
            "INSERT INTO dispatch_action (" +
            " reservation_id," +
            " equipment_number," +
            " action_type," +
            " status," +
            " from_street, from_city, from_province, from_postal, from_country," +
            " to_street, to_city, to_province, to_postal, to_country," +
            " scheduled_datetime," +
            " driver_id," +
            " notes" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setObject(1, action.getReservationId());
            ps.setString(2, action.getEquipmentNumber());
            ps.setString(3, action.getActionType().name());
            ps.setString(4, action.getStatus().name());

            // FROM address
            Address from = action.getFromAddress();
            if (from != null) {
                ps.setString(5, from.getStreet());
                ps.setString(6, from.getCity());
                ps.setString(7, from.getProvince());
                ps.setString(8, from.getPostalCode());
                ps.setString(9, from.getCountry());
            } else {
                ps.setNull(5, java.sql.Types.VARCHAR);
                ps.setNull(6, java.sql.Types.VARCHAR);
                ps.setNull(7, java.sql.Types.VARCHAR);
                ps.setNull(8, java.sql.Types.VARCHAR);
                ps.setNull(9, java.sql.Types.VARCHAR);
            }

            // TO address
            Address to = action.getToAddress();
            if (to != null) {
                ps.setString(10, to.getStreet());
                ps.setString(11, to.getCity());
                ps.setString(12, to.getProvince());
                ps.setString(13, to.getPostalCode());
                ps.setString(14, to.getCountry());
            } else {
                ps.setNull(10, java.sql.Types.VARCHAR);
                ps.setNull(11, java.sql.Types.VARCHAR);
                ps.setNull(12, java.sql.Types.VARCHAR);
                ps.setNull(13, java.sql.Types.VARCHAR);
                ps.setNull(14, java.sql.Types.VARCHAR);
            }

            if (action.getScheduledDateTime() != null) {
                ps.setTimestamp(15, Timestamp.valueOf(action.getScheduledDateTime()));
            } else {
                ps.setNull(15, java.sql.Types.TIMESTAMP);
            }

            ps.setObject(16, action.getDriverId());
            ps.setString(17, action.getNotes());

            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            action.setDispatchActionId(keyHolder.getKey().longValue());
        }

        return action;
    }

    // ------------------------------------------------------------
    // FIND BY RESERVATION
    // ------------------------------------------------------------
    public List<DispatchAction> findByReservationId(Long reservationId) {

        String sql = "SELECT * FROM dispatch_action WHERE reservation_id = ? ORDER BY dispatch_action_id";

        return jdbcTemplate.query(sql, new Object[]{reservationId}, (rs, rowNum) -> {

            DispatchAction action = new DispatchAction();
            action.setDispatchActionId(rs.getLong("dispatch_action_id"));
            action.setReservationId(rs.getLong("reservation_id"));
            action.setEquipmentNumber(rs.getString("equipment_number"));

            action.setActionType(DispatchActionType.valueOf(rs.getString("action_type")));
            action.setStatus(DispatchActionStatus.valueOf(rs.getString("status")));

            // FROM address
            String fromStreet = rs.getString("from_street");
            if (fromStreet != null) {
                action.setFromAddress(new Address(
                        fromStreet,
                        rs.getString("from_city"),
                        rs.getString("from_province"),
                        rs.getString("from_postal"),
                        rs.getString("from_country")
                ));
            }

            // TO address
            String toStreet = rs.getString("to_street");
            if (toStreet != null) {
                action.setToAddress(new Address(
                        toStreet,
                        rs.getString("to_city"),
                        rs.getString("to_province"),
                        rs.getString("to_postal"),
                        rs.getString("to_country")
                ));
            }

            Timestamp ts = rs.getTimestamp("scheduled_datetime");
            if (ts != null) {
                action.setScheduledDateTime(ts.toLocalDateTime());
            }

            action.setDriverId(rs.getObject("driver_id", Long.class));
            action.setNotes(rs.getString("notes"));

            return action;
        });
    }
}