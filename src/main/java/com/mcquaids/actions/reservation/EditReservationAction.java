package com.mcquaids.actions.reservation;

import com.mcquaids.model.Customer;
import com.opensymphony.xwork2.Action;

public class EditReservationAction extends BaseReservationAction {

    private static final long serialVersionUID = 1L;

    private String leaseID;

    // Explicit flag to indicate return from a selector (customer or equipment)
    private boolean fromSelector;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public EditReservationAction() {
        super();
    }

    public boolean isFromSelector() {
        return fromSelector;
    }

    public void setFromSelector(boolean fromSelector) {
        this.fromSelector = fromSelector;
    }

    @Override
    public String execute() {

        try {
            // Normalize reservationID from posted reservation if needed
            if (reservationID == null && reservation != null) {
                reservationID = reservation.getReservationID();
            }

            // Fix-up for chained SaveReservation
            if (reservationID != null &&
                reservation != null &&
                reservation.getReservationID() == null) {
                reservation.setReservationID(reservationID);
            }

            System.out.println(
                "reservationID=" + reservationID +
                "; reservedEquipmentID=" + reservedEquipmentID +
                "; fromSelector=" + fromSelector
            );

            // Detect posted reservation state (customer selector case)
            boolean hasPostedReservationState =
                    reservation != null &&
                    reservation.getCustomerID() != null;

            // Only preserve form state when coming from selector AND we have posted fields
            boolean preserveFormState = fromSelector && hasPostedReservationState;

            // ALWAYS load reservation from DB unless preserving form state
            if (reservationID != null && !preserveFormState) {
                super.reservation = reservationService.getReservation(reservationID);
            }

            // ALWAYS load line items when reservationID exists
            if (reservationID != null) {
                super.reservationLineItemsDTO =
                        reservationService.getReservationLineItems(reservationID);
            }

            // ALWAYS load reserved equipment if parameter is present
            if (reservedEquipmentID != null) {
                super.reservedEquipment = equipmentService.findEquipment(reservedEquipmentID);
            }

            // Page title logic
            if (reservationID == null) {
        	    actionType = "CREATE";
                pageTitle = "New Reservation";
            } else if (preserveFormState) {
        	    actionType = "EDIT";
            } else {
        	    actionType = "EDIT";
                pageTitle = "Reservation #" + reservationID;
            }

            // Enrich customer if present
            if (reservation != null && reservation.getCustomerID() != null) {
                Customer fullCustomer =
                    customerService.edit(reservation.getCustomerID().toString());
                reservation.setCustomer(fullCustomer);
            }
            
System.out.println(reservation.getCustomer().getFullName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Action.SUCCESS;
    }

    public String getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(String leaseID) {
        this.leaseID = leaseID;
    }

}