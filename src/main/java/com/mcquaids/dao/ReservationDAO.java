package com.mcquaids.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.dao.rowmappers.ReservationQueryDTORowMapper;
import com.mcquaids.dao.rowmappers.ReservationRowMapper;
import com.mcquaids.dao.rowmappers.ReservationViewRowMapper;
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
                     "(CustomerID, ReservationStatusCode, StartDate, EndDate, Instructions, LeaseID, " +
                     "DeliveryStreet, DeliveryCity, DeliveryProvince, DeliveryPostalCode, DeliveryCountry, DeliverySameAsCustomer, " +
                     "SecondaryStreet, SecondaryCity, SecondaryProvince, SecondaryPostalCode, SecondaryCountry, SecondaryDeliveryDate, " +
                     "AdditionalPersonName, AdditionalPersonPhone, AdditionalPersonEmail, " +
                     "DateCreated) " +
                     "VALUES (:CustomerID, :ReservationStatusCode, :StartDate, :EndDate, :Instructions, :LeaseID, " +
                     ":DeliveryStreet, :DeliveryCity, :DeliveryProvince, :DeliveryPostalCode, :DeliveryCountry, :DeliverySameAsCustomer, " +
                     ":SecondaryStreet, :SecondaryCity, :SecondaryProvince, :SecondaryPostalCode, :SecondaryCountry, :SecondaryDeliveryDate, " +
                     ":AdditionalPersonName, :AdditionalPersonPhone, :AdditionalPersonEmail, " +
                     "NOW())";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("CustomerID", reservation.getCustomerID());
        params.addValue("ReservationStatusCode", reservation.getReservationStatusCode());
        params.addValue("StartDate", reservation.getStartDate());
        params.addValue("EndDate", reservation.getEndDate());
        params.addValue("Instructions", reservation.getInstructions());
        params.addValue("LeaseID", reservation.getLeaseID());

        // Primary delivery
<<<<<<< HEAD
        params.addValue("DeliveryStreet", normalize(reservation.getDeliveryStreet()));
        params.addValue("DeliveryCity", normalize(reservation.getDeliveryCity()));
        params.addValue("DeliveryProvince", normalize(reservation.getDeliveryProvince()));
        params.addValue("DeliveryPostalCode", normalize(reservation.getDeliveryPostalCode()));
        params.addValue("DeliveryCountry", normalize(reservation.getDeliveryCountry()));        
        params.addValue("DeliverySameAsCustomer", reservation.getDeliverySameAsCustomer());

        // Secondary delivery
        params.addValue("SecondaryStreet", normalize(reservation.getSecondaryStreet()));
        params.addValue("SecondaryCity", normalize(reservation.getSecondaryCity()));
        params.addValue("SecondaryProvince", normalize(reservation.getSecondaryProvince()));
        params.addValue("SecondaryPostalCode", normalize(reservation.getSecondaryPostalCode()));
        params.addValue("SecondaryCountry", normalize(reservation.getSecondaryCountry()));
=======
        params.addValue("DeliveryStreet", reservation.getDeliveryStreet());
        params.addValue("DeliveryCity", reservation.getDeliveryCity());
        params.addValue("DeliveryProvince", reservation.getDeliveryProvince());
        params.addValue("DeliveryPostalCode", reservation.getDeliveryPostalCode());
        params.addValue("DeliveryCountry", reservation.getDeliveryCountry());
        params.addValue("DeliverySameAsCustomer", reservation.getDeliverySameAsCustomer());

        // Secondary delivery
        params.addValue("SecondaryStreet", reservation.getSecondaryStreet());
        params.addValue("SecondaryCity", reservation.getSecondaryCity());
        params.addValue("SecondaryProvince", reservation.getSecondaryProvince());
        params.addValue("SecondaryPostalCode", reservation.getSecondaryPostalCode());
        params.addValue("SecondaryCountry", reservation.getSecondaryCountry());
>>>>>>> origin/main
        params.addValue("SecondaryDeliveryDate", reservation.getSecondaryDeliveryDate());

        // Additional Person (MVP fields)
        params.addValue("AdditionalPersonName", reservation.getAdditionalPersonName());
        params.addValue("AdditionalPersonPhone", reservation.getAdditionalPersonPhone());
        params.addValue("AdditionalPersonEmail", reservation.getAdditionalPersonEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, params, keyHolder);

        return keyHolder.getKey().intValue();
    }
    

    private String normalize(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }    
    

    // ------------------------------------------------------------
    // READ (single)
    // ------------------------------------------------------------
    public Reservation getReservation(Integer reservationID) {
        String sql = "SELECT * FROM reservation WHERE ReservationID = :ReservationID";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ReservationID", reservationID);

        return template.queryForObject(sql, params, new ReservationRowMapper());
    }

<<<<<<< HEAD
=======

 // ------------------------------------------------------------
 // UPDATE
 // ------------------------------------------------------------
    public void updateReservation(Reservation reservation) {
   	 
   	 System.out.println("SDBANKs- Entered updateReservation()");
   	 System.out.println(reservation.toString());
>>>>>>> origin/main

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
                     "DeliveryStreet = :DeliveryStreet, " +
                     "DeliveryCity = :DeliveryCity, " +
                     "DeliveryProvince = :DeliveryProvince, " +
                     "DeliveryPostalCode = :DeliveryPostalCode, " +
                     "DeliveryCountry = :DeliveryCountry, " +
                     "DeliverySameAsCustomer = :DeliverySameAsCustomer, " +

                     // Secondary delivery
                     "SecondaryStreet = :SecondaryStreet, " +
                     "SecondaryCity = :SecondaryCity, " +
                     "SecondaryProvince = :SecondaryProvince, " +
                     "SecondaryPostalCode = :SecondaryPostalCode, " +
                     "SecondaryCountry = :SecondaryCountry, " +
                     "SecondaryDeliveryDate = :SecondaryDeliveryDate, " +

                     // Additional Person (MVP fields)
                     "AdditionalPersonName = :AdditionalPersonName, " +
                     "AdditionalPersonPhone = :AdditionalPersonPhone, " +
                     "AdditionalPersonEmail = :AdditionalPersonEmail, " +

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

        // Delivery address
<<<<<<< HEAD
        params.addValue("DeliveryStreet", normalize(reservation.getDeliveryStreet()));
        params.addValue("DeliveryCity", normalize(reservation.getDeliveryCity()));
        params.addValue("DeliveryProvince", normalize(reservation.getDeliveryProvince()));
        params.addValue("DeliveryPostalCode", normalize(reservation.getDeliveryPostalCode()));
        params.addValue("DeliveryCountry", normalize(reservation.getDeliveryCountry()));
        params.addValue("DeliverySameAsCustomer", reservation.getDeliverySameAsCustomer());
        
        // Secondary delivery
        params.addValue("SecondaryStreet", normalize(reservation.getSecondaryStreet()));
        params.addValue("SecondaryCity", normalize(reservation.getSecondaryCity()));
        params.addValue("SecondaryProvince", normalize(reservation.getSecondaryProvince()));
        params.addValue("SecondaryPostalCode", normalize(reservation.getSecondaryPostalCode()));
        params.addValue("SecondaryCountry", normalize(reservation.getSecondaryCountry()));
=======
        params.addValue("DeliveryStreet", reservation.getDeliveryStreet());
        params.addValue("DeliveryCity", reservation.getDeliveryCity());
        params.addValue("DeliveryProvince", reservation.getDeliveryProvince());
        params.addValue("DeliveryPostalCode", reservation.getDeliveryPostalCode());
        params.addValue("DeliveryCountry", reservation.getDeliveryCountry());
        params.addValue("DeliverySameAsCustomer", reservation.getDeliverySameAsCustomer());
        
        // Secondary delivery
        params.addValue("SecondaryStreet", reservation.getSecondaryStreet());
        params.addValue("SecondaryCity", reservation.getSecondaryCity());
        params.addValue("SecondaryProvince", reservation.getSecondaryProvince());
        params.addValue("SecondaryPostalCode", reservation.getSecondaryPostalCode());
        params.addValue("SecondaryCountry", reservation.getSecondaryCountry());
>>>>>>> origin/main
        params.addValue("SecondaryDeliveryDate", reservation.getSecondaryDeliveryDate());

        // Additional Person (MVP fields)
        params.addValue("AdditionalPersonName", reservation.getAdditionalPersonName());
        params.addValue("AdditionalPersonPhone", reservation.getAdditionalPersonPhone());
        params.addValue("AdditionalPersonEmail", reservation.getAdditionalPersonEmail());

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
            "Instructions, LeaseID, DateCreated, DateUpdated, " +

            // Primary delivery
            "DeliveryStreet, DeliveryCity, DeliveryProvince, DeliveryPostalCode, DeliveryCountry, DeliverySameAsCustomer, " +

            // Secondary delivery
            "SecondaryStreet, SecondaryCity, SecondaryProvince, SecondaryPostalCode, SecondaryCountry, SecondaryDeliveryDate, " +

            // Additional Person (MVP fields)
            "AdditionalPersonName, AdditionalPersonPhone, AdditionalPersonEmail, " +

            // Customer fields
            "CustomerNotes, CustomerCreatedDateTime, CustomerCreatedUserID, " +

            // User fields
            "FirstName, LastName, Phone, Email, street, City, Province, Country, PostalCode, " +

            // Status description
            "reservationStatusDescription " +

            "FROM reservation_view WHERE 1=1 "
        );

        MapSqlParameterSource params = new MapSqlParameterSource();

        if (reservationID != null) {
            sql.append(" AND ReservationID = :ReservationID ");
            params.addValue("ReservationID", reservationID);
        }

        if (customerID != null && !customerID.trim().isEmpty()) {
            sql.append(" AND CustomerID = :CustomerID ");
            params.addValue("CustomerID", customerID);
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

<<<<<<< HEAD
 public boolean isEquipmentOnActiveReservation(Integer equipmentNumber) {

	    String sql =
	        "SELECT COUNT(*) " +
	        "FROM reservation_line_item rli " +
	        "JOIN reservation r ON r.ReservationID = rli.ReservationID " +
	        "WHERE rli.EquipmentNumber = :equipmentNumber " +
	        "  AND r.ReservationStatusCode IN ('1001-02', '1001-03', '1001-04', '1001-05') " +
	        "  AND r.StartDate <= NOW() " +
	        "  AND r.EndDate >= NOW()";

	    MapSqlParameterSource params = new MapSqlParameterSource()
	            .addValue("equipmentNumber", equipmentNumber);

	    Integer count = template.queryForObject(sql, params, Integer.class);

	    return count != null && count > 0;
	}



=======

>>>>>>> origin/main
    
    
}