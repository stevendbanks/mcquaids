<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Reservation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h1 class="mt-4 mb-4"><s:property value="pageTitle"/></h1>

    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>

    <s:form action="save" method="post" namespace="/reservation" theme="bootstrap">
        <s:hidden name="SaveActionType" />

        <div class="row">
                  <s:hidden id="reservationID" name="reservation.reservationID"
                                 cssErrorClass="fieldError" cssClass="form-control" readonly="true"/>

            <div class="col-sm-3">
                <div class="form-group">
                    <label for="customer">Customer</label>
                    <div class="input-group">
                       <s:hidden id="customerID" name="reservation.customerID"
                                     cssErrorClass="fieldError" cssClass="form-control" />
                        <s:textfield id="name" name="reservation.customer.fullName"
                                     cssErrorClass="fieldError" cssClass="form-control" disabled="true"/>
                        <div class="input-group-append no-print">
                            <button type="button" class="btn btn-outline-secondary"
                                    data-toggle="modal" data-target="#ModalCustomerSearch">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-sm-3">
                <div class="form-group">
                    <label for="reservationStatusCode">Reservation Status</label>
                    <s:select id="reservationStatusCode" name="reservation.reservationStatusCode"
                              list="codeValues.reservationStatuses" listKey="key" listValue="value"
                              headerKey="" headerValue="Select Reservation Status"
                              cssClass="form-control" />
                </div>
            </div>

            <div class="col-sm-3">
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
                                 format="yyyy-MM-dd" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label for="instructions">Reservation Notes</label>
                    <s:textarea name="reservation.notes"
                                cssErrorClass="fieldError" cssClass="form-control" />
                </div>
            </div>
        </div>

<!-- Equipment Specs Section -->
<div class="card mb-4">
    <div class="card-body">
        <h5 class="card-title">Requested Equipment</h5>

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
                    <th scope="col">Quantity</th>
                    <th scope="col">Notes</th>
                    <th scope="col"></th>
                </tr>
            </thead>

            <tbody id="reservationLineItemsTable">

                <s:iterator value="reservationLineItemsDTO" var="item">

                    <!-- Main row -->
					<tr class="line-item"
					     data-id="<s:property value='#item.reservationLineItemID'/>"
					     data-type="<s:property value='#item.equipmentType'/>"
					     data-props='<s:property value="#item.propertiesJson" escapeHtml="false" />'>
     
                        <td><s:property value="#item.equipmentTypeText" /></td>
                        <td><s:property value="#item.equipmentSubTypeText" /></td>
                        <td><s:property value="#item.quantity" /></td>
                        <td><s:property value="#item.notes" /></td>

                        <td>
                            <button type="button"
                                    class="btn btn-sm btn-secondary toggle-properties">
                                Details
                            </button>
                        </td>
                    </tr>

                    <!-- Hidden expandable row for type-specific properties Check reservations-edit.js for function called loadPropertiesPanel() -->
                    <tr id="props-<s:property value='#item.reservationLineItemID'/>"
                        class="properties-panel"
                        style="display:none;">
                        <td colspan="5">
                            <!-- Dynamic content will be injected here -->
                        </td>
                    </tr>

                </s:iterator>

            </tbody>
        </table>
    </div>
</div>


 <jsp:include page="AddEquipmentSection.jsp" />





<div class="form-group no-print">
    <div class="col-sm-offset-2 col-sm-3">
        <s:submit
            value="Save Reservation"
            cssClass="btn btn-primary btn-lg btn-block mt-4" />
    </div>
</div>


    </s:form>
</div>

<script src="/mcquaids/javascript/pages/reservation-edit.js"></script>
<jsp:include page="../ModalEquipmentSearch.jsp" />
<jsp:include page="../ModalCustomerSearch.jsp" />



<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


</body>
</html>