package com.mcquaids.actions.movement;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcquaids.model.MovementOrderEventLog;
import com.mcquaids.model.MovementOrderHeader;
import com.mcquaids.model.MovementOrderLine;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.AvailabilityService;
import com.mcquaids.service.CustomerService;
import com.mcquaids.service.EquipmentService;
import com.mcquaids.service.MovementOrderService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseMovementAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------
    // Shared lookup values
    // ------------------------------------------------------------
    protected CodeValues codeValues;

    // ------------------------------------------------------------
    // Shared Movement Order fields
    // ------------------------------------------------------------
    protected Long movementOrderId;
    protected MovementOrderHeader movementOrderHeader = new MovementOrderHeader();
    protected List<MovementOrderLine> movementOrderLines;
    protected List<MovementOrderEventLog> movementOrderEvents;

    protected String actionTypeText = "Move";
    protected String pageTitle;
    protected String caller;

    protected Map<String, String> errors = new HashMap<>();

    // ------------------------------------------------------------
    // Shared services
    // ------------------------------------------------------------
    protected MovementOrderService movementOrderService = new MovementOrderService();
    protected EquipmentService equipmentService = new EquipmentService();
    protected AvailabilityService availabilityService = new AvailabilityService();
    protected CustomerService customerService = new CustomerService();

    // ------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------
    public BaseMovementAction() {
        codeValues = new CodeValues();
    }

    // ------------------------------------------------------------
    // Shared helpers
    // ------------------------------------------------------------

    protected Integer stringToInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return Integer.parseInt(value);
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public Long getMovementOrderId() {
        return movementOrderId;
    }

    public void setMovementOrderId(Long movementOrderId) {
        this.movementOrderId = movementOrderId;
    }

    public MovementOrderHeader getMovementOrderHeader() {
        return movementOrderHeader;
    }

    public List<MovementOrderLine> getMovementOrderLines() {
        return movementOrderLines;
    }

    public List<MovementOrderEventLog> getMovementOrderEvents() {
        return movementOrderEvents;
    }

    public CodeValues getCodeValues() {
        return codeValues;
    }

    public void setCodeValues(CodeValues codeValues) {
        this.codeValues = codeValues;
    }
    
    /**
	 * @return the actionTypeText
	 */
	public String getActionTypeText() {
		return actionTypeText;
	}

	/**
	 * @param actionTypeText the actionTypeText to set
	 */
	public void setActionTypeText(String actionTypeText) {
		this.actionTypeText = actionTypeText;
	}

	/**
	 * @param movementOrderHeader the movementOrderHeader to set
	 */
	public void setMovementOrderHeader(MovementOrderHeader movementOrderHeader) {
		this.movementOrderHeader = movementOrderHeader;
	}

	/**
	 * @param movementOrderLines the movementOrderLines to set
	 */
	public void setMovementOrderLines(List<MovementOrderLine> movementOrderLines) {
		this.movementOrderLines = movementOrderLines;
	}

	/**
	 * @param movementOrderEvents the movementOrderEvents to set
	 */
	public void setMovementOrderEvents(List<MovementOrderEventLog> movementOrderEvents) {
		this.movementOrderEvents = movementOrderEvents;
	}

	@Override
    public Collection<String> getActionMessages() {
        return super.getActionMessages();
    }

    @Override
    public Collection<String> getActionErrors() {
        return super.getActionErrors();
    }

    
    
}
