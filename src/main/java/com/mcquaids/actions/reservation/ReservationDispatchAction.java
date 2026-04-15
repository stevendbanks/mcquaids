package com.mcquaids.actions.reservation;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchGroup;
import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.service.DispatchActionService;
import com.mcquaids.service.DispatchGroupingService;
import com.opensymphony.xwork2.ActionContext;

public class ReservationDispatchAction extends BaseReservationAction {

    private static final long serialVersionUID = 1L;

    @Override
    public String execute() {

    	try {
        DispatchActionService dispatchActionService = new DispatchActionService();

        // 1. Load reservation
        super.reservation = reservationService.getReservation(reservationID);

        // 2. Load reservation line items (equipment)
        List<ReservationLineItemDTO> lineItems =
                reservationService.getReservationLineItems(reservationID);

        // 3. Load existing dispatch actions (the existing plan)
        List<DispatchAction> existingActions =
                dispatchActionService.getActionsByReservationId(reservationID);

        // 4. Determine which equipment numbers already have actions
        Set<Integer> equipmentInPlan = existingActions.stream()
                .map(DispatchAction::getEquipmentNumber)
                .collect(Collectors.toSet());

         Map<Integer, ReservationLineItemDTO> lineItemsByEquipment =
        	        lineItems.stream().collect(Collectors.toMap(
        	                ReservationLineItemDTO::getEquipmentNumber,
        	                li -> li
        	        ));

       	Set<Integer> equipmentOnReservation = lineItemsByEquipment.keySet();    
           
        Set<Integer> newEquipment = new HashSet<>(equipmentOnReservation);
        
        newEquipment.removeAll(equipmentInPlan);
        
     // 5b. Determine which equipment was removed
        Set<Integer> removedEquipment = new HashSet<>(equipmentInPlan);
        removedEquipment.removeAll(equipmentOnReservation); 
        
     // Mark removed tasks
        for (DispatchAction action : existingActions) {
            if (removedEquipment.contains(action.getEquipmentNumber())) {
                action.setRemovedFromReservation(true);   // or setStatus(CANCELED)
                dispatchActionService.update(action);
            }
        }
        
        

        // 6. Generate full workflows ONLY for new equipment

        	
        	for (Integer eq : newEquipment) {
            ReservationLineItemDTO li = lineItemsByEquipment.get(eq);

            List<DispatchAction> newActions =
                    dispatchActionService.generateActionsForLineItem(reservation, li);

            existingActions.addAll(newActions);
        }
        	
        // 6.5  Attach DTOs to existing actions (new actions already have DTO)
        	for (DispatchAction action : existingActions) {
        	    if (action.getReservationLineItemDTO() == null) {
        	        ReservationLineItemDTO dto = lineItemsByEquipment.get(action.getEquipmentNumber());
        	        action.setReservationLineItemDTO(dto);
        	    }
        	}  	

        // 7. Group all actions (existing + new)
        DispatchGroupingService groupingService = new DispatchGroupingService();
        List<DispatchGroup> groups = groupingService.group(existingActions);

        // 8. Push to UI
        ActionContext.getContext().put("reservation", reservation);
        ActionContext.getContext().put("dispatchGroups", groups);
        
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}

        return "dispatchPlan";
    }
}