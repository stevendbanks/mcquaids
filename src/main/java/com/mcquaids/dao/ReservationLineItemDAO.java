package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.model.ReservationLineItem;
import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.utils.JsonUtils;
import com.mcquaids.utils.PropertyHydrator;

public class ReservationLineItemDAO {

    private final NamedParameterJdbcTemplate template;

    public ReservationLineItemDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public ReservationLineItemDAO(JdbcTemplate jdbcTemplate) {
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    }

    // ------------------------------------------------------------
    // CREATE
    // ------------------------------------------------------------
    public Integer createReservationLineItem(
            Integer reservationID, 
            Integer equipmentNumber,
            String lineItemNotes) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO reservation_line_item "
                   + "(ReservationID, EquipmentNumber, LineItemNotes, DateAdded) "
                   + "VALUES (:ReservationID, :EquipmentNumber, :LineItemNotes, :DateAdded)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);
        params.addValue("EquipmentNumber", equipmentNumber);
        params.addValue("LineItemNotes", lineItemNotes);
        params.addValue("DateAdded", new java.sql.Date(System.currentTimeMillis()));

        template.update(sql, params, keyHolder, new String[]{"reservationLineItem"});

        return keyHolder.getKey().intValue();
    }

    // ------------------------------------------------------------
    // READ (single)
    // ------------------------------------------------------------
    public ReservationLineItem getReservationLineItem(int reservationLineItemID) {
        String sql = "SELECT * FROM reservation_line_item "
                   + "WHERE reservationLineItem = :reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationLineItem", reservationLineItemID);

        return template.queryForObject(sql, params, new ReservationLineItemRowMapper());
    }

    // ------------------------------------------------------------
    // READ (all for reservation)
    // ------------------------------------------------------------
    public List<ReservationLineItem> getReservedEquipmentByReservationID(Integer reservationID) {
        String sql = "SELECT * FROM reservation_line_item "
                   + "WHERE ReservationID = :ReservationID "
                   + "ORDER BY reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);

        return template.query(sql, params, new ReservationLineItemRowMapper());
    }

    // ------------------------------------------------------------
    // UPDATE
    // ------------------------------------------------------------
    public boolean updateReservationLineItem(ReservationLineItem item) {
        String sql = "UPDATE reservation_line_item SET "
                   + "EquipmentNumber = :EquipmentNumber, "
                   + "LineItemNotes = :LineItemNotes "
                   + "WHERE reservationLineItem = :reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationLineItem", item.getReservationLineItemID());
        params.addValue("EquipmentNumber", item.getEquipmentNumber());
        params.addValue("LineItemNotes", item.getLineItemNotes());

        try {
            return template.update(sql, params) > 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------
    public void deleteReservationLineItem(int reservationLineItemID) {
    	
    	System.out.println("deleteReservationLineItem.reservationLineItemID=" + reservationLineItemID);
        String sql = "DELETE FROM reservation_line_item "
                   + "WHERE reservationLineItem = :reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationLineItem", reservationLineItemID);

        template.update(sql, params);
    }

    // ------------------------------------------------------------
    // VIEW (list)
    // ------------------------------------------------------------
    public List<ReservationLineItemDTO> getReservationLineItems(Integer reservationID) {
        String sql = "SELECT * FROM reservation_line_item_view "
                   + "WHERE ReservationID = :ReservationID "
                   + "ORDER BY reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);

        return template.query(sql, params, new ReservationLineItemDTORowMapper());
    }

    // ------------------------------------------------------------
    // VIEW (single)
    // ------------------------------------------------------------
    public ReservationLineItemDTO viewReservationLineItem(Integer reservationLineItemID) {
        String sql = "SELECT * FROM reservation_line_item_view "
                   + "WHERE reservationLineItem = :reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationLineItem", reservationLineItemID);

        return template.queryForObject(sql, params, new ReservationLineItemDTORowMapper());
    }

    // ------------------------------------------------------------
    // RowMapper for base model
    // ------------------------------------------------------------
    private static class ReservationLineItemRowMapper implements RowMapper<ReservationLineItem> {
        @Override
        public ReservationLineItem mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReservationLineItem item = new ReservationLineItem();

            item.setReservationLineItemID(rs.getInt("reservationLineItem"));
            item.setReservationID(rs.getInt("ReservationID"));
            item.setEquipmentNumber(rs.getInt("EquipmentNumber"));
            item.setLineItemNotes(rs.getString("LineItemNotes"));

            java.sql.Date sqlDate = rs.getDate("DateAdded");
            if (sqlDate != null) {
                item.setDateAdded(sqlDate.toLocalDate());
            }

            return item;
        }
    }

    // ------------------------------------------------------------
    // RowMapper for DTO (view)
    // ------------------------------------------------------------
    private static class ReservationLineItemDTORowMapper implements RowMapper<ReservationLineItemDTO> {
        @Override
        public ReservationLineItemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReservationLineItemDTO dto = new ReservationLineItemDTO();

            // Base fields
            dto.setReservationLineItemID(rs.getInt("reservationLineItem"));
            dto.setReservationID(rs.getInt("ReservationID"));
            dto.setEquipmentNumber(rs.getInt("EquipmentNumber"));
            dto.setLineItemNotes(rs.getString("LineItemNotes"));

            java.sql.Date sqlDate = rs.getDate("DateAdded");
            if (sqlDate != null) {
                dto.setDateAdded(sqlDate.toLocalDate());
            }

            // Equipment metadata
            dto.setEquipmentTypeText(rs.getString("EquipmentTypeText"));
            dto.setEquipmentSubTypeText(rs.getString("EquipmentSubTypeText"));
            dto.setSerialNumber(rs.getString("SerialNumber"));
            dto.setManufacturer(rs.getString("Manufacturer"));
            dto.setEquipmentNotes(rs.getString("EquipmentNotes"));
            
            String propsJson = rs.getString("EquipmentProperties");

            if (propsJson != null && !propsJson.isEmpty()) {
                // 1) JSON → Map (raw, with codes)
                var rawProps = JsonUtils.setPropertiesFromJson(propsJson);

                // 2) Map (codes) → Map (text) using your existing hydrator
                var hydratedProps = PropertyHydrator.hydrateFromJson(JsonUtils.toJson(rawProps));

                // 3) Store hydrated values in the DTO
                dto.setEquipmentProperties(hydratedProps);
            }

            return dto;
        }
    }
}