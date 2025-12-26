<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Reservation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-4 mb-4">Create New Reservation</h1>

    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>

    <s:form action="save" method="post" namespace="/reservation" theme="bootstrap">
        <s:hidden name="SaveActionType" />

        <div class="row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="reservationID">Reservation ID</label>
                    <s:textfield id="reservationID" name="reservation.reservationID"
                                 cssErrorClass="fieldError" cssClass="form-control" />
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label for="customerID">Customer ID</label>
                    <div class="input-group">
                        <s:textfield id="customerID" name="reservation.customerID"
                                     cssErrorClass="fieldError" cssClass="form-control" />
                        <div class="input-group-append">
                            <button type="button" class="btn btn-outline-secondary"
                                    data-toggle="modal" data-target="#ModalCustomerSearch">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label for="reservationStatusCode">Reservation Status</label>
                    <s:select id="reservationStatusCode" name="reservation.reservationStatusCode"
                              list="codeValues.reservationStatuses" listKey="key" listValue="value"
                              headerKey="" headerValue="Select Reservation Status"
                              cssClass="form-control" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="reservationStartDate">Reservation Start Date</label>
                    <s:textfield type="date" name="reservation.reservationStartDate"
                                 cssErrorClass="fieldError" cssClass="form-control"
                                 format="yyyy-MM-dd"/>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label for="reservationEndDate">Reservation End Date (Expected)</label>
                    <s:textfield type="date" name="reservation.reservationEndDate"
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
                                cssErrorClass="fieldError" cssClass="form-control" />
                </div>
            </div>
        </div>

        <!-- Equipment Specs Section -->
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="card-title">Requested Equipment</h5>
                <table id="equipmentTable" class="table mt-4">
                    <thead>
                        <tr>
                            <th scope="col">Equipment Type</th>
                            <th scope="col">SubType</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Notes</th>
                        </tr>
                    </thead>
                    <tbody id="modalEquipmentSearchResults">
                        <s:iterator value="reservedEquipmentView">
                            <tr>
                                <td><s:property value="equipmentTypeText" /></td>
                                <td><s:property value="equipmentSubTypeText" /></td>
                                <td><s:property value="quantity" /></td>
                                <td><s:property value="notes" /></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>

                <div class="input-group mt-3">
                    <input type="text" class="form-control" id="addEquipmentToReservationInput"
                           placeholder="Select Equipment Type"
                           onkeydown="if(event.keyCode == 13) GetEquipmentForReservation()">
                    <button class="btn btn-outline-primary" type="button"
                            onclick="GetEquipmentForReservation()">Add</button>
                    <button class="btn btn-primary" type="button"
                            onclick="GetEquipmentForReservation()">Search</button>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <s:submit value="Save Reservation" cssClass="btn btn-primary" />
            </div>
        </div>
    </s:form>
</div>

<jsp:include page="../ModalCustomerSearch.jsp" />
<jsp:include page="../ModalEquipmentSearch.jsp" />

<script>
function GetEquipmentForReservation() {
    var input = document.getElementById("addEquipmentToReservationInput").value;
    if(input.trim() !== "") {
        // TODO: implement addEquipmentToReservation()
        console.log("Adding equipment spec: " + input);
    } else {
        var reservationID = document.getElementById("reservationID").value;
        window.location.href = '/mcquaids/reservation/?reservationID=' + reservationID;
    }
}
</script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>