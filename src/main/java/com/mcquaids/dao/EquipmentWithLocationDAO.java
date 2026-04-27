package com.mcquaids.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.dao.rowmappers.EquipmentWithLocationRowMapper;
import com.mcquaids.model.EquipmentWithLocation;
import com.mcquaids.service.DaoDataSource;

public class EquipmentWithLocationDAO {

    private final NamedParameterJdbcTemplate template;

    public EquipmentWithLocationDAO() {
        this.template = DaoDataSource.namedParameterJdbcTemplate;;
    }

    private static final String SELECT_ONE_SQL =
        "SELECT * FROM equipment_view_with_location WHERE EquipmentNumber = :equipmentNumber";

    private static final String SELECT_MANY_SQL =
        "SELECT * FROM equipment_view_with_location WHERE EquipmentNumber IN (:equipmentNumbers)";

    public EquipmentWithLocation getEquipment(int equipmentNumber) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentNumber", equipmentNumber);

        return template.queryForObject(
                SELECT_ONE_SQL,
                params,
                new EquipmentWithLocationRowMapper()
        );
    }

    public List<EquipmentWithLocation> getEquipmentList(List<Integer> equipmentNumbers) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentNumbers", equipmentNumbers);

        return template.query(
                SELECT_MANY_SQL,
                params,
                new EquipmentWithLocationRowMapper() 
        );
    }
}
