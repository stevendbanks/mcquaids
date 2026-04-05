package com.mcquaids.dao;

import com.mcquaids.model.Address;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchActionStatus;
import com.mcquaids.model.DispatchActionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

<<<<<<< HEAD
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
=======
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
>>>>>>> origin/main
import java.util.List;

public class DispatchActionDAO {

    private final JdbcTemplate jdbcTemplate;

    public DispatchActionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

<<<<<<< HEAD
 // ------------------------------------------------------------
 // INSERT
 // ------------------------------------------------------------
 public DispatchAction insert(DispatchAction action) {

     String sql =
         "INSERT INTO dispatch_action (" +
         " reservation_id," +
         " reservation_line_item_id," +
         " equipment_number," +
         " action_type," +
         " status," +
         " from_street, from_city, from_province, from_postal, from_country," +
         " from_yard_id, from_location_name," +
         " to_street, to_city, to_province, to_postal, to_country," +
         " to_yard_id, to_location_name," +
         " scheduled_datetime," +
         " driver_id," +
         " driver_token," +
         " notes," +
         " completed_at" +
         ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

     KeyHolder keyHolder = new GeneratedKeyHolder();

     jdbcTemplate.update(connection -> {
         PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

         ps.setObject(1, action.getReservationID());
         ps.setObject(2, action.getReservationLineItemID());
         ps.setInt(3, action.getEquipmentNumber());
         ps.setString(4, action.getActionType().name());
         ps.setString(5, action.getStatus().name());

         // FROM address
         Address from = action.getFromAddress();
         if (from != null) {
             ps.setString(6, from.getStreet());
             ps.setString(7, from.getCity());
             ps.setString(8, from.getProvince());
             ps.setString(9, from.getPostalCode());
             ps.setString(10, from.getCountry());
         } else {
             ps.setNull(6, Types.VARCHAR);
             ps.setNull(7, Types.VARCHAR);
             ps.setNull(8, Types.VARCHAR);
             ps.setNull(9, Types.VARCHAR);
             ps.setNull(10, Types.VARCHAR);
         }

         // FROM yard metadata
         ps.setObject(11, action.getFromYardId());
         ps.setString(12, action.getFromLocationName());

         // TO address
         Address to = action.getToAddress();
         if (to != null) {
             ps.setString(13, to.getStreet());
             ps.setString(14, to.getCity());
             ps.setString(15, to.getProvince());
             ps.setString(16, to.getPostalCode());
             ps.setString(17, to.getCountry());
         } else {
             ps.setNull(13, Types.VARCHAR);
             ps.setNull(14, Types.VARCHAR);
             ps.setNull(15, Types.VARCHAR);
             ps.setNull(16, Types.VARCHAR);
             ps.setNull(17, Types.VARCHAR);
         }

         // TO yard metadata
         ps.setObject(18, action.getToYardId());
         ps.setString(19, action.getToLocationName());

         // Scheduled date
         if (action.getScheduledDateTime() != null) {
             ps.setObject(20, action.getScheduledDateTime());
         } else {
             ps.setNull(20, Types.TIMESTAMP);
         }

         ps.setObject(21, action.getDriverId());
         ps.setObject(22, action.getDriverToken());
         ps.setString(23, action.getNotes());

         // ⭐ NEW: completed_at
         if (action.getCompletedAt() != null) {
             ps.setTimestamp(24, Timestamp.valueOf(action.getCompletedAt()));
         } else {
             ps.setNull(24, Types.TIMESTAMP);
         }

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
    public List<DispatchAction> findByReservationId(Integer reservationId) {

        String sql = "SELECT * FROM dispatch_action WHERE reservation_id = ? ORDER BY dispatch_action_id";

        return jdbcTemplate.query(sql, new Object[]{reservationId}, (rs, rowNum) -> mapRow(rs));
    }

    // ------------------------------------------------------------
    // FIND BY ID
    // ------------------------------------------------------------
    public DispatchAction getByDispatchActionID(Long dispatchActionId) {

        String sql = "SELECT * FROM dispatch_action WHERE dispatch_action_id = ?";

        return jdbcTemplate.query(sql, ps -> ps.setLong(1, dispatchActionId), rs -> {
            if (!rs.next()) return null;
            return mapRow(rs);
        });
    }

    // ------------------------------------------------------------
    // UPDATE (status + removed flag)
    // ------------------------------------------------------------
    public void update(DispatchAction action) {

        String sql =
            "UPDATE dispatch_action SET " +
            " removed_from_reservation = ?, " +
            " status = ?, " +
            " from_yard_id = ?, from_location_name = ?, " +
            " to_yard_id = ?, to_location_name = ? " +
            "WHERE dispatch_action_id = ?";

        jdbcTemplate.update(sql,
            action.isRemovedFromReservation(),
            action.getStatus().name(),
            action.getFromYardId(),
            action.getFromLocationName(),
            action.getToYardId(),
            action.getToLocationName(),
            action.getDispatchActionId()
        );
    }
    
    
    public void markCompleted(DispatchAction action) {

        String sql =
            "UPDATE dispatch_action SET " +
            " status = ?, " +
            " notes = ?, " +
            " completed_at = ? " +
            "WHERE dispatch_action_id = ?";

        jdbcTemplate.update(sql,
            DispatchActionStatus.COMPLETED.name(),
            action.getNotes(),
            action.getCompletedAt() != null ? Timestamp.valueOf(action.getCompletedAt()) : null,
            action.getDispatchActionId()
        );

        action.setStatus(DispatchActionStatus.COMPLETED);
    }

    public void markCancelled(DispatchAction action) {
        String sql = "UPDATE dispatch_action SET status = ? WHERE dispatch_action_id = ?";
        jdbcTemplate.update(sql, DispatchActionStatus.CANCELLED.name(), action.getDispatchActionId());
        action.setStatus(DispatchActionStatus.CANCELLED);
    }
    
    
    public void markInProgress(DispatchAction action) {
        String sql = "UPDATE dispatch_action SET status = ? WHERE dispatch_action_id = ?";
        jdbcTemplate.update(sql, DispatchActionStatus.IN_PROGRESS.name(), action.getDispatchActionId());
        action.setStatus(DispatchActionStatus.IN_PROGRESS);
    }

    public void markPending(DispatchAction action) {
        String sql = "UPDATE dispatch_action SET status = ? WHERE dispatch_action_id = ?";
        jdbcTemplate.update(sql, DispatchActionStatus.PENDING.name(), action.getDispatchActionId());
        action.setStatus(DispatchActionStatus.PENDING);
    }
    
    
    

    // ------------------------------------------------------------
    // UPDATE FROM CALENDAR SYNC
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

    public void updateCalendarLinkage(Long dispatchActionId, String eventId, String calendarId, Instant now) {

        String sql =
            "UPDATE dispatch_action " +
            "SET google_event_id = ?, " +
            "    google_calendar_id = ?, " +
            "    last_calendar_sync_at = ? " +
            "WHERE dispatch_action_id = ?";

        jdbcTemplate.update(sql,
                eventId,
                calendarId,
                Timestamp.from(now),
                dispatchActionId
        );
    }    
    
    // ------------------------------------------------------------
    // FIND BY GOOGLE EVENT ID
    // ------------------------------------------------------------
    public DispatchAction getByGoogleEventId(String googleEventId) {

        String sql = "SELECT * FROM dispatch_action WHERE google_event_id = ?";

        return jdbcTemplate.query(sql, ps -> ps.setString(1, googleEventId), rs -> {
            if (!rs.next()) return null;
            return mapRow(rs);
        });
    }

    // ------------------------------------------------------------
    // CLEAR CALENDAR LINKAGE
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

    // ------------------------------------------------------------
    // ROW MAPPER
    // ------------------------------------------------------------
    private DispatchAction mapRow(ResultSet rs) throws SQLException {

        DispatchAction action = new DispatchAction();

        action.setDispatchActionId(rs.getLong("dispatch_action_id"));
        action.setReservationID(rs.getInt("reservation_id"));
        action.setReservationLineItemID(rs.getObject("reservation_line_item_id", Integer.class));
        action.setEquipmentNumber(rs.getInt("equipment_number"));
        action.setActionType(DispatchActionType.valueOf(rs.getString("action_type")));
        action.setStatus(DispatchActionStatus.valueOf(rs.getString("status")));

        action.setGoogleEventId(rs.getString("google_event_id"));
        action.setGoogleCalendarId(rs.getString("google_calendar_id"));

        Timestamp syncTs = rs.getTimestamp("last_calendar_sync_at");
        action.setLastCalendarSyncAt(syncTs != null ? syncTs.toInstant() : null);
        
        Timestamp completedTs = rs.getTimestamp("completed_at");
        action.setCompletedAt(completedTs != null ? completedTs.toLocalDateTime() : null);

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

        // FROM yard metadata
        action.setFromYardId(rs.getObject("from_yard_id", Long.class));
        action.setFromLocationName(rs.getString("from_location_name"));

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

        // TO yard metadata
        action.setToYardId(rs.getObject("to_yard_id", Long.class));
        action.setToLocationName(rs.getString("to_location_name"));

        Timestamp schedTs = rs.getTimestamp("scheduled_datetime");
        action.setScheduledDateTime(schedTs != null ? schedTs.toLocalDateTime() : null);

        action.setDriverId(rs.getObject("driver_id", Long.class));
        action.setDriverToken(rs.getString("driver_token"));
        action.setNotes(rs.getString("notes"));

        return action;
    }

    private Timestamp toTimestamp(Instant instant) {
        return instant == null ? null : Timestamp.from(instant);
=======
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
>>>>>>> origin/main
    }
}