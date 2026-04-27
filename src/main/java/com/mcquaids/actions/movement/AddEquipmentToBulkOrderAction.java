package com.mcquaids.actions.movement;

import com.mcquaids.model.MovementOrderLine;

public class AddEquipmentToBulkOrderAction extends BaseMovementAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MovementOrderLine line;

    private boolean success;
    private String message;

    @Override
    public String execute() {

        try {
            String user = getCaller();

            line.setMovementOrderId(movementOrderId);

            movementOrderService.addLineToOrder(line, user);

            this.success = true;
            this.message = "Equipment added to bulk order";

        } catch (Exception ex) {
            this.success = false;
            this.message = "Error adding equipment: " + ex.getMessage();
        }

        return SUCCESS;
    }

    public void setLine(MovementOrderLine line) { this.line = line; }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
