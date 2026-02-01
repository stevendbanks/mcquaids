package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.model.Customer;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationQueryDTO;

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
    public void createReservation(Reservation reservation) {

        String sql = "INSERT INTO reservation " +
                     "(ReservationID, CustomerID, ReservationStatusCode, StartDate, EndDate, Notes, LeaseID, DateCreated) " +
                     "VALUES (:ReservationID, :CustomerID, :ReservationStatusCode, :StartDate, :EndDate, :Notes, :LeaseID, NOW())";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservation.getReservationID());
        params.addValue("CustomerID", reservation.getCustomerID());
        params.addValue("ReservationStatusCode", reservation.getReservationStatusCode());
        params.addValue("StartDate", reservation.getStartDate());
        params.addValue("EndDate", reservation.getEndDate());
        params.addValue("Notes", reservation.getNotes());
        params.addValue("LeaseID", reservation.getLeaseID());

        template.update(sql, params);
    }

    // ------------------------------------------------------------
    // READ (single)
    // ------------------------------------------------------------
    public Reservation getReservation(String reservationID) {
System.out.println("SDBANKS-DAO -> reservationID=" + reservationID);
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
                     "Notes = :Notes, " +
                     "LeaseID = :LeaseID, " +
                     "DateUpdated = NOW() " +
                     "WHERE ReservationID = :ReservationID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservation.getReservationID());
        params.addValue("CustomerID", reservation.getCustomerID());
        params.addValue("ReservationStatusCode", reservation.getReservationStatusCode());
        params.addValue("StartDate", reservation.getStartDate());
        params.addValue("EndDate", reservation.getEndDate());
        params.addValue("Notes", reservation.getNotes());
        params.addValue("LeaseID", reservation.getLeaseID());

        template.update(sql, params);
    }

    // ------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------
    public void deleteReservation(String reservationID, String customerID) {

        String sql = "DELETE FROM reservation " +
                     "WHERE ReservationID = :ReservationID AND CustomerID = :CustomerID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);
        params.addValue("CustomerID", customerID);

        template.update(sql, params);
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
 public List<ReservationQueryDTO> getReservationDetails(String reservationID,
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

            r.setReservationID(rs.getString("ReservationID"));
            r.setCustomerID(rs.getString("CustomerID"));
            r.setReservationStatusCode(rs.getString("ReservationStatusCode"));
            r.setStartDate(rs.getDate("StartDate"));
            r.setEndDate(rs.getDate("EndDate"));
            r.setNotes(rs.getString("Notes"));
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
            dto.setReservationID(rs.getString("ReservationID"));
            dto.setCustomerID(rs.getString("CustomerID"));
            dto.setReservationStatusCode(rs.getString("ReservationStatusCode"));
            dto.setStartDate(rs.getDate("StartDate"));
            dto.setEndDate(rs.getDate("EndDate"));
            dto.setNotes(rs.getString("Notes"));
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
}