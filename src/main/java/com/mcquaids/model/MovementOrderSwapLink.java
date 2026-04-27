package com.mcquaids.model;

public class MovementOrderSwapLink {

    private long swapLinkId;
    private long movementOrderId;
    private long lineAId;
    private long lineBId;

    public long getSwapLinkId() {
        return swapLinkId;
    }

    public void setSwapLinkId(long swapLinkId) {
        this.swapLinkId = swapLinkId;
    }

    public long getMovementOrderId() {
        return movementOrderId;
    }

    public void setMovementOrderId(long movementOrderId) {
        this.movementOrderId = movementOrderId;
    }

    public long getLineAId() {
        return lineAId;
    }

    public void setLineAId(long lineAId) {
        this.lineAId = lineAId;
    }

    public long getLineBId() {
        return lineBId;
    }

    public void setLineBId(long lineBId) {
        this.lineBId = lineBId;
    }
}
