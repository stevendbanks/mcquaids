package com.mcquaids.service;



import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.EquipmentLocationHistoryDAO;
import com.mcquaids.model.EquipmentLocationHistory;
import com.mcquaids.model.Reservation;

public class EquipmentLocationHistoryService {

    private final EquipmentLocationHistoryDAO locationDao;

    public EquipmentLocationHistoryService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.locationDao = new EquipmentLocationHistoryDAO(jdbcTemplate); 
    }

    /**
     * Deliver equipment to the reservation's delivery address.
     */
    public void deliverEquipment(int equipmentNumber, Reservation reservation) {

        // Close previous location
        locationDao.closeCurrentLocation(equipmentNumber);

        // Build new history entry
        EquipmentLocationHistory history = new EquipmentLocationHistory();
        history.setEquipmentNumber(equipmentNumber);
        history.setStreet(reservation.getDeliveryStreet());
        history.setCity(reservation.getDeliveryCity());
        history.setProvince(reservation.getDeliveryProvince());
        history.setPostal(reservation.getDeliveryPostal());
        history.setCountry(reservation.getDeliveryCountry());
        history.setLocationType("DELIVERY_SITE");
        history.setReservationId(reservation.getReservationID());
        history.setNotes("Delivered per reservation");

        // Insert new location
        locationDao.insertLocationHistory(history);
    }

    /**
     * Return equipment to the yard.
     */
    public void returnEquipment(int equipmentNumber) {

        // Close previous location
        locationDao.closeCurrentLocation(equipmentNumber);

        // Build new history entry
        EquipmentLocationHistory history = new EquipmentLocationHistory();
        history.setEquipmentNumber(equipmentNumber);
        history.setLocationType("ON_PREMISE");

        // You can refine these yard details later or load from config
        history.setStreet("Yard");
        history.setCity("Charlottetown");
        history.setProvince("PE");
        history.setCountry("Canada");
        history.setNotes("Returned to yard");

        // Insert new location
        locationDao.insertLocationHistory(history);
    }

    /**
     * Swap equipment: driver chooses a different trailer than requested.
     */
    public void swapEquipment(int requestedEquipment,
                              int actualEquipment,
                              Reservation reservation,
                              String reason) {

        // Close previous location of the actual equipment
        locationDao.closeCurrentLocation(actualEquipment);

        // Build new history entry
        EquipmentLocationHistory history = new EquipmentLocationHistory();
        history.setEquipmentNumber(actualEquipment);
        history.setStreet(reservation.getDeliveryStreet());
        history.setCity(reservation.getDeliveryCity());
        history.setProvince(reservation.getDeliveryProvince());
        history.setPostal(reservation.getDeliveryPostal());
        history.setCountry(reservation.getDeliveryCountry());
        history.setLocationType("SWAP_SITE");
        history.setReservationId(reservation.getReservationID());

        String notes = "Driver substituted " + actualEquipment +
                       " for requested " + requestedEquipment +
                       ". Reason: " + reason;
        history.setNotes(notes);

        // Insert new location
        locationDao.insertLocationHistory(history);
    }

    /**
     * Move equipment manually (yard move, maintenance, etc.)
     */
    public void moveEquipmentManually(int equipmentNumber,
                                      String street,
                                      String city,
                                      String province,
                                      String postal,
                                      String country,
                                      String notes) {

        // Close previous location
        locationDao.closeCurrentLocation(equipmentNumber);

        // Build new history entry
        EquipmentLocationHistory history = new EquipmentLocationHistory();
        history.setEquipmentNumber(equipmentNumber);
        history.setStreet(street);
        history.setCity(city);
        history.setProvince(province);
        history.setPostal(postal);
        history.setCountry(country);
        history.setLocationType("ON_PREMISE");
        history.setNotes(notes);

        // Insert new location
        locationDao.insertLocationHistory(history);
    }
}