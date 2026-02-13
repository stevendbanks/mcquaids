package com.mcquaids.actions.reservation.lineItems;

public class AddEquipmentToReservationAction extends BaseReservationManagementAction {

    private static final long serialVersionUID = 1L;

    private String equipmentNotes;

    public String getEquipmentNotes() {
        return equipmentNotes;
    }

    public void setEquipmentNotes(String equipmentNotes) {
        this.equipmentNotes = equipmentNotes;
    }

    @Override
    public String execute() {

        System.out.println("equipmentNumber=" + equipmentNumber);
        System.out.println("reservationID=" + reservationID);
        System.out.println("equipmentNotes=" + equipmentNotes);

        // NEW simplified call — no quantity
        reservationLineItemDTO =
                reservationService.addEquipmentToReservation(
                        reservationID,
                        equipmentNumber,
                        equipmentNotes
                );

        addActionMessage("Equipment added successfully.");
        return SUCCESS;
    }
}