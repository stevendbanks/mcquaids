package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.model.MovementOrderLine;
import com.mcquaids.model.enums.MovementOrderLineStatus;

public class MovementOrderLineDAO {

    private final NamedParameterJdbcTemplate template;

    public MovementOrderLineDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    // ------------------------------------------------------------
    // SQL
    // ------------------------------------------------------------

    private static final String INSERT_SQL =
        "INSERT INTO movement_order_line (" +
        "MovementOrderID, EquipmentNumber, LineStatus, CreatedBy, CreatedDateTime" +
        ") VALUES (" +
        ":MovementOrderID, :EquipmentNumber, :LineStatus, :CreatedBy, :CreatedDateTime" +
        ")";

    private static final String UPDATE_STATUS_SQL =
        "UPDATE movement_order_line SET LineStatus = :LineStatus " +
        "WHERE MovementOrderLineID = :MovementOrderLineID";

    private static final String SELECT_BY_ID_SQL =
        "SELECT * FROM movement_order_line WHERE MovementOrderLineID = :MovementOrderLineID";

    private static final String SELECT_BY_ORDER_SQL =
        "SELECT * FROM movement_order_line WHERE MovementOrderID = :MovementOrderID " +
        "ORDER BY MovementOrderLineID ASC";

    private static final String DELETE_SQL =
        "DELETE FROM movement_order_line WHERE MovementOrderLineID = :lineId";

    // ------------------------------------------------------------
    // INSERT (KeyHolder)
    // ------------------------------------------------------------

    public long insertLine(MovementOrderLine line) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", line.getMovementOrderId())
                .addValue("EquipmentNumber", line.getEquipmentNumber())
                .addValue("LineStatus", line.getLineStatus().name())
                .addValue("CreatedBy", line.getCreatedBy())
                .addValue("CreatedDateTime", line.getCreatedDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(INSERT_SQL, params, keyHolder, new String[]{"MovementOrderLineID"});

        Number key = keyHolder.getKey();
        if (key != null) {
            long id = key.longValue();
            line.setMovementOrderLineId(id);
            return id;
        }

        return -1;
    }

    // ------------------------------------------------------------
    // UPDATE STATUS
    // ------------------------------------------------------------

    public void updateLineStatus(long movementOrderLineId, MovementOrderLineStatus status) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderLineID", movementOrderLineId)
                .addValue("LineStatus", status.name());

        template.update(UPDATE_STATUS_SQL, params);
    }

    // ------------------------------------------------------------
    // SELECT BY ID
    // ------------------------------------------------------------

    public MovementOrderLine getLineById(long movementOrderLineId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderLineID", movementOrderLineId);

        List<MovementOrderLine> list = template.query(
                SELECT_BY_ID_SQL,
                params,
                (rs, rowNum) -> mapRow(rs)
        );

        return list.isEmpty() ? null : list.get(0);
    }

    // ------------------------------------------------------------
    // SELECT BY ORDER ID
    // ------------------------------------------------------------

    public List<MovementOrderLine> getLinesByOrderId(long movementOrderId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", movementOrderId);

        return template.query(
                SELECT_BY_ORDER_SQL,
                params,
                (rs, rowNum) -> mapRow(rs)
        );
    }

    // ------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------

    public void deleteLine(long lineId) {
        Map<String, Object> params = Map.of("lineId", lineId);
        template.update(DELETE_SQL, params);
    }

    // ------------------------------------------------------------
    // ROW MAPPER
    // ------------------------------------------------------------

    private MovementOrderLine mapRow(ResultSet rs) throws SQLException {

        MovementOrderLine line = new MovementOrderLine();

        line.setMovementOrderLineId(rs.getLong("MovementOrderLineID"));
        line.setMovementOrderId(rs.getLong("MovementOrderID"));

        Object eqObj = rs.getObject("EquipmentNumber");
        line.setEquipmentNumber(eqObj != null ? ((Number) eqObj).intValue() : null);

        line.setLineStatus(
                MovementOrderLineStatus.valueOf(rs.getString("LineStatus"))
        );

        line.setCreatedBy(rs.getString("CreatedBy"));

        if (rs.getTimestamp("CreatedDateTime") != null) {
            line.setCreatedDate(rs.getTimestamp("CreatedDateTime").toLocalDateTime());
        }

        return line;
    }
}
