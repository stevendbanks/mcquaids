package com.mcquaids.actions.movement;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchGroup;
import com.mcquaids.model.MovementOrderHeader;
import com.mcquaids.model.MovementOrderLine;
import com.mcquaids.service.DispatchActionService;
import com.mcquaids.service.DispatchGroupingService;
import com.mcquaids.service.MovementOrderService;
import com.opensymphony.xwork2.ActionContext;

public class MovementOrderDispatchAction {

    private Long movementOrderId;

    private final MovementOrderService movementOrderService = new MovementOrderService();
    private final DispatchActionService dispatchActionService = new DispatchActionService();

    public void setMovementOrderId(Long movementOrderId) {
        this.movementOrderId = movementOrderId;
    }

    public String execute() {

        try {
            // 1. Load Movement Order header (REAL method name)
            MovementOrderHeader header =
                    movementOrderService.getOrder(movementOrderId);

            // 2. Load Movement Order lines (REAL method name)
            List<MovementOrderLine> lines =
                    movementOrderService.getLines(movementOrderId);

            // 3. Load existing dispatch actions for this Movement Order
            List<DispatchAction> existingActions =
                    dispatchActionService.getActionsByMovementOrderId(movementOrderId);

            // 4. Determine which equipment numbers already have actions
            Set<Integer> equipmentInPlan = existingActions.stream()
                    .map(DispatchAction::getEquipmentNumber)
                    .collect(Collectors.toSet());

            Map<Integer, MovementOrderLine> linesByEquipment =
                    lines.stream().collect(Collectors.toMap(
                            MovementOrderLine::getEquipmentNumber,
                            li -> li
                    ));

            Set<Integer> equipmentOnOrder = linesByEquipment.keySet();

            Set<Integer> newEquipment = new HashSet<>(equipmentOnOrder);
            newEquipment.removeAll(equipmentInPlan);

            // 5. Determine which equipment was removed
            Set<Integer> removedEquipment = new HashSet<>(equipmentInPlan);
            removedEquipment.removeAll(equipmentOnOrder);

            // Mark removed tasks
            for (DispatchAction action : existingActions) {
                if (removedEquipment.contains(action.getEquipmentNumber())) {
                    action.setRemovedFromReservation(true); // JSP uses this flag generically
                    dispatchActionService.update(action);
                }
            }

            // 6. Generate dispatch actions for new equipment
            for (Integer eq : newEquipment) {
                MovementOrderLine line = linesByEquipment.get(eq);

                // Movement Orders currently create ONE action per line
                DispatchAction newAction =
                        dispatchActionService.createActionForMovementOrder(header, line);

                existingActions.add(newAction);
            }

//            // 6.5 Attach MovementOrderLine DTOs to existing actions
//            for (DispatchAction action : existingActions) {
//                if (action.getMovementOrderLine() == null) {
//                    MovementOrderLine dto = linesByEquipment.get(action.getEquipmentNumber());
//                    action.setMovementOrderLine(dto);
//                }
//            }

            // 7. Group all actions
            DispatchGroupingService groupingService = new DispatchGroupingService();
            List<DispatchGroup> groups = groupingService.group(existingActions);

            // 8. Push to UI – unified JSP contract
            ActionContext ctx = ActionContext.getContext();

            ctx.put("sourceType", "MOVEMENT");
            ctx.put("sourceId", movementOrderId);

            ctx.put("dispatchPlanStatusText", header.getStatus());
            
            System.out.println("SDABNKs- FIX This.  the header needs a CreateDateTime field like the Reservation");
//            ctx.put("createdDateTime", header.getCreatedDateTime());
            ctx.put("createdDateTime", new Date());
            ctx.put("lastUpdatedDateTime", LocalDateTime.now());

            ctx.put("dispatchGroups", groups);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "dispatchPlan";
    }
}
