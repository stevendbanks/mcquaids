package com.mcquaids.actions.admin.events;

<<<<<<< HEAD
import com.mcquaids.model.Address;
import com.mcquaids.model.EquipmentLocationHistory;
=======


import com.mcquaids.model.Address;
>>>>>>> origin/main
import com.mcquaids.service.EquipmentEventService;
import com.opensymphony.xwork2.ActionSupport;

public class AdminEquipmentEventAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
    // Incoming fields
=======
    // -----------------------------
    // Incoming JSON fields
    // -----------------------------
>>>>>>> origin/main
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

<<<<<<< HEAD
    // Injected service
=======
    // -----------------------------
    // Service (setter injection recommended)
    // -----------------------------
>>>>>>> origin/main
    private EquipmentEventService equipmentEventService;

    public void setEquipmentEventService(EquipmentEventService equipmentEventService) {
        this.equipmentEventService = equipmentEventService;
    }

<<<<<<< HEAD
=======
    // -----------------------------
    // Execute
    // -----------------------------
>>>>>>> origin/main
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

<<<<<<< HEAD
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
=======
            // Classic Java 13 switch
            switch (eventType) {

                case "INSPECTED":
                    equipmentEventService.recordInspection(
                            equipmentNumber,
                            notes,
                            reservationId
                    );
                    break;

                case "DAMAGE_DISCOVERED":
                    equipmentEventService.recordDamage(
                            equipmentNumber,
                            notes,
                            reservationId
                    );
                    break;

                case "MAINTENANCE":
                    equipmentEventService.recordMaintenance(
                            equipmentNumber,
                            notes,
                            reservationId
                    );
                    break;

//                case "PICKUP":
//                    equipmentEventService.recordPickupEvent(
//                            reservationId,
//                            String.valueOf(equipmentNumber),
//                            buildFromAddress(),
//                            getCurrentUserId(),
//                            null
//                    );
//                    break;
//
//                case "DROPOFF":
//                    equipmentEventService.recordDeliveryEvent(
//                            reservationId,
//                            String.valueOf(equipmentNumber),
//                            buildToAddress(),
//                            getCurrentUserId(),
//                            null
//                    );
//                    break;
//
//                case "TRANSFER":
//                    equipmentEventService.recordMoveEvent(
//                            String.valueOf(equipmentNumber),
//                            buildFromAddress(),
//                            buildToAddress(),
//                            getCurrentUserId(),
//                            null,
//                            reservationId
//                    );
//                    break;
//
//                case "RETURN":
//                    equipmentEventService.recordMoveEvent(
//                            String.valueOf(equipmentNumber),
//                            buildFromAddress(),
//                            buildToAddress(),
//                            getCurrentUserId(),
//                            null,
//                            reservationId
//                    );
//                    break;
>>>>>>> origin/main

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

<<<<<<< HEAD
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
=======
    // -----------------------------
    // Helpers
    // -----------------------------
    private Address buildFromAddress() {
        return new Address(
                fromStreet,
                fromCity,
                fromProvince,
                fromPostal,
                fromCountry
        );
    }

    private Address buildToAddress() {
        return new Address(
                toStreet,
                toCity,
                toProvince,
                toPostal,
                toCountry
        );
    }

    private Long getCurrentUserId() {
        // Replace with your actual session/auth logic
        return null;
    }

    // -----------------------------
    // Getters & Setters
    // -----------------------------
    public int getEquipmentNumber() { return equipmentNumber; }
    public void setEquipmentNumber(int equipmentNumber) { this.equipmentNumber = equipmentNumber; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public Integer getReservationId() { return reservationId; }
    public void setReservationId(Integer reservationId) { this.reservationId = reservationId; }

    public String getFromStreet() { return fromStreet; }
    public void setFromStreet(String fromStreet) { this.fromStreet = fromStreet; }

    public String getFromCity() { return fromCity; }
    public void setFromCity(String fromCity) { this.fromCity = fromCity; }

    public String getFromProvince() { return fromProvince; }
    public void setFromProvince(String fromProvince) { this.fromProvince = fromProvince; }

    public String getFromPostal() { return fromPostal; }
    public void setFromPostal(String fromPostal) { this.fromPostal = fromPostal; }

    public String getFromCountry() { return fromCountry; }
    public void setFromCountry(String fromCountry) { this.fromCountry = fromCountry; }

    public String getToStreet() { return toStreet; }
    public void setToStreet(String toStreet) { this.toStreet = toStreet; }

    public String getToCity() { return toCity; }
    public void setToCity(String toCity) { this.toCity = toCity; }

    public String getToProvince() { return toProvince; }
    public void setToProvince(String toProvince) { this.toProvince = toProvince; }

    public String getToPostal() { return toPostal; }
    public void setToPostal(String toPostal) { this.toPostal = toPostal; }

    public String getToCountry() { return toCountry; }
    public void setToCountry(String toCountry) { this.toCountry = toCountry; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
>>>>>>> origin/main
}