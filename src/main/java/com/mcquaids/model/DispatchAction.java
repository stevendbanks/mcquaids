package com.mcquaids.model;

import java.time.LocalDateTime;

public class DispatchAction {

	private Long dispatchActionId;
	private Long reservationId;
	private String equipmentNumber;

	private DispatchActionType actionType;

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
	
	
	
	private DispatchActionStatus status;
	
	
	

	private Address fromAddress;
	private Address toAddress;

	private LocalDateTime scheduledDateTime;
	private Long driverId;

	private String notes;

	public DispatchAction() {
	}

	private DispatchAction(Builder builder) {
		this.dispatchActionId = builder.dispatchActionId;
		this.reservationId = builder.reservationId;
		this.equipmentNumber = builder.equipmentNumber;
		this.actionType = builder.actionType;
		this.status = builder.status;
		this.fromAddress = builder.fromAddress;
		this.toAddress = builder.toAddress;
		this.scheduledDateTime = builder.scheduledDateTime;
		this.driverId = builder.driverId;
		this.notes = builder.notes;
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

	public DispatchSourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(DispatchSourceType sourceType) {
		this.sourceType = sourceType;
	}

	public static Builder builder() {
		return new Builder();
	}

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
}