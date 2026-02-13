package com.mcquaids.model;

import java.util.Date;

public class ReservationViewDTO extends Reservation {

    // Customer table extras
    private String customerNotes;
    private Date customerCreatedDateTime;
    private String customerCreatedUserID;

    // CodeValue description
    private String reservationStatusDescription;

    // --- Getters and Setters ---

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public Date getCustomerCreatedDateTime() {
        return customerCreatedDateTime;
    }

    public void setCustomerCreatedDateTime(Date customerCreatedDateTime) {
        this.customerCreatedDateTime = customerCreatedDateTime;
    }

    public String getCustomerCreatedUserID() {
        return customerCreatedUserID;
    }

    public void setCustomerCreatedUserID(String customerCreatedUserID) {
        this.customerCreatedUserID = customerCreatedUserID;
    }

    public String getReservationStatusDescription() {
        return reservationStatusDescription;
    }

    public void setReservationStatusDescription(String reservationStatusDescription) {
        this.reservationStatusDescription = reservationStatusDescription;
    }
}
