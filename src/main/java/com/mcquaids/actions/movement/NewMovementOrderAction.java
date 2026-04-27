package com.mcquaids.actions.movement;

import java.util.List;

import com.mcquaids.model.MovementOrderHeader;
import com.mcquaids.model.MovementOrderHeader.MovementType;
import com.mcquaids.model.MovementOrderHeader.Priority;
import com.mcquaids.model.Yard;
import com.mcquaids.service.YardService;

public class NewMovementOrderAction extends BaseMovementAction {

    private static final long serialVersionUID = 1L;

    private List<Yard> yards;

    @Override
    public String execute() {

        movementOrderHeader = new MovementOrderHeader();

        movementOrderHeader.setPriority(Priority.NORMAL);
        movementOrderHeader.setMovementType(MovementType.REPOSITION);

        YardService yardService = new YardService();
        this.yards = yardService.getYards();

        this.pageTitle = "Create Movement Order";

        return SUCCESS;
    }


    public List<Yard> getYards() {
        return yards;
    }
}
