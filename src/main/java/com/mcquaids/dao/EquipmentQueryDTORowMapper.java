package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.EquipmentQueryDTO;

public class EquipmentQueryDTORowMapper implements RowMapper<EquipmentQueryDTO> {

    @Override
    public EquipmentQueryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return EquipmentQueryDTO.fromResultSet(rs);
    }
	
}
