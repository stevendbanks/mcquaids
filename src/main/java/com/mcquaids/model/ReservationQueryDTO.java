package com.mcquaids.model;

public class ReservationQueryDTO extends Reservation {

    private String reservationStatusDescription;
    private Customer customer;

    public String getReservationStatusDescription() {
        return reservationStatusDescription;
    }

    public void setReservationStatusDescription(String reservationStatusDescription) {
        this.reservationStatusDescription = reservationStatusDescription;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}