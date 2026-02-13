package com.mcquaids.actions.reservation.lineItems;

import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.service.ReservationService;
import com.opensymphony.xwork2.ActionSupport;

public class SubstituteEquipmentAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private Integer oldReservationLineItemID;
    private Integer newEquipmentNumber;
    private    Integer reservationID;

    private ReservationService reservationService = new ReservationService();

    // Returned to the UI
    private ReservationLineItemDTO newLineItemDTO;

    @Override
    public String execute() {
       try {
    	   this.reservationID = reservationService.substituteEquipmentInReservation(oldReservationLineItemID, newEquipmentNumber);
	         addActionMessage("Equipment was successfully substituted.");
	         System.out.println("SubstituteEquipmentAction - Equipment was successfully substituted");
	         return SUCCESS;

        } catch (Exception ex) {
        	    ex.printStackTrace();
	            addActionError("Unable to substitute equipment at this time.");
	            return ERROR;
        }
    }

    // Getters for JSON serialization
    public ReservationLineItemDTO getNewLineItemDTO() {
        return newLineItemDTO;
    }

    public void setOldReservationLineItemID(Integer reservationLineItemID) {
        this.oldReservationLineItemID = reservationLineItemID;
    }

    public void setNewEquipmentNumber(Integer newEquipmentNumber) {
        this.newEquipmentNumber = newEquipmentNumber;
    }

	/**
	 * @return the reservationID
	 */
	public Integer getReservationID() {
		return reservationID;
	}

	/**
	 * @param reservationID the reservationID to set
	 */
	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}
}