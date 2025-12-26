package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.model.EquipmentLocation;

public class EquipmentLocationDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public EquipmentLocationDAO(NamedParameterJdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    public EquipmentLocationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
	}



    
    
    public void insertEquipmentLocation(EquipmentLocation equipmentLocation) {
        String sql = "INSERT INTO equipment_location (equipmentNumber, locationID, locationDescription, street, city, provinceCode, postalCode, latitude, longitude, createdDatetime, createdUserID) " +
                     "VALUES (:equipmentNumber, :locationID, :locationDescription, :street, :city, :provinceCode, :postalCode, :latitude, :longitude, :createdDatetime, :createdUserID)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("equipmentNumber", equipmentLocation.getEquipmentNumber());
        params.addValue("locationID", equipmentLocation.getLocationID());
        params.addValue("locationDescription", equipmentLocation.getLocationDescription());
        params.addValue("street", equipmentLocation.getStreet());
        params.addValue("city", equipmentLocation.getCity());
        params.addValue("provinceCode", equipmentLocation.getProvinceCode());
        params.addValue("postalCode", equipmentLocation.getPostalCode());
        params.addValue("latitude", equipmentLocation.getLatitude());
        params.addValue("longitude", equipmentLocation.getLongitude());
        params.addValue("createdDatetime", equipmentLocation.getCreatedDatetime());
        params.addValue("createdUserID", equipmentLocation.getCreatedUserID());

        jdbcTemplate.update(sql, params);
    }

    public List<EquipmentLocation> findEquipmentLocationHistoryByEquipmentNumber(String equipmentNumber) {
        String sql = "SELECT * FROM equipment_location WHERE equipmentNumber = :equipmentNumber ORDER BY createdDatetime DESC";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("equipmentNumber", equipmentNumber);

        List<EquipmentLocation> equipmentLocations = jdbcTemplate.query(sql, params, new EquipmentLocationRowMapper());
        if (equipmentLocations.isEmpty()) {
            return null;
        } else {
            return equipmentLocations;
        }
    }
    
    public EquipmentLocation findEquipmentsCurrentLocationByEquipmentNumber(String equipmentNumber) {
        String sql = "SELECT * FROM equipment_location WHERE equipmentNumber = :equipmentNumber ORDER BY createdDatetime DESC LIMIT 1";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("equipmentNumber", equipmentNumber);

        EquipmentLocation equipmentLocation = jdbcTemplate.queryForObject(sql, params, new EquipmentLocationRowMapper());

            return equipmentLocation;
        }
    }    
    

    final class EquipmentLocationRowMapper implements RowMapper<EquipmentLocation> {
        public EquipmentLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
            EquipmentLocation equipmentLocation = new EquipmentLocation();
            equipmentLocation.setEquipmentNumber(rs.getString("equipmentNumber"));
            equipmentLocation.setLocationID(rs.getString("locationID"));
            equipmentLocation.setLocationDescription(rs.getString("locationDescription"));
            equipmentLocation.setStreet(rs.getString("street"));
            equipmentLocation.setCity(rs.getString("city"));
            equipmentLocation.setProvinceCode(rs.getString("provinceCode"));
            equipmentLocation.setPostalCode(rs.getString("postalCode"));
            equipmentLocation.setLatitude(rs.getDouble("latitude"));
            equipmentLocation.setLongitude(rs.getDouble("longitude"));
            equipmentLocation.setCreatedDatetime(new Date(rs.getTimestamp("createdDatetime").getTime()));
            equipmentLocation.setCreatedUserID(rs.getString("createdUserID"));
            return equipmentLocation;
        }
    }
    
    