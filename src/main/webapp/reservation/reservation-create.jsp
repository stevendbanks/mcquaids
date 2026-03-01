<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

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
    <s:hidden name="actionType"/>  <%--  will be CREATE or EDIT --%>

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
