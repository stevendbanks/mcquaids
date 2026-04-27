package com.mcquaids.actions.movement;

import com.mcquaids.model.enums.MovementOrderLineStatus;

public class UpdateMovementOrderLineStatusAction extends BaseMovementAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long lineId;
	private MovementOrderLineStatus status;

	private boolean success;
	private String message;

	@Override
	public String execute() {

		try {
			String user = getCaller();

			movementOrderService.updateLineStatus(lineId, status, user);

			this.success = true;
			this.message = "Line status updated";

		} catch (Exception ex) {
			this.success = false;
			this.message = "Error updating line status: " + ex.getMessage();
		}

		return SUCCESS;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}

	public void setStatus(MovementOrderLineStatus status) {
		this.status = status;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
