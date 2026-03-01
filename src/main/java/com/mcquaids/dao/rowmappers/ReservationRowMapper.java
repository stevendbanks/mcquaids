package com.mcquaids.dao.rowmappers;

import com.mcquaids.model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {

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

        // Delivery address fields
        r.setDeliveryStreet(rs.getString("DeliveryStreet"));
        r.setDeliveryCity(rs.getString("DeliveryCity"));
        r.setDeliveryProvince(rs.getString("DeliveryProvince"));
        r.setDeliveryPostalCode(rs.getString("DeliveryPostalCode"));
        r.setDeliveryCountry(rs.getString("DeliveryCountry"));
        r.setDeliverySameAsCustomer(rs.getBoolean("DeliverySameAsCustomer"));

        // Secondary delivery
        r.setSecondaryStreet(rs.getString("SecondaryStreet"));
        r.setSecondaryCity(rs.getString("SecondaryCity"));
        r.setSecondaryProvince(rs.getString("SecondaryProvince"));
        r.setSecondaryPostalCode(rs.getString("SecondaryPostalCode"));
        r.setSecondaryCountry(rs.getString("SecondaryCountry"));
        r.setSecondaryDeliveryDate(rs.getDate("SecondaryDeliveryDate"));

        // Additional Person (MVP fields)
        r.setAdditionalPersonName(rs.getString("AdditionalPersonName"));
        r.setAdditionalPersonPhone(rs.getString("AdditionalPersonPhone"));
        r.setAdditionalPersonEmail(rs.getString("AdditionalPersonEmail"));

        return r;
    }
}
