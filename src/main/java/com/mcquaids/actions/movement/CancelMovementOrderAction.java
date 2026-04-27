package com.mcquaids.actions.movement;

import com.mcquaids.model.enums.MovementOrderStatus;

public class CancelMovementOrderAction extends BaseMovementAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success;
    private String message;

    @Override
    public String execute() {

        try {
            String user = getCaller();
 
            movementOrderService.updateHeaderStatus(movementOrderId, MovementOrderStatus.CANCELLED, user); 

            this.success = true;
            this.message = "Movement order cancelled"; 

        } catch (Exception ex) {
            this.success = false;
            this.message = "Error cancelling movement order: " + ex.getMessage();
        }

        return SUCCESS;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
