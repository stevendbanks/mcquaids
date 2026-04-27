<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container mt-4">

    <!-- Page Header -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>
            Movement Order #<s:property value="movementOrderHeader.movementOrderID"/>
        </h3>

        <!-- Actions Dropdown -->
        <div class="btn-group">
            <button type="button"
                    class="btn btn-outline-dark dropdown-toggle"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false">
                Actions
            </button>

            <div class="dropdown-menu dropdown-menu-right">

                <a class="dropdown-item"
                   href="/movement/editMovementOrder.action?movementOrderId=<s:property value='movementOrderHeader.movementOrderID'/>">
                    Edit Movement Order
                </a>

                <button class="dropdown-item"
                        type="button"
                        onclick="cancelMovementOrder()">
                    Cancel Movement Order
                </button>

                <button class="dropdown-item"
                        type="button"
                        onclick="duplicateMovementOrder()">
                    Duplicate Movement Order
                </button>

                <button class="dropdown-item"
                        type="button"
                        onclick="openDispatchPlan(<s:property value='movementOrderHeader.movementOrderID'/>)">
                    Dispatch Plan
                </button>

            </div>
        </div>
    </div>

    <!-- Status + Priority -->
    <div class="alert alert-info">
        <strong>Status:</strong>
        <s:property value="movementOrderHeader.status"/>

        <span class="ms-3 ml-4">
            <strong>Priority:</strong>
            <s:property value="movementOrderHeader.priority"/> 
        </span>
    </div>

    <!-- Movement Details -->
    <div class="card mb-4">
        <div class="card-body">

            <h5 class="card-title">Movement Details</h5>

            <div class="row">

                <div class="col-md-4">
                    <strong>Requested Date:</strong><br/>
                    <s:date name="movementOrderHeader.requestedDate" format="MMM dd, yyyy h:mm a"/>
                    
                </div>

                <div class="col-md-4">
                    <strong>Movement Type:</strong><br/>
                    <s:property value="movementOrderHeader.movementType"/>
                </div>

                <div class="col-md-4">
                    <strong>Reason:</strong><br/>
                    <s:property value="movementOrderHeader.reasonCode"/>
                </div>

            </div>

            <div class="row mt-3">
                <div class="col-md-12">
                    <strong>Notes:</strong><br/>
                    <s:property value="movementOrderHeader.notes"/>
                </div>
            </div>

        </div>
    </div>

    <!-- Equipment Lines -->
    <div class="card mb-4">
        <div class="card-body">

            <h5 class="card-title">Equipment Lines</h5>

            <c:forEach var="vm" items="${movementOrderLineViews}">
                <div class="card mb-3 shadow-sm">
                    <div class="card-body">

                        <!-- Equipment Header -->
                        <div class="d-flex justify-content-between align-items-center mb-2">
                            <h5 class="mb-0">
                                Equipment #${vm.equipmentNumber}
                            </h5>

                            <span class="badge bg-secondary">
                                Line Status: ${vm.lineStatus}
                            </span>
                        </div>

                        <!-- Equipment Details -->
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <strong>Type:</strong><br/>
                                ${vm.equipmentType} / ${vm.equipmentSubType}
                            </div>

                            <div class="col-md-4">
                                <strong>Origin:</strong><br/>
                                ${vm.fromLocationType}  - ${vm.fromCity}, ${vm.fromProvince}
                            </div>

                            <div class="col-md-4">
                                <strong>Destination:</strong><br/>
                                ${vm.targetLocationType}
                                <c:if test="${vm.targetYardName != null}">
                                   -  ${vm.targetYardName}
                                </c:if>
                            </div>
                        </div>

                    </div>
                </div>
            </c:forEach>

        </div>
    </div>

</div>

<script>
    function openDispatchPlan(movementOrderId) {
        window.location.href = "/mcquaids/movement/createDispatchPlan?movementOrderId=" + movementOrderId;
    }
</script>
