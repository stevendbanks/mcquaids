package com.mcquaids.service;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.ZoneId;
=======
>>>>>>> origin/main
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

<<<<<<< HEAD


import com.mcquaids.dao.EquipmentDAO;
import com.mcquaids.dao.EquipmentEventDAO;
import com.mcquaids.dao.EquipmentLocationHistoryDAO;
import com.mcquaids.model.Equipment;
import com.mcquaids.model.EquipmentEvent;
import com.mcquaids.model.EquipmentLocationHistory;
import com.mcquaids.model.SafetyStatus;

public class EquipmentEventService {

    private final AvailabilityService availabilityService;	
	
    private final EquipmentDAO equipmentDAO;
=======
import com.mcquaids.dao.EquipmentEventDAO;
import com.mcquaids.dao.EquipmentLocationHistoryDAO;
import com.mcquaids.model.EquipmentEvent;
import com.mcquaids.model.EquipmentLocationHistory;

public class EquipmentEventService {

>>>>>>> origin/main
    private final EquipmentEventDAO eventDAO;
    private final EquipmentLocationHistoryDAO locationHistoryDAO;

    public EquipmentEventService() {
<<<<<<< HEAD
    	 this.availabilityService = new AvailabilityService();
    	
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.equipmentDAO = new EquipmentDAO(jdbcTemplate);
=======
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;

>>>>>>> origin/main
        this.eventDAO = new EquipmentEventDAO(jdbcTemplate);
        this.locationHistoryDAO = new EquipmentLocationHistoryDAO(jdbcTemplate);
    }

    // ---------------------------------------------------------------------
    // NON-MOVEMENT EVENTS
    // ---------------------------------------------------------------------

<<<<<<< HEAD
    public void recordInspection(Integer equipmentNumber, String inspector, String notes) {
        EquipmentEvent event = EquipmentEvent.forInspection(equipmentNumber, inspector, notes);
        recordEvent(event);
    }

    public void recordDamage(Integer equipmentNumber, String notes, String createdBy) {
        EquipmentEvent event = new EquipmentEvent();
        event.setEquipmentNumber(equipmentNumber);
        event.setEventType("DAMAGE_DISCOVERED");
        event.setCreatedAt(LocalDateTime.now());
        event.setCreatedBy(createdBy);
        event.setNotes(notes);
        recordEvent(event);
    }

    public void recordMaintenance(Integer equipmentNumber, String notes) {
        EquipmentEvent event = EquipmentEvent.forMaintenanceCompleted(equipmentNumber, notes);
        recordEvent(event);
    }

    // ---------------------------------------------------------------------
    // UNIFIED MOVEMENT PIPELINE
    // ---------------------------------------------------------------------

    public void recordUnifiedMove(
            Integer equipmentNumber,
            EquipmentLocationHistory newLocation,
            String eventType,
            String notes,
            Integer reservationId,
            Long actionId,
            String actionType,
            String createdBy) {

        LocalDateTime now = LocalDateTime.now();

        // 1. Close previous interval
=======
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
>>>>>>> origin/main
        EquipmentLocationHistory current = locationHistoryDAO.findOpenLocation(equipmentNumber);
        if (current != null) {
            current.setEndDateTime(now);
            locationHistoryDAO.update(current);
        }

<<<<<<< HEAD
        // 2. Insert new interval
        newLocation.setEquipmentNumber(equipmentNumber);
        newLocation.setStartDateTime(now);
        newLocation.setEndDateTime(null);
        newLocation.setReservationId(reservationId);
        newLocation.setActionId(actionId);
        newLocation.setActionType(actionType);
        newLocation.setNotes(notes);

        locationHistoryDAO.insertLocationHistory(newLocation);

        // 3. Create semantic event
        EquipmentEvent event = new EquipmentEvent();
        event.setEquipmentNumber(equipmentNumber);
        event.setEventType(eventType);
        event.setActionId(actionId);
        event.setActionType(actionType);
        event.setCreatedAt(now);
        event.setCreatedBy(createdBy);
        event.setNotes(notes);

        eventDAO.insert(event);
    }
    
    public void recordEvent(EquipmentEvent event) {

    	eventDAO.insert(event);

        switch (event.getActionType()) {
            case "INSPECTION":
                handleInspection(event);
                break;

            case "DAMAGE":
                handleDamage(event);
                break;

            case "MAINTENANCE":
                handleMaintenance(event);
                break;
        }

        availabilityService.recalculate(event.getEquipmentNumber());
    }

    private void handleInspection(EquipmentEvent event) {
        Integer equipmentNumber = event.getEquipmentNumber();

        // Load the equipment record
        Equipment equipment = equipmentDAO.findByEquipmentNumber(equipmentNumber);
        if (equipment == null) {
            // Optionally log this — an event without equipment is a data integrity issue
            return;
        }

        // Convert LocalDateTime → Date
        Date inspectionDate = Date.from(
            event.getCreatedAt()
                 .atZone(ZoneId.systemDefault())
                 .toInstant()
        );
        
        equipment.setInspectionDate(inspectionDate);

        // Persist the update
        equipmentDAO.updateEquipment(equipment);
    }

    private void handleDamage(EquipmentEvent event) {
        Integer equipmentNumber = event.getEquipmentNumber();

        Equipment equipment = equipmentDAO.findByEquipmentNumber(equipmentNumber);
        if (equipment == null) {
            return;
        }

        // Mark equipment as damaged / unsafe
        equipment.setSafetyStatusCode(SafetyStatus.DAMAGED);  // or SafetyStatusCode.DAMAGED

        // Persist the update
        equipmentDAO.updateEquipment(equipment);
    }
    
    private void handleMaintenance(EquipmentEvent event) {
        Integer equipmentNumber = event.getEquipmentNumber();

        Equipment equipment = equipmentDAO.findByEquipmentNumber(equipmentNumber);
        if (equipment == null) {
            return;
        }

        // 1. Mark equipment as safe / repaired
        equipment.setSafetyStatusCode(SafetyStatus.OK);


// ************  Since the EquipmentEvent class will have the entire history of maintenance events, we do not need it here        
//        // 2. Update maintenance date (using event timestamp)
//        Date maintenanceDate = Date.from(
//            event.getCreatedAt()
//                 .atZone(ZoneId.systemDefault())
//                 .toInstant()
//        );
//        equipment.setMaintenanceDate(maintenanceDate);

        // 3. Persist the update
        equipmentDAO.updateEquipment(equipment);
    }
    
    
    
}
=======
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
>>>>>>> origin/main
