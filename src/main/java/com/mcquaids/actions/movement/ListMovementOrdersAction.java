package com.mcquaids.actions.movement;

import java.util.List;

import com.mcquaids.model.MovementOrderDTO;
import com.mcquaids.service.MovementOrderService;

public class ListMovementOrdersAction extends BaseMovementAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MovementOrderService movementOrderService;

    private List<MovementOrderDTO> movementOrders;

    // Filters
    private String status;
    private String movementType;
    private Integer equipmentNumber;
    private String driver;

    public ListMovementOrdersAction() {
        this.movementOrderService = new MovementOrderService();
    }

    @Override
    public String execute() throws Exception {

        movementOrders = movementOrderService.searchMovementOrders(
                status,
                movementType,
                equipmentNumber,
                driver
        );

        System.out.println("Breakpoint");
        
        return SUCCESS;
    }

    // Getters/setters for JSP binding

    public List<MovementOrderDTO> getMovementOrders() {
        return movementOrders;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
