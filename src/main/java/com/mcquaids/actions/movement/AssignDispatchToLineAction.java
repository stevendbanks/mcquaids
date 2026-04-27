package com.mcquaids.actions.movement;

public class AssignDispatchToLineAction extends BaseMovementAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long lineId;
	private long dispatchId;

	private boolean success;
	private String message;

	@Override
	public String execute() {

		try {
			String user = getCaller();

			movementOrderService.assignDispatchToLine(lineId, dispatchId, user);

			this.success = true;
			this.message = "Dispatch assigned";

		} catch (Exception ex) {
			this.success = false;
			this.message = "Error assigning dispatch: " + ex.getMessage();
		}

		return SUCCESS;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}

	public void setDispatchId(long dispatchId) {
		this.dispatchId = dispatchId;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
