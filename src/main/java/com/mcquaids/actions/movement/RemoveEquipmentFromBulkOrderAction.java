package com.mcquaids.actions.movement;

public class RemoveEquipmentFromBulkOrderAction extends BaseMovementAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long lineId;

    private boolean success;
    private String message;

    @Override
    public String execute() {

        try {
            String user = getCaller();

            movementOrderService.removeLineFromOrder(lineId, user);

            this.success = true;
            this.message = "Equipment removed from bulk order";

        } catch (Exception ex) {
            this.success = false;
            this.message = "Error removing equipment: " + ex.getMessage();
        }

        return SUCCESS;
    }

    public void setLineId(long lineId) { this.lineId = lineId; }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
