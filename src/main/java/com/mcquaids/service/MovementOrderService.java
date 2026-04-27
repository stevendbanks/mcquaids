package com.mcquaids.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.dao.MovementOrderEventLogDAO;
import com.mcquaids.dao.MovementOrderHeaderDAO;
import com.mcquaids.dao.MovementOrderLineDAO;
import com.mcquaids.dao.MovementOrderSwapLinkDAO;
import com.mcquaids.model.DispatchAction;
import com.mcquaids.model.MovementOrderDTO;
import com.mcquaids.model.MovementOrderEventLog;
import com.mcquaids.model.MovementOrderHeader;
import com.mcquaids.model.MovementOrderLine;
import com.mcquaids.model.enums.MovementOrderLineStatus;
import com.mcquaids.model.enums.MovementOrderStatus;

public class MovementOrderService {

    private final MovementOrderHeaderDAO headerDAO;
    private final MovementOrderLineDAO lineDAO;
    private final MovementOrderSwapLinkDAO swapLinkDAO;
    private final MovementOrderEventLogDAO eventLogDAO;

    public MovementOrderService() {

        NamedParameterJdbcTemplate jdbcTemplate = DaoDataSource.namedParameterJdbcTemplate;

        this.headerDAO = new MovementOrderHeaderDAO(jdbcTemplate);
        this.lineDAO = new MovementOrderLineDAO(jdbcTemplate);
        this.swapLinkDAO = new MovementOrderSwapLinkDAO(jdbcTemplate);
        this.eventLogDAO = new MovementOrderEventLogDAO(jdbcTemplate);
    }

    // ------------------------------------------------------------
    // CREATE MOVEMENT ORDER
    // ------------------------------------------------------------

    public long createMovementOrder(MovementOrderHeader header, List<MovementOrderLine> lines, String createdBy) {

        long orderId = headerDAO.insertHeader(header);

        DispatchActionService dispatchService = new DispatchActionService();

        for (MovementOrderLine line : lines) {

            line.setMovementOrderId(orderId);
            long lineId = lineDAO.insertLine(line);

            DispatchAction da = dispatchService.createActionForMovementOrder(header, line);

            writeEvent(orderId, lineId, "LINE_CREATED", createdBy,
                    "Line created for equipment " + line.getEquipmentNumber());

            writeEvent(orderId, lineId, "DISPATCH_CREATED", createdBy,
                    "DispatchAction " + da.getDispatchActionId() + " created");
        }

        writeEvent(orderId, null, "ORDER_CREATED", createdBy, "Movement order created");

        return orderId;
    }

    // ------------------------------------------------------------
    // LIST ALL ORDERS
    // ------------------------------------------------------------

    public List<MovementOrderHeader> listAllOrders() {
        return headerDAO.listAllHeaders();
    }

    // ------------------------------------------------------------
    // ADD LINE TO ORDER
    // ------------------------------------------------------------

    public long addLineToOrder(MovementOrderLine line, String performedBy) {

        long lineId = lineDAO.insertLine(line);

        writeEvent(
                line.getMovementOrderId(),
                lineId,
                "LINE_ADDED",
                performedBy,
                "Equipment " + line.getEquipmentNumber() + " added to bulk order"
        );

        updateHeaderStatusIfNeeded(line.getMovementOrderId(), performedBy);

        return lineId;
    }

    // ------------------------------------------------------------
    // UPDATE HEADER STATUS
    // ------------------------------------------------------------

    public void updateHeaderStatus(Long orderId, MovementOrderStatus status, String performedBy) {

        headerDAO.updateStatus(orderId, status);

        writeEvent(
                orderId,
                null,
                "ORDER_STATUS_" + status.name(),
                performedBy,
                "Order status updated to " + status.name()
        );
    }

    // ------------------------------------------------------------
    // REMOVE LINE
    // ------------------------------------------------------------

    public void removeLineFromOrder(long lineId, String performedBy) {

        MovementOrderLine line = lineDAO.getLineById(lineId);
        if (line == null) return;

        long orderId = line.getMovementOrderId();

        lineDAO.deleteLine(lineId);

        writeEvent(
                orderId,
                lineId,
                "LINE_REMOVED",
                performedBy,
                "Equipment " + line.getEquipmentNumber() + " removed from bulk order"
        );

        updateHeaderStatusIfNeeded(orderId, performedBy);
    }

    // ------------------------------------------------------------
    // UPDATE LINE STATUS
    // ------------------------------------------------------------

    public void updateLineStatus(long lineId, MovementOrderLineStatus status, String performedBy) {

        MovementOrderLine line = lineDAO.getLineById(lineId);
        if (line == null) return;

        lineDAO.updateLineStatus(lineId, status);

        writeEvent(
                line.getMovementOrderId(),
                lineId,
                "LINE_STATUS_" + status.name(),
                performedBy,
                "Line status updated to " + status.name()
        );

        updateHeaderStatusIfNeeded(line.getMovementOrderId(), performedBy);
    }

    // ------------------------------------------------------------
    // ASSIGN DISPATCH (NO LONGER UPDATES LINE TABLE)
    // ------------------------------------------------------------

    public void assignDispatchToLine(long lineId, long dispatchId, String performedBy) {

        MovementOrderLine line = lineDAO.getLineById(lineId);
        if (line == null) return;

        writeEvent(
                line.getMovementOrderId(),
                lineId,
                "DISPATCH_ASSIGNED",
                performedBy,
                "Dispatch " + dispatchId + " assigned to line"
        );
    }

    // ------------------------------------------------------------
    // HEADER STATUS ENGINE
    // ------------------------------------------------------------

    private void updateHeaderStatusIfNeeded(long orderId, String performedBy) {

        List<MovementOrderLine> lines = lineDAO.getLinesByOrderId(orderId);

        boolean allCompleted = lines.stream()
                .allMatch(l -> l.getLineStatus() == MovementOrderLineStatus.COMPLETED);

        boolean anyInProgress = lines.stream()
                .anyMatch(l -> l.getLineStatus() == MovementOrderLineStatus.IN_TRANSIT
                        || l.getLineStatus() == MovementOrderLineStatus.ASSIGNED);

        if (allCompleted) {
            headerDAO.updateStatus(orderId, MovementOrderStatus.COMPLETED);
            writeEvent(orderId, null, "ORDER_COMPLETED", performedBy, "All lines completed");
        }
        else if (anyInProgress) {
            headerDAO.updateStatus(orderId, MovementOrderStatus.IN_PROGRESS);
            writeEvent(orderId, null, "ORDER_IN_PROGRESS", performedBy, "Order now in progress");
        }
    }

    // ------------------------------------------------------------
    // EVENT LOGGING
    // ------------------------------------------------------------

    private void writeEvent(Long orderId, Long lineId, String type, String performedBy, String notes) {

        MovementOrderEventLog log = new MovementOrderEventLog();

        log.setMovementOrderId(orderId);
        log.setMovementOrderLineId(lineId);
        log.setEventType(type);
        log.setEventDateTime(LocalDateTime.now());
        log.setPerformedBy(performedBy);
        log.setNotes(notes);

        eventLogDAO.insertEvent(log);
    }

    // ------------------------------------------------------------
    // QUERY HELPERS
    // ------------------------------------------------------------

    public MovementOrderHeader getOrder(long orderId) {
        return headerDAO.getHeaderById(orderId);
    }

    public List<MovementOrderLine> getLines(long orderId) {
        return lineDAO.getLinesByOrderId(orderId);
    }

    public List<MovementOrderEventLog> getEvents(long orderId) {
        return eventLogDAO.listEventsByOrderId(orderId);
    }

    public List<MovementOrderDTO> searchMovementOrders(
            String status,
            String movementType,
            Integer equipmentNumber,
            String driver
    ) {
        return headerDAO.search(status, movementType, equipmentNumber, driver);
    }

}
