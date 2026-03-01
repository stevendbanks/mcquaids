package com.mcquaids.service;

import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.EquipmentEventDAO;
import com.mcquaids.dao.EquipmentLocationHistoryDAO;
import com.mcquaids.model.EquipmentEvent;
import com.mcquaids.model.EquipmentLocationHistory;

public class EquipmentEventService {

    private final EquipmentEventDAO eventDAO;
    private final EquipmentLocationHistoryDAO locationHistoryDAO;

    public EquipmentEventService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;

        this.eventDAO = new EquipmentEventDAO(jdbcTemplate);
        this.locationHistoryDAO = new EquipmentLocationHistoryDAO(jdbcTemplate);
    }

    // ---------------------------------------------------------------------
    // NON-MOVEMENT EVENTS
    // ---------------------------------------------------------------------

    public void recordInspection(int equipmentNumber, String notes, Integer reservationId) {
        EquipmentEvent event = new EquipmentEvent();
        event.setEquipmentNumber(equipmentNumber);
        event.setEventType("INSPECTED");
        event.setEventDateTime(new Date());
        event.setReservationId(reservationId);
        event.setNotes(notes);

        eventDAO.insert(event);
    }

    public void recordDamage(int equipmentNumber, String notes, Integer reservationId) {
        EquipmentEvent event = new EquipmentEvent();
        event.setEquipmentNumber(equipmentNumber);
        event.setEventType("DAMAGE_DISCOVERED");
        event.setEventDateTime(new Date());
        event.setReservationId(reservationId);
        event.setNotes(notes);

        eventDAO.insert(event);
    }

    public void recordMaintenance(int equipmentNumber, String notes, Integer reservationId) {
        EquipmentEvent event = new EquipmentEvent();
        event.setEquipmentNumber(equipmentNumber);
        event.setEventType("MAINTENANCE");
        event.setEventDateTime(new Date());
        event.setReservationId(reservationId);
        event.setNotes(notes);

        eventDAO.insert(event);
    }

    // ---------------------------------------------------------------------
    // SEMANTIC MOVEMENT EVENTS (DELIVERY, PICKUP, MOVE)
    // ---------------------------------------------------------------------

    public void recordDeliveryEvent(Long reservationId,
                                    Long equipmentId,
                                    String toLocation,
                                    Long userId) {

        recordMoveEvent(
                equipmentId,
                "WAREHOUSE",
                toLocation,
                userId,
                reservationId,
                "DELIVERY",
                null
        );
    }


    /**
     * Records a PICKUP event for a piece of equipment.
     *
     * <p>This represents the driver retrieving equipment FROM the customer site
     * and returning it to the warehouse. It closes the current location interval
     * (customer site) and opens a new interval at the warehouse.</p>
     *
     * @param reservationId the reservation associated with the pickup
     * @param equipmentId   the unique identifier of the equipment being picked up
     * @param fromLocation  the customer location where the pickup occurs
     * @param userId        the user performing or recording the pickup action
     */
    public void recordPickupEvent(Long reservationId,
                                  Long equipmentId,
                                  String fromLocation,
                                  Long userId) {

        recordMoveEvent(
                equipmentId,
                fromLocation,
                "WAREHOUSE",
                userId,
                reservationId,
                "PICKUP",
                null
        );
    }

    public void recordMoveEvent(Long equipmentId,
                                String fromLocation,
                                String toLocation,
                                Long userId) {

        recordMoveEvent(
                equipmentId,
                fromLocation,
                toLocation,
                userId,
                null,
                "MOVE",
                null
        );
    }

    // ---------------------------------------------------------------------
    // INTERNAL UNIFIED MOVEMENT HANDLER
    // ---------------------------------------------------------------------

    private void recordMoveEvent(Long equipmentId,
                                 String fromLocation,
                                 String toLocation,
                                 Long userId,
                                 Long reservationId,
                                 String eventType,
                                 String notes) {

        int equipmentNumber = equipmentId.intValue();
        Date now = new Date();

        // 1. Close the current open location interval (if any)
        EquipmentLocationHistory current = locationHistoryDAO.findOpenLocation(equipmentNumber);
        if (current != null) {
            current.setEndDateTime(now);
            locationHistoryDAO.update(current);
        }

        // 2. Build the movement event
        EquipmentEvent event = new EquipmentEvent();
        event.setEquipmentNumber(equipmentNumber);
        event.setEventType(eventType);
        event.setEventDateTime(now);

        // For now, treat fromLocation/toLocation as street-only;
        // you can expand this later to full address if needed.
        event.setFromStreet(fromLocation);
        event.setFromCity(null);
        event.setFromProvince(null);
        event.setFromPostal(null);
        event.setFromCountry(null);

        event.setToStreet(toLocation);
        event.setToCity(null);
        event.setToProvince(null);
        event.setToPostal(null);
        event.setToCountry(null);

        event.setReservationId(reservationId == null ? null : reservationId.intValue());
        event.setNotes(notes);

        // 3. Generic event pipeline
        recordEvent(event);
    }

    // ---------------------------------------------------------------------
    // GENERIC EVENT PIPELINE
    // ---------------------------------------------------------------------

    public void recordEvent(EquipmentEvent event) {
        eventDAO.insert(event);
        updateCurrentLocation(event);
    }

    // ---------------------------------------------------------------------
    // LOCATION HISTORY UPDATE
    // ---------------------------------------------------------------------

    private void updateCurrentLocation(EquipmentEvent event) {
        Date now = event.getEventDateTime();

        EquipmentLocationHistory newLocation = new EquipmentLocationHistory();
        newLocation.setEquipmentNumber(event.getEquipmentNumber());

        newLocation.setStreet(event.getToStreet());
        newLocation.setCity(event.getToCity());
        newLocation.setProvince(event.getToProvince());
        newLocation.setPostal(event.getToPostal());
        newLocation.setCountry(event.getToCountry());

        newLocation.setLocationType(determineLocationType(event.getEventType()));
        newLocation.setStartDateTime(now);
        newLocation.setReservationId(event.getReservationId());
        newLocation.setNotes(event.getNotes());

        locationHistoryDAO.insertLocationHistory(newLocation);
    }

    // ---------------------------------------------------------------------
    // Helper: determine location type based on movement event
    // ---------------------------------------------------------------------
    private String determineLocationType(String eventType) {
        switch (eventType) {
            case "PICKUP":
                return "CUSTOMER_SITE";
            case "DELIVERY":
                return "DELIVERY_SITE";
            case "MOVE":
                return "SWAP_SITE";
            case "RETURN":
                return "ON_PREMISE";
            default:
                return "CUSTOMER_SITE";
        }
    }
}