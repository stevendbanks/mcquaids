package com.mcquaids.actions.movement;

import java.util.ArrayList;
import java.util.List;

import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.EquipmentWithLocation;
import com.mcquaids.model.MovementOrderHeader;
import com.mcquaids.model.MovementOrderLine;
import com.mcquaids.model.Yard;
import com.mcquaids.service.DispatchActionService;
import com.mcquaids.service.YardService;
import com.mcquaids.viewmodel.MovementOrderLineViewModel;

public class ViewMovementOrderAction extends BaseMovementAction {

    private static final long serialVersionUID = 1L;

    private List<MovementOrderLineViewModel> movementOrderLineViews;

    @Override
    public String execute() {

        YardService yardService = new YardService();

        movementOrderHeader = movementOrderService.getOrder(movementOrderId);

        Long targetYardId = movementOrderHeader.getTargetYardId();
        if (targetYardId != null) {
            Yard y = yardService.getYardById(targetYardId);
            movementOrderHeader.setTargetStreet(y.getAddress().getStreet());
            movementOrderHeader.setTargetCity(y.getAddress().getCity());
            movementOrderHeader.setTargetProvince(y.getAddress().getProvince());
            movementOrderHeader.setTargetName(y.getName());
        }

        List<MovementOrderLine> lines = movementOrderService.getLines(movementOrderId);

        movementOrderEvents = movementOrderService.getEvents(movementOrderId);

        movementOrderLineViews = new ArrayList<>();

        DispatchActionService dispatchActionService = new DispatchActionService();

        for (MovementOrderLine line : lines) {

            MovementOrderLineViewModel vm = new MovementOrderLineViewModel();

            EquipmentWithLocation eq = equipmentService.getEquipmentWithLocation(line.getEquipmentNumber());

            vm.setEquipmentNumber(eq.getEquipmentNumber());
            vm.setEquipmentType(eq.getEquipmentTypeText());
            vm.setEquipmentSubType(eq.getEquipmentSubTypeText());

            vm.setFromLocationType(eq.getLocationType());
            vm.setFromStreet(eq.getStreet());
            vm.setFromCity(eq.getCity());
            vm.setFromProvince(eq.getProvince());
            vm.setFromCountry(eq.getCountry());

            if ("ON_PREMISE".equals(eq.getLocationType())) {
                Yard y = yardService.getYardById(eq.getYardId());
                vm.setFromYardName(y.getName());
            }

            vm.setTargetLocationType(movementOrderHeader.getTargetLocationType().name());

            if (movementOrderHeader.getTargetLocationType() == MovementOrderHeader.TargetLocationType.ON_PREMISE) {
                Yard dest = yardService.getYardById(movementOrderHeader.getTargetYardId());
                vm.setTargetYardName(dest != null ? dest.getName() : "Unknown Yard");
            }

            // DISPATCH (new architecture: lookup by MovementOrderLineID)
         // DISPATCH (new architecture: lookup by MovementOrderLineID)
            List<DispatchAction> actions =
                    dispatchActionService.getActionsByMovementOrderLineId(line.getMovementOrderLineId());

            if (!actions.isEmpty()) {
                DispatchAction da = actions.get(0); // current UI supports one dispatch per line
                vm.setDispatchId(da.getDispatchActionId());
                vm.setDispatchStatus(da.getStatus().name());
                vm.setScheduledDateTime(da.getScheduledDateTime());
                vm.setCompletedDateTime(da.getCompletedAt());
            }


            vm.setLineStatus(line.getLineStatus().name());

            movementOrderLineViews.add(vm);
        }

        pageTitle = "Movement Order " + movementOrderId;

        return SUCCESS;
    }

    public List<MovementOrderLineViewModel> getMovementOrderLineViews() {
        return movementOrderLineViews;
    }
}
