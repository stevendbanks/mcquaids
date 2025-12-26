package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.model.LeasedEquipment;
import com.mcquaids.model.LeasedEquipmentView;
import com.mcquaids.utils.JsonUtils;

public class LeaseEquipmentDAO {
    private NamedParameterJdbcTemplate template;

    public LeaseEquipmentDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public LeaseEquipmentDAO(JdbcTemplate jdbcTemplate) {
		template = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
	}

	// Create
    public void createLeasedEquipment(String leaseID, String equipmentNumber, String notes) throws DuplicateKeyException {
    	
        String query = "INSERT INTO lease_equipment (LeaseID, EquipmentNumber, DateAddedToLease, Notes) VALUES (:LeaseID, :EquipmentNumber, :DateAddedToLease, :Notes)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID", leaseID);
        params.addValue("EquipmentNumber", equipmentNumber);
        params.addValue("DateAddedToLease", new java.sql.Date(System.currentTimeMillis()));
        params.addValue("Notes", notes);
        template.update(query, params);
    }

    // Read
    public LeasedEquipment editLeasedEquipment(String pLeaseID, String pEquipmentNumber) {
        String query = "SELECT * FROM lease_equipment WHERE LeaseID = :LeaseID AND EquipmentNumber = :EquipmentNumber";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID", pLeaseID);
        params.addValue("EquipmentNumber", pEquipmentNumber);
        return template.queryForObject(query, params, new LeasedEquipmentRowMapper());
    }

    // Read
    public LeasedEquipmentView editLeasedEquipmentView(String pLeaseID, String pEquipmentNumber) {
        String query = "SELECT * FROM leased_equipment_view WHERE LeaseID = :LeaseID AND EquipmentNumber = :EquipmentNumber";
        
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID", pLeaseID);
        params.addValue("EquipmentNumber", pEquipmentNumber);
        
        return  template.queryForObject(query, params, new LeasedEquipmentViewRowMapper());
    }
    
    
    public boolean updateLeasedEquipment(LeasedEquipment leaseEquipment) {
    	
        String query = "UPDATE lease_equipment SET DateAddedToLease = :DateAddedToLease, DateRemovedFromLease = :DateRemovedFromLease, Notes = :Notes WHERE LeaseID = :LeaseID AND EquipmentNumber = :EquipmentNumber";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID", leaseEquipment.getLeaseID());
        params.addValue("EquipmentNumber", leaseEquipment.getEquipmentNumber());
        params.addValue("DateAddedToLease", leaseEquipment.getDateAddedToLease());
        params.addValue("DateRemovedFromLease", leaseEquipment.getDateRemovedFromLease());
        params.addValue("Notes", leaseEquipment.getNotes());

        try {
            int rowsAffected = template.update(query, params);
            if (rowsAffected == 0) {
                // No rows updated - handle accordingly
                return false;
            }
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Delete
    public void deleteLeasedEquipment(String pLeaseID, String equipmentNumber) {
        String query = "DELETE FROM lease_equipment WHERE LeaseID = :LeaseID AND EquipmentNumber = :EquipmentNumber";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID", pLeaseID);
        params.addValue("EquipmentNumber", equipmentNumber);
        template.update(query, params);
    }


	public List<LeasedEquipment> findAllLeasedEquipmentByLeaseID(String pLeaseID) {
        String query = "SELECT * FROM lease_equipment WHERE LeaseID = :LeaseID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID", pLeaseID);
        return (List<LeasedEquipment>) template.query(query, params, new LeasedEquipmentRowMapper());
    }    


	public List<LeasedEquipmentView> getLeasedEquipmentViewByLeaseID(String leaseID) {
	    String sql = "SELECT * FROM leased_equipment_view WHERE LeaseID = :leaseID";

	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("leaseID", leaseID);

	    return template.query(sql, params, new LeasedEquipmentViewRowMapper());
	}	
	
	
	public class LeasedEquipmentViewRowMapper implements RowMapper<LeasedEquipmentView> {
	    @Override
	    public LeasedEquipmentView mapRow(ResultSet rs, int rowNum) throws SQLException {
	        LeasedEquipmentView view = new LeasedEquipmentView();

	        view.setLeaseID(rs.getString("LeaseID"));
	        view.setEquipmentNumber(rs.getString("EquipmentNumber"));
	        view.setDateAddedToLease(rs.getDate("DateAddedToLease"));
	        view.setDateRemovedFromLease(rs.getDate("DateRemovedFromLease"));
	        view.setSpecialNotes(rs.getString("SpecialNotes"));
	        view.setNotes(rs.getString("Notes"));
	        view.setEquipmentType(rs.getString("EquipmentType"));
	        view.setEquipmentTypeText(rs.getString("equipmentTypeText"));
	        view.setEquipmentSubType(rs.getString("EquipmentSubType"));
	        view.setEquipmentSubTypeText(rs.getString("equipmentSubTypeText"));
	        view.setSerialNumber(rs.getString("SerialNumber"));
	        view.setManufacturer(rs.getString("Manufacturer"));
	        view.setManufacturedDate(rs.getDate("ManufacturedDate"));
	        view.setPurchasePrice(rs.getDouble("PurchasePrice"));
	        view.setPurchaseDate(rs.getDate("PurchaseDate"));
	        view.setInspectionDate(rs.getDate("InspectionDate"));
	        
//          Convert JSON string to Map if needed
          String propertiesJson = rs.getString("Properties");
          if (propertiesJson != null) {
        	  view.setProperties(JsonUtils.setPropertiesFromJson(propertiesJson)); 
          } else {
        	  view.setProperties(new HashMap<>());
          }	        
	        
	        
	        
	        view.setAvailabilityStatusCode(rs.getString("AvailabilityStatusCode"));
	        view.setAvailabilityStatusText(rs.getString("availabilityStatusText"));
	        view.setConditionStatusCode(rs.getString("ConditionStatusCode"));
	        view.setConditionStatusText(rs.getString("conditionStatusText"));
	        view.setMaintenanceStatusCode(rs.getString("MaintenanceStatusCode"));
	        view.setMaintenanceStatusText(rs.getString("maintenanceStatusText"));
	        view.setCleaningStatusCode(rs.getString("CleaningStatusCode"));
	        view.setCleaningStatusTest(rs.getString("cleaningStatusTest"));
	        view.setBookingStatusCode(rs.getString("BookingStatusCode"));
	        view.setBookingStatusCodeText(rs.getString("bookingStatusCodeText"));

	        return view;
	    }
	}
	
    
    private static final class LeasedEquipmentRowMapper implements RowMapper<LeasedEquipment> {
        public LeasedEquipment mapRow(ResultSet rs, int rowNum) throws SQLException {
            LeasedEquipment leaseEquipment = new LeasedEquipment();
            leaseEquipment.setLeaseID(rs.getString("LeaseID"));
            leaseEquipment.setEquipmentNumber(rs.getString("EquipmentNumber"));
            leaseEquipment.setDateAddedToLease(rs.getDate("DateAddedToLease"));
            leaseEquipment.setDateRemovedFromLease(rs.getDate("DateRemovedFromLease"));
            leaseEquipment.setNotes(rs.getString("Notes"));
            return leaseEquipment;
        }
    }


	public LeasedEquipmentView getLeasedEquipmentDetails(String pEquipmentNumber) {
        String query = "SELECT * FROM leased_equipment_view WHERE EquipmentNumber = :EquipmentNumber";
        
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("EquipmentNumber", pEquipmentNumber);
        
        return  template.queryForObject(query, params, new LeasedEquipmentViewRowMapper());

	}

	public boolean updateLeasedEquipmentAdditionalNote(String leaseID, String equipmentNumber, String notes) {
        String query = "UPDATE lease_equipment SET Notes = :Notes WHERE LeaseID = :LeaseID AND EquipmentNumber = :EquipmentNumber";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID",leaseID);
        params.addValue("EquipmentNumber", equipmentNumber);
        params.addValue("Notes", notes);

        try {
            int rowsAffected = template.update(query, params);
            if (rowsAffected == 0) {
                // No rows updated - handle accordingly
                return false;
            }
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
	}


}
