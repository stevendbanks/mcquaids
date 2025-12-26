package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.model.Customer;
import com.mcquaids.model.Lease;
import com.mcquaids.model.LeaseQueryDTO;

public class LeaseDAO {
    private NamedParameterJdbcTemplate template;

	public LeaseDAO(JdbcTemplate jdbcTemplate) {
		template = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
	}


	// Create
    public void createLease(Lease lease) {
        String query = "INSERT INTO lease (CustomerID, LeaseID, LeaseSignDate, LeaseTerminationDate, LeaseTerminationReasonCode, LeaseStartDate, LeaseEndDate, LeaseStatusCode, Instructions) VALUES (:CustomerID, :LeaseID, :LeaseSignDate, :LeaseTerminationDate, :LeaseTerminationReasonCode, :LeaseStartDate, :LeaseEndDate, :LeaseStatusCode, :Instructions)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("CustomerID", lease.getCustomerID());
        params.addValue("LeaseID", lease.getLeaseID());
        params.addValue("LeaseSignDate", lease.getLeaseSignDate());
        params.addValue("LeaseTerminationDate", lease.getLeaseTerminationDate());
        params.addValue("LeaseTerminationReasonCode", lease.getLeaseTerminationReasonCode());
        params.addValue("LeaseStartDate", lease.getLeaseStartDate());
        params.addValue("LeaseEndDate", lease.getLeaseEndDate());
        params.addValue("LeaseStatusCode", lease.getLeaseStatusCode());
        params.addValue("Instructions", lease.getInstructions());
        template.update(query, params);
    }

    // Read
    public Lease getLease(String pLeaseID) {
        String query = "SELECT * FROM lease WHERE LeaseID = :LeaseID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID", pLeaseID);
        return template.queryForObject(query, params, new LeaseRowMapper());
    }
    
    
    public List<LeaseQueryDTO> getLeaseDetails(String leaseId, String customerId) {
        String sql = "SELECT * FROM Lease_view " +
                     "WHERE LeaseID = :leaseId OR CustomerID = :customerId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("leaseId", leaseId);
        params.addValue("customerId", customerId);

        return template.query(sql, params, new LeaseQueryDTORowMapper());
    }    
    

    // Update
    public void updateLease(Lease lease) {
        String query = "UPDATE lease SET LeaseSignDate = :LeaseSignDate, LeaseTerminationDate = :LeaseTerminationDate, LeaseTerminationReasonCode = :LeaseTerminationReasonCode, LeaseStartDate = :LeaseStartDate, LeaseEndDate = :LeaseEndDate, LeaseStatusCode = :LeaseStatusCode, Instructions = :Instructions WHERE LeaseID = :LeaseID AND CustomerID = :CustomerID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("CustomerID", lease.getCustomerID());
        params.addValue("LeaseID", lease.getLeaseID());
        params.addValue("LeaseSignDate", lease.getLeaseSignDate());
        params.addValue("LeaseTerminationDate", lease.getLeaseTerminationDate());
        params.addValue("LeaseTerminationReasonCode", lease.getLeaseTerminationReasonCode());
        params.addValue("LeaseStartDate", lease.getLeaseStartDate());
        params.addValue("LeaseEndDate", lease.getLeaseEndDate());
        params.addValue("LeaseStatusCode", lease.getLeaseStatusCode());
        params.addValue("Instructions", lease.getInstructions());
        template.update(query, params);
    }

    // Delete
    public void deleteLease(String pLeaseID, String pCustomerID) {
        String query = "DELETE FROM lease WHERE LeaseID = :LeaseID AND CustomerID = :CustomerID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("LeaseID", pLeaseID);
        params.addValue("CustomerID", pCustomerID);
        template.update(query, params);
    }
    
    

	public List<Lease> getAllLeasesForCustomerID(String pCustomerID) {
	       String query = "SELECT * FROM lease WHERE CustomerID = :CustomerID";
	        MapSqlParameterSource params = new MapSqlParameterSource();
	        params.addValue("CustomerID", pCustomerID);
	        return (List<Lease>) template.query(query, params, new LeaseRowMapper());
	    }    
 
	
	public List<LeaseQueryDTO> queryLease(String pLeaseID,String pCustomerID,
			String pLeaseStatusCode) {
		
		
		StringBuilder sql = new StringBuilder(
		" SELECT lease.*, cv1.EnglishDescription AS leaseStatusDescription, cv2.EnglishDescription AS leaseTerminationDescription " +
		" FROM lease " +
		" INNER JOIN codevalue cv1 ON lease.LeaseStatusCode = cv1.CodeValue " +
		" LEFT JOIN codevalue cv2 ON lease.LeaseTerminationReasonCode = cv2.CodeValue " +
		" WHERE 1 = 1 ");

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		if (StringUtils.isNotEmpty(pLeaseID)) {
			sql.append(" AND lease.LeaseID = :leaseID");
			parameters.addValue("leaseID", pLeaseID);
		}
		
		if (StringUtils.isNotEmpty(pCustomerID)) {
			sql.append(" AND lease.CustomerID = :customerID");
			parameters.addValue("customerID", pCustomerID);
		}
		
		if (StringUtils.isNotEmpty(pLeaseStatusCode) && (!pLeaseStatusCode.startsWith("All"))) {
			sql.append(" AND lease.LeaseStatusCode = :LeaseStatusCode");
			parameters.addValue("LeaseStatusCode", pLeaseStatusCode);
		}

		
		System.out.println(sql.toString());
		
		return template.query(sql.toString(), parameters,
				new BeanPropertyRowMapper<>(LeaseQueryDTO.class));

	}	
	

    private static final class LeaseRowMapper implements RowMapper<Lease> {
        public Lease mapRow(ResultSet rs, int rowNum) throws SQLException {
            Lease lease = new Lease();
            lease.setCustomerID(rs.getString("CustomerID"));
            lease.setLeaseID(rs.getString("LeaseID"));
            lease.setLeaseSignDate(rs.getDate("LeaseSignDate"));
            lease.setLeaseTerminationDate(rs.getDate("LeaseTerminationDate"));
            lease.setLeaseTerminationReasonCode(rs.getString("LeaseTerminationReasonCode"));
            lease.setLeaseStartDate(rs.getDate("LeaseStartDate"));
            lease.setLeaseEndDate(rs.getDate("LeaseEndDate"));
            lease.setLeaseStatusCode(rs.getString("LeaseStatusCode"));
            lease.setInstructions(rs.getString("Instructions"));
            return lease;
        }
    }
    
    
    private static final class LeaseQueryDTORowMapper implements RowMapper<LeaseQueryDTO> {
        @Override
        public LeaseQueryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            LeaseQueryDTO leaseQueryDTO = new LeaseQueryDTO();

            // Populate Lease fields
            leaseQueryDTO.setCustomerID(rs.getString("CustomerID"));
            leaseQueryDTO.setLeaseID(rs.getString("LeaseID"));
            leaseQueryDTO.setLeaseSignDate(rs.getDate("LeaseSignDate"));
            leaseQueryDTO.setLeaseTerminationDate(rs.getDate("LeaseTerminationDate"));
            leaseQueryDTO.setLeaseTerminationReasonCode(rs.getString("LeaseTerminationReasonCode"));
            leaseQueryDTO.setLeaseStartDate(rs.getDate("LeaseStartDate"));
            leaseQueryDTO.setLeaseEndDate(rs.getDate("LeaseEndDate"));
            leaseQueryDTO.setLeaseStatusCode(rs.getString("LeaseStatusCode"));
            leaseQueryDTO.setInstructions(rs.getString("Instructions"));

            // Populate additional fields
            leaseQueryDTO.setLeaseStatusDescription(rs.getString("leaseStatusDescription"));
            leaseQueryDTO.setLeaseTerminationDescription(rs.getString("leaseTerminationDescription"));

            // Populate Customer fields
            Customer customer = new Customer();
            customer.setNotes(rs.getString("CustomerNotes"));
            customer.setCreatedDateTime(rs.getTimestamp("CustomerCreatedDateTime"));
            customer.setCreatedUserID(rs.getString("CustomerCreatedUserID"));

            // Populate User fields
            customer.setUserID(rs.getString("CustomerID"));
            customer.setFirstName(rs.getString("FirstName"));
            customer.setLastName(rs.getString("LastName"));
            customer.setStreet(rs.getString("street"));
            customer.setCity(rs.getString("City"));
            customer.setProvince(rs.getString("Province"));
            customer.setCountry(rs.getString("Country"));
            customer.setPhone(rs.getString("Phone"));
            customer.setEmail(rs.getString("Email"));

            leaseQueryDTO.setCustomer(customer);

            return leaseQueryDTO;
        }    
    }



}
