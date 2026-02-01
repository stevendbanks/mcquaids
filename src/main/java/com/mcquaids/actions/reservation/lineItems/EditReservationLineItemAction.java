package com.mcquaids.actions.reservation.lineItems;

import com.mcquaids.utils.JsonUtils;

public class EditReservationLineItemAction extends BaseReservationManagementAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {

		try {
			
			
			reservationLineItemDTO = reservationService.viewReservationLineItem(reservationLineItemID);
			
			reservationLineItemDTO.setProperties(JsonUtils.resolveProperties(reservationLineItemDTO.getProperties())); 
			
		
		if (null == reservationLineItemDTO) {
			addActionError("Reservation Line Item Not Found");
			return "input";
		}

		} catch (Exception ex ) {
			ex.printStackTrace();
		}
		return "success";
	}
}