<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Reservation</title>
</head>
<body>

<div class="container">
<script type="module" src="/mcquaids/javascript/pages/reservation-edit.js"></script>
    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>

    <!-- Main Save Reservation form -->
    <s:form action="save" method="post" namespace="/reservation" theme="bootstrap">
        <s:hidden name="actionType" value="EDIT"/>

<div class="card mb-4">
    <div class="card-body">
<div class="d-flex justify-content-between align-items-center mb-3">
<h5 class="card-title mb-0">
    <s:property value="pageTitle"/>
    <span class="badge badge-info ml-2">
        <s:property value="reservation.reservationStatusText"/>
    </span>
</h5>

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
        </div>
    </div>
</div>
        <div class="row">
            <s:hidden id="reservationID" name="reservation.reservationID"
                      cssErrorClass="fieldError" cssClass="form-control" readonly="true"/>
            <s:hidden id="reservationStatusCode" name="reservation.reservationStatusCode"
                      readonly="true"/>                      

            <div class="col-sm-4">
                <div class="form-group">
                    <label for="customerID">Customer</label>
                    <div class="input-group">
                        <s:hidden id="customerID" name="reservation.customerID"
                                  cssErrorClass="fieldError" cssClass="form-control"/>
						<s:textfield 
						    id="fullName"
						    name="reservation.customer.fullName"
						    cssClass="form-control"
						    disabled="true"
						    theme="simple"
						/>                                     
                        <div class="input-group-append no-print">
                            <button type="button" class="btn btn-outline-secondary"
                                    data-toggle="modal" data-target="#ModalCustomerSearch">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-2">
                <div class="form-group">
                    <label for="reservationStartDate">Required Date</label>
                    <s:textfield type="date" name="reservation.startDate"
                                 cssErrorClass="fieldError" cssClass="form-control"
                                 format="yyyy-MM-dd"/>
                </div>
            </div>

            <div class="col-sm-3">
                <div class="form-group">
                    <label for="reservationEndDate">Return Date</label>
                    <s:textfield type="date" name="reservation.endDate"
                                 cssErrorClass="fieldError" cssClass="form-control"
                                 format="yyyy-MM-dd"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label for="instructions">Reservation Notes</label>
                    <s:textarea name="reservation.instructions"
                                cssErrorClass="fieldError" cssClass="form-control"/>
                </div>
            </div>
        </div>

    </div>
</div>  

        <!-- Requested Equipment list -->
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="card-title">Reserved Equipment</h5>

                <div class="mb-3 no-print">
                    <button type="button"
                            class="btn btn-sm btn-outline-primary"
                            onclick="expandAllDetails()">
                        Expand All
                    </button>

                    <button type="button"
                            class="btn btn-sm btn-outline-secondary"
                            onclick="collapseAllDetails()">
                        Collapse All
                    </button>
                </div>

                <table id="equipmentTable" class="table mt-4">
                    <thead>
                    <tr>
                        <th scope="col">Equipment Type</th>
                        <th scope="col">SubType</th>
                        <th scope="col">Notes</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>

                    <tbody id="reservationLineItemsTable">
                    <s:iterator value="reservationLineItemsDTO" var="item">

                        <!-- Main row -->
<tr class="line-item"
    data-id="<s:property value='#item.reservationLineItemID'/>"
    data-type="<s:property value='#item.equipmentTypeText'/>"
    data-props='<s:property value="#item.equipmentPropertiesAsJson" escapeHtml="false" />'>

    <!-- Equipment Type -->
    <td><s:property value="#item.equipmentTypeText"/></td>

    <!-- SubType -->
    <td><s:property value="#item.equipmentSubTypeText"/></td>

    <!-- Notes -->
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

                <!-- Save Reservation -->
                <s:submit
                        value="Save Reservation"
                        cssClass="btn btn-primary btn-lg"/>

                <!-- Search Equipment -->
                <button type="button"
                        class="btn btn-outline-primary btn-lg"
                        onclick="DisplayEquipmentSearch()">
                    <i class="fa fa-search"></i> Search Equipment
                </button>

            </div>
        </div>

    </s:form>
    <!-- End Save Reservation form -->

    <!-- Add Equipment section -->
    <jsp:include page="AddEquipmentSection.jsp"/>

</div>

<script>
    function DisplayEquipmentSearch() {
        var reservationId = document.getElementById("reservationID").value;
        window.location.href =
            '/mcquaids/equipment/index?caller=RESERVE&reservationId=' + reservationId;
    }
</script>

<jsp:include page="../ModalEquipmentSearch.jsp"/>
<jsp:include page="../ModalCustomerSearch.jsp"/>

</body>
</html>