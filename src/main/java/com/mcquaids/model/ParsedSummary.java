package com.mcquaids.model;

public class ParsedSummary {

    private final DispatchActionType actionType;
    private final Long driverId;
    private final String driverName;

    public ParsedSummary(DispatchActionType actionType, Long driverId, String driverName) {
        this.actionType = actionType;
        this.driverId = driverId;
        this.driverName = driverName;
    }

	/**
	 * @return the actionType
	 */
	public DispatchActionType getActionType() {
		return actionType;
	}

	/**
	 * @return the driverId
	 */
	public Long getDriverId() {
		return driverId;
	}

	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}


}