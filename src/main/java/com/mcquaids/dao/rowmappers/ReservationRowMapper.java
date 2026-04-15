package com.mcquaids.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.Reservation;

public class ReservationRowMapper implements RowMapper<Reservation> {

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {

        Reservation r = new Reservation();

        r.setReservationID(rs.getInt("ReservationID"));
        r.setCustomerID(rs.getString("CustomerID"));
        r.setReservationStatusCode(rs.getString("ReservationStatusCode"));

        Timestamp startTs = rs.getTimestamp("StartDate");
        r.setStartDate(startTs == null ? null : startTs.toLocalDateTime());

        Timestamp endTs = rs.getTimestamp("EndDate");
        r.setEndDate(endTs == null ? null : endTs.toLocalDateTime());

        r.setInstructions(rs.getString("Instructions"));
        r.setLeaseID(rs.getString("LeaseID"));
        r.setDateCreated(rs.getTimestamp("DateCreated"));
        r.setDateUpdated(rs.getTimestamp("DateUpdated"));

        // NEW LEASE FIELDS
        r.setLeaseDocumentPath(rs.getString("LeaseDocumentPath"));

        Timestamp signedTs = rs.getTimestamp("LeaseSignedDate");
        r.setLeaseSignedDate(signedTs == null ? null : signedTs);

        r.setLeaseSignedBy(rs.getString("LeaseSignedBy"));

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

        Timestamp ts = rs.getTimestamp("SecondaryDeliveryDate");
        r.setSecondaryDeliveryDate(ts == null ? null : ts.toLocalDateTime());

        // Additional Person (MVP fields)
        r.setAdditionalPersonName(rs.getString("AdditionalPersonName"));
        r.setAdditionalPersonPhone(rs.getString("AdditionalPersonPhone"));
        r.setAdditionalPersonEmail(rs.getString("AdditionalPersonEmail"));

        return r;
    }
}
