package com.mcquaids.service;

import java.util.ArrayList;
import java.util.List;

import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchActionType;
import com.mcquaids.model.DispatchGroup;

public class DispatchGroupingService {

    public List<DispatchGroup> group(List<DispatchAction> actions) {

        List<DispatchGroup> groups = new ArrayList<>();

        DispatchGroup pickupDeliver = new DispatchGroup("Pickup & Deliver");
        DispatchGroup move = new DispatchGroup("Move");
        DispatchGroup finalPickup = new DispatchGroup("Final Pickup");
        DispatchGroup futureDelivery = new DispatchGroup("Future Delivery (TBD)");

        for (DispatchAction a : actions) {

            if (a.getActionType() == DispatchActionType.DELIVER) {
                pickupDeliver.add(a);
                continue;
            }

            if (a.getActionType() == DispatchActionType.MOVE) {
                move.add(a);
                continue;
            }

            if (a.getActionType() == DispatchActionType.PICKUP) {
                finalPickup.add(a);
                continue;
            }
        }

        if (!pickupDeliver.isEmpty()) groups.add(pickupDeliver);
        if (!move.isEmpty()) groups.add(move);
        if (!finalPickup.isEmpty()) groups.add(finalPickup);
        if (!futureDelivery.isEmpty()) groups.add(futureDelivery);

        return groups;
    }
}