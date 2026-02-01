package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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

    private NamedParameterJdbcTemplate template;

    public ReservationLineItemDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public ReservationLineItemDAO(JdbcTemplate jdbcTemplate) {
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    }

    // CREATE
    public int createReservationLineitem(
            String reservationID,
            int equipmentType,
            String equipmentSubType,
            int quantity,
            String notes,
            Map<String, String> properties) throws DuplicateKeyException {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            String sql = "INSERT INTO reservation_line_item "
                    + "(ReservationID, EquipmentType, EquipmentSubType, Quantity, Notes, Properties, DateAdded) "
                    + "VALUES (:ReservationID, :EquipmentType, :EquipmentSubType, :Quantity, :Notes, :Properties, :DateAdded)";

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("ReservationID", reservationID);
            params.addValue("EquipmentType", equipmentType);
            params.addValue("EquipmentSubType", equipmentSubType);
            params.addValue("Quantity", quantity);
            params.addValue("Notes", notes);
            params.addValue("Properties", JsonUtils.toJson(properties));
            params.addValue("DateAdded", new java.sql.Date(System.currentTimeMillis()));

            template.update(sql, params, keyHolder, new String[]{"reservationLineItem"});

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return keyHolder.getKey().intValue();
    }

    // READ (single)
    public ReservationLineItem getReservationLineItem(int reservationLineItemID) {
        String sql = "SELECT * FROM reservation_line_item "
                + "WHERE reservationLineItem = :reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationLineItem", reservationLineItemID);

        return template.queryForObject(sql, params, new ReservedEquipmentRowMapper());
    }

    // READ (all for reservation)
    public List<ReservationLineItem> getReservedEquipmentByReservationID(String reservationID) {
        String sql = "SELECT * FROM reservation_line_item WHERE ReservationID = :ReservationID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);

        return template.query(sql, params, new ReservedEquipmentRowMapper());
    }

    // UPDATE
    public boolean updateReservationLineItem(ReservationLineItem req) {
        String sql = "UPDATE reservation_line_item SET "
        		 + "EquipmentNumber = :EquipmentNumber, "
                + "EquipmentSubType = :EquipmentSubType, "
                + "Quantity = :Quantity, "
                + "Notes = :Notes, "
                + "Properties = :Properties "
                + "WHERE reservationLineItem = :reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationLineItem", req.getReservationLineItemID());
        params.addValue("EquipmentNumber", req.getEquipmentNumber());
        params.addValue("EquipmentSubType", req.getEquipmentSubType());
        params.addValue("Quantity", req.getQuantity());
        params.addValue("Notes", req.getNotes());
        params.addValue("Properties", JsonUtils.toJson(req.getProperties()));

        try {
            return template.update(sql, params) > 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public void deleteReservationLineItem(int reservationLineItemID) {
        String sql = "DELETE FROM reservation_line_item "
                + "WHERE reservationLineItem = :reservationLineItem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationLineItem", reservationLineItemID);

        template.update(sql, params);
    }

    // VIEW (list)
    public List<ReservationLineItemDTO> getReservationLineItems(String reservationID) {
        String sql = "SELECT * FROM reservation_line_item_view "
                + "WHERE ReservationID = :ReservationID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);

        return template.query(sql, params, new ReservationLineItemDTORowMapper());
    }

    // VIEW (single)
    public ReservationLineItemDTO viewReservationLineItem(int reservationLineitemID) {

        String sql = "SELECT * FROM reservation_line_item_view "
                + "WHERE reservationLineItem = :reservationLineitem";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationLineitem", reservationLineitemID);

        return template.queryForObject(sql, params, new ReservationLineItemDTORowMapper());
    }

    // DTO RowMapper
    private static class ReservationLineItemDTORowMapper implements RowMapper<ReservationLineItemDTO> {

        @Override
        public ReservationLineItemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReservationLineItemDTO dto = new ReservationLineItemDTO();

            dto.setReservationLineItemID(rs.getInt("reservationLineItem"));
            dto.setReservationID(rs.getString("ReservationID"));
            dto.setEquipmentNumber(rs.getInt("EquipmentNumber"));
            dto.setEquipmentType(rs.getString("EquipmentType"));
            dto.setEquipmentSubType(rs.getString("EquipmentSubType"));
            dto.setQuantity(rs.getInt("Quantity"));
            dto.setNotes(rs.getString("Notes"));

            String propertiesJson = rs.getString("Properties");
            Map<String, String> hydrated = PropertyHydrator.hydrateFromJson(propertiesJson);
            dto.setProperties(hydrated);    
            
            dto.setDateAdded(rs.getTimestamp("DateAdded"));
            dto.setEquipmentTypeText(rs.getString("EquipmentTypeText"));
            dto.setEquipmentSubTypeText(rs.getString("EquipmentSubTypeText"));

            return dto;
        }
    }

    // Model RowMapper
    private static class ReservedEquipmentRowMapper implements RowMapper<ReservationLineItem> {
        @Override
        public ReservationLineItem mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReservationLineItem req = new ReservationLineItem();

            req.setReservationLineItemID(rs.getInt("reservationLineItem"));
            req.setReservationID(rs.getString("ReservationID"));
            req.setEquipmentNumber(rs.getInt("EquipmentNumber"));
            req.setEquipmentType(rs.getString("EquipmentType"));
            req.setEquipmentSubType(rs.getString("EquipmentSubType"));
            req.setQuantity(rs.getInt("Quantity"));
            req.setNotes(rs.getString("Notes"));

            String propertiesJson = rs.getString("Properties");
            if (propertiesJson != null) {
                req.setProperties(JsonUtils.setPropertiesFromJson(propertiesJson));
            } else {
                req.setProperties(new HashMap<String, String>());
            }

            req.setDateAdded(rs.getDate("DateAdded"));

            return req;
        }
    }
}