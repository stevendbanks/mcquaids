package com.mcquaids.actions.movement;

public class CreateSwapLinkAction extends BaseMovementAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long lineAId;
    private long lineBId;

    private boolean success;
    private String message;

    @Override
    public String execute() {

        try {
            String user = getCaller();

            movementOrderService.createSwapLink(movementOrderId, lineAId, lineBId, user);

            this.success = true;
            this.message = "Swap link created";

        } catch (Exception ex) {
            this.success = false;
            this.message = "Error creating swap link: " + ex.getMessage();
        }

        return SUCCESS;
    }

    public void setLineAId(long lineAId) { this.lineAId = lineAId; }
    public void setLineBId(long lineBId) { this.lineBId = lineBId; }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
