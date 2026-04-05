package com.mcquaids.service;

import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.DispatchActionDAO;
import com.mcquaids.model.Address;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.EquipmentLocationHistory;

public class DriverActionService {

    private final DispatchActionDAO dispatchActionDao;
    private final EquipmentEventService equipmentEventService;

    private String error = null;

    
    public DriverActionService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;

        this.dispatchActionDao = new DispatchActionDAO(jdbcTemplate);
        this.equipmentEventService = new EquipmentEventService();
    }

    public void completeAction(Long dispatchActionId, String notes) {

        DispatchAction action = dispatchActionDao.getByDispatchActionID(dispatchActionId);


        switch (action.getStatus()) {
            case CANCELLED:
                error = "Dispatch Action was previously cancelled";
                break;
            case COMPLETED:
                error = "Dispatch Action was already marked complete";
                break;
            default:
                // no error
        }

        if (error != null) {
            return;
        }


        action.setNotes(notes);
        action.setCompletedAt(LocalDateTime.now());

        dispatchActionDao.markCompleted(action);

        // Movement + event + location history all go through unified pipeline
        updateEquipmentLocation(action, notes);
    }

    private void updateEquipmentLocation(DispatchAction action, String notes) {

        // If no destination, do nothing
        if (action.getToYardId() == null && action.getToAddress() == null) {
            return;
        }

        EquipmentLocationHistory history = new EquipmentLocationHistory();
        history.setNotes("Moved by dispatch action " + action.getDispatchActionId() +
                         (notes != null ? (": " + notes) : ""));

        // Case 1: Move to a yard
        if (action.getToYardId() != null) {
            history.setLocationType("ON_PREMISE");
            history.setYardID(action.getToYardId());
        }
        // Case 2: Move to an address
        else {
            Address a = action.getToAddress();
            history.setLocationType("CUSTOMER_SITE");
            history.setStreet(a.getStreet());
            history.setCity(a.getCity());
            history.setProvince(a.getProvince());
            history.setPostal(a.getPostalCode());
            history.setCountry(a.getCountry());
        }

        // NEW: pass actionId + actionType + createdBy
        equipmentEventService.recordUnifiedMove(
            action.getEquipmentNumber(),
            history,
            "DRIVER_COMPLETED_ACTION",
            notes,
            action.getReservationID(),
            action.getDispatchActionId(),   // actionId
            "DISPATCH",                     // actionType
            "driver"                        // createdBy
        );
    }


    public void validateToken(Long dispatchActionId, String token) {

        DispatchAction action = dispatchActionDao.getByDispatchActionID(dispatchActionId);

        if (action == null) {
            error = "Invalid action ID";
        }

        if (action.getDriverToken() == null || !action.getDriverToken().equals(token)) {
            error  = "Invalid or expired token";
        }

        switch (action.getStatus()) {
        case CANCELLED:
            error = "Dispatch Action was previously cancelled";
            break;
        case COMPLETED:
            error = "Dispatch Action was already marked complete";
            break;
        default:
            // no error
    }
    
    }

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
}