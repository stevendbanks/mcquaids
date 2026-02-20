package com.mcquaids.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.EquipmentLocationHistory;

public class EquipmentLocationHistoryRowMapper implements RowMapper<EquipmentLocationHistory> {

    @Override
    public EquipmentLocationHistory mapRow(ResultSet rs, int rowNum) throws SQLException {

        EquipmentLocationHistory history = new EquipmentLocationHistory();

        history.setLocationHistoryID(rs.getInt("LocationHistoryID"));
        history.setEquipmentNumber(rs.getInt("EquipmentNumber"));
        history.setStreet(rs.getString("Street"));
        history.setCity(rs.getString("City"));
        history.setProvince(rs.getString("Province"));
        history.setPostal(rs.getString("Postal"));
        history.setCountry(rs.getString("Country"));
        history.setLocationType(rs.getString("LocationType"));
        history.setStartDateTime(rs.getTimestamp("StartDateTime"));
        history.setEndDateTime(rs.getTimestamp("EndDateTime"));
        history.setReservationId(rs.getInt("ReservationID"));
        history.setNotes(rs.getString("Notes"));

        return history;
    }
}