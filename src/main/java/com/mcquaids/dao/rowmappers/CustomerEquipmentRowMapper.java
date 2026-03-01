package com.mcquaids.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.CustomerEquipmentDTO;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.utils.PropertyHydrator;

public class CustomerEquipmentRowMapper implements RowMapper<CustomerEquipmentDTO> {

    @Override
    public CustomerEquipmentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerEquipmentDTO dto = new CustomerEquipmentDTO();

        dto.setReservationID(rs.getInt("ReservationID"));
        dto.setCustomerID(rs.getString("CustomerID"));
        dto.setStartDate(rs.getDate("StartDate"));
        dto.setEndDate(rs.getDate("EndDate"));
        dto.setReservationStatusCode(rs.getString("ReservationStatusCode"));
        dto.setReservationStatusCodeText(rs.getString("ReservationStatusCodeText"));

        dto.setReservationLineItem(rs.getInt("reservationLineItem"));
        dto.setEquipmentNumber(rs.getInt("EquipmentNumber"));
        dto.setEquipmentType(rs.getInt("EquipmentType"));
        dto.setEquipmentSubType(rs.getString("EquipmentSubType"));
        dto.setEquipmentTypeText(rs.getString("EquipmentTypeText"));  
        dto.setEquipmentSubTypeText(rs.getString("EquipmentSubTypeText"));    // NEW

        
        dto.setEquipmentNotes(rs.getString("EquipmentNotes"));


        return dto;
    }
}