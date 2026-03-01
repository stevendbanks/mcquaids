package com.mcquaids.actions.reservation;

import java.util.List;

import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchGroup;
import com.mcquaids.model.DispatchSourceType;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationLineItem;
import com.mcquaids.service.DispatchActionService;
import com.mcquaids.service.DispatchGroupingService;
import com.opensymphony.xwork2.ActionContext;

public class ReservationDispatchAction extends BaseReservationAction {

    private static final long serialVersionUID = 1L;

    @Override
    public String execute() {

        DispatchActionService dispatchActionService = new DispatchActionService();

        super.reservation = reservationService.getReservation(reservationID);
        
        System.out.println(reservation.getCustomer().getFullName());

        List<ReservationLineItem> lineItems =
                reservationService.getReservedEquipmentByReservationID(reservation.getReservationID());

        List<DispatchAction> actions =
                dispatchActionService.generateActionsForReservation(reservation, lineItems);

        // MVP placeholder
        actions.forEach(a -> a.setSourceType(DispatchSourceType.RESERVATION));

        DispatchGroupingService groupingService = new DispatchGroupingService();
        List<DispatchGroup> groups = groupingService.group(actions);

        ActionContext.getContext().put("reservation", reservation);
        ActionContext.getContext().put("dispatchGroups", groups);

        return "dispatchPlan";
    }
}