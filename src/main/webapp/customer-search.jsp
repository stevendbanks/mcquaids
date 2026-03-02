<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <!-- Header + Return button -->
    <div class="row mt-4 mb-3 align-items-center">
        <div class="col-sm-9">
            <h1 class="mb-0">Customer Search</h1>
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
            <label for="businessName">Business Name</label>
            <input type="text" class="form-control" id="businessName"
                   onchange="resetOtherFields('businessName'); searchCustomer();">
        </div>

        <div class="form-group col-sm-2">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email"
                   onchange="resetOtherFields('email'); searchCustomer();">
        </div>

        <div class="form-group col-sm-3 text-left">
            <label class="empty-label">&nbsp;</label>
			<button type="button" class="btn btn-success" onclick="loadCreateCustomer()">
			    <i class="fa fa-plus"></i> Add New Customer
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

			<%-- Check if we are in a modal to show the selection column --%>
			<s:if test="%{#parameters.isModal == 'true' || isModal == 'true'}">
			    <th scope="col">Action</th>
			</s:if>
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
    $.ajax({
        url: '/mcquaids/customer/search',
        type: 'get',
        data: {
            phoneNumber: $('#phoneNumber').val(),
            customerID: $('#userID').val(),
            customerName: $('#customerName').val(),
            email: $('#email').val(),
            businessName: $('#businessName').val()
        },

        success: function(response) {
        	// Clean version of your queryString logic
        	var currentQuery = window.location.search; 
        	var queryString = currentQuery ? currentQuery.replace('?', '&') : '';
        			

        	
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
	             // 1. First, determine if we are in "Modal Mode" 
	             // We can check if the modal body is visible or if a specific flag exists
	             var isModal = $('#customerModal').is(':visible');
	
	             // 2. Update the row building logic
				var customerLink;
				var isModal = $('#customerModal').is(':visible');
				
				if (isModal) {
				    // MODAL MODE: Use our new clean function to "flip" to the Edit view
				    customerLink = '<a href="javascript:void(0);" onclick="loadEditInModal(\'' + customer.userID + '\')">' 
				                   + customer.userID + '</a>';
				} else {
				    // STANDALONE MODE: Standard full-page navigation
				    customerLink = '<a href="/mcquaids/customer/edit.action?userID=' + customer.userID + '">' 
				                   + customer.userID + '</a>';
				}
	
	             row.append($('<td>').html(customerLink));            
                

                row.append($('<td>').text(customer.fullName));
                row.append($('<td>').text(customer.email));
                row.append($('<td>').text(customer.phoneNumber));

                var actionCell = buildActionCellForCustomer(
                    customer.userID,
                    customer.fullName
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
function buildActionCellForCustomer(customerId, customerName) {
    // 1. Detect Context
    var isModal = $('#customerModal').is(':visible');

    // 2. Lookup Mode (Modal)
    // Always provide a Select button to pass data back to the parent form
    if (isModal) {
        return $('<td>').html(
            '<button type="button" class="btn btn-primary btn-sm" ' +
            'onclick="selectCustomerForReservation(\'' + customerId + '\', \'' + customerName + '\')">' +
            'Select</button>'
        );
    }

    // 3. Navigation Mode (Standalone)
    // Always provide a Link to go to the edit/detail page
    var editUrl = '/mcquaids/customer/edit?userID=' + customerId;
    return $('<td>').html(
        '<a class="btn btn-secondary btn-sm" href="' + editUrl + '">View/Edit</a>'
    );
}
</script>



<script>
function resetOtherFields(changedId) {
    var ids = ['customerName', 'phoneNumber', 'userID', 'email', 'businessName'];

    ids.forEach(function(id) {
        if (id !== changedId) {
            $('#' + id).val('');
        }
    });

    $('#searchResults').empty();
}
</script>


<script>
function loadCreateCustomer() {
    var isModal = $('#customerModal').is(':visible');
    var baseUrl = '/mcquaids/customer/create.action';

    if (isModal) {
        // Just pass the one flag that matters for the UI context
        $('#customerModalBody').load(baseUrl + '?isModal=true');
    } else {
        // Standard navigation for management pages
        window.location.href = baseUrl;
    }
}
window.loadCreateCustomer = loadCreateCustomer;

function loadEditInModal(userID) {
    console.info("Context: Modal. Loading Edit fragment for: " + userID);
    
    // Simple, clean URL with only the ID and the Modal flag
    var editUrl = '/mcquaids/customer/edit.action?userID=' + userID + '&isModal=true';
    
    $('#customerModalBody').load(editUrl, function(response, status, xhr) {
        if (status === "error") {
            console.error("Edit load failed: " + xhr.status);
        }
    });
}
window.loadEditInModal = loadEditInModal;
</script>