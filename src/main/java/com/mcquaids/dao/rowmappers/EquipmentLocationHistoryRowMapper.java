package com.mcquaids.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.EquipmentLocationHistory;

public class EquipmentLocationHistoryRowMapper implements RowMapper<EquipmentLocationHistory> {

    @Override
    public EquipmentLocationHistory mapRow(ResultSet rs, int rowNum) throws SQLException {

        EquipmentLocationHistory history = new EquipmentLocationHistory();

        history.setLocationHistoryID(rs.getLong("LocationHistoryID"));
        history.setEquipmentNumber(rs.getInt("EquipmentNumber"));
        history.setStreet(rs.getString("Street"));
        history.setCity(rs.getString("City"));
        history.setProvince(rs.getString("Province"));
        history.setPostal(rs.getString("Postal"));
        history.setCountry(rs.getString("Country"));
        history.setLocationType(rs.getString("LocationType"));
        history.setStartDateTime(rs.getObject("StartDateTime", LocalDateTime.class));
        history.setEndDateTime(rs.getObject("EndDateTime", LocalDateTime.class));
        history.setReservationId(rs.getInt("ReservationID"));
        history.setNotes(rs.getString("Notes"));

        return history;
    }
}