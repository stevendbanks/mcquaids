package com.mcquaids.actions.admin.events;

import com.mcquaids.model.Address;
import com.mcquaids.model.EquipmentLocationHistory;
import com.mcquaids.service.EquipmentEventService;
import com.opensymphony.xwork2.ActionSupport;

public class AdminEquipmentEventAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    // Incoming fields
    private int equipmentNumber;
    private String eventType;
    private Integer reservationId;

    private String fromStreet;
    private String fromCity;
    private String fromProvince;
    private String fromPostal;
    private String fromCountry;

    private String toStreet;
    private String toCity;
    private String toProvince;
    private String toPostal;
    private String toCountry;

    private String notes;

    // Injected service
    private EquipmentEventService equipmentEventService;

    public void setEquipmentEventService(EquipmentEventService equipmentEventService) {
        this.equipmentEventService = equipmentEventService;
    }

    @Override
    public String execute() {

        try {
            if (equipmentEventService == null) {
                addActionError("EquipmentEventService not configured");
                return ERROR;
            }

            if (eventType == null) {
                addActionError("Event type is required");
                return ERROR;
            }

            switch (eventType) {

                // -----------------------------------------
                // NON-MOVEMENT EVENTS
                // -----------------------------------------
                case "INSPECTED":
                    equipmentEventService.recordInspection(reservationId, "Mechanic Name Here", notes);
                    break;

                case "DAMAGE_DISCOVERED":
                    equipmentEventService.recordDamage(equipmentNumber,"Driver Name Here", notes);
                    break;

                case "MAINTENANCE":
                    equipmentEventService.recordMaintenance(equipmentNumber, notes);
                    break;

                // -----------------------------------------
                // MOVEMENT EVENTS (use unified pipeline)
                // -----------------------------------------
                case "PICKUP":
                case "DROPOFF":
                case "TRANSFER":
                case "RETURN":
                    handleMovementEvent();
                    break;

                default:
                    addActionError("Unknown event type: " + eventType);
                    return ERROR;
            }

            addActionMessage("Event recorded successfully");
            return SUCCESS;

        } catch (Exception ex) {
            addActionError("Error recording event: " + ex.getMessage());
            return ERROR;
        }
    }

    private void handleMovementEvent() {

        EquipmentLocationHistory loc = new EquipmentLocationHistory();
        loc.setNotes(notes);

        // FROM address (optional)
        Address from = buildFromAddress();
        Address to = buildToAddress();

        // Determine location type
        if (to != null && to.getStreet() != null) {
            loc.setLocationType("CUSTOMER_SITE");
            loc.setStreet(to.getStreet());
            loc.setCity(to.getCity());
            loc.setProvince(to.getProvince());
            loc.setPostal(to.getPostalCode());
            loc.setCountry(to.getCountry());
        } else {
            // Default to warehouse
            loc.setLocationType("ON_PREMISE");
        }

        equipmentEventService.recordUnifiedMove(
                equipmentNumber,
                loc,
                eventType,
                notes,
                reservationId,
                null  // admin userId (optional)
        );
    }

    private Address buildFromAddress() {
        return new Address(fromStreet, fromCity, fromProvince, fromPostal, fromCountry);
    }

    private Address buildToAddress() {
        return new Address(toStreet, toCity, toProvince, toPostal, toCountry);
    }

    private Long getCurrentUserId() {
        return null;
    }

    // Getters & setters omitted for brevity...
}