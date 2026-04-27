package com.mcquaids.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.model.Address;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchActionStatus;
import com.mcquaids.model.DispatchActionType;
import com.mcquaids.model.DispatchSourceType;

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
            " reservation_id," +                 // 1
            " reservation_line_item_id," +       // 2
            " MovementOrderID," +                // 3
            " MovementOrderLineID," +            // 4
            " equipment_number," +               // 5
            " action_type," +                    // 6
            " status," +                         // 7
            " source_type," +                    // ⭐ 8 NEW
            " from_street, from_city, from_province, from_postal, from_country," + // 9–13
            " from_yard_id, from_location_name," +                                 // 14–15
            " to_street, to_city, to_province, to_postal, to_country," +           // 16–20
            " to_yard_id, to_location_name," +                                     // 21–22
            " scheduled_datetime," +                                                // 23
            " driver_id," +                                                         // 24
            " driver_token," +                                                      // 25
            " notes," +                                                             // 26
            " completed_at" +                                                       // 27
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // ⭐ TOTAL PLACEHOLDERS = 27

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setObject(1, action.getReservationID());
            ps.setObject(2, action.getReservationLineItemID());
            ps.setObject(3, action.getMovementOrderID());
            ps.setObject(4, action.getMovementOrderLineID());
            ps.setInt(5, action.getEquipmentNumber());
            ps.setString(6, action.getActionType().name());
            ps.setString(7, action.getStatus().name());

            // ⭐ NEW: source_type
            ps.setString(8, action.getSourceType() != null ? action.getSourceType().name() : null);

            Address from = action.getFromAddress();
            if (from != null) {
                ps.setString(9, from.getStreet());
                ps.setString(10, from.getCity());
                ps.setString(11, from.getProvince());
                ps.setString(12, from.getPostalCode());
                ps.setString(13, from.getCountry());
            } else {
                ps.setNull(9, Types.VARCHAR);
                ps.setNull(10, Types.VARCHAR);
                ps.setNull(11, Types.VARCHAR);
                ps.setNull(12, Types.VARCHAR);
                ps.setNull(13, Types.VARCHAR);
            }

            ps.setObject(14, action.getFromYardId());
            ps.setString(15, action.getFromLocationName());

            Address to = action.getToAddress();
            if (to != null) {
                ps.setString(16, to.getStreet());
                ps.setString(17, to.getCity());
                ps.setString(18, to.getProvince());
                ps.setString(19, to.getPostalCode());
                ps.setString(20, to.getCountry());
            } else {
                ps.setNull(16, Types.VARCHAR);
                ps.setNull(17, Types.VARCHAR);
                ps.setNull(18, Types.VARCHAR);
                ps.setNull(19, Types.VARCHAR);
                ps.setNull(20, Types.VARCHAR);
            }

            ps.setObject(21, action.getToYardId());
            ps.setString(22, action.getToLocationName());

            if (action.getScheduledDateTime() != null) {
                ps.setObject(23, action.getScheduledDateTime());
            } else {
                ps.setNull(23, Types.TIMESTAMP);
            }

            ps.setObject(24, action.getDriverId());
            ps.setObject(25, action.getDriverToken());
            ps.setString(26, action.getNotes());

            if (action.getCompletedAt() != null) {
                ps.setTimestamp(27, Timestamp.valueOf(action.getCompletedAt()));
            } else {
                ps.setNull(27, Types.TIMESTAMP);
            }

            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            action.setDispatchActionId(keyHolder.getKey().longValue());
        }

        return action;
    }

    // ------------------------------------------------------------
    // FINDERS
    // ------------------------------------------------------------
    public DispatchAction getByDispatchActionID(Long dispatchActionId) {
        String sql = "SELECT * FROM dispatch_action WHERE dispatch_action_id = ?";
        return jdbcTemplate.query(sql, ps -> ps.setLong(1, dispatchActionId), rs -> {
            if (!rs.next()) return null;
            return mapRow(rs);
        });
    }

    public List<DispatchAction> findByReservationId(Integer reservationId) {
        return jdbcTemplate.query(
            "SELECT * FROM dispatch_action WHERE reservation_id = ? ORDER BY dispatch_action_id",
            new Object[]{reservationId},
            (rs, rowNum) -> mapRow(rs)
        );
    }

    public List<DispatchAction> findByMovementOrderLineId(Long movementOrderLineId) {
        return jdbcTemplate.query(
            "SELECT * FROM dispatch_action WHERE MovementOrderLineID = ? ORDER BY dispatch_action_id",
            new Object[]{movementOrderLineId},
            (rs, rowNum) -> mapRow(rs)
        );
    }

    public List<DispatchAction> findByMovementOrderId(Long movementOrderId) {
        return jdbcTemplate.query(
            "SELECT * FROM dispatch_action WHERE MovementOrderID = ? ORDER BY dispatch_action_id",
            new Object[]{movementOrderId},
            (rs, rowNum) -> mapRow(rs)
        );
    }

    // ------------------------------------------------------------
    // UPDATE
    // ------------------------------------------------------------
    public void update(DispatchAction action) {
        String sql =
            "UPDATE dispatch_action SET " +
            " removed_from_reservation = ?, " +
            " status = ?, " +
            " source_type = ?, " +  // ⭐ NEW
            " from_yard_id = ?, from_location_name = ?, " +
            " to_yard_id = ?, to_location_name = ? " +
            "WHERE dispatch_action_id = ?";

        jdbcTemplate.update(sql,
            action.isRemovedFromReservation(),
            action.getStatus().name(),
            action.getSourceType() != null ? action.getSourceType().name() : null,
            action.getFromYardId(),
            action.getFromLocationName(),
            action.getToYardId(),
            action.getToLocationName(),
            action.getDispatchActionId()
        );
    }

    // ------------------------------------------------------------
    // CALENDAR SYNC
    // ------------------------------------------------------------
    public void updateCalendarLinkage(Long dispatchActionId, String eventId, String calendarId, Instant now) {

        String sql =
            "UPDATE dispatch_action SET " +
            " google_event_id = ?, " +
            " google_calendar_id = ?, " +
            " last_calendar_sync_at = ? " +
            "WHERE dispatch_action_id = ?";

        jdbcTemplate.update(sql,
            eventId,
            calendarId,
            Timestamp.from(now),
            dispatchActionId
        );
    }
    
    // ------------------------------------------------------------
    // CALENDAR SYNC (full update from Google event)
    // ------------------------------------------------------------
    public void updateFromCalendarSync(DispatchAction action) {

        Timestamp lastSync = toTimestamp(action.getLastCalendarSyncAt());

        String sql =
            "UPDATE dispatch_action SET " +
            " google_event_id = ?, " +
            " google_calendar_id = ?, " +
            " last_calendar_sync_at = ?, " +
            " scheduled_datetime = ?, " +
            " notes = ?, " +
            " status = ?, " +
            " from_yard_id = ?, from_location_name = ?, " +
            " to_yard_id = ?, to_location_name = ? " +
            "WHERE dispatch_action_id = ?";

        jdbcTemplate.update(sql,
            action.getGoogleEventId(),
            action.getGoogleCalendarId(),
            lastSync,
            action.getScheduledDateTime(),
            action.getNotes(),
            action.getStatus().name(),
            action.getFromYardId(),
            action.getFromLocationName(),
            action.getToYardId(),
            action.getToLocationName(),
            action.getDispatchActionId()
        );
    }

    // ------------------------------------------------------------
    // CLEAR CALENDAR LINKAGE BY EVENT ID
    // ------------------------------------------------------------
    public void clearCalendarLinkageByEventId(String eventId) {

        String sql =
            "UPDATE dispatch_action SET " +
            " google_event_id = NULL, " +
            " google_calendar_id = NULL, " +
            " last_calendar_sync_at = NULL " +
            "WHERE google_event_id = ?";

        jdbcTemplate.update(sql, eventId);
    }

    private Timestamp toTimestamp(Instant instant) {
        return instant == null ? null : Timestamp.from(instant);
    }
    
    

    // ------------------------------------------------------------
    // ROW MAPPER
    // ------------------------------------------------------------
    private DispatchAction mapRow(ResultSet rs) throws SQLException {

        DispatchAction action = new DispatchAction();

        action.setDispatchActionId(rs.getLong("dispatch_action_id"));
        action.setReservationID(rs.getInt("reservation_id"));
        action.setReservationLineItemID(rs.getObject("reservation_line_item_id", Integer.class));

        action.setMovementOrderID(rs.getObject("MovementOrderID", Long.class));
        action.setMovementOrderLineID(rs.getObject("MovementOrderLineID", Long.class));

        action.setEquipmentNumber(rs.getInt("equipment_number"));
        action.setActionType(DispatchActionType.valueOf(rs.getString("action_type")));
        action.setStatus(DispatchActionStatus.valueOf(rs.getString("status")));

        // ⭐ NEW: source_type
        String src = rs.getString("source_type");
        if (src != null) {
            action.setSourceType(DispatchSourceType.valueOf(src));
        }

        action.setGoogleEventId(rs.getString("google_event_id"));
        action.setGoogleCalendarId(rs.getString("google_calendar_id"));

        Timestamp syncTs = rs.getTimestamp("last_calendar_sync_at");
        action.setLastCalendarSyncAt(syncTs != null ? syncTs.toInstant() : null);

        Timestamp completedTs = rs.getTimestamp("completed_at");
        action.setCompletedAt(completedTs != null ? completedTs.toLocalDateTime() : null);

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

        action.setFromYardId(rs.getObject("from_yard_id", Long.class));
        action.setFromLocationName(rs.getString("from_location_name"));

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

        action.setToYardId(rs.getObject("to_yard_id", Long.class));
        action.setToLocationName(rs.getString("to_location_name"));

        Timestamp schedTs = rs.getTimestamp("scheduled_datetime");
        action.setScheduledDateTime(schedTs != null ? schedTs.toLocalDateTime() : null);

        action.setDriverId(rs.getObject("driver_id", Long.class));
        action.setDriverToken(rs.getString("driver_token"));
        action.setNotes(rs.getString("notes"));

        return action;
    }
}
