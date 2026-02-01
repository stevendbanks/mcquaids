package com.mcquaids.actions.reservation.lineItems;

public class AddEquipmentToReservationAction extends BaseReservationManagementAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String execute() {
		
		System.out.println("equipmentNumber="+equipmentNumber);
		System.out.println("reservationID="+reservationID);
		System.out.println("equipmentQty="+equipmentQty);
		System.out.println("equipmentNotes="+equipmentNotes);
		reservationLineItemDTO = reservationService.addEquipmentToReservation(reservationID, equipmentNumber, equipmentQty, equipmentNotes);
		
		
        return SUCCESS;
    }
}