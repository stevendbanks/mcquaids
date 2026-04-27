package com.mcquaids.actions.movement;

import com.mcquaids.model.MovementOrderSwapLink;

public class ViewSwapMovementOrderAction extends BaseMovementAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MovementOrderSwapLink swapLink;

    @Override
    public String execute() {

        movementOrderHeader = movementOrderService.getOrder(movementOrderId);
        movementOrderLines = movementOrderService.getLines(movementOrderId);
        movementOrderEvents = movementOrderService.getEvents(movementOrderId);

        swapLink = movementOrderService.getSwapLink(movementOrderId);

        pageTitle = "Swap Movement Order " + movementOrderId;

        return SUCCESS;
    }

    public MovementOrderSwapLink getSwapLink() {
        return swapLink;
    }
}
