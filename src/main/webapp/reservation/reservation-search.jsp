<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reservation Search</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="mb-5">
        <h1 class="mt-4 mb-4">Search Reservations</h1>

        <!-- Error Message -->
        <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>

        <div class="form-row">
            <div class="form-group col-sm-3">
                <div class="input-group">
                    <div class="floating-label">
                        <input type="text" class="form-control" id="reservationID"
                               placeholder=" "
                               onchange="resetOtherFields('reservationID'); searchReservations();">
                        <label for="reservationID">Reservation ID</label>
                    </div>
                </div>
            </div>

            <div class="form-group col-sm-3">
                <div class="input-group">
                    <div class="floating-label">
                        <input type="text" class="form-control" id="customerName" placeholder=" "
                               onchange="resetOtherFields('customerName'); searchReservations();" >
                        <label for="customerName">Customer Name</label>
                    </div>
                    <div class="input-group-append">
                        <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#ModalCustomerSearch">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>

            <div class="form-group col-sm-2">
                <!-- Create New Reservation Button -->
                <button id="createReservation" class="btn btn-info" onclick="createReservation()">Create New Reservation</button>
            </div>
        </div>
    </div>

    <table class="table mt-5">
        <thead>
        <tr>
            <th scope="col">Reservation Number</th>
            <th scope="col">Customer Name</th>
            <th scope="col">Reservation Status</th>
            <th scope="col">Notes</th>
        </tr>
        </thead>
        <tbody id="searchResults">
        <!-- Search results will be inserted here -->
        </tbody>
    </table>
</div>

<script>
function createReservation() {
    window.location.href = "/mcquaids/reservation/create";
}

function searchReservations() {
    console.log("Searching Reservations");
    $.ajax({
        url: '/mcquaids/reservation/search',
        type: 'get',
        data: {
            reservationID: $('#reservationID').val(),
            customerID: $('#customerID').val()
        },
        success: function(response) {
            var tbody = $('#searchResults');
            tbody.empty();

            if (response.length === 0) {
                $('#errorMessage').text('No Matching Records Found').show();
            } else {
                $('#errorMessage').hide();
                response.forEach(function(reservation) {
                    var row = $('<tr>').addClass('customer-row');
                    row.append($('<td>').html('<a href="/mcquaids/reservation/edit-reservation?reservationID=' + reservation.reservationID + '" class="reservation-select" data-id="' + reservation.reservationID + '">' + reservation.reservationID + '</a>'));
                    row.append($('<td>').text(reservation.customer.lastName + ", " + reservation.customer.firstName));
                    row.append($('<td>').text(reservation.reservationStatusDescription));
                    row.append($('<td>').text(reservation.notes));
                    tbody.append(row);
                });
            }
        }
    });
}

function resetOtherFields(changedElementId) {
    var ids = [ 'reservationID', 'customerID' ];
    ids.forEach(function(id) {
        if (id !== changedElementId) {
            $('#' + id).val('');
        }
    });
}
</script>

<jsp:include page="../ModalCustomerSearch.jsp" />

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>