<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<!-- Read caller + reservationId -->
<c:set var="caller" value="${param.caller}" />
<c:set var="reservationId" value="${param['reservation.reservationID']}" />

<!-- Main Save Reservation form -->
<s:form action="save" method="post" namespace="/reservation" theme="bootstrap">
    <s:hidden name="actionType" value="CREATE"/>

<div class="card mb-4">
    <div class="card-body">

<div class="d-flex justify-content-between align-items-center mb-3">
    <h5 class="card-title mb-0">
        <s:property value="pageTitle"/>
        <span class="badge badge-info ml-2">
            <s:property value="reservation.reservationStatusText"/>
        </span>
    </h5>
</div>

<div class="row">

    <s:hidden id="reservationID" name="reservation.reservationID"/>
    <s:hidden id="reservationStatusCode" name="reservation.reservationStatusCode"/>

    <!-- CUSTOMER -->
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
                    <button type="button"
                            class="btn btn-outline-secondary"
                            onclick="DisplayCustomerSearch()">
                        <i class="fa fa-search"></i>
                    </button>
                </div>

            </div>
        </div>
    </div>

    <!-- START DATE -->
    <div class="col-sm-2">
        <div class="form-group">
            <label for="reservationStartDate">Required Date</label>
            <s:textfield id="reservationStartDate" type="date"
                         name="reservation.startDate"
                         cssErrorClass="fieldError" cssClass="form-control"
                         format="yyyy-MM-dd"/>
        </div>
    </div>

    <!-- END DATE -->
    <div class="col-sm-3">
        <div class="form-group">
            <label for="reservationEndDate">Return Date</label>
            <s:textfield id="reservationEndDate" type="date"
                         name="reservation.endDate"
                         cssErrorClass="fieldError" cssClass="form-control"
                         format="yyyy-MM-dd"/>
        </div>
    </div>

</div>

<!-- NOTES -->
<div class="row">
    <div class="col-sm-12">
        <div class="form-group">
            <label for="instructions">Reservation Notes</label>
            <s:textarea id="instructions" name="reservation.instructions"
                        cssErrorClass="fieldError" cssClass="form-control"/>
        </div>
    </div>
</div>

</div>
</div>

<!-- Save Reservation -->
<div class="form-group no-print">
    <div class="d-flex justify-content-between mt-4">
        <s:submit value="Save Reservation" cssClass="btn btn-primary btn-lg"/>
    </div>
</div>

</s:form>
<!-- End Save Reservation form -->

</div>

<script>

    function DisplayEquipmentSearch() {
        const reservationID = document.getElementById("reservationID").value;
        window.location.href =
            '/mcquaids/equipment/index?caller=RESERVE&reservation.reservationID=' + reservationID;
    }

    function DisplayCustomerSearch() {

        const params = new URLSearchParams();

        // Identify the workflow
        params.set("caller", "RESERVE");

        // Include reservationID if editing an existing reservation
        const reservationID = document.getElementById("reservationID").value;
        if (reservationID) {
            params.set("reservation.reservationID", reservationID);
        }

        // Helper to safely read field values
        function safeGet(id) {
            const el = document.getElementById(id);
            return el ? el.value : "";
        }

        // Pass reservation fields using OGNL names
        params.set("reservation.startDate", safeGet("reservationStartDate"));
        params.set("reservation.endDate", safeGet("reservationEndDate"));
        params.set("reservation.instructions", safeGet("instructions"));

        // Customer fields (if present)
        params.set("reservation.customerID", safeGet("customerID"));
        params.set("reservation.customer.fullName", safeGet("fullName"));
        params.set("reservation.reservationStatusCode", safeGet("reservationStatusCode"));

        // Redirect to Customer Search
        window.location.href = '/mcquaids/customer/index?' + params.toString();
    }

</script>

</body>
</html>