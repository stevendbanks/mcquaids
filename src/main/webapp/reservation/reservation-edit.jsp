<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">

    <!-- Workflow + Page JS -->
    <script src="/mcquaids/javascript/common/workflow.js"></script>
	<script type="module" src="/mcquaids/javascript/workflows/reservation-delivery-address-workflow.js"></script>
    <script type="module" src="/mcquaids/javascript/pages/reservation-edit.js"></script>

    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>
    <!-- Read caller + reservationId -->
		<c:set var="caller" value="${param.caller}" />
		<c:set var="reservationId" value="${param['reservation.reservationID']}" />

    <!-- Main Save Reservation form -->
    <s:form action="save" method="post" namespace="/reservation" theme="bootstrap">

        <!-- Hidden fields -->
        <s:hidden name="actionType"/>
        <s:hidden id="reservationID" name="reservation.reservationID"/>
        <s:hidden id="reservationStatusCode" name="reservation.reservationStatusCode"/>
        <s:hidden id="customerID" name="reservation.customerID"/>

        <!-- Reservation Header -->
        <div class="card mb-4">
            <div class="card-body">

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="card-title mb-0">
                        <s:property value="pageTitle"/>
                        <span class="badge badge-info ml-2">
                            <s:property value="reservation.reservationStatusText"/>
                        </span>
                    </h5>

                    <!-- Action Dropdown -->
                    <div class="btn-group">
                        <button type="button"
                                class="btn btn-outline-dark dropdown-toggle"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                            Actions
                        </button>

                        <div class="dropdown-menu dropdown-menu-right">
                            <button class="dropdown-item" type="button" onclick="changeReservationStatus()">
                                Change Status
                            </button>
                            <button class="dropdown-item" type="button" onclick="cancelReservation()">
                                Cancel Reservation
                            </button>
                            <button class="dropdown-item" type="button" onclick="duplicateReservation()">
                                Duplicate Reservation
                            </button>
                            <button class="dropdown-item" type="button" onclick="printReservation()">
                                Print
                            </button>
						    <button class="dropdown-item" type="button" onclick="createDispatchPlan()">
						        Create Dispatch Plan
						    </button>
                            
                        </div>
                    </div>
                </div>

                <!-- Reservation Fields -->
                <div class="row">

                    <!-- Customer -->
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="customerID">Customer</label>

                            <div class="input-group">
                                <s:textfield
                                        id="fullName"
                                        name="reservation.customer.fullName"
                                        cssClass="form-control"
                                        disabled="true"
                                        theme="simple"
                                />

                                <div class="input-group-append no-print">
                                    <button type="button"
                                            class="btn btn-outline-secondary"
                                            onclick="navigateToCustomerSearch()">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Required Date -->
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label for="reservationStartDate">Required Date</label>
                            <s:textfield id="reservationStartDate"
                                         type="date"
                                         name="reservation.startDate"
                                         cssClass="form-control"
                                         format="yyyy-MM-dd"/>
                        </div>
                    </div>

                    <!-- Return Date -->
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label for="reservationEndDate">Return Date</label>
                            <s:textfield id="reservationEndDate"
                                         type="date"
                                         name="reservation.endDate"
                                         cssClass="form-control"
                                         format="yyyy-MM-dd"/>
                        </div>
                    </div>

                </div>

                <!-- Notes -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="instructions">Reservation Notes</label>
                            <s:textarea id="instructions"
                                        name="reservation.instructions"
                                        cssClass="form-control auto-expand"
                                        oninput="autoExpand(this)"/>
                        </div>
                    </div>
                </div>
                
<!-- Additional Contact (Optional) -->
<div class="row mt-3">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="additionalPersonName">Additional Person Name</label>
            <s:textfield id="additionalPersonName"
                         name="reservation.additionalPersonName"
                         cssClass="form-control"/>
        </div>
    </div>

    <div class="col-sm-4">
        <div class="form-group">
            <label for="additionalPersonPhone">Additional Person Phone</label>
            <s:textfield id="additionalPersonPhone"
                         name="reservation.additionalPersonPhone"
                         cssClass="form-control"/>
        </div>
    </div>

    <div class="col-sm-4">
        <div class="form-group">
            <label for="additionalPersonEmail">Additional Person Email</label>
            <s:textfield id="additionalPersonEmail"
                         name="reservation.additionalPersonEmail"
                         cssClass="form-control"/>
        </div>
    </div>
</div>                

            </div>
        </div>

		<!-- Delivery Address Panel -->
		<jsp:include page="/reservation/reservation-delivery-address-panel.jsp" />

		<!-- Delivery Address Panel -->
		<jsp:include page="/reservation/reservation-secondary-delivery-address-panel.jsp" />


        <!-- Requested Equipment list -->
        <div class="card mb-4">
            <div class="card-body">

                <h5 class="card-title">Reserved Equipment</h5>

                <table id="equipmentTable" class="table mt-4">
                    <thead>
                    <tr>
                        <th scope="col">Equipment Number</th>
                        <th scope="col">Equipment Type</th>
                        <th scope="col">SubType</th>
                        <th scope="col">Notes</th>

                        <th scope="col" class="text-right">
                            <div class="btn-group">
                                <button type="button"
                                        class="btn btn-sm btn-outline-primary"
                                        onclick="expandAllDetails()">
                                    Expand All
                                </button>
                            </div>
                        </th>

                        <th scope="col" class="text-right">
                            <div class="btn-group">
                                <button type="button"
                                        class="btn btn-sm btn-outline-secondary"
                                        onclick="collapseAllDetails()">
                                    Collapse All
                                </button>
                            </div>
                        </th>
                    </tr>
                    </thead>

                    <tbody id="reservationLineItemsTable">

                    <s:iterator value="reservationLineItemsDTO" var="item">

                        <!-- Main row -->
                        <tr class="line-item"
                            data-id="<s:property value='#item.reservationLineItemID'/>"
                            data-type="<s:property value='#item.equipmentTypeText'/>"
                            data-props='<s:property value="#item.equipmentPropertiesAsJson" escapeHtml="false" />'>

                            <td><s:property value="#item.equipmentNumber"/></td>
                            <td><s:property value="#item.equipmentTypeText"/></td>
                            <td><s:property value="#item.equipmentSubTypeText"/></td>
                            <td><s:property value="#item.lineItemNotes"/></td>

                            <!-- Details button -->
                            <td class="text-right">
                                <button type="button"
                                        class="btn btn-sm btn-secondary toggle-properties">
                                    Details
                                </button>
                            </td>

                            <!-- Actions dropdown -->
                            <td class="text-right">
                                <div class="btn-group">
                                    <button type="button"
                                            class="btn btn-sm btn-outline-dark dropdown-toggle"
                                            data-toggle="dropdown"
                                            aria-haspopup="true"
                                            aria-expanded="false">
                                        Actions
                                    </button>

                                    <div class="dropdown-menu dropdown-menu-right">
                                        <button class="dropdown-item"
                                                type="button"
                                                onclick="removeLineItem('<s:property value="#item.reservationLineItemID"/>')">
                                            Remove
                                        </button>

                                        <button class="dropdown-item"
                                                type="button"
                                                onclick="substituteLineItem('<s:property value="#item.reservationLineItemID"/>')">
                                            Substitute
                                        </button>

                                        <button class="dropdown-item"
                                                type="button"
                                                onclick="markReturned('<s:property value="#item.reservationLineItemID"/>')">
                                            Mark Returned
                                        </button>
                                    </div>
                                </div>
                            </td>

                        </tr>

                        <!-- Hidden expandable row -->
                        <tr id="props-<s:property value='#item.reservationLineItemID'/>"
                            class="properties-panel"
                            style="display:none;">
                            <td colspan="5"></td>
                        </tr>

                    </s:iterator>

                    </tbody>
                </table>

            </div>
        </div>
        
        

        <!-- Save Reservation + Search Equipment buttons -->
        <div class="form-group no-print">
            <div class="d-flex justify-content-between mt-4">

                <s:submit
                        value="Save Reservation"
                        cssClass="btn btn-primary btn-lg"/>

                <button type="button"
                        class="btn btn-outline-primary btn-lg"
                        onclick="navigateToEquipmentSearch()">
                    <i class="fa fa-search"></i> Search Equipment
                </button>

            </div>
        </div>
        
        <!-- Add Equipment Section (original placement) -->
        <jsp:include page="AddEquipmentSection.jsp"/>        

    </s:form>

</div>

