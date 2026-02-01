package com.mcquaids.actions.reservation.lineItems;

import com.mcquaids.actions.reservation.BaseReservationAction;

public class GetReservationLineItemsAction extends BaseReservationAction {

	private static final long serialVersionUID = 1L;
	


	public GetReservationLineItemsAction() {
		super();
	}

    @Override
    public String execute() {
        System.out.println("Executing EditReservationAction.execute()");

        try {
        		super.reservationLineItemsDTO = reservationService.getReservationLineItems(reservationID );
        	} catch (Exception ex) {
            addActionError("Unable to Retrieve Reservation Line Items: " + ex.getMessage());
            ex.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }
	
}
