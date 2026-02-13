package com.mcquaids.model;

import java.time.LocalDate;

public class ReservationLineItem {

    private Integer reservationLineItemID;
    private Integer reservationID;
    private int equipmentNumber;
    private String lineItemNotes;
    private LocalDate dateAdded;

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

    public int getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(int equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getLineItemNotes() {
        return lineItemNotes;
    }

    public void setLineItemNotes(String lineItemNotes) {
        this.lineItemNotes = lineItemNotes;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
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