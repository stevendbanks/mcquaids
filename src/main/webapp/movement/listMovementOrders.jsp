<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div id="errorMessage" class="alert" style="display:none;"></div>

<div class="container-fluid mt-3">

<div class="card mb-3">
    <div class="card-header">
        <strong>Filters</strong>
    </div>
    <div class="card-body">
        <form action="listMovementOrders.do" method="get" class="row g-3">

            <!-- Status -->
            <div class="col-md-3">
                <label for="status" class="form-label">Status</label>
<select name="status" id="status" class="form-select">
    <option value="">All</option>

    <c:forEach var="s" items="${codeValues.movementStatuses}">
        <option value="${s.key}"
            ${empty param.status && s.key == 'NEW' ? 'selected' : ''}
            ${param.status == s.key ? 'selected' : ''}>
            ${s.value}
        </option>
    </c:forEach>
</select>

            </div>

            <!-- Movement Type -->
            <div class="col-md-3">
                <label for="movementType" class="form-label">Movement Type</label>
                <select name="movementType" id="movementType" class="form-select">
                    <option value="">All</option>
                    <c:forEach var="mt" items="${codeValues.moveTypes}">
                        <option value="${mt.key}" ${param.movementType == mt.key ? 'selected' : ''}>
                            ${mt.value}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Equipment Number -->
            <div class="col-md-3">
                <label for="equipmentNumber" class="form-label">Equipment #</label>
                <input type="text" name="equipmentNumber" id="equipmentNumber"
                       value="${fn:escapeXml(param.equipmentNumber)}"
                       class="form-control" placeholder="e.g. 12345">
            </div>

            <!-- Driver -->
            <div class="col-md-3">
                <label for="driver" class="form-label">Requested By</label>
                <input type="text" name="driver" id="driver"
                       value="${fn:escapeXml(param.driver)}"
                       class="form-control" placeholder="Driver name">
            </div>

            <!-- Buttons -->
            <div class="col-12 text-end mt-3">
                <button type="submit" class="btn btn-primary">Apply Filters</button>
                <a href="listMovementOrders.do" class="btn btn-secondary">Clear</a>
            </div>

        </form>
    </div>
</div>



    <div class="card mb-3">
        <div class="card-header bg-primary text-white">
            Movement Orders
        </div>
        <div class="card-body p-0">

            <table class="table table-striped table-bordered mb-0">
                <thead class="thead-light">
                    <tr>
                        <th>Order #</th>
                        <th>Type</th>
                        <th>Equipment</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Status</th>
                        <th>Requested</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="order" items="${movementOrders}">
                        <tr>
                            <td>${order.displayOrderNumber}</td>
                            <td>${order.movementTypeText}</td>
                            <td>${order.equipmentDisplay}</td>
                            <td>${order.fromLocationDisplay}</td>
                            <td>${order.toLocationDisplay}</td>
                            <td>${order.status}</td>
                            <td>${order.requestedAtFormatted}</td>
                            <td>
                                <a href="/mcquaids/movement/viewMovementOrder?movementOrderId=${order.movementOrderId}"
                                   class="btn btn-sm btn-primary">
                                    View
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        </div>
    </div>

</div>

<script type="module" src="/javascript/pages/movement-order.js"></script>
