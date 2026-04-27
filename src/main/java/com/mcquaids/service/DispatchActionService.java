package com.mcquaids.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

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
import com.mcquaids.model.EquipmentQueryDTO;
import com.mcquaids.model.MovementOrderHeader;
import com.mcquaids.model.MovementOrderHeader.TargetLocationType;
import com.mcquaids.model.MovementOrderLine;
import com.mcquaids.model.Reservation;
import com.mcquaids.model.ReservationLineItemDTO;
import com.mcquaids.model.Yard;

public class DispatchActionService {

    private final DispatchActionDAO dispatchActionDao;
    private final EquipmentLocationHistoryService locationService;
    private final YardService yardService;
    private ReservationDAO reservationDAO;
    private CustomerDAO customerDAO;
    private ReservationLineItemDAO lineItemDAO;
    private EquipmentService equipmentService;

    private GoogleCalendarService googleCalendarService;

    public DispatchActionService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.dispatchActionDao = new DispatchActionDAO(jdbcTemplate);
        this.locationService = new EquipmentLocationHistoryService();
        this.yardService = new YardService();
        this.equipmentService = new EquipmentService();

        this.reservationDAO = new ReservationDAO(jdbcTemplate);
        this.customerDAO = new CustomerDAO();
        this.lineItemDAO = new ReservationLineItemDAO(jdbcTemplate);

        this.googleCalendarService = new GoogleCalendarService();
    }

    public DispatchAction getActionById(Long id) {
        DispatchAction x = dispatchActionDao.getByDispatchActionID(id);
        if (x != null) {
            x.setReservationLineItemDTO(lineItemDAO.viewReservationLineItem(x.getReservationLineItemID()));
        }
        return x;
    }

//    public String pushToCalendarOLD(Long dispatchActionId) throws Exception {
//
//        DispatchAction da = dispatchActionDao.getByDispatchActionID(dispatchActionId);
//
//        Reservation res = reservationDAO.getReservation(da.getReservationID());
//        Customer cust = customerDAO.findByCustomerID(res.getCustomerID());
//        ReservationLineItemDTO li = lineItemDAO.viewReservationLineItem(da.getReservationLineItemID());
//
//        DispatchCalendarDTO dto = new DispatchCalendarDTO();
//        dto.setDispatchActionId(dispatchActionId);
//        dto.reservationId = res.getReservationID();
//        dto.customerName = cust.getFullName();
//        dto.customerEmail = cust.getEmail();
//        dto.equipmentType = li.getEquipmentTypeText();
//        dto.equipmentSubType = li.getEquipmentSubTypeText();
//        dto.equipmentNumber = da.getEquipmentNumber();
//
//        dto.fromAddress = da.getFromDisplay();
//        dto.toAddress = da.getToDisplay();
//
//        dto.start = da.getScheduledDateTimeAtZone();
//        dto.end = dto.start.plusHours(1);
//        dto.notes = da.getNotes();
//
//        return googleCalendarService.pushReservationToCalendar(dto);
//    }

    public String pushToCalendar(Long dispatchActionId) throws Exception {

        DispatchAction da = dispatchActionDao.getByDispatchActionID(dispatchActionId);
        if (da == null) throw new Exception("DispatchAction not found: " + dispatchActionId);

        DispatchCalendarDTO dto = buildCalendarDTO(da);

        return googleCalendarService.pushDispatchToCalendar(dto);
    }
    
    
    
    
    public void removeFromCalendar(Long dispatchActionId) throws Exception {

        DispatchAction da = dispatchActionDao.getByDispatchActionID(dispatchActionId);
        if (da == null) throw new Exception("DispatchAction not found for ID: " + dispatchActionId);

        String eventId = da.getGoogleEventId();
        String calendarId = da.getGoogleCalendarId();

        if (eventId != null && calendarId != null) {
            googleCalendarService.deleteEvent(calendarId, eventId);
        }

        dispatchActionDao.updateCalendarLinkage(
            dispatchActionId,
            null,
            null,
            Instant.now()
        );
    }

    // ------------------------------------------------------------
    // RESERVATION DISPATCH GENERATION
    // ------------------------------------------------------------

    public List<DispatchAction> generateActionsForReservation(
            Reservation reservation,
            List<ReservationLineItemDTO> lineItems
    ) {
        List<DispatchAction> all = new ArrayList<>();

        for (ReservationLineItemDTO item : lineItems) {
            all.addAll(generateActionsForLineItem(reservation, item));
        }

        return all;
    }

    public List<DispatchAction> createActionsForMovementOrder(
            MovementOrderHeader header,
            MovementOrderLine line
    ) {
        List<DispatchAction> actions = new ArrayList<>();

        // For now, Movement Orders only create a single MOVE action.
        // But this method allows future expansion (multi-stop, swap, etc.)
        DispatchAction action = createActionForMovementOrder(header, line);
        actions.add(action);

        return actions;
    }

    
    public List<DispatchAction> generateActionsForLineItem(
            Reservation reservation,
            ReservationLineItemDTO lineItem
    ) {
        EquipmentContext ctx = buildEquipmentContext(reservation, lineItem);

        if (ctx.isAtYard()) return handleCaseAtYard(ctx);
        if (ctx.isAtPrimary()) return handleCaseAtPrimary(ctx);
        if (ctx.isAtNonYardCustomer()) return handleCaseAtOtherCustomer(ctx);

        return handleCaseUnknownOrNoHistory(ctx);
    }
    
    
 // In DispatchActionService
    
    public List<DispatchAction> getActionsByMovementOrderId(Long movementOrderId) {
        if (movementOrderId == null) {
            return Collections.emptyList();
        }

        return dispatchActionDao.findByMovementOrderId(movementOrderId);
    }
    

    public List<DispatchAction> getActionsByMovementOrderLineId(Long movementOrderLineId) {
        return dispatchActionDao.findByMovementOrderLineId(movementOrderLineId);
    }


    private EquipmentContext buildEquipmentContext(
            Reservation reservation,
            ReservationLineItemDTO lineItem
    ) {
        EquipmentContext ctx = new EquipmentContext();

        ctx.reservation = reservation;
        ctx.lineItem = lineItem;
        ctx.equipmentNumber = lineItem.getEquipmentNumber();

        Long preferredYardId = equipmentService.getPreferredYardId(ctx.equipmentNumber);
        ctx.preferredYard = yardService.getYardById(preferredYardId);

        EquipmentLocationHistory loc = locationService.getCurrentLocation(ctx.equipmentNumber);

        if (loc != null && "ON_PREMISE".equalsIgnoreCase(loc.getLocationType())) {
            ctx.currentYard = yardService.getYardById(loc.getYardID());
            ctx.currentAddress = null;
            ctx.atYard = true;
        } else if (loc != null) {
            ctx.currentAddress = loc.toAddress();
        } else {
            ctx.currentYard = ctx.preferredYard;
            ctx.currentAddress = null;
            ctx.atYard = true;
        }

        ctx.hasSecondary = reservation.getSecondaryDeliveryAddress() != null;
        ctx.hasReturnDate = reservation.getEndDate() != null;

        ctx.atPrimary = ctx.currentAddress != null
                && ctx.currentAddress.equals(reservation.getDeliveryAddress());

        ctx.atNonYardCustomer = !ctx.atYard && !ctx.atPrimary;

        return ctx;
    }

    // ------------------------------------------------------------
    // RESERVATION CASE HANDLERS
    // ------------------------------------------------------------

    private List<DispatchAction> handleCaseAtYard(EquipmentContext ctx) {
        List<DispatchAction> actions = new ArrayList<>();

        actions.add(createAction(
                ctx.reservation.getReservationID(),
                ctx.lineItem.getReservationLineItemID(),
                ctx.equipmentNumber,
                DispatchActionType.DELIVER,
                ctx.currentYard,
                ctx.reservation.getDeliveryAddress(),
                ctx.reservation.getStartDate()
        ));

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

    private List<DispatchAction> handleCaseAtPrimary(EquipmentContext ctx) {
        List<DispatchAction> actions = new ArrayList<>();

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

    private List<DispatchAction> handleCaseAtOtherCustomer(EquipmentContext ctx) {
        List<DispatchAction> actions = new ArrayList<>();

        actions.add(createAction(
                ctx.reservation.getReservationID(),
                ctx.lineItem.getReservationLineItemID(),
                ctx.equipmentNumber,
                DispatchActionType.PICKUP,
                ctx.currentAddress,
                ctx.preferredYard,
                ctx.reservation.getStartDate().minusDays(1)
        ));

        actions.add(createAction(
                ctx.reservation.getReservationID(),
                ctx.lineItem.getReservationLineItemID(),
                ctx.equipmentNumber,
                DispatchActionType.DELIVER,
                ctx.preferredYard,
                ctx.reservation.getDeliveryAddress(),
                ctx.reservation.getStartDate()
        ));

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

    private List<DispatchAction> handleCaseUnknownOrNoHistory(EquipmentContext ctx) {
        ctx.currentYard = ctx.preferredYard;
        ctx.currentAddress = ctx.preferredYard.getAddress();
        ctx.atYard = true;
        return handleCaseAtYard(ctx);
    }

    // ------------------------------------------------------------
    // MOVEMENT ORDER DISPATCH CREATION
    // ------------------------------------------------------------

    public DispatchAction createActionForMovementOrder(
            MovementOrderHeader header,
            MovementOrderLine line
    ) {
        int equipmentNumber = line.getEquipmentNumber();

        EquipmentLocationHistory loc = locationService.getCurrentLocation(equipmentNumber);
        Address fromAddress;
        Yard fromYard = null;

        if (loc != null && "ON_PREMISE".equalsIgnoreCase(loc.getLocationType())) {
            fromYard = yardService.getYardById(loc.getYardID());
            fromAddress = fromYard.getAddress();
        } else if (loc != null) {
            fromAddress = loc.toAddress();
        } else {
            Long preferredYardId = equipmentService.getPreferredYardId(equipmentNumber);
            fromYard = yardService.getYardById(preferredYardId);
            fromAddress = fromYard.getAddress();
        }

        Address toAddress;
        Yard toYard = null;

        if (header.getTargetLocationType() == TargetLocationType.ON_PREMISE) {
            toYard = yardService.getYardById(header.getTargetYardId());
            toAddress = toYard.getAddress();
        } else {
            toAddress = new Address(
                    header.getTargetStreet(),
                    header.getTargetCity(),
                    header.getTargetProvince(),
                    header.getTargetPostal(),
                    header.getTargetCountry()
            );
        }

        DispatchAction action = new DispatchAction();
        action.setEquipmentNumber(equipmentNumber);
        action.setActionType(DispatchActionType.MOVE);
        action.setStatus(DispatchActionStatus.NEW);
        action.setSourceType(DispatchSourceType.MOVEMENT_ORDER);

        action.setMovementOrderID(header.getMovementOrderId());
        action.setMovementOrderLineID(line.getMovementOrderLineId());

        action.setFromAddress(fromAddress);
        action.setToAddress(toAddress);

        if (fromYard != null) {
            action.setFromYardId(fromYard.getYardId());
            action.setFromLocationName(fromYard.getName());
        }

        if (toYard != null) {
            action.setToYardId(toYard.getYardId());
            action.setToLocationName(toYard.getName());
        }

        action.setScheduledDateTime(LocalDateTime.now());

        dispatchActionDao.insert(action);

        return action;
    }

    // ------------------------------------------------------------
    // RESERVATION DISPATCH CREATION HELPERS
    // ------------------------------------------------------------

    private DispatchAction createAction(
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
        action.setStatus(DispatchActionStatus.NEW);

        action.setFromAddress(from);
        action.setToAddress(to);

        action.setFromYardId(null);
        action.setFromLocationName(null);
        action.setToYardId(null);
        action.setToLocationName(null);

        action.setScheduledDateTime(scheduledDate);
        action.setSourceType(DispatchSourceType.RESERVATION);

        return action;
    }

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

    private List<DispatchAction> persist(List<DispatchAction> actions) {
        for (DispatchAction action : actions) {
            dispatchActionDao.insert(action);
        }
        return actions;
    }

    public List<DispatchAction> getActionsByReservationId(Integer reservationID) {
        return dispatchActionDao.findByReservationId(reservationID);
    }

    public void update(DispatchAction action) {
        dispatchActionDao.update(action);
    }
    
    private DispatchCalendarDTO buildCalendarDTO(DispatchAction da) throws Exception {

        DispatchSourceType source = da.getSourceType();

        switch (source) {

            case RESERVATION:
                return buildReservationCalendarDTO(da);

            case MOVEMENT_ORDER:
                return buildMovementOrderCalendarDTO(da);

            default:
                throw new Exception("Unsupported dispatch source type: " + source);
        }
    }
    
    private DispatchCalendarDTO buildReservationCalendarDTO(DispatchAction da) throws Exception {

        Reservation res = reservationDAO.getReservation(da.getReservationID());
        Customer cust = customerDAO.findByCustomerID(res.getCustomerID());
        ReservationLineItemDTO li = lineItemDAO.viewReservationLineItem(da.getReservationLineItemID());

        DispatchCalendarDTO dto = new DispatchCalendarDTO();
        dto.setDispatchActionId(da.getDispatchActionId());
        dto.setSourceType(DispatchSourceType.RESERVATION);

        dto.setReservationId(res.getReservationID());
        dto.setCustomerName(cust.getFullName());
        dto.setCustomerEmail(cust.getEmail());
        dto.setEquipmentType(li.getEquipmentTypeText());
        dto.setEquipmentSubType(li.getEquipmentSubTypeText());
        dto.setEquipmentNumber(da.getEquipmentNumber());

        dto.setFromAddress(da.getFromDisplay());
        dto.setToAddress(da.getToDisplay());

        dto.setStart(da.getScheduledDateTimeAtZone());
        dto.setEnd(dto.getStart().plusHours(1));
        dto.setNotes(da.getNotes());

        // Unified event title + description
        dto.setEventTitle("Reservation #" + res.getReservationID()
                + " – " + da.getActionType().name()
                + " Equipment " + da.getEquipmentNumber());

        dto.setEventDescription(
                "Customer: " + cust.getFullName() + "\n" +
                "From: " + dto.getFromAddress() + "\n" +
                "To: " + dto.getToAddress() + "\n" +
                "Notes: " + (dto.getNotes() == null ? "" : dto.getNotes())
        );

        return dto;
    }

    private DispatchCalendarDTO buildMovementOrderCalendarDTO(DispatchAction da) throws Exception {

        DispatchCalendarDTO dto = new DispatchCalendarDTO();
        dto.setDispatchActionId(da.getDispatchActionId());
        dto.setSourceType(DispatchSourceType.MOVEMENT_ORDER);

        // ⭐ Load full equipment metadata
        EquipmentQueryDTO eq = equipmentService.findEquipment(da.getEquipmentNumber());

        dto.setEquipmentNumber(da.getEquipmentNumber());
        dto.setEquipmentType(eq != null ? eq.getEquipmentTypeText() : "Equipment");
        dto.setEquipmentSubType(eq != null ? eq.getEquipmentSubTypeText() : "");

        dto.setCustomerName("Movement Order");
        dto.setCustomerEmail(null);

        dto.setFromAddress(da.getFromDisplay());
        dto.setToAddress(da.getToDisplay());

        dto.setStart(da.getScheduledDateTimeAtZone());
        dto.setEnd(dto.getStart().plusHours(1));
        dto.setNotes(da.getNotes());

        // ⭐ Unified event title
        dto.setEventTitle(
            "Movement Order – " + da.getActionType().name() +
            " Equipment " + da.getEquipmentNumber()
        );

        // ⭐ Unified event description
        dto.setEventDescription(
            "Equipment Type: " + dto.getEquipmentType() + "\n" +
            "Equipment Subtype: " + dto.getEquipmentSubType() + "\n\n" +
            "From: " + dto.getFromAddress() + "\n" +
            "To: " + dto.getToAddress() + "\n" +
            "Notes: " + (dto.getNotes() == null ? "" : dto.getNotes())
        );

        return dto;
    }

        
    

    // ------------------------------------------------------------
    // Inner context class
    // ------------------------------------------------------------

    private static class EquipmentContext {
        Reservation reservation;
        ReservationLineItemDTO lineItem;

        int equipmentNumber;

        Yard preferredYard;
        Yard currentYard;
        Address currentAddress;

        boolean atYard;
        boolean atPrimary;
        boolean atNonYardCustomer;
        boolean hasSecondary;
        boolean hasReturnDate;

        boolean isAtYard() { return atYard; }
        boolean isAtPrimary() { return atPrimary; }
        boolean isAtNonYardCustomer() { return atNonYardCustomer; }
        

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
}
