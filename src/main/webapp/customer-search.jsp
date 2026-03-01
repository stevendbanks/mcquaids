<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <!-- Read caller + reservation.reservationID -->
    <c:set var="caller" value="${param.caller}" />
    <c:set var="reservationId" value="${param['reservation.reservationID']}" />

    <!-- Header + Return button -->
    <div class="row mt-4 mb-3 align-items-center">
        <div class="col-sm-9">
            <h1 class="mb-0">Customer Search</h1>
        </div>
        <div class="col-sm-1 text-right">
<c:choose>

    <c:when test="${caller == 'RESERVE'}">
        <button class="btn btn-secondary ml-3" onclick="navigateBackToReservation()" >Return</button>
    </c:when>

    <c:when test="${caller == 'SEARCH_RESERVATIONS'}">
        <button class="btn btn-secondary  ml-3" onclick="navigateBackToReservationSearch()">Return</button>
    </c:when>

    <c:otherwise>
        <!-- No return button for standalone mode -->
    </c:otherwise>

</c:choose>
        </div>
    </div>

    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display:none;"></div>

    <!-- Search Filters -->
    <div class="form-row">

        <div class="form-group col-sm-2">
            <label for="customerName">First or Last Name</label>
            <input type="text" class="form-control" id="customerName"
                   onchange="resetOtherFields('customerName'); searchCustomer();">
        </div>

        <div class="form-group col-sm-2">
            <label for="phoneNumber">Phone Number</label>
            <input type="tel" class="form-control" id="phoneNumber"
                   placeholder="###-###-####"
                   onchange="resetOtherFields('phoneNumber'); searchCustomer();">
        </div>

        <div class="form-group col-sm-2">
            <label for="userID">User ID</label>
            <input type="text" class="form-control" id="userID"
                   onchange="resetOtherFields('userID'); searchCustomer();">
        </div>

        <div class="form-group col-sm-2">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email"
                   onchange="resetOtherFields('email'); searchCustomer();">
        </div>

        <div class="form-group col-sm-3 text-left">
            <label class="empty-label">&nbsp;</label>
			<button id="createCustomer"
			        type="button"
			        class="btn btn-success"
			        onclick="navigateToCustomerCreate()">
			    Add New Customer
			</button>
        </div>
    </div>

    <!-- Results Table -->
    <table class="table mt-4">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Phone</th>

            <c:if test="${caller == 'RESERVE'}">
                <th scope="col">Action</th>
            </c:if>
        </tr>
        </thead>

        <tbody id="searchResults">
        </tbody>
    </table>

</div>

<!-- ========================= JS SECTION ========================= -->

<script>
function searchCustomer(e) {

    if (e) { e.preventDefault(); }
console.warn("SDBANKS - searchCustomer() Entered");
    $.ajax({
        url: '/mcquaids/customer/search',
        type: 'get',
        data: {
            phoneNumber: $('#phoneNumber').val(),
            customerID: $('#userID').val(),
            customerName: $('#customerName').val(),
            email: $('#email').val()
        },

        success: function(response) {
        	var queryString = window.location.search;   // includes the leading '?'
        	var queryString = window.location.search.replace('?', '&');
        			

        	
            var tbody = $('#searchResults');
            tbody.empty();

            // Handle Struts actionErrors
            if (response.actionErrors != null) {
                $('#errorMessage').text(response.actionErrors).show();
                return;
            }

            $('#errorMessage').hide();

            
            response.forEach(function(customer) {
                var row = $('<tr>');
                row.append($('<td>').html(
                    '<a href="/mcquaids/customer/edit?userID=' + customer.userID + queryString + '">' +
                        customer.userID +
                    '</a>'
                ));               
                

                row.append($('<td>').text(customer.lastName + ", " + customer.firstName));
                row.append($('<td>').text(customer.email));
                row.append($('<td>').text(customer.phoneNumber));

                var actionCell = buildActionCellForCustomer(
                    customer.userID,
                    customer.firstName + ' ' + customer.lastName,
                    '${caller}'
                );

                if (actionCell) {
                    row.append(actionCell);
                }

                tbody.append(row);
            });
        }
    });
}
</script>

<script>
function buildActionCellForCustomer(customerId, customerName, caller) {

    caller = (caller || "").trim();

    // === 1. Reservation workflow ===
    if (caller === 'RESERVE') {

        const params = new URLSearchParams(window.location.search);

        params.set("reservation.customerID", customerId);
        params.set("reservation.customer.fullName", customerName);
        params.set("fromSelector", "true");

        const reservationID = params.get("reservation.reservationID");

        let returnUrl;

        if (reservationID) {
            returnUrl = '/mcquaids/reservation/edit-reservation?' + params.toString();
        } else {
            returnUrl = '/mcquaids/reservation/create?' + params.toString();
        }

        return $('<td>').html(
            '<a class="btn btn-primary btn-sm" href="' + returnUrl + '">Select</a>'
        );
    }

    // === 2. Search Reservations workflow ===
    if (caller === 'SEARCH_RESERVATIONS') {

        // Build URL back to Search Reservations
        const params = new URLSearchParams();
        params.set("customerID", customerId);
        params.set("customerName", customerName);

        const returnUrl = '/mcquaids/reservation/?' + params.toString();

        return $('<td>').html(
            '<a class="btn btn-primary btn-sm" href="' + returnUrl + '">Select</a>'
        );
    }

    return null;
}
</script>



<script>
function resetOtherFields(changedId) {
    var ids = ['customerName', 'phoneNumber', 'userID', 'email'];

    ids.forEach(function(id) {
        if (id !== changedId) {
            $('#' + id).val('');
        }
    });

    $('#searchResults').empty();
}
</script>


<script>
function returnToCaller() {
    const caller = '${caller}';
    const reservationId = '${reservationId}';

    if (caller === 'RESERVE') {
        window.location.href =
            '/mcquaids/reservation/edit-reservation?reservation.reservationID=' + reservationId;
        return;
    }

    window.history.back();
}
</script>