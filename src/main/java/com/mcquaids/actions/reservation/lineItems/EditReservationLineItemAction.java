package com.mcquaids.actions.reservation.lineItems;

public class EditReservationLineItemAction extends BaseReservationManagementAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {

		try {

			reservationLineItemDTO = reservationService.viewReservationLineItem(reservationLineItemID);

			if (null == reservationLineItemDTO) {
				addActionError("Reservation Line Item Not Found");
				return "input";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}
}