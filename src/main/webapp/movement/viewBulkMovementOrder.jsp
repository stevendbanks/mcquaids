<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="errorMessage" class="alert" style="display:none;"></div>

<div class="container-fluid mt-3">

    <!-- Header -->
    <div class="card mb-3">
        <div class="card-header bg-primary text-white">
            Bulk Movement Order #${movementOrderHeader.movementOrderId}
        </div>
        <div class="card-body">
            <p><strong>Status:</strong> ${movementOrderHeader.orderStatus}</p>
            <p><strong>Created By:</strong> ${movementOrderHeader.createdBy}</p>
            <p><strong>Created On:</strong> ${movementOrderHeader.createdDate}</p>
        </div>
    </div>

    <!-- Add Equipment -->
    <div class="card mb-3">
        <div class="card-header bg-success text-white">
            Add Equipment to Bulk Order
        </div>
        <div class="card-body">
            <div class="form-inline">
                <input type="text"
                       class="form-control mr-2"
                       placeholder="Equipment Number"
                       data-bulk-equipment-number />

                <button class="btn btn-success"
                        data-bulk-add-button
                        data-order-id="${movementOrderHeader.movementOrderId}">
                    Add
                </button>
            </div>
        </div>
    </div>

    <!-- Bulk Lines -->
    <div class="card mb-3">
        <div class="card-header bg-secondary text-white">
            Equipment in Bulk Order
        </div>
        <div class="card-body p-0">
            <table class="table table-striped table-bordered mb-0">
                <thead class="thead-light">
                    <tr>
                        <th>Line ID</th>
                        <th>Equipment #</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="line" items="${movementOrderLines}">
                        <tr>
                            <td>${line.movementOrderLineId}</td>
                            <td>${line.equipmentNumber}</td>
                            <td>${line.fromLocation}</td>
                            <td>${line.toLocation}</td>
                            <td>${line.lineStatus}</td>
                            <td>
                                <button class="btn btn-danger btn-sm"
                                        data-bulk-remove-button
                                        data-line-id="${line.movementOrderLineId}">
                                    Remove
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Event Log -->
    <div class="card mb-3">
        <div class="card-header bg-info text-white">
            Event Log
        </div>
        <div class="card-body p-0">
            <table class="table table-sm table-striped mb-0">
                <thead class="thead-light">
                    <tr>
                        <th>Date/Time</th>
                        <th>Type</th>
                        <th>Performed By</th>
                        <th>Notes</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="event" items="${movementOrderEvents}">
                        <tr>
                            <td>${event.eventDateTime}</td>
                            <td>${event.eventType}</td>
                            <td>${event.performedBy}</td>
                            <td>${event.notes}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<script type="module" src="/javascript/pages/movement-order.js"></script>
<script type="module" src="/javascript/pages/movement-order-bulk.js"></script>

<script>
    if (window.initMovementOrderBulk) initMovementOrderBulk();
</script>
