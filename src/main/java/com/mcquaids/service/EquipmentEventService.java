package com.mcquaids.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;



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
    private final EquipmentEventDAO eventDAO;
    private final EquipmentLocationHistoryDAO locationHistoryDAO;

    public EquipmentEventService() {
    	 this.availabilityService = new AvailabilityService();
    	
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.equipmentDAO = new EquipmentDAO(jdbcTemplate);
        this.eventDAO = new EquipmentEventDAO(jdbcTemplate);
        this.locationHistoryDAO = new EquipmentLocationHistoryDAO(jdbcTemplate);
    }

    // ---------------------------------------------------------------------
    // NON-MOVEMENT EVENTS
    // ---------------------------------------------------------------------

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
        EquipmentLocationHistory current = locationHistoryDAO.findOpenLocation(equipmentNumber);
        if (current != null) {
            current.setEndDateTime(now);
            locationHistoryDAO.update(current);
        }

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
