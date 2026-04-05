package com.mcquaids.service;

<<<<<<< HEAD
import java.time.Instant;
import java.time.LocalDateTime;
=======
>>>>>>> origin/main
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

<<<<<<< HEAD
import com.mcquaids.dao.CustomerDAO;
import com.mcquaids.dao.DispatchActionDAO;
import com.mcquaids.dao.ReservationDAO;
import com.mcquaids.dao.ReservationLineItemDAO;
import com.mcquaids.model.Address;
import com.mcquaids.model.Customer;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchActionStatus;
import com.mcquaids.model.DispatchActionType;
import com.mcquaids.model.DispatchCalendarDTO;
import com.mcquaids.model.DispatchSourceType;
import com.mcquaids.model.EquipmentLocationHistory;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.model.Yard;
=======
import com.mcquaids.dao.DispatchActionDAO;
import com.mcquaids.model.Address;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.DispatchActionStatus;
import com.mcquaids.model.DispatchActionType;
import com.mcquaids.model.DispatchSourceType;
import com.mcquaids.model.EquipmentLocationHistory;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationLineItem;
>>>>>>> origin/main

public class DispatchActionService {

    private final DispatchActionDAO dispatchActionDao;
    private final EquipmentLocationHistoryService locationService;
    private final YardService yardService;
<<<<<<< HEAD
	private ReservationDAO reservationDAO;
	private CustomerDAO customerDAO;
	private ReservationLineItemDAO lineItemDAO;
	private EquipmentService equipmentService;
	
	private GoogleCalendarService googleCalendarService;

    public DispatchActionService() { 
=======

    public DispatchActionService() {
>>>>>>> origin/main
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.dispatchActionDao = new DispatchActionDAO(jdbcTemplate);
        this.locationService = new EquipmentLocationHistoryService();
        this.yardService = new YardService();
<<<<<<< HEAD
        this.equipmentService = new EquipmentService();

        this.reservationDAO = new ReservationDAO(jdbcTemplate);
        this.customerDAO = new CustomerDAO();
        this.lineItemDAO = new ReservationLineItemDAO(jdbcTemplate);
        
        this.googleCalendarService = new GoogleCalendarService();

    
    }

	public DispatchAction getActionById(Long id) {
		DispatchAction x =  dispatchActionDao.getByDispatchActionID(id);
		x.setReservationLineItemDTO(lineItemDAO.viewReservationLineItem(x.getReservationLineItemID()));
		return x;
	}	
    
    
    public String pushToCalendar(Long dispatchActionId) throws Exception {

        DispatchAction da = dispatchActionDao.getByDispatchActionID(dispatchActionId);

        Reservation res = reservationDAO.getReservation(da.getReservationID());
        Customer cust = customerDAO.findByCustomerID(res.getCustomerID());
        ReservationLineItemDTO li = lineItemDAO.viewReservationLineItem(da.getReservationLineItemID());

        DispatchCalendarDTO dto = new DispatchCalendarDTO();
        dto.setDispatchActionId(dispatchActionId);
        dto.reservationId = res.getReservationID();
        dto.customerName = cust.getFullName();
        dto.customerEmail = cust.getEmail();
        dto.equipmentType = li.getEquipmentTypeText();
        dto.equipmentSubType = li.getEquipmentSubTypeText();
        dto.equipmentNumber = da.getEquipmentNumber();

        // *** FIXED: yard-aware display ***
        dto.fromAddress = da.getFromDisplay();
        dto.toAddress = da.getToDisplay();

        dto.start = da.getScheduledDateTimeAtZone();
        dto.end = dto.start.plusHours(1);
        dto.notes = da.getNotes();

        return googleCalendarService.pushReservationToCalendar(dto);
    }    

    
    public void removeFromCalendar(Long dispatchActionId) throws Exception {

        // Load the dispatch action
        DispatchAction da = dispatchActionDao.getByDispatchActionID(dispatchActionId);
        if (da == null) {
            throw new Exception("DispatchAction not found for ID: " + dispatchActionId);
        }

        // If no event exists, nothing to delete — but still clear DB linkage
        String eventId = da.getGoogleEventId();
        String calendarId = da.getGoogleCalendarId();

        if (eventId != null && calendarId != null) {
            // Delete event from Google Calendar
            googleCalendarService.deleteEvent(calendarId, eventId);
        }

        // Clear DB linkage
        dispatchActionDao.updateCalendarLinkage(
            dispatchActionId,
            null,   // google_event_id
            null,   // google_calendar_id
            Instant.now()
        );
    }    
    
    

=======
    }

>>>>>>> origin/main
    // ------------------------------------------------------------
    // Generate actions for ALL equipment in a reservation
    // ------------------------------------------------------------
    public List<DispatchAction> generateActionsForReservation(
            Reservation reservation,
<<<<<<< HEAD
            List<ReservationLineItemDTO> lineItems
    ) {
        List<DispatchAction> all = new ArrayList<>();

        for (ReservationLineItemDTO item : lineItems) {
=======
            List<ReservationLineItem> lineItems
    ) {
        List<DispatchAction> all = new ArrayList<>();

        for (ReservationLineItem item : lineItems) {
>>>>>>> origin/main
            all.addAll(generateActionsForLineItem(reservation, item));
        }

        return all;
    }

    public List<DispatchAction> generateActionsForLineItem(
            Reservation reservation,
<<<<<<< HEAD
            ReservationLineItemDTO lineItem
    ) {
        EquipmentContext ctx = buildEquipmentContext(reservation, lineItem);
        
        System.out.println("CTX DEBUG: " + ctx);


        if (ctx.isAtYard()) {
            return handleCaseAtYard(ctx);
        }

        if (ctx.isAtPrimary()) {
            return handleCaseAtPrimary(ctx);
        }

        if (ctx.isAtNonYardCustomer()) {
            return handleCaseAtOtherCustomer(ctx);
        }

        return handleCaseUnknownOrNoHistory(ctx);
    }    
    
    
    private EquipmentContext buildEquipmentContext(
            Reservation reservation,
            ReservationLineItemDTO lineItem
    ) {
        EquipmentContext ctx = new EquipmentContext();

        ctx.reservation = reservation;
        ctx.lineItem = lineItem;
        ctx.equipmentNumber = lineItem.getEquipmentNumber();

        // Preferred yard
        Long preferredYardId = equipmentService.getPreferredYardId(ctx.equipmentNumber);
        ctx.preferredYard = yardService.getYardById(preferredYardId);

        // Current location
        EquipmentLocationHistory loc = locationService.getCurrentLocation(ctx.equipmentNumber);

        if (loc != null && "ON_PREMISE".equalsIgnoreCase(loc.getLocationType())) {
            ctx.currentYard = yardService.getYardById(loc.getYardID());
            ctx.currentAddress = null;
            ctx.atYard = true;
        } else if (loc != null) {
            ctx.currentAddress = loc.toAddress();
        } else {
            // No history – assume at preferred yard
            ctx.currentYard = ctx.preferredYard;
            ctx.currentAddress = null;
            ctx.atYard = true;
        }

        
        
        ctx.hasSecondary = (reservation.getSecondaryDeliveryAddress() != null);
        ctx.hasReturnDate = (reservation.getEndDate() != null);

        ctx.atPrimary = ctx.currentAddress != null
                && ctx.currentAddress.equals(reservation.getDeliveryAddress());

        ctx.atNonYardCustomer = !ctx.atYard && !ctx.atPrimary;
        
        
        

        return ctx;
    }    
    
    // Case 1: Equipment currently at a yard
    private List<DispatchAction> handleCaseAtYard(EquipmentContext ctx) {
        List<DispatchAction> actions = new ArrayList<>();

        // DELIVER: yard -> primary
        actions.add(createAction(
                ctx.reservation.getReservationID(),
                ctx.lineItem.getReservationLineItemID(),
                ctx.equipmentNumber,
                DispatchActionType.DELIVER,
                ctx.currentYard,
                ctx.reservation.getDeliveryAddress(),
                ctx.reservation.getStartDate()
        ));

        // MOVE: primary -> secondary (if any)
        if (ctx.hasSecondary) {
            actions.add(createAction(
                    ctx.reservation.getReservationID(),
                    ctx.lineItem.getReservationLineItemID(),
                    ctx.equipmentNumber,
                    DispatchActionType.MOVE,
                    ctx.reservation.getDeliveryAddress(),
                    ctx.reservation.getSecondaryDeliveryAddress(),
                    ctx.reservation.getSecondaryDeliveryDate()
            ));
        }

        // PICKUP: last customer location -> preferred yard (if return)
        if (ctx.hasReturnDate) {
            Address pickupFrom = ctx.hasSecondary
                    ? ctx.reservation.getSecondaryDeliveryAddress()
                    : ctx.reservation.getDeliveryAddress();

            actions.add(createAction(
                    ctx.reservation.getReservationID(),
                    ctx.lineItem.getReservationLineItemID(),
                    ctx.equipmentNumber,
                    DispatchActionType.PICKUP,
                    pickupFrom,
                    ctx.preferredYard,
                    ctx.reservation.getEndDate()
            ));
=======
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
>>>>>>> origin/main
        }

        return persist(actions);
    }
<<<<<<< HEAD

    // Case 2: Equipment currently at primary delivery address
    private List<DispatchAction> handleCaseAtPrimary(EquipmentContext ctx) {
        List<DispatchAction> actions = new ArrayList<>();

        // If there's a secondary, we need a MOVE
        if (ctx.hasSecondary) {
            actions.add(createAction(
                    ctx.reservation.getReservationID(),
                    ctx.lineItem.getReservationLineItemID(),
                    ctx.equipmentNumber,
                    DispatchActionType.MOVE,
                    ctx.reservation.getDeliveryAddress(),
                    ctx.reservation.getSecondaryDeliveryAddress(),
                    ctx.reservation.getSecondaryDeliveryDate()
            ));
        }

        // If there's a return, PICKUP from last customer location to preferred yard
        if (ctx.hasReturnDate) {
            Address pickupFrom = ctx.hasSecondary
                    ? ctx.reservation.getSecondaryDeliveryAddress()
                    : ctx.reservation.getDeliveryAddress();

            actions.add(createAction(
                    ctx.reservation.getReservationID(),
                    ctx.lineItem.getReservationLineItemID(),
                    ctx.equipmentNumber,
                    DispatchActionType.PICKUP,
                    pickupFrom,
                    ctx.preferredYard,
                    ctx.reservation.getEndDate()
            ));
        }

        return persist(actions);
    }

    // Case 3: Equipment currently at some other customer address
    private List<DispatchAction> handleCaseAtOtherCustomer(EquipmentContext ctx) {
        List<DispatchAction> actions = new ArrayList<>();

        // First, PICKUP from currentAddress to preferred yard
        actions.add(createAction(
                ctx.reservation.getReservationID(),
                ctx.lineItem.getReservationLineItemID(),
                ctx.equipmentNumber,
                DispatchActionType.PICKUP,
                ctx.currentAddress,
                ctx.preferredYard,
                ctx.reservation.getStartDate().minusDays(1) // adjust if you have a better rule
        ));

        // Then, DELIVER from preferred yard to primary
        actions.add(createAction(
                ctx.reservation.getReservationID(),
                ctx.lineItem.getReservationLineItemID(),
                ctx.equipmentNumber,
                DispatchActionType.DELIVER,
                ctx.preferredYard,
                ctx.reservation.getDeliveryAddress(),
                ctx.reservation.getStartDate()
        ));

        // Secondary?
        if (ctx.hasSecondary) {
            actions.add(createAction(
                    ctx.reservation.getReservationID(),
                    ctx.lineItem.getReservationLineItemID(),
                    ctx.equipmentNumber,
                    DispatchActionType.MOVE,
                    ctx.reservation.getDeliveryAddress(),
                    ctx.reservation.getSecondaryDeliveryAddress(),
                    ctx.reservation.getSecondaryDeliveryDate()
            ));
        }

        // Return?
        if (ctx.hasReturnDate) {
            Address pickupFrom = ctx.hasSecondary
                    ? ctx.reservation.getSecondaryDeliveryAddress()
                    : ctx.reservation.getDeliveryAddress();

            actions.add(createAction(
                    ctx.reservation.getReservationID(),
                    ctx.lineItem.getReservationLineItemID(),
                    ctx.equipmentNumber,
                    DispatchActionType.PICKUP,
                    pickupFrom,
                    ctx.preferredYard,
                    ctx.reservation.getEndDate()
            ));
        }

        return persist(actions);
    }

    // Case 4: Unknown / no history – treat as at preferred yard
    private List<DispatchAction> handleCaseUnknownOrNoHistory(EquipmentContext ctx) {
        ctx.currentYard = ctx.preferredYard;
        ctx.currentAddress = ctx.preferredYard.getAddress();
        ctx.atYard = true;
        return handleCaseAtYard(ctx);
    }    
    
    

=======
>>>>>>> origin/main
    // ------------------------------------------------------------
    // Helper: Create a DispatchAction
    // ------------------------------------------------------------
    private DispatchAction createAction(
<<<<<<< HEAD
            Integer reservationID,
            Integer reservationLineItemID,
            int equipmentNumber,
            DispatchActionType type,
            Address from,
            Address to,
            LocalDateTime scheduledDate
    ) {
        DispatchAction action = new DispatchAction();
        action.setReservationID(reservationID);
        action.setReservationLineItemID(reservationLineItemID);
        action.setEquipmentNumber(equipmentNumber);
        action.setActionType(type);
        action.setStatus(DispatchActionStatus.PENDING);

        // Address-based
        action.setFromAddress(from);
        action.setToAddress(to);

        // Clear yard metadata
        action.setFromYardId(null);
        action.setFromLocationName(null);
        action.setToYardId(null);
        action.setToLocationName(null);

        action.setScheduledDateTime(scheduledDate);
=======
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
>>>>>>> origin/main
        action.setSourceType(DispatchSourceType.RESERVATION);

        return action;
    }
<<<<<<< HEAD
    
    
    private DispatchAction createAction(
            Integer reservationID,
            Integer reservationLineItemID,
            int equipmentNumber,
            DispatchActionType type,
            Yard fromYard,
            Address to,
            LocalDateTime scheduledDate
    ) {
        DispatchAction action = createAction(
                reservationID,
                reservationLineItemID,
                equipmentNumber,
                type,
                fromYard.getAddress(),
                to,
                scheduledDate
        );

        action.setFromYardId(fromYard.getYardId());
        action.setFromLocationName(fromYard.getName());

        return action;
    }    
    
    private DispatchAction createAction(
            Integer reservationID,
            Integer reservationLineItemID,
            int equipmentNumber,
            DispatchActionType type,
            Address from,
            Yard toYard,
            LocalDateTime scheduledDate
    ) {
        DispatchAction action = createAction(
                reservationID,
                reservationLineItemID,
                equipmentNumber,
                type,
                from,
                toYard.getAddress(),
                scheduledDate
        );

        action.setToYardId(toYard.getYardId());
        action.setToLocationName(toYard.getName());

        return action;
    }    
    
    private DispatchAction createAction(
            Integer reservationID,
            Integer reservationLineItemID,
            int equipmentNumber,
            DispatchActionType type,
            Yard fromYard,
            Yard toYard,
            LocalDateTime scheduledDate
    ) {
        DispatchAction action = createAction(
                reservationID,
                reservationLineItemID,
                equipmentNumber,
                type,
                fromYard.getAddress(),
                toYard.getAddress(),
                scheduledDate
        );

        action.setFromYardId(fromYard.getYardId());
        action.setFromLocationName(fromYard.getName());
        action.setToYardId(toYard.getYardId());
        action.setToLocationName(toYard.getName());

        return action;
    }    
    
=======
>>>>>>> origin/main

    // ------------------------------------------------------------
    // Helper: Persist all actions
    // ------------------------------------------------------------
    private List<DispatchAction> persist(List<DispatchAction> actions) {
        for (DispatchAction action : actions) {
            dispatchActionDao.insert(action);
        }
        return actions;
    }

<<<<<<< HEAD



	public List<DispatchAction> getActionsByReservationId(Integer reservationID) {
	    return dispatchActionDao.findByReservationId(reservationID);

	}



	public void update(DispatchAction action) {
		  dispatchActionDao.update(action);
		
	}
	
	
	

    // ------------------------------------------------------------
    // Inner context class (implementation detail of this service)
    // ------------------------------------------------------------
    private static class EquipmentContext {
        Reservation reservation;
        ReservationLineItemDTO lineItem;

        int equipmentNumber;

        Yard preferredYard;
        Yard currentYard;          // nullable
        Address currentAddress;    // where the unit is now

        boolean atYard;
        boolean atPrimary;
        boolean atNonYardCustomer;
        boolean hasSecondary;
        boolean hasReturnDate;

        // --------------------------------------------------------
        // Helper methods used by generateActionsForLineItem()
        // --------------------------------------------------------
        boolean isAtYard() {
            return atYard;
        }

        boolean isAtPrimary() {
            return atPrimary;
        }

        boolean isAtNonYardCustomer() {
            return atNonYardCustomer;
        }

        boolean hasSecondaryMove() {
            return hasSecondary;
        }

        boolean isReturning() {
            return hasReturnDate;
        }
        
        @Override
        public String toString() {
            return "EquipmentContext{" +
                    "equipmentNumber=" + equipmentNumber +
                    ", preferredYard=" + (preferredYard != null ? preferredYard.getName() : "null") +
                    ", currentYard=" + (currentYard != null ? currentYard.getName() : "null") +
                    ", currentAddress=" + currentAddress +
                    ", atYard=" + atYard +
                    ", atPrimary=" + atPrimary +
                    ", atNonYardCustomer=" + atNonYardCustomer +
                    ", hasSecondary=" + hasSecondary +
                    ", hasReturnDate=" + hasReturnDate +
                    '}';
        }        
        
    }



=======
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
>>>>>>> origin/main
}