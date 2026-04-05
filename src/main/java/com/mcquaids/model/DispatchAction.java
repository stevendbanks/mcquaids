package com.mcquaids.model;

<<<<<<< HEAD
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
=======
import java.time.LocalDateTime;
>>>>>>> origin/main

public class DispatchAction {

	private Long dispatchActionId;
<<<<<<< HEAD
	private boolean removedFromReservation;
	private Integer reservationID;
	private Integer equipmentNumber;
	


	private DispatchActionType actionType;
	
	private String googleEventId;
	private String googleCalendarId;
	
	
	
	private Instant lastCalendarSyncAt;

	private DispatchActionStatus status;
	private LocalDateTime completedAt;
	

	private Address fromAddress;
	private Address toAddress;
	
	private Long fromYardId;
	private String fromLocationName;

	private Long toYardId;
	private String toLocationName;	
	

	private LocalDateTime scheduledDateTime;
	private Long driverId;
	private String driverToken;	

	
	private String notes;

	private Integer reservationLineItemID;
	private ReservationLineItemDTO reservationLineItemDTO;

	
=======
	private Long reservationId;
	private String equipmentNumber;

	private DispatchActionType actionType;

>>>>>>> origin/main
	/**
	 * Indicates where this dispatch action originated from.
	 *
	 * MVP placeholder only — this value is set by the UI when generating
	 * dispatch actions (e.g., RESERVATION or WORK_ORDER) but is not yet
	 * persisted to the database. It allows the dispatch plan UI to display
	 * the source workflow and keeps the system future‑proof for when
	 * Work Orders are introduced.
	 */
	private DispatchSourceType sourceType;
	
	
	
<<<<<<< HEAD
=======
	private DispatchActionStatus status;
	
	
	

	private Address fromAddress;
	private Address toAddress;

	private LocalDateTime scheduledDateTime;
	private Long driverId;

	private String notes;

>>>>>>> origin/main
	public DispatchAction() {
	}

	private DispatchAction(Builder builder) {
		this.dispatchActionId = builder.dispatchActionId;
<<<<<<< HEAD
		this.reservationID = builder.reservationID;
		this.reservationLineItemID = builder.reservationLineItemID;
=======
		this.reservationId = builder.reservationId;
>>>>>>> origin/main
		this.equipmentNumber = builder.equipmentNumber;
		this.actionType = builder.actionType;
		this.status = builder.status;
		this.fromAddress = builder.fromAddress;
		this.toAddress = builder.toAddress;
		this.scheduledDateTime = builder.scheduledDateTime;
		this.driverId = builder.driverId;
		this.notes = builder.notes;
<<<<<<< HEAD
		
	    this.reservationLineItemDTO = builder.reservationLineItemDTO;

	}
	
	public DispatchAction(DispatchAction other) {
	    this.dispatchActionId = other.dispatchActionId;
	    this.removedFromReservation = other.removedFromReservation;
	    this.reservationID = other.reservationID;
	    this.reservationLineItemID = other.reservationLineItemID;
	    this.equipmentNumber = other.equipmentNumber;

	    this.actionType = other.actionType;
	    this.status = other.status;
	    this.completedAt = other.completedAt;

	    this.fromAddress = other.fromAddress;
	    this.toAddress = other.toAddress;

	    this.fromYardId = other.fromYardId;
	    this.fromLocationName = other.fromLocationName;

	    this.toYardId = other.toYardId;
	    this.toLocationName = other.toLocationName;

	    this.scheduledDateTime = other.scheduledDateTime;
	    this.driverId = other.driverId;
	    this.driverToken = other.driverToken;

	    this.notes = other.notes;

	    // ⭐ THE IMPORTANT PART ⭐
	    this.googleEventId = other.googleEventId;
	    this.googleCalendarId = other.googleCalendarId;
	    this.lastCalendarSyncAt = other.lastCalendarSyncAt;

	    // Also copy your DTO
	    this.reservationLineItemDTO = other.reservationLineItemDTO;

	    this.sourceType = other.sourceType;
	}	
	

	public String getUniversalEidUrl() {
	    if (googleEventId == null || googleCalendarId == null) {
	        return null;
	    }

	    String raw = googleEventId + " " + googleCalendarId;
	    String encoded = Base64.getUrlEncoder().encodeToString(
	        raw.getBytes(StandardCharsets.UTF_8)
	    );

	    return "https://www.google.com/calendar/event?eid=" + encoded;
	}	
	
	
=======
	}

	/**
	 * @return the dispatchActionId
	 */
	public Long getDispatchActionId() {
		return dispatchActionId;
	}

	/**
	 * @param dispatchActionId the dispatchActionId to set
	 */
	public void setDispatchActionId(Long dispatchActionId) {
		this.dispatchActionId = dispatchActionId;
	}
>>>>>>> origin/main

	/**
	 * @return the reservationId
	 */
<<<<<<< HEAD
	public Integer getReservationID() {
		return reservationID;
	}

	/**
	 * @param reservationID the reservationID to set
	 */
	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}

	/**
	 * @return the removedFromReservation
	 */
	public boolean isRemovedFromReservation() {
		return removedFromReservation;
	}

	/**
	 * @param removedFromReservation the removedFromReservation to set
	 */
	public void setRemovedFromReservation(boolean removedFromReservation) {
		this.removedFromReservation = removedFromReservation;
	}

	/**



	/**
=======
	public Long getReservationId() {
		return reservationId;
	}

	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * @return the equipmentNumber
	 */
	public String getEquipmentNumber() {
		return equipmentNumber;
	}

	/**
	 * @param equipmentNumber the equipmentNumber to set
	 */
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}

	/**
>>>>>>> origin/main
	 * @return the actionType
	 */
	public DispatchActionType getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(DispatchActionType actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the status
	 */
	public DispatchActionStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(DispatchActionStatus status) {
		this.status = status;
	}

<<<<<<< HEAD
	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

=======
>>>>>>> origin/main
	/**
	 * @return the fromAddress
	 */
	public Address getFromAddress() {
		return fromAddress;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(Address fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the toAddress
	 */
	public Address getToAddress() {
		return toAddress;
	}

	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(Address toAddress) {
		this.toAddress = toAddress;
	}

	/**
<<<<<<< HEAD
	 * @return the fromYardId
	 */
	public Long getFromYardId() {
		return fromYardId;
	}

	/**
	 * @param fromYardId the fromYardId to set
	 */
	public void setFromYardId(Long fromYardId) {
		this.fromYardId = fromYardId;
	}

	/**
	 * @return the fromLocationName
	 */
	public String getFromLocationName() {
		return fromLocationName;
	}

	/**
	 * @param fromLocationName the fromLocationName to set
	 */
	public void setFromLocationName(String fromLocationName) {
		this.fromLocationName = fromLocationName;
	}

	/**
	 * @return the toYardId
	 */
	public Long getToYardId() {
		return toYardId;
	}

	/**
	 * @param toYardId the toYardId to set
	 */
	public void setToYardId(Long toYardId) {
		this.toYardId = toYardId;
	}

	/**
	 * @return the toLocationName
	 */
	public String getToLocationName() {
		return toLocationName;
	}

	/**
	 * @param toLocationName the toLocationName to set
	 */
	public void setToLocationName(String toLocationName) {
		this.toLocationName = toLocationName;
	}

	/**
=======
>>>>>>> origin/main
	 * @return the scheduledDateTime
	 */
	public LocalDateTime getScheduledDateTime() {
		return scheduledDateTime;
	}
<<<<<<< HEAD
	
	public ZonedDateTime getScheduledDateTimeAtZone() {
	    if (scheduledDateTime == null) {
	        return null;
	    }

	    // scheduledDateTime is a LocalDateTime for now
	    ZoneId zone = ZoneId.of("America/Halifax");

	    // Default time-of-day: midnight (00:00)
	    return scheduledDateTime.atZone(zone);
	}
	
=======
>>>>>>> origin/main

	/**
	 * @param scheduledDateTime the scheduledDateTime to set
	 */
	public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}

	/**
	 * @return the driverId
	 */
	public Long getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

<<<<<<< HEAD
	public String getDriverToken() {
		return driverToken;
	}

	public void setDriverToken(String driverToken) {
		this.driverToken = driverToken;
	}

=======
>>>>>>> origin/main
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

<<<<<<< HEAD
	/**
	 * @return the googleEventId
	 */
	public String getGoogleEventId() {
		return googleEventId;
	}

	/**
	 * @param googleEventId the googleEventId to set
	 */
	public void setGoogleEventId(String googleEventId) {
		this.googleEventId = googleEventId;
	}


	public String getGoogleCalendarId() {
		return googleCalendarId;
	}

	public void setGoogleCalendarId(String googleCalendarId) {
		this.googleCalendarId = googleCalendarId;
	}

	public Instant getLastCalendarSyncAt() {
		return lastCalendarSyncAt;
	}

	public void setLastCalendarSyncAt(Instant lastCalendarSyncAt) {
		this.lastCalendarSyncAt = lastCalendarSyncAt;
	}

=======
>>>>>>> origin/main
	public DispatchSourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(DispatchSourceType sourceType) {
		this.sourceType = sourceType;
	}

	public static Builder builder() {
		return new Builder();
	}
<<<<<<< HEAD
	

	public Integer getReservationLineItemID() {
	    return reservationLineItemID;
	}

	public void setReservationLineItemID(Integer reservationLineItemID) {
	    this.reservationLineItemID = reservationLineItemID;
	}	
		
	

	/**
	 * @return the reservationLineItemDTO
	 */
	public ReservationLineItemDTO getReservationLineItemDTO() {
		return reservationLineItemDTO;
	}

	/**
	 * @param reservationLineItemDTO the reservationLineItemDTO to set
	 */
	public void setReservationLineItemDTO(ReservationLineItemDTO reservationLineItemDTO) {
		this.reservationLineItemDTO = reservationLineItemDTO;
	}





	public void setEquipmentNumber(Integer equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
		
	}

	public Integer getEquipmentNumber() {
		return this.equipmentNumber;
	}

	public Long getDispatchActionId() {
	    return dispatchActionId;
	}

	public void setDispatchActionId(Long dispatchActionId) {
	    this.dispatchActionId = dispatchActionId;
	}
	
	public String getFromDisplay() {
	    if (fromYardId != null && fromLocationName != null) {
	        return fromLocationName;
	    }
	    if (fromAddress != null) {
	        return fromAddress.toSingleLine();
	    }
	    return "";
	}

	public String getToDisplay() {
	    if (toYardId != null && toLocationName != null) {
	        return toLocationName;
	    }
	    if (toAddress != null) {
	        return toAddress.toSingleLine();
	    }
	    return "";
	}	
	
	public String getScheduledDateTimeDisplay() {
	    if (scheduledDateTime == null) return "";

	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");
	    return scheduledDateTime.format(fmt);
	}
	
	@Override
	public String toString() {
		return "DispatchAction [dispatchActionId=" + dispatchActionId + ", removedFromReservation="
				+ removedFromReservation + ", reservationID=" + reservationID + ", equipmentNumber=" + equipmentNumber
				+ ", actionType=" + actionType + ", sourceType=" + sourceType + ", status=" + status + ", fromAddress="
				+ fromAddress + ", toAddress=" + toAddress + ", scheduledDateTime=" + scheduledDateTime + ", driverId="
				+ driverId + ", notes=" + notes + ", reservationLineItemID=" + reservationLineItemID + "]";
	}
	
	public static class Builder {

	    private Long dispatchActionId;
	    private boolean removedFromReservation;
	    private Integer reservationID;
	    private Integer reservationLineItemID;
	    private Integer equipmentNumber;

	    private DispatchActionType actionType;
	    private DispatchActionStatus status;

	    private String googleEventId;
	    private String googleCalendarId;
	    private Instant lastCalendarSyncAt;

	    private LocalDateTime completedAt;

	    private Address fromAddress;
	    private Address toAddress;

	    private Long fromYardId;
	    private String fromLocationName;

	    private Long toYardId;
	    private String toLocationName;

	    private LocalDateTime scheduledDateTime;
	    private Long driverId;
	    private String driverToken;

	    private String notes;

	    private ReservationLineItemDTO reservationLineItemDTO;

	    private DispatchSourceType sourceType;


	    // ------------------------------------------------------------
	    // Fluent Setters
	    // ------------------------------------------------------------

	    public Builder dispatchActionId(Long id) {
	        this.dispatchActionId = id;
	        return this;
	    }

	    public Builder removedFromReservation(boolean removed) {
	        this.removedFromReservation = removed;
	        return this;
	    }
	    
	    public boolean isRemovedFromReservation() {
	        return removedFromReservation;
	    }

	    public void setRemovedFromReservation(boolean removedFromReservation) {
	        this.removedFromReservation = removedFromReservation;
	    }	    

	    public Builder reservationID(Integer id) {
	        this.reservationID = id;
	        return this;
	    }

	    public Builder reservationLineItemID(Integer id) {
	        this.reservationLineItemID = id;
	        return this;
	    }

	    public Builder reservationLineItemDTO(ReservationLineItemDTO dto) {
	        this.reservationLineItemDTO = dto;
	        return this;
	    }

	    public Builder equipmentNumber(Integer num) {
	        this.equipmentNumber = num;
	        return this;
	    }

	    public Builder actionType(DispatchActionType type) {
	        this.actionType = type;
	        return this;
	    }

	    public Builder status(DispatchActionStatus status) {
	        this.status = status;
	        return this;
	    }

	    public Builder googleEventId(String id) {
	        this.googleEventId = id;
	        return this;
	    }

	    public Builder googleCalendarId(String id) {
	        this.googleCalendarId = id;
	        return this;
	    }

	    public Builder lastCalendarSyncAt(Instant ts) {
	        this.lastCalendarSyncAt = ts;
	        return this;
	    }

	    public Builder completedAt(LocalDateTime ts) {
	        this.completedAt = ts;
	        return this;
	    }

	    public Builder fromAddress(Address addr) {
	        this.fromAddress = addr;
	        return this;
	    }

	    public Builder toAddress(Address addr) {
	        this.toAddress = addr;
	        return this;
	    }

	    public Builder fromYardId(Long id) {
	        this.fromYardId = id;
	        return this;
	    }

	    public Builder fromLocationName(String name) {
	        this.fromLocationName = name;
	        return this;
	    }

	    public Builder toYardId(Long id) {
	        this.toYardId = id;
	        return this;
	    }

	    public Builder toLocationName(String name) {
	        this.toLocationName = name;
	        return this;
	    }

	    public Builder scheduledDateTime(LocalDateTime dt) {
	        this.scheduledDateTime = dt;
	        return this;
	    }

	    public Builder driverId(Long id) {
	        this.driverId = id;
	        return this;
	    }

	    public Builder driverToken(String token) {
	        this.driverToken = token;
	        return this;
	    }

	    public Builder notes(String notes) {
	        this.notes = notes;
	        return this;
	    }

	    public Builder sourceType(DispatchSourceType type) {
	        this.sourceType = type;
	        return this;
	    }


	    // ------------------------------------------------------------
	    // Legacy Getters/Setters (preserved exactly as you had them)
	    // ------------------------------------------------------------

	    public Long getDispatchActionId() {
	        return dispatchActionId;
	    }

	    public void setDispatchActionId(Long dispatchActionId) {
	        this.dispatchActionId = dispatchActionId;
	    }

	    public Integer getEquipmentNumber() {
	        return equipmentNumber;
	    }

	    public void setEquipmentNumber(Integer equipmentNumber) {
	        this.equipmentNumber = equipmentNumber;
	    }

	    public DispatchActionType getActionType() {
	        return actionType;
	    }

	    public void setActionType(DispatchActionType actionType) {
	        this.actionType = actionType;
	    }

	    public DispatchActionStatus getStatus() {
	        return status;
	    }

	    public void setStatus(DispatchActionStatus status) {
	        this.status = status;
	    }

	    public Address getFromAddress() {
	        return fromAddress;
	    }

	    public void setFromAddress(Address fromAddress) {
	        this.fromAddress = fromAddress;
	    }

	    public Address getToAddress() {
	        return toAddress;
	    }

	    public void setToAddress(Address toAddress) {
	        this.toAddress = toAddress;
	    }

	    public LocalDateTime getScheduledDateTime() {
	        return scheduledDateTime;
	    }

	    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
	        this.scheduledDateTime = scheduledDateTime;
	    }

	    public Long getDriverId() {
	        return driverId;
	    }

	    public void setDriverId(Long driverId) {
	        this.driverId = driverId;
	    }

	    public String getNotes() {
	        return notes;
	    }

	    public void setNotes(String notes) {
	        this.notes = notes;
	    }


	    // ------------------------------------------------------------
	    // New Getters/Setters for new fields
	    // ------------------------------------------------------------

	    public String getGoogleEventId() {
	        return googleEventId;
	    }

	    public void setGoogleEventId(String googleEventId) {
	        this.googleEventId = googleEventId;
	    }

	    public String getGoogleCalendarId() {
	        return googleCalendarId;
	    }

	    public void setGoogleCalendarId(String googleCalendarId) {
	        this.googleCalendarId = googleCalendarId;
	    }

	    public Instant getLastCalendarSyncAt() {
	        return lastCalendarSyncAt;
	    }

	    public void setLastCalendarSyncAt(Instant lastCalendarSyncAt) {
	        this.lastCalendarSyncAt = lastCalendarSyncAt;
	    }

	    public LocalDateTime getCompletedAt() {
	        return completedAt;
	    }

	    public void setCompletedAt(LocalDateTime completedAt) {
	        this.completedAt = completedAt;
	    }

	    public Long getFromYardId() {
	        return fromYardId;
	    }

	    public void setFromYardId(Long fromYardId) {
	        this.fromYardId = fromYardId;
	    }

	    public String getFromLocationName() {
	        return fromLocationName;
	    }

	    public void setFromLocationName(String fromLocationName) {
	        this.fromLocationName = fromLocationName;
	    }

	    public Long getToYardId() {
	        return toYardId;
	    }

	    public void setToYardId(Long toYardId) {
	        this.toYardId = toYardId;
	    }

	    public String getToLocationName() {
	        return toLocationName;
	    }

	    public void setToLocationName(String toLocationName) {
	        this.toLocationName = toLocationName;
	    }

	    public String getDriverToken() {
	        return driverToken;
	    }

	    public void setDriverToken(String driverToken) {
	        this.driverToken = driverToken;
	    }

	    public DispatchSourceType getSourceType() {
	        return sourceType;
	    }

	    public void setSourceType(DispatchSourceType sourceType) {
	        this.sourceType = sourceType;
	    }


	    // ------------------------------------------------------------
	    // Build
	    // ------------------------------------------------------------

	    public DispatchAction build() {
	        return new DispatchAction(this);
	    }
	}	
	

	
=======

	public static class Builder {
		private Long dispatchActionId;
		private Long reservationId;
		private String equipmentNumber;
		private DispatchActionType actionType;
		private DispatchActionStatus status = DispatchActionStatus.PENDING;
		private Address fromAddress;
		private Address toAddress;
		private LocalDateTime scheduledDateTime;
		private Long driverId;
		private String notes;

		public Builder dispatchActionId(Long id) {
			this.dispatchActionId = id;
			return this;
		}

		public Builder reservationId(Long id) {
			this.reservationId = id;
			return this;
		}

		public Builder equipmentNumber(String num) {
			this.equipmentNumber = num;
			return this;
		}

		public Builder actionType(DispatchActionType type) {
			this.actionType = type;
			return this;
		}

		public Builder status(DispatchActionStatus status) {
			this.status = status;
			return this;
		}

		public Builder fromAddress(Address addr) {
			this.fromAddress = addr;
			return this;
		}

		public Builder toAddress(Address addr) {
			this.toAddress = addr;
			return this;
		}

		public Builder scheduledDateTime(LocalDateTime dt) {
			this.scheduledDateTime = dt;
			return this;
		}

		public Builder driverId(Long id) {
			this.driverId = id;
			return this;
		}

		public Builder notes(String notes) {
			this.notes = notes;
			return this;
		}

		/**
		 * @return the dispatchActionId
		 */
		public Long getDispatchActionId() {
			return dispatchActionId;
		}

		/**
		 * @param dispatchActionId the dispatchActionId to set
		 */
		public void setDispatchActionId(Long dispatchActionId) {
			this.dispatchActionId = dispatchActionId;
		}

		/**
		 * @return the reservationId
		 */
		public Long getReservationId() {
			return reservationId;
		}

		/**
		 * @param reservationId the reservationId to set
		 */
		public void setReservationId(Long reservationId) {
			this.reservationId = reservationId;
		}

		/**
		 * @return the equipmentNumber
		 */
		public String getEquipmentNumber() {
			return equipmentNumber;
		}

		/**
		 * @param equipmentNumber the equipmentNumber to set
		 */
		public void setEquipmentNumber(String equipmentNumber) {
			this.equipmentNumber = equipmentNumber;
		}

		/**
		 * @return the actionType
		 */
		public DispatchActionType getActionType() {
			return actionType;
		}

		/**
		 * @param actionType the actionType to set
		 */
		public void setActionType(DispatchActionType actionType) {
			this.actionType = actionType;
		}

		/**
		 * @return the status
		 */
		public DispatchActionStatus getStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(DispatchActionStatus status) {
			this.status = status;
		}

		/**
		 * @return the fromAddress
		 */
		public Address getFromAddress() {
			return fromAddress;
		}

		/**
		 * @param fromAddress the fromAddress to set
		 */
		public void setFromAddress(Address fromAddress) {
			this.fromAddress = fromAddress;
		}

		/**
		 * @return the toAddress
		 */
		public Address getToAddress() {
			return toAddress;
		}

		/**
		 * @param toAddress the toAddress to set
		 */
		public void setToAddress(Address toAddress) {
			this.toAddress = toAddress;
		}

		/**
		 * @return the scheduledDateTime
		 */
		public LocalDateTime getScheduledDateTime() {
			return scheduledDateTime;
		}

		/**
		 * @param scheduledDateTime the scheduledDateTime to set
		 */
		public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
			this.scheduledDateTime = scheduledDateTime;
		}

		/**
		 * @return the driverId
		 */
		public Long getDriverId() {
			return driverId;
		}

		/**
		 * @param driverId the driverId to set
		 */
		public void setDriverId(Long driverId) {
			this.driverId = driverId;
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

		public DispatchAction build() {
			return new DispatchAction(this);
		}
	}

	// Getters and setters omitted for brevity but should be included
>>>>>>> origin/main
}