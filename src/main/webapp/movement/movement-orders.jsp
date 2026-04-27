<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="container mt-4">

    <!-- Page Header -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Movement Orders</h3>
        <a href="movement/create.action" class="btn btn-primary">
            Create Movement Order
        </a>
    </div>

    <!-- Filters -->
    <div class="card mb-4">
        <div class="card-body">
            <form class="form-row" method="get" action="movement/list.action">

                <div class="form-group col-md-3">
                    <label>Status</label>
                    <select class="form-control" name="status">
                        <option value="">All</option>
                        <option ${param.status == 'NEW' ? 'selected' : ''}>NEW</option>
                        <option ${param.status == 'ASSIGNED' ? 'selected' : ''}>ASSIGNED</option>
                        <option ${param.status == 'IN_TRANSIT' ? 'selected' : ''}>IN_TRANSIT</option>
                        <option ${param.status == 'COMPLETED' ? 'selected' : ''}>COMPLETED</option>
                        <option ${param.status == 'CANCELLED' ? 'selected' : ''}>CANCELLED</option>
                    </select>
                </div>

                <div class="form-group col-md-3">
                    <label>Movement Type</label>
                    <select class="form-control" name="movementType">
                        <option value="">All</option>
                        <option ${param.movementType == 'REPOSITION' ? 'selected' : ''}>Reposition</option>
                        <option ${param.movementType == 'INSPECTION' ? 'selected' : ''}>Inspection</option>
                        <option ${param.movementType == 'CLEANING' ? 'selected' : ''}>Cleaning</option>
                        <option ${param.movementType == 'MAINTENANCE' ? 'selected' : ''}>Maintenance</option>
                        <option ${param.movementType == 'CUSTOMER_REQUEST' ? 'selected' : ''}>Customer Request</option>
                        <option ${param.movementType == 'SWAP' ? 'selected' : ''}>Swap</option>
                    </select>
                </div>

                <div class="form-group col-md-3">
                    <label>Equipment #</label>
                    <input type="text"
                           class="form-control"
                           name="equipmentNumber"
                           value="${fn:escapeXml(param.equipmentNumber)}"
                           placeholder="12345">
                </div>

                <div class="form-group col-md-3">
                    <label>Driver</label>
                    <input type="text"
                           class="form-control"
                           name="driver"
                           value="${fn:escapeXml(param.driver)}"
                           placeholder="Driver name">
                </div>

                <div class="form-group col-md-12 text-right">
                    <a href="movement/list.action" class="btn btn-secondary">Clear</a>
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>

            </form>
        </div>
    </div>

    <!-- Movement Orders Table -->
    <div class="card">
        <div class="card-body p-0">

            <table class="table table-striped table-sm mb-0">
                <thead class="thead-light">
                    <tr>
                        <th>Order #</th>
                        <th>Equipment</th>
                        <th>Type</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Driver</th>
                        <th>Status</th>
                        <th>Requested</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                <c:forEach var="mo" items="${movementOrders}">
                    <tr>
                        <td>${mo.displayOrderNumber}</td>
                        <td>${mo.equipmentNumber} (${mo.equipmentTypeText})</td>
                        <td>${mo.movementTypeText}</td>
                        <td>${mo.fromLocationDisplay}</td>
                        <td>${mo.toLocationDisplay}</td>
                        <td>${mo.driverName != null ? mo.driverName : 'Unassigned'}</td>

                        <td>
                            <c:choose>
                                <c:when test="${mo.status == 'IN_TRANSIT'}">
                                    <span class="badge badge-warning text-dark">IN_TRANSIT</span>
                                </c:when>
                                <c:when test="${mo.status == 'NEW'}">
                                    <span class="badge badge-secondary">NEW</span>
                                </c:when>
                                <c:when test="${mo.status == 'COMPLETED'}">
                                    <span class="badge badge-success">COMPLETED</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-light">${mo.status}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>${mo.requestedAtFormatted}</td>

                        <td>
                            <a href="movement/view.action?movementOrderId=${mo.movementOrderId}"
                               class="btn btn-sm btn-outline-primary">
                                View
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty movementOrders}">
                    <tr>
                        <td colspan="9" class="text-center text-muted py-3">
                            No movement orders found.
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>

        </div>
    </div>

</div>
