package com.mcquaids.model;

import java.time.LocalDateTime;
import com.mcquaids.model.enums.MovementOrderLineStatus;

public class MovementOrderLine {

    // -------------------------
    // FIELDS
    // -------------------------

    private long movementOrderLineId;
    private long movementOrderId;

    private Integer equipmentNumber;

    private MovementOrderLineStatus lineStatus;

    private String createdBy;
    private LocalDateTime createdDate;

    // -------------------------
    // GETTERS / SETTERS
    // -------------------------

    public long getMovementOrderLineId() {
        return movementOrderLineId;
    }

    public void setMovementOrderLineId(long movementOrderLineId) {
        this.movementOrderLineId = movementOrderLineId;
    }

    public long getMovementOrderId() {
        return movementOrderId;
    }

    public void setMovementOrderId(long movementOrderId) {
        this.movementOrderId = movementOrderId;
    }

    public Integer getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public MovementOrderLineStatus getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(MovementOrderLineStatus lineStatus) {
        this.lineStatus = lineStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
