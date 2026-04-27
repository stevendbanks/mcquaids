package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.model.MovementOrderEventLog;

public class MovementOrderEventLogDAO {

    private final NamedParameterJdbcTemplate template;

    public MovementOrderEventLogDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    // ------------------------------------------------------------
    // SQL
    // ------------------------------------------------------------

    private static final String INSERT_SQL =
        "INSERT INTO movement_order_event_log (" +
        "MovementOrderID, MovementOrderLineID, EventType, EventDateTime, PerformedBy, Notes" +
        ") VALUES (" +
        ":MovementOrderID, :MovementOrderLineID, :EventType, :EventDateTime, :PerformedBy, :Notes" +
        ")";

    private static final String SELECT_BY_ORDER_SQL =
        "SELECT * FROM movement_order_event_log " +
        "WHERE MovementOrderID = :MovementOrderID " +
        "ORDER BY EventDateTime ASC";

    private static final String SELECT_BY_LINE_SQL =
        "SELECT * FROM movement_order_event_log " +
        "WHERE MovementOrderLineID = :MovementOrderLineID " +
        "ORDER BY EventDateTime ASC";

    // ------------------------------------------------------------
    // INSERT
    // ------------------------------------------------------------

    public long insertEvent(MovementOrderEventLog log) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", log.getMovementOrderId())
                .addValue("MovementOrderLineID", log.getMovementOrderLineId())
                .addValue("EventType", log.getEventType())
                .addValue("EventDateTime", log.getEventDateTime())
                .addValue("PerformedBy", log.getPerformedBy())
                .addValue("Notes", log.getNotes());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(INSERT_SQL, params, keyHolder, new String[]{"EventID"});

        Number key = keyHolder.getKey();
        return key != null ? key.longValue() : -1;
    }

    // ------------------------------------------------------------
    // SELECT BY ORDER
    // ------------------------------------------------------------

    public List<MovementOrderEventLog> listEventsByOrderId(long orderId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", orderId);

        return template.query(
                SELECT_BY_ORDER_SQL,
                params,
                (rs, rowNum) -> mapRow(rs)
        );
    }

    // ------------------------------------------------------------
    // SELECT BY LINE
    // ------------------------------------------------------------

    public List<MovementOrderEventLog> listEventsByLineId(long lineId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderLineID", lineId);

        return template.query(
                SELECT_BY_LINE_SQL,
                params,
                (rs, rowNum) -> mapRow(rs)
        );
    }

    // ------------------------------------------------------------
    // ROW MAPPER
    // ------------------------------------------------------------

    private MovementOrderEventLog mapRow(ResultSet rs) throws SQLException {

        MovementOrderEventLog log = new MovementOrderEventLog();

        log.setEventId(rs.getLong("EventID"));
        log.setMovementOrderId(rs.getLong("MovementOrderID"));

        Object lineObj = rs.getObject("MovementOrderLineID");
        log.setMovementOrderLineId(lineObj != null ? rs.getLong("MovementOrderLineID") : null);

        log.setEventType(rs.getString("EventType"));

        if (rs.getTimestamp("EventDateTime") != null) {
            log.setEventDateTime(rs.getTimestamp("EventDateTime").toLocalDateTime());
        }

        log.setPerformedBy(rs.getString("PerformedBy"));
        log.setNotes(rs.getString("Notes"));

        return log;
    }
}
