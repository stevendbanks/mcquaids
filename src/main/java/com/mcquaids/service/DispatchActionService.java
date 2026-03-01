package com.mcquaids.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.DispatchActionDAO;
import com.mcquaids.model.Address;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchActionStatus;
import com.mcquaids.model.DispatchActionType;
import com.mcquaids.model.DispatchSourceType;
import com.mcquaids.model.EquipmentLocationHistory;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationLineItem;

public class DispatchActionService {

    private final DispatchActionDAO dispatchActionDao;
    private final EquipmentLocationHistoryService locationService;
    private final YardService yardService;

    public DispatchActionService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.dispatchActionDao = new DispatchActionDAO(jdbcTemplate);
        this.locationService = new EquipmentLocationHistoryService();
        this.yardService = new YardService();
    }

    // ------------------------------------------------------------
    // Generate actions for ALL equipment in a reservation
    // ------------------------------------------------------------
    public List<DispatchAction> generateActionsForReservation(
            Reservation reservation,
            List<ReservationLineItem> lineItems
    ) {
        List<DispatchAction> all = new ArrayList<>();

        for (ReservationLineItem item : lineItems) {
            all.addAll(generateActionsForLineItem(reservation, item));
        }

        return all;
    }

    public List<DispatchAction> generateActionsForLineItem(
            Reservation reservation,
            ReservationLineItem lineItem
    ) {
        List<DispatchAction> actions = new ArrayList<>();

        int equipmentNumber = lineItem.getEquipmentNumber();
        Address deliveryAddress = reservation.getDeliveryAddress();
        Address secondaryAddress = reservation.getSecondaryDeliveryAddress();

        // CORRECT: use reservation end date, not line item
        boolean hasReturnDate = (reservation.getEndDate() != null);

        // 1. Determine initial location
        EquipmentLocationHistory loc = locationService.getCurrentLocation(equipmentNumber);
        Address yard = yardService.getPreferredYardAddress();

        Address currentLocation = (loc != null ? loc.toAddress() : yard);
        String locationType = (loc != null ? loc.getLocationType() : "UNKNOWN");

        boolean hasSecondary = secondaryAddress != null &&
                secondaryAddress.getStreet() != null &&
                !secondaryAddress.getStreet().trim().isEmpty();

        boolean isAtPrimary = currentLocation != null && currentLocation.equals(deliveryAddress);
        boolean isAtYard = "YARD".equalsIgnoreCase(locationType);

        boolean hasKnownLocation = (loc != null && !"UNKNOWN".equalsIgnoreCase(locationType));
        boolean isAtNonYardCustomer = hasKnownLocation && !isAtYard && !isAtPrimary;

        // CASE 1: Equipment is in a yard
        if (isAtYard) {

            DispatchAction deliver = createAction(
                    reservation.getReservationID(),
                    equipmentNumber,
                    DispatchActionType.DELIVER,
                    currentLocation,
                    deliveryAddress
            );
            actions.add(deliver);
            currentLocation = deliveryAddress;

            if (hasSecondary) {
                DispatchAction move = createAction(
                        reservation.getReservationID(),
                        equipmentNumber,
                        DispatchActionType.MOVE,
                        currentLocation,
                        secondaryAddress
                );
                actions.add(move);
                currentLocation = secondaryAddress;

                if (hasReturnDate) {
                    DispatchAction pickup = createAction(
                            reservation.getReservationID(),
                            equipmentNumber,
                            DispatchActionType.PICKUP,
                            currentLocation,
                            yard
                    );
                    actions.add(pickup);
                    currentLocation = yard;
                }

            } else {
                if (hasReturnDate) {
                    DispatchAction pickup = createAction(
                            reservation.getReservationID(),
                            equipmentNumber,
                            DispatchActionType.PICKUP,
                            currentLocation,
                            yard
                    );
                    actions.add(pickup);
                    currentLocation = yard;
                }
            }

            return persist(actions);
        }

        // CASE 2: Equipment is already at the primary delivery address
        if (isAtPrimary) {

            if (hasSecondary) {
                DispatchAction move = createAction(
                        reservation.getReservationID(),
                        equipmentNumber,
                        DispatchActionType.MOVE,
                        currentLocation,
                        secondaryAddress
                );
                actions.add(move);
                currentLocation = secondaryAddress;

                if (hasReturnDate) {
                    DispatchAction pickup = createAction(
                            reservation.getReservationID(),
                            equipmentNumber,
                            DispatchActionType.PICKUP,
                            currentLocation,
                            yard
                    );
                    actions.add(pickup);
                    currentLocation = yard;
                }

            } else {
                if (hasReturnDate) {
                    DispatchAction pickup = createAction(
                            reservation.getReservationID(),
                            equipmentNumber,
                            DispatchActionType.PICKUP,
                            currentLocation,
                            yard
                    );
                    actions.add(pickup);
                    currentLocation = yard;
                }
            }

            return persist(actions);
        }

        // CASE 3: Equipment is at a DIFFERENT customer site
        if (isAtNonYardCustomer) {
            DispatchAction pickupOld = createAction(
                    reservation.getReservationID(),
                    equipmentNumber,
                    DispatchActionType.PICKUP,
                    currentLocation,
                    yard
            );
            actions.add(pickupOld);
            currentLocation = yard;

        } else {
            currentLocation = yard;
        }

        DispatchAction deliver = createAction(
                reservation.getReservationID(),
                equipmentNumber,
                DispatchActionType.DELIVER,
                currentLocation,
                deliveryAddress
        );
        actions.add(deliver);
        currentLocation = deliveryAddress;

        if (hasSecondary) {
            DispatchAction move = createAction(
                    reservation.getReservationID(),
                    equipmentNumber,
                    DispatchActionType.MOVE,
                    currentLocation,
                    secondaryAddress
            );
            actions.add(move);
            currentLocation = secondaryAddress;

            if (hasReturnDate) {
                DispatchAction pickup = createAction(
                        reservation.getReservationID(),
                        equipmentNumber,
                        DispatchActionType.PICKUP,
                        currentLocation,
                        yard
                );
                actions.add(pickup);
                currentLocation = yard;
            }

        } else {
            if (hasReturnDate) {
                DispatchAction pickup = createAction(
                        reservation.getReservationID(),
                        equipmentNumber,
                        DispatchActionType.PICKUP,
                        currentLocation,
                        yard
                );
                actions.add(pickup);
                currentLocation = yard;
            }
        }

        return persist(actions);
    }
    // ------------------------------------------------------------
    // Helper: Create a DispatchAction
    // ------------------------------------------------------------
    private DispatchAction createAction(
            Integer reservationId,
            int equipmentNumber,
            DispatchActionType type,
            Address from,
            Address to
    ) {
        DispatchAction action = new DispatchAction();
        action.setReservationId(reservationId.longValue());
        action.setEquipmentNumber(String.valueOf(equipmentNumber));
        action.setActionType(type);
        action.setStatus(DispatchActionStatus.PENDING);
        action.setFromAddress(from);
        action.setToAddress(to);

        // MVP placeholder — not persisted yet
        action.setSourceType(DispatchSourceType.RESERVATION);

        return action;
    }

    // ------------------------------------------------------------
    // Helper: Persist all actions
    // ------------------------------------------------------------
    private List<DispatchAction> persist(List<DispatchAction> actions) {
        for (DispatchAction action : actions) {
            dispatchActionDao.insert(action);
        }
        return actions;
    }

    // ------------------------------------------------------------
    // Helper: Compare two addresses
    // ------------------------------------------------------------
    private boolean addressesEqual(Address a, Address b) {
        if (a == null || b == null) return false;
        return safe(a.getStreet()).equalsIgnoreCase(safe(b.getStreet())) &&
               safe(a.getCity()).equalsIgnoreCase(safe(b.getCity())) &&
               safe(a.getProvince()).equalsIgnoreCase(safe(b.getProvince())) &&
               safe(a.getPostalCode()).equalsIgnoreCase(safe(b.getPostalCode())) &&
               safe(a.getCountry()).equalsIgnoreCase(safe(b.getCountry()));
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }
}