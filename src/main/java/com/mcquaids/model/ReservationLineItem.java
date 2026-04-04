package com.mcquaids.model;

import java.time.LocalDateTime;

public class ReservationLineItem {

    private Integer reservationLineItemID;
    private Integer reservationID;
    private Integer equipmentNumber;
    private String lineItemNotes;
    private LocalDateTime dateAdded;

    // --- Getters and Setters ---

    public Integer getReservationLineItemID() {
        return reservationLineItemID;
    }

    public void setReservationLineItemID(Integer reservationLineItemID) {
        this.reservationLineItemID = reservationLineItemID;
    }

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public String getReservationIDAsDisplay() {
        return "R" + String.format("%05d", this.reservationID);
    }

    public Integer getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getLineItemNotes() {
        return lineItemNotes;
    }

    public void setLineItemNotes(String lineItemNotes) {
        this.lineItemNotes = lineItemNotes;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "ReservationLineItem{" +
                "reservationLineItemID=" + reservationLineItemID +
                ", reservationID=" + reservationID +
                ", equipmentNumber=" + equipmentNumber +
                ", lineItemNotes='" + lineItemNotes + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }
}