package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.model.Customer;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationQueryDTO;
import com.mcquaids.model.ReservationViewDTO;

public class ReservationDAO {

    private NamedParameterJdbcTemplate template;

    public ReservationDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public ReservationDAO(JdbcTemplate jdbcTemplate) {
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    }

    // ------------------------------------------------------------
    // CREATE
    // ------------------------------------------------------------
    public Integer createReservation(Reservation reservation) {

        String sql = "INSERT INTO reservation " +
                     "(CustomerID, ReservationStatusCode, StartDate, EndDate, Instructions, LeaseID, DateCreated) " +
                     "VALUES (:CustomerID, :ReservationStatusCode, :StartDate, :EndDate, :Instructions, :LeaseID, NOW())";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("CustomerID", reservation.getCustomerID());
        params.addValue("ReservationStatusCode", reservation.getReservationStatusCode());
        params.addValue("StartDate", reservation.getStartDate());
        params.addValue("EndDate", reservation.getEndDate());
        params.addValue("Instructions", reservation.getInstructions());
        params.addValue("LeaseID", reservation.getLeaseID());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, params, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // ------------------------------------------------------------
    // READ (single)
    // ------------------------------------------------------------
    public Reservation getReservation(Integer reservationID) {
    	System.out.println("SDBANKS-> reservationID=" + reservationID);
        String sql = "SELECT * FROM reservation WHERE ReservationID = :ReservationID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);

        return template.queryForObject(sql, params, new ReservationRowMapper());
    }

    // ------------------------------------------------------------
    // UPDATE
    // ------------------------------------------------------------
    public void updateReservation(Reservation reservation) {

        String sql = "UPDATE reservation SET " +
                     "CustomerID = :CustomerID, " +
                     "ReservationStatusCode = :ReservationStatusCode, " +
                     "StartDate = :StartDate, " +
                     "EndDate = :EndDate, " +
                     "Instructions = :Instructions, " +
                     "LeaseID = :LeaseID, " +
                     "DateUpdated = NOW() " +
                     "WHERE ReservationID = :ReservationID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservation.getReservationID());
        params.addValue("CustomerID", reservation.getCustomerID());
        params.addValue("ReservationStatusCode", reservation.getReservationStatusCode());
        params.addValue("StartDate", reservation.getStartDate());
        params.addValue("EndDate", reservation.getEndDate());
        params.addValue("Instructions", reservation.getInstructions());
        params.addValue("LeaseID", reservation.getLeaseID());

        template.update(sql, params);
    }

    // ------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------
    public void deleteReservation(Integer reservationID, String customerID) {

        String sql = "DELETE FROM reservation " +
                     "WHERE ReservationID = :ReservationID AND CustomerID = :CustomerID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);
        params.addValue("CustomerID", customerID);

        template.update(sql, params);
    }
    
    

    public List<ReservationViewDTO> findReservationsByCriteria(
            Integer reservationID,
            String customerID
    ) {

        StringBuilder sql = new StringBuilder(
            "SELECT ReservationID, CustomerID, ReservationStatusCode, StartDate, EndDate, " +
            "Instructions, LeaseID, DateCreated, DateUpdated, CustomerNotes, CustomerCreatedDateTime, " +
            "CustomerCreatedUserID, FirstName, LastName, Phone, Email, street, City, Province, " +
            "Country,  PostalCode, reservationStatusDescription " +
            "FROM reservation_view WHERE 1=1 "
        );

        MapSqlParameterSource params = new MapSqlParameterSource();

        if (reservationID != null ) {
            sql.append(" AND ReservationID = :ReservationID ");
            params.addValue("ReservationID", reservationID);
        }

        if (customerID != null && !customerID.trim().isEmpty()) {
            sql.append(" AND CustomerID = :CustomerID ");
            params.addValue("CustomerID",customerID);
        }

        return template.query(sql.toString(), params, new ReservationViewRowMapper());
    }
    
    

    // ------------------------------------------------------------
    // READ (all for customer)
    // ------------------------------------------------------------
    public List<Reservation> getAllReservationsForCustomerID(String customerID) {

        String sql = "SELECT * FROM reservation WHERE CustomerID = :CustomerID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("CustomerID", customerID);

        return template.query(sql, params, new ReservationRowMapper());
    }

 // ------------------------------------------------------------
 // QUERY (DTO-style, similar to LeaseQueryDTO)
 // ------------------------------------------------------------
 public List<ReservationQueryDTO> getReservationDetails(Integer reservationID,
                                                        String customerID,
                                                        String reservationStatusCode) {

     String sql = "SELECT * FROM reservation_view " +
                  "WHERE (:ReservationID IS NULL OR ReservationID = :ReservationID) " +
                  "AND (:CustomerID IS NULL OR CustomerID = :CustomerID) " +
                  "AND (:ReservationStatusCode IS NULL OR ReservationStatusCode = :ReservationStatusCode)";

     MapSqlParameterSource params = new MapSqlParameterSource();
     params.addValue("ReservationID", reservationID);
     params.addValue("CustomerID", customerID);
     params.addValue("ReservationStatusCode", reservationStatusCode);

     return template.query(sql, params, new ReservationQueryDTORowMapper());
 }

    // ------------------------------------------------------------
    // ROW MAPPERS
    // ------------------------------------------------------------
    private static class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation r = new Reservation();

            r.setReservationID(rs.getInt("ReservationID"));
            r.setCustomerID(rs.getString("CustomerID"));
            r.setReservationStatusCode(rs.getString("ReservationStatusCode"));
            r.setStartDate(rs.getDate("StartDate"));
            r.setEndDate(rs.getDate("EndDate"));
            r.setInstructions(rs.getString("Instructions"));
            r.setLeaseID(rs.getString("LeaseID"));
            r.setDateCreated(rs.getTimestamp("DateCreated"));
            r.setDateUpdated(rs.getTimestamp("DateUpdated"));

            return r;
        }
    }

    private static class ReservationQueryDTORowMapper implements RowMapper<ReservationQueryDTO> {

        @Override
        public ReservationQueryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReservationQueryDTO dto = new ReservationQueryDTO();

            // ------------------------------
            // Base Reservation fields
            // ------------------------------
            dto.setReservationID(rs.getInt("ReservationID"));
            dto.setCustomerID(rs.getString("CustomerID"));
            dto.setReservationStatusCode(rs.getString("ReservationStatusCode"));
            dto.setStartDate(rs.getDate("StartDate"));
            dto.setEndDate(rs.getDate("EndDate"));
            dto.setInstructions(rs.getString("Instructions"));
            dto.setLeaseID(rs.getString("LeaseID"));
            dto.setDateCreated(rs.getTimestamp("DateCreated"));
            dto.setDateUpdated(rs.getTimestamp("DateUpdated"));

            // ------------------------------
            // Description fields
            // ------------------------------
            dto.setReservationStatusDescription(rs.getString("reservationStatusDescription"));

            // ------------------------------
            // Build Customer object
            // ------------------------------
            Customer customer = new Customer();
            customer.setUserID(rs.getString("CustomerID"));
            customer.setNotes(rs.getString("CustomerNotes"));
            customer.setCreatedDateTime(rs.getTimestamp("CustomerCreatedDateTime"));
            customer.setCreatedUserID(rs.getString("CustomerCreatedUserID"));

            // User table fields
            customer.setFirstName(rs.getString("FirstName"));
            customer.setLastName(rs.getString("LastName"));
            customer.setPhone(rs.getString("Phone"));
            customer.setEmail(rs.getString("Email"));
            customer.setStreet(rs.getString("street"));
            customer.setCity(rs.getString("City"));
            customer.setProvince(rs.getString("Province"));
            customer.setCountry(rs.getString("Country"));

            dto.setCustomer(customer);

            return dto;
        }
    }

    public class ReservationViewRowMapper implements RowMapper<ReservationViewDTO> {

        @Override
        public ReservationViewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ReservationViewDTO dto = new ReservationViewDTO();

            // -----------------------------
            // 1. Base Reservation fields
            // -----------------------------
            dto.setReservationID(rs.getInt("ReservationID"));
            dto.setCustomerID(rs.getString("CustomerID"));
            dto.setReservationStatusCode(rs.getString("ReservationStatusCode"));
            dto.setStartDate(rs.getTimestamp("StartDate"));
            dto.setEndDate(rs.getTimestamp("EndDate"));
            dto.setInstructions(rs.getString("Instructions"));
            dto.setLeaseID(rs.getString("LeaseID"));
            dto.setDateCreated(rs.getTimestamp("DateCreated"));
            dto.setDateUpdated(rs.getTimestamp("DateUpdated"));

            // -----------------------------
            // 2. Build Customer (which extends User)
            // -----------------------------
            Customer customer = new Customer();

            // User fields (inherited by Customer)
            customer.setUserID(rs.getString("CustomerID"));   // same as reservation.CustomerID
            customer.setFirstName(rs.getString("FirstName"));
            customer.setLastName(rs.getString("LastName"));
            customer.setStreet(rs.getString("street"));
            customer.setCity(rs.getString("City"));
            customer.setProvince(rs.getString("Province"));
            customer.setCountry(rs.getString("Country"));
            customer.setPostalCode(rs.getString("postalCode")); // only if your view includes it
            customer.setPhone(rs.getString("Phone"));
            customer.setEmail(rs.getString("Email"));

            // Customer-specific fields
            customer.setNotes(rs.getString("CustomerNotes"));
            customer.setCreatedDateTime(rs.getTimestamp("CustomerCreatedDateTime"));
            customer.setCreatedUserID(rs.getString("CustomerCreatedUserID"));

            // Attach customer to reservation
            dto.setCustomer(customer);

            // -----------------------------
            // 3. View-only fields
            // -----------------------------
            dto.setReservationStatusDescription(rs.getString("reservationStatusDescription"));

            return dto;
        }
    }   
    
    
}