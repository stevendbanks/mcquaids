/**
 * 
 */
package com.mcquaids.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.model.lookup.CodeValue;


/**
 * 
 */
public class CodeValueDAO {

	private JdbcTemplate jdbcTemplate;

	public CodeValueDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



    public List<CodeValue> queryLookupTable(String tableName) {

        // --- SAFETY CHECK: prevent SQL injection ---
        if (tableName == null || !tableName.matches("^lkp_[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Invalid lookup table name: " + tableName);
        }

        // Build SQL dynamically (table name cannot be parameterized)
        String sql = "SELECT CodeValue AS CodeValue, EnglishDescription AS EnglishDescription " +
                     "FROM " + tableName + " ORDER BY CodeValue";

        // No parameters needed, but using NamedParameterJdbcTemplate for consistency
        NamedParameterJdbcTemplate named =
                new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        return named.query(sql, new BeanPropertyRowMapper<>(CodeValue.class));
    }


	public List<CodeValue> queryCodeValues(String pCodeTypeTableEnglishDescription) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	    StringBuilder sql = new StringBuilder("SELECT cv.CodeValue as CodeValue, cv.EnglishDescription as EnglishDescription FROM CodeType ct INNER JOIN CodeValue cv ON ct.id = cv.CodeType WHERE ct.EnglishDescription = :EnglishDesciption AND ct.id = cv.CodeType");
	    parameters.addValue("EnglishDesciption", pCodeTypeTableEnglishDescription);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        return  namedParameterJdbcTemplate.query(sql.toString(), parameters, new BeanPropertyRowMapper<>(CodeValue.class));
	    
	}

	public List<CodeValue> findCodeTableTypes(String pIsTypeOfEquipment) {
		
	  MapSqlParameterSource parameters = new MapSqlParameterSource();
	  StringBuilder sql = new StringBuilder("SELECT ID as CodeValue, EnglishDescription FROM CodeType WHERE IsTypeOfEquipment = :isTypeOfEquipment");
	  parameters.addValue("isTypeOfEquipment", pIsTypeOfEquipment);

      NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
      
      return  namedParameterJdbcTemplate.query(sql.toString(), parameters,  new BeanPropertyRowMapper<>(CodeValue.class));
	    
	}
	

	public CodeValue findCodeValue(int pCodeType, String pCodeValue) {		
		
		  MapSqlParameterSource parameters = new MapSqlParameterSource();
		  StringBuilder sql = new StringBuilder("SELECT * FROM CodeValue WHERE CodeType = :CodeType and CodeValue = :CodeValue");
		  parameters.addValue("CodeType", pCodeType);
		  parameters.addValue("CodeValue", pCodeValue);

	      NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
	      
	      return  namedParameterJdbcTemplate.queryForObject(sql.toString(), parameters,  new BeanPropertyRowMapper<>(CodeValue.class));
		    
		}

	
	

}
