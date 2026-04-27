package com.mcquaids.actions.movement;

public class ViewBulkMovementOrderAction extends BaseMovementAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String execute() {

        movementOrderHeader = movementOrderService.getOrder(movementOrderId);
        movementOrderLines = movementOrderService.getLines(movementOrderId);
        movementOrderEvents = movementOrderService.getEvents(movementOrderId);

        pageTitle = "Bulk Movement Order " + movementOrderId;

        return SUCCESS;
    }
}
