package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.model.MovementOrderSwapLink;

public class MovementOrderSwapLinkDAO {

    private final NamedParameterJdbcTemplate template;

    public MovementOrderSwapLinkDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    // ------------------------------------------------------------
    // SQL
    // ------------------------------------------------------------

    private static final String INSERT_SQL =
        "INSERT INTO movement_order_swap_link (" +
        "MovementOrderID, LineAID, LineBID" +
        ") VALUES (" +
        ":MovementOrderID, :LineAID, :LineBID" +
        ")";

    private static final String SELECT_BY_ORDER_SQL =
        "SELECT * FROM movement_order_swap_link " +
        "WHERE MovementOrderID = :MovementOrderID";

    private static final String DELETE_BY_ORDER_SQL =
        "DELETE FROM movement_order_swap_link WHERE MovementOrderID = :MovementOrderID";

    // ------------------------------------------------------------
    // INSERT
    // ------------------------------------------------------------

    public long insertSwapLink(MovementOrderSwapLink link) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", link.getMovementOrderId())
                .addValue("LineAID", link.getLineAId())
                .addValue("LineBID", link.getLineBId());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(INSERT_SQL, params, keyHolder, new String[]{"SwapLinkID"});

        Number key = keyHolder.getKey();
        return key != null ? key.longValue() : -1;
    }

    // ------------------------------------------------------------
    // SELECT BY ORDER
    // ------------------------------------------------------------

    public MovementOrderSwapLink getSwapLinkByOrderId(long orderId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", orderId);

        List<MovementOrderSwapLink> list = template.query(
                SELECT_BY_ORDER_SQL,
                params,
                (rs, rowNum) -> mapRow(rs)
        );

        return list.isEmpty() ? null : list.get(0);
    }

    // ------------------------------------------------------------
    // DELETE BY ORDER
    // ------------------------------------------------------------

    public void deleteSwapLinkByOrderId(long orderId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", orderId);

        template.update(DELETE_BY_ORDER_SQL, params);
    }

    // ------------------------------------------------------------
    // ROW MAPPER
    // ------------------------------------------------------------

    private MovementOrderSwapLink mapRow(ResultSet rs) throws SQLException {

        MovementOrderSwapLink link = new MovementOrderSwapLink();

        link.setSwapLinkId(rs.getLong("SwapLinkID"));
        link.setMovementOrderId(rs.getLong("MovementOrderID"));
        link.setLineAId(rs.getLong("LineAID"));
        link.setLineBId(rs.getLong("LineBID"));

        return link;
    }
}
