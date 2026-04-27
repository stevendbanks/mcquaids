package com.mcquaids.actions.movement;

import java.util.List;

import com.mcquaids.dto.MovementOrderHeaderDTO;
import com.mcquaids.service.MovementOrderService;

public class ListMovementOrdersAction extends BaseMovementAction {

    private MovementOrderService movementOrderService;

    private List<MovementOrderHeaderDTO> movementOrders;

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

        return SUCCESS;
    }

    // Getters/setters for JSP binding

    public List<MovementOrderHeaderDTO> getMovementOrders() {
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
