package com.mcquaids.actions.reservation.lineItems;

import org.apache.commons.lang3.StringUtils;

public class RemoveEquipmentFromReservationAction extends BaseReservationManagementAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {

	    System.out.println("SDBANKS-> Removing Draft Equipment {" + reservationLineItemID + "} from reservation");

	    reservationService.removeEquipmentFromReservation(reservationLineItemID);

	    if (StringUtils.isNotBlank(reservationService.getErrorMessage())) {
	        addActionError(reservationService.getErrorMessage());
	        this.jsonErrorMessage = reservationService.getErrorMessage();
	        return ERROR;
	    }

	    String msg = "Equipment was successfully removed from the reservation. {"  + reservationLineItemID + "}";
	    addActionMessage(msg);
	    this.jsonSuccessMessage = msg;
	    System.out.println("DEBUG: Action messages = " + getActionMessages());
	    return SUCCESS;
	}

	
	

}