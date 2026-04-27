package com.mcquaids.model;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class DispatchAction {

	private Long dispatchActionId;
	private boolean removedFromReservation;

	private Integer reservationID;
	private Integer reservationLineItemID;

	private Long movementOrderID;
	private Long movementOrderLineID;

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

	public DispatchAction() {
	}

	private DispatchAction(Builder builder) {
		this.dispatchActionId = builder.dispatchActionId;
		this.removedFromReservation = builder.removedFromReservation;

		this.reservationID = builder.reservationID;
		this.reservationLineItemID = builder.reservationLineItemID;

		this.movementOrderID = builder.movementOrderID;
		this.movementOrderLineID = builder.movementOrderLineID;

		this.equipmentNumber = builder.equipmentNumber;

		this.actionType = builder.actionType;
		this.status = builder.status;

		this.googleEventId = builder.googleEventId;
		this.googleCalendarId = builder.googleCalendarId;
		this.lastCalendarSyncAt = builder.lastCalendarSyncAt;

		this.completedAt = builder.completedAt;

		this.fromAddress = builder.fromAddress;
		this.toAddress = builder.toAddress;

		this.fromYardId = builder.fromYardId;
		this.fromLocationName = builder.fromLocationName;

		this.toYardId = builder.toYardId;
		this.toLocationName = builder.toLocationName;

		this.scheduledDateTime = builder.scheduledDateTime;
		this.driverId = builder.driverId;
		this.driverToken = builder.driverToken;

		this.notes = builder.notes;

		this.reservationLineItemDTO = builder.reservationLineItemDTO;
		this.sourceType = builder.sourceType;
	}

	public DispatchAction(DispatchAction other) {
		this.dispatchActionId = other.dispatchActionId;
		this.removedFromReservation = other.removedFromReservation;

		this.reservationID = other.reservationID;
		this.reservationLineItemID = other.reservationLineItemID;

		this.movementOrderID = other.movementOrderID;
		this.movementOrderLineID = other.movementOrderLineID;

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

		this.googleEventId = other.googleEventId;
		this.googleCalendarId = other.googleCalendarId;
		this.lastCalendarSyncAt = other.lastCalendarSyncAt;

		this.reservationLineItemDTO = other.reservationLineItemDTO;
		this.sourceType = other.sourceType;
	}

	public String getUniversalEidUrl() {
		if (googleEventId == null || googleCalendarId == null)
			return null;

		String raw = googleEventId + " " + googleCalendarId;
		String encoded = Base64.getUrlEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));

		return "https://www.google.com/calendar/event?eid=" + encoded;
	}

	public Integer getReservationID() {
		return reservationID;
	}

	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}

	public Integer getReservationLineItemID() {
		return reservationLineItemID;
	}

	public void setReservationLineItemID(Integer reservationLineItemID) {
		this.reservationLineItemID = reservationLineItemID;
	}

	public Long getMovementOrderID() {
		return movementOrderID;
	}

	public void setMovementOrderID(Long movementOrderID) {
		this.movementOrderID = movementOrderID;
	}

	public Long getMovementOrderLineID() {
		return movementOrderLineID;
	}

	public void setMovementOrderLineID(Long movementOrderLineID) {
		this.movementOrderLineID = movementOrderLineID;
	}

	public boolean isRemovedFromReservation() {
		return removedFromReservation;
	}

	public void setRemovedFromReservation(boolean removedFromReservation) {
		this.removedFromReservation = removedFromReservation;
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

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
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

	public LocalDateTime getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}

	public ZonedDateTime getScheduledDateTimeAtZone() {
		if (scheduledDateTime == null)
			return null;
		ZoneId zone = ZoneId.of("America/Halifax");
		return scheduledDateTime.atZone(zone);
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getDriverToken() {
		return driverToken;
	}

	public void setDriverToken(String driverToken) {
		this.driverToken = driverToken;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

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

	public DispatchSourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(DispatchSourceType sourceType) {
		this.sourceType = sourceType;
	}

	public ReservationLineItemDTO getReservationLineItemDTO() {
		return reservationLineItemDTO;
	}

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
		if (fromYardId != null && fromLocationName != null)
			return fromLocationName;
		if (fromAddress != null)
			return fromAddress.toSingleLine();
		return "";
	}

	public String getToDisplay() {
		if (toYardId != null && toLocationName != null)
			return toLocationName;
		if (toAddress != null)
			return toAddress.toSingleLine();
		return "";
	}

	public String getScheduledDateTimeDisplay() {
		if (scheduledDateTime == null)
			return "";
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");
		return scheduledDateTime.format(fmt);
	}

	@Override
	public String toString() {
		return "DispatchAction [dispatchActionId=" + dispatchActionId + ", reservationID=" + reservationID
				+ ", reservationLineItemID=" + reservationLineItemID + ", movementOrderID=" + movementOrderID
				+ ", movementOrderLineID=" + movementOrderLineID + ", equipmentNumber=" + equipmentNumber
				+ ", actionType=" + actionType + ", status=" + status + ", scheduledDateTime=" + scheduledDateTime
				+ ", driverId=" + driverId + ", notes=" + notes + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Long dispatchActionId;
		private boolean removedFromReservation;

		private Integer reservationID;
		private Integer reservationLineItemID;

		private Long movementOrderID;
		private Long movementOrderLineID;

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

		public Builder dispatchActionId(Long id) {
			this.dispatchActionId = id;
			return this;
		}

		public Builder removedFromReservation(boolean removed) {
			this.removedFromReservation = removed;
			return this;
		}

		public Builder reservationID(Integer id) {
			this.reservationID = id;
			return this;
		}

		public Builder reservationLineItemID(Integer id) {
			this.reservationLineItemID = id;
			return this;
		}

		public Builder movementOrderID(Long id) {
			this.movementOrderID = id;
			return this;
		}

		public Builder movementOrderLineID(Long id) {
			this.movementOrderLineID = id;
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

		public Builder reservationLineItemDTO(ReservationLineItemDTO dto) {
			this.reservationLineItemDTO = dto;
			return this;
		}

		public Builder sourceType(DispatchSourceType type) {
			this.sourceType = type;
			return this;
		}

		public DispatchAction build() {
			return new DispatchAction(this);
		}
	}
}
