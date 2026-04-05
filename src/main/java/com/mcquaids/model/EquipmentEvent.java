package com.mcquaids.model;

<<<<<<< HEAD
import java.time.LocalDateTime;

public class EquipmentEvent {

    private Long eventId;
    private Integer equipmentNumber;

    private String eventType;     // DRIVER_COMPLETED_ACTION, INSPECTED, etc.
    private Long actionId;        // dispatch_action_id, work_order_action_id, etc.
    private String actionType;    // DISPATCH, WORKORDER, MAINTENANCE, INSPECTION

    private String notes;
    private LocalDateTime createdAt;
    private String createdBy;
    
    
    public static EquipmentEvent forDriverCompletedAction(DispatchAction action, String notes) {
        EquipmentEvent e = new EquipmentEvent();
        e.setEventType("DRIVER_COMPLETED_ACTION");
        e.setEquipmentNumber(action.getEquipmentNumber());
        e.setActionId(action.getDispatchActionId());
        e.setActionType("DISPATCH");
        e.setNotes(notes);
        e.setCreatedAt(LocalDateTime.now());
        e.setCreatedBy("driver");
        return e;
    }
    

    public static EquipmentEvent forMaintenanceCompleted(Integer equipmentNumber, String notes) {
        EquipmentEvent e = new EquipmentEvent();
        e.setEventType("MAINTENANCE_COMPLETED");
        e.setEquipmentNumber(equipmentNumber);
        e.setActionType("MAINTENANCE");
        e.setNotes(notes);
        e.setCreatedAt(LocalDateTime.now());
        e.setCreatedBy("maintenance");
        return e;
    }    
    
    public static EquipmentEvent forInspection(Integer equipmentNumber, String inspector, String notes) {
        EquipmentEvent e = new EquipmentEvent();
        e.setEventType("INSPECTED");
        e.setEquipmentNumber(equipmentNumber);
        e.setActionType("INSPECTION");
        e.setNotes("Inspector: " + inspector + ". " + notes);
        e.setCreatedAt(LocalDateTime.now());
        e.setCreatedBy(inspector);
        return e;
    }    
    
    public static EquipmentEvent forWorkOrderCompleted(WorkOrderAction action, String notes) {
        EquipmentEvent e = new EquipmentEvent();
        e.setEventType("WORKORDER_COMPLETED");
        e.setEquipmentNumber(action.getEquipmentNumber());
        e.setActionId(action.getWorkOrderActionId());
        e.setActionType("WORKORDER");
        e.setNotes(notes);
        e.setCreatedAt(LocalDateTime.now());
        e.setCreatedBy("driver");
        return e;
    }    
    
    
	/**
	 * @return the eventId
	 */
	public Long getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the equipmentNumber
	 */
	public Integer getEquipmentNumber() {
		return equipmentNumber;
	}
	/**
	 * @param equipmentNumber the equipmentNumber to set
	 */
	public void setEquipmentNumber(Integer equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return the actionId
	 */
	public Long getActionId() {
		return actionId;
	}
	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


}
=======

import java.util.Date;

public class EquipmentEvent {

    private int eventId;
    private int equipmentNumber;

    private String eventType;        // e.g., PICKUP, DROPOFF, INSPECTED, DAMAGE_DISCOVERED, MAINTENANCE
    private Date eventDateTime;

    // Optional FROM location (movement events only)
    private String fromStreet;
    private String fromCity;
    private String fromProvince;
    private String fromPostal;
    private String fromCountry;

    // Optional TO location (movement events only)
    private String toStreet;
    private String toCity;
    private String toProvince;
    private String toPostal;
    private String toCountry;

    private Integer reservationId;   // nullable
    private String notes;

    // ----- Getters and Setters -----

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(int equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(Date eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getFromStreet() {
        return fromStreet;
    }

    public void setFromStreet(String fromStreet) {
        this.fromStreet = fromStreet;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getFromProvince() {
        return fromProvince;
    }

    public void setFromProvince(String fromProvince) {
        this.fromProvince = fromProvince;
    }

    public String getFromPostal() {
        return fromPostal;
    }

    public void setFromPostal(String fromPostal) {
        this.fromPostal = fromPostal;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getToStreet() {
        return toStreet;
    }

    public void setToStreet(String toStreet) {
        this.toStreet = toStreet;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToProvince() {
        return toProvince;
    }

    public void setToProvince(String toProvince) {
        this.toProvince = toProvince;
    }

    public String getToPostal() {
        return toPostal;
    }

    public void setToPostal(String toPostal) {
        this.toPostal = toPostal;
    }

    public String getToCountry() {
        return toCountry;
    }

    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
>>>>>>> origin/main
