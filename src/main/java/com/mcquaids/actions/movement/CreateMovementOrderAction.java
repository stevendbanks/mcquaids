package com.mcquaids.actions.movement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mcquaids.model.EquipmentWithLocation;
import com.mcquaids.model.MovementOrderLine;
import com.mcquaids.model.Yard;
import com.mcquaids.model.enums.MovementOrderLineStatus;
import com.mcquaids.model.enums.MovementOrderStatus;
import com.mcquaids.service.YardService;

public class CreateMovementOrderAction extends BaseMovementAction {

    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------
    // Input fields
    // ------------------------------------------------------------
    private List<Integer> equipmentNumbers;

    // ------------------------------------------------------------
    // Output fields (for JSP)
    // ------------------------------------------------------------
    private List<EquipmentWithLocation> selectedEquipment;
    private List<Yard> yards;

    // ------------------------------------------------------------
    // INPUT (GET)
    // ------------------------------------------------------------
    @Override
    public String input() {

        if (equipmentNumbers == null || equipmentNumbers.isEmpty()) {
            addActionError("No equipment selected.");
            return ERROR;
        }

        YardService yardService = new YardService();
        this.yards = yardService.getYards();

        selectedEquipment = equipmentService.getEquipmentWithLocation(equipmentNumbers);

        this.pageTitle = "Create Movement Order";
        return INPUT;
    }

    // ------------------------------------------------------------
    // EXECUTE (POST)
    // ------------------------------------------------------------
    @Override
    public String execute() {
    	// Ensure movementOrderHeader is on the ValueStack for binding
    	this.getMovementOrderHeader();

        String user = caller;

        if (equipmentNumbers == null || equipmentNumbers.isEmpty()) {
            addActionError("No equipment selected.");
            return ERROR;
        }

        try {
            YardService yardService = new YardService();
            this.yards = yardService.getYards();

            selectedEquipment = equipmentService.getEquipmentWithLocation(equipmentNumbers);

            List<MovementOrderLine> lines = new ArrayList<>();

            for (EquipmentWithLocation eq : selectedEquipment) {

                MovementOrderLine line = new MovementOrderLine();
                line.setEquipmentNumber(eq.getEquipmentNumber());
                line.setLineStatus(MovementOrderLineStatus.NEW);
                line.setCreatedBy(user);
                line.setCreatedDate(LocalDateTime.now());

                lines.add(line);
            }

            // Header metadata (destination already bound via movementOrderHeader.*)
            movementOrderHeader.setRequestedBy(user);
            movementOrderHeader.setRequestedDate(LocalDateTime.now());
            movementOrderHeader.setStatus(MovementOrderStatus.NEW);

            long newId = movementOrderService.createMovementOrder(movementOrderHeader, lines, user);

            this.movementOrderId = newId;

            addActionMessage("Movement Order #" + newId + " created successfully.");

        } catch (Exception ex) {
            ex.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    // ------------------------------------------------------------
    // Getters / Setters
    // ------------------------------------------------------------

    public List<Integer> getEquipmentNumbers() {
        return equipmentNumbers;
    }

    public void setEquipmentNumbers(List<Integer> equipmentNumbers) {
        this.equipmentNumbers = equipmentNumbers;
    }

    public List<EquipmentWithLocation> getSelectedEquipment() {
        return selectedEquipment;
    }

    public List<Yard> getYards() {
        return yards;
    }
}
