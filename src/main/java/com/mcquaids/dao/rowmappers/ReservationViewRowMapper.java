package com.mcquaids.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.Timestamp;
import java.time.LocalDate;
=======
>>>>>>> origin/main

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.Customer;
import com.mcquaids.model.ReservationViewDTO;

public class ReservationViewRowMapper implements RowMapper<ReservationViewDTO> {

    @Override
    public ReservationViewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        ReservationViewDTO dto = new ReservationViewDTO();

        // Base Reservation fields
        dto.setReservationID(rs.getInt("ReservationID"));
        dto.setCustomerID(rs.getString("CustomerID"));
        dto.setReservationStatusCode(rs.getString("ReservationStatusCode"));
<<<<<<< HEAD
 //       dto.setStartDate(rs.getTimestamp("StartDate"));
//        dto.setStartDate(rs.getObject("StartDate", LocalDate.class));
////        dto.setEndDate(rs.getTimestamp("EndDate"));
//        dto.setEndDate(rs.getObject("EndDate", LocalDate.class));
        
        Timestamp startTs = rs.getTimestamp("StartDate");
        dto.setStartDate(startTs == null ? null : startTs.toLocalDateTime());

        Timestamp endTs = rs.getTimestamp("EndDate");
        dto.setEndDate(endTs == null ? null : endTs.toLocalDateTime());        
        
        
=======
        dto.setStartDate(rs.getTimestamp("StartDate"));
        dto.setEndDate(rs.getTimestamp("EndDate"));
>>>>>>> origin/main
        dto.setInstructions(rs.getString("Instructions"));
        dto.setLeaseID(rs.getString("LeaseID"));
        dto.setDateCreated(rs.getTimestamp("DateCreated"));
        dto.setDateUpdated(rs.getTimestamp("DateUpdated"));

        // Delivery address fields
        dto.setDeliveryStreet(rs.getString("DeliveryStreet"));
        dto.setDeliveryCity(rs.getString("DeliveryCity"));
        dto.setDeliveryProvince(rs.getString("DeliveryProvince"));
        dto.setDeliveryPostalCode(rs.getString("DeliveryPostalCode"));
        dto.setDeliveryCountry(rs.getString("DeliveryCountry"));
        dto.setDeliverySameAsCustomer(rs.getBoolean("DeliverySameAsCustomer"));

        // Secondary delivery
        dto.setSecondaryStreet(rs.getString("SecondaryStreet"));
        dto.setSecondaryCity(rs.getString("SecondaryCity"));
        dto.setSecondaryProvince(rs.getString("SecondaryProvince"));
        dto.setSecondaryPostalCode(rs.getString("SecondaryPostalCode"));
        dto.setSecondaryCountry(rs.getString("SecondaryCountry"));
<<<<<<< HEAD
//        dto.setSecondaryDeliveryDate(rs.getObject("SecondaryDeliveryDate", LocalDate.class));
        
        Timestamp ts = rs.getTimestamp("SecondaryDeliveryDate");
        dto.setSecondaryDeliveryDate(ts == null ? null : ts.toLocalDateTime());
=======
        dto.setSecondaryDeliveryDate(rs.getDate("SecondaryDeliveryDate"));
>>>>>>> origin/main

        // Additional Person (MVP fields)
        dto.setAdditionalPersonName(rs.getString("AdditionalPersonName"));
        dto.setAdditionalPersonPhone(rs.getString("AdditionalPersonPhone"));
        dto.setAdditionalPersonEmail(rs.getString("AdditionalPersonEmail"));

        // Build Customer (extends User)
        Customer customer = new Customer();
        customer.setUserID(rs.getString("CustomerID"));
        customer.setFirstName(rs.getString("FirstName"));
        customer.setLastName(rs.getString("LastName"));
        customer.setStreet(rs.getString("street"));
        customer.setCity(rs.getString("City"));
        customer.setProvince(rs.getString("Province"));
        customer.setCountry(rs.getString("Country"));
        customer.setPostalCode(rs.getString("postalCode"));
        customer.setPhone(rs.getString("Phone"));
        customer.setEmail(rs.getString("Email"));

        customer.setNotes(rs.getString("CustomerNotes"));
        customer.setCreatedDateTime(rs.getTimestamp("CustomerCreatedDateTime"));
        customer.setCreatedUserID(rs.getString("CustomerCreatedUserID"));

        dto.setCustomer(customer);

        // View-only fields
        dto.setReservationStatusDescription(rs.getString("reservationStatusDescription"));

        return dto;
    }
}