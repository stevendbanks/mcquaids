package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.mcquaids.model.EquipmentCategory;

public class EquipmentCategoryDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    


	public EquipmentCategoryDao(JdbcTemplate jdbcTemplate) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		
	}

    public EquipmentCategoryDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void saveEquipmentCategory(EquipmentCategory equipmentCategory) {
        String sql = "INSERT INTO equipment_categories (CategoryID, EquipmentType, EquipmentSubType, Properties, HaulingEquipment) " +
                     "VALUES (:categoryId, :equipmentType, :equipmentSubType, :properties, :haulingEquipment)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("categoryId", equipmentCategory.getCategoryId())
                .addValue("equipmentType", equipmentCategory.getEquipmentType())
                .addValue("equipmentSubType", equipmentCategory.getEquipmentSubType())
                .addValue("properties", equipmentCategory.getProperties())
                .addValue("haulingEquipment", equipmentCategory.getHaulingEquipment());
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public EquipmentCategory getEquipmentCategoryById(int categoryId) {
        String sql = "SELECT * FROM equipment_categories WHERE CategoryID = :categoryId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("categoryId", categoryId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new EquipmentCategoryRowMapper());
    }

    public List<EquipmentCategory> getAllEquipmentCategories() {
        String sql = "SELECT * FROM equipment_categories";
        return namedParameterJdbcTemplate.query(sql, new EquipmentCategoryRowMapper());
    }

    
    public List<EquipmentCategory> queryEquipmentCategories(EquipmentCategory equipmentCategory) {
        StringBuilder sql = new StringBuilder("SELECT * FROM equipment_categories WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (equipmentCategory.getCategoryId() != 0) {
            sql.append(" AND CategoryID = :categoryId");
            params.put("categoryId", equipmentCategory.getCategoryId());
        }
        if (equipmentCategory.getEquipmentType() != 0) {
            sql.append(" AND EquipmentType = :equipmentType");
            params.put("equipmentType", equipmentCategory.getEquipmentType());
        }
        if (equipmentCategory.getEquipmentSubType() != null && !equipmentCategory.getEquipmentSubType().isEmpty()) {
            sql.append(" AND EquipmentSubType = :equipmentSubType");
            params.put("equipmentSubType", equipmentCategory.getEquipmentSubType());
        }
        if (equipmentCategory.getProperties() != null && !equipmentCategory.getProperties().isEmpty()) {
            sql.append(" AND Properties = :properties");
            params.put("properties", equipmentCategory.getProperties());
        }
        if (equipmentCategory.getHaulingEquipment() != null && !equipmentCategory.getHaulingEquipment().isEmpty()) {
            sql.append(" AND HaulingEquipment = :haulingEquipment");
            params.put("haulingEquipment", equipmentCategory.getHaulingEquipment());
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource(params);
        return namedParameterJdbcTemplate.query(sql.toString(), namedParameters, new EquipmentCategoryRowMapper());
    }    
    
    
    private static final class EquipmentCategoryRowMapper implements RowMapper<EquipmentCategory> {
        public EquipmentCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            EquipmentCategory equipmentCategory = new EquipmentCategory();
            equipmentCategory.setCategoryId(rs.getInt("CategoryID"));
            equipmentCategory.setEquipmentType(rs.getInt("EquipmentType"));
            equipmentCategory.setEquipmentSubType(rs.getString("EquipmentSubType"));
            equipmentCategory.setProperties(rs.getString("Properties"));
            equipmentCategory.setHaulingEquipment(rs.getString("HaulingEquipment"));
            return equipmentCategory;
        }
    }
}
