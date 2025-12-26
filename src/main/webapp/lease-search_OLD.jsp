 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search For a Lease</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div class="container">
    <div class="mb-5">
        <h1 class="mt-4 mb-4">Search Leases</h1>
        
        <!-- Error Message -->
        <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>


				<div class="form-row">
				
					<div class="form-group col-sm-3">
						<div class="input-group">
						  <div class="floating-label">
							<input type="text" class="form-control" id="leaseID"
								placeholder=" "
								onchange="resetOtherFields('leaseID'); searchLeases();" oninput="checkInput(this);">
							<label for="leaseID">Lease ID</label>
							</div>
						</div>
					</div>

					<div class="form-group col-sm-3">
					    <div class="input-group">
					        <div class="floating-label">
					            <input type="text" class="form-control" id="customerID" placeholder=" " onchange="resetOtherFields('customerID'); searchLeases();" oninput="checkInput(this)">
					            <label for="customerID">Customer ID</label>
					        </div>
					        <div class="input-group-append">
					            <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#ModalCustomerSearch">
					                <i class="fa fa-search"></i>
					            </button>
					        </div>
					    </div>
					</div>
					<div class="form-group col-sm-2">
				        <!-- Create New Lease Button -->
				        <button id="createLease" class="btn btn-success" onclick="createLease()">Create New Lease</button>
        		    </div>					
				</div>
</div>       

        
        <table class="table mt-5">
            <thead>
                <tr>
                    <th scope="col">Lease Number</th>
                    <th scope="col">Customer Name</th>
                    <th scope="col">Lease Status</th>
                    <th scope="col">Instructions</th>
                </tr>
            </thead>
            <tbody id="searchResults">
                <!-- Search results will be inserted here -->
            </tbody>
        </table>
    </div>
    
    
<script>

function createLease() {
    window.location.href = "/mcquaids/lease/create";
}


    function searchLeases() {
    	console.log("Searching Leases");
            $.ajax({
                url: '/mcquaids/lease/search',
                type: 'get',
                data: {
                    leaseID: $('#leaseID').val(),
                    customerID: $('#customerID').val()
                },
            
                success: function(response) {
                    var tbody = $('#searchResults');
                    tbody.empty();
                    
                    if (response.length === 0) {
                        // No matching records found
                        $('#errorMessage').text('No Matching Records Found').show();
                        $('#createCustomer').show();
                    } else {
                        $('#errorMessage').hide();
                        response.forEach(function(lease) {
                            var row = $('<tr>').addClass('customer-row');
                            row.append($('<td>').html('<a href="/mcquaids/lease/edit-lease?leaseID=' + lease.leaseID + '" class="lease-select" data-id="' + lease.leaseID + '">' + lease.leaseID + '</a>'));
							row.append($('<td>').text(lease.customer.lastName + ", " + lease.customer.firstName));
                            row.append($('<td>').text(lease.leaseStatusDescription));
                            row.append($('<td>').text(lease.instructions));
                            tbody.append(row);
                        });
                    }
                },

            });

        
    }


function resetOtherFields(changedElementId) {
    var ids = [ 'availabilityStatusCode', 'leaseID', 'customerID', 'conditionStatusCode'];
    ids.forEach(function(id) {
        if (id !== changedElementId) {
            $('#' + id).val('');
        }
    });
}
</script>

<!--  Include Javascript for the Customer Search Modal Window. -->
<jsp:include page="ModalCustomerSearch.jsp" />


    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
