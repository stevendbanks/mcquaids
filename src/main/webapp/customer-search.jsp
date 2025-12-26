    <div class="container">
        <h1 class="mt-4 mb-4">Customer Search</h1>
        
        <!-- Error Message -->
        <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>
        
            <div class="form-row">
                <div class="form-group col-sm-2">
                    <label for="customerName">First or Last Name</label>
                    <input type="text" class="form-control" id="customerName" onchange="searchCustomer();">
                </div>
              <div class="form-group col-sm-2">
                    <label for="phoneNumber">Phone Number</label> 
                    <input type="tel" class="form-control" id="phoneNumber" placeholder="###-###-####" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" title="Format: 902-314-1232" onchange="searchCustomer();">
                </div>

                <div class="form-group col-sm-2">
                    <label for="customerID">User ID</label>
                    <input type="text" class="form-control" id="userID" onchange="searchCustomer();">
                </div>
                <div class="form-group col-sm-2">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" id="email" onchange="searchCustomer();">
                </div>
                <div class="form-group col-sm-3">
                    <label for="createCustomer" class="empty-label"></label>
                    <button id="createCustomer" type="button" class="btn btn-success" onclick="createCustomer()">Add New Customer</button>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Search</button>
        
        <!-- Create New Customer Button -->

        
        <table class="table mt-4">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone Number</th>
                </tr>
            </thead>
            <tbody id="searchResults">
                <!-- Search results will be inserted here -->
            </tbody>
        </table>
    </div>

<script>
	function searchCustomer(e) {
        if (e) {
            e.preventDefault(); // Prevent the default form submission if 'e' is provided
        }
        
		$.ajax({
					url : '/mcquaids/customer/search',
					type : 'get',
					data : {
						phoneNumber : $('#phoneNumber').val(),
						customerID : $('#userID').val(),
						customerName : $('#customerName').val(),
						email : $('#email').val()
					},
					success : function(response) {
						var tbody = $('#searchResults');
						tbody.empty();

						if (response.length === 0) {
							// No matching records found
							$('#errorMessage')
									.text('No Matching Records Found').show();
							$('#createCustomer').show();
						} else {
							$('#errorMessage').hide();
							response.forEach(function(customer) {
								var row = $('<tr>');
								if (!("userID" in customer)) {
									row.append($('<td>').html(
											'<a href="/mcquaids/customer/edit?userID='
													+ customer.userID + '">'
													+ "Make them Customer"
													+ '</a>'));
								} else {
									row
											.append($('<td>').html(
													'<a href="/mcquaids/customer/edit?userID='
															+ customer.userID
															+ '">'
															+ customer.userID
															+ '</a>'));
								}

								row.append($('<td>').text(
										customer.lastName + ", "
												+ customer.firstName));
								row.append($('<td>').text(customer.email));
								row.append($('<td>').html(
										'<a href="tel:' + customer.phoneNumber + '">'
												+ customer.phone + '</a>'));
								tbody.append(row);
							});
						}
					}
				});
	}

	function createCustomer() {
		alert("HELLO");
		var url = '/mcquaids/customer/create';
		window.location.href = url;
	}
</script>

<script>
    // ***  This script will create onchange listener for each of the input fields.
    // ***  When enabled, each time you fill in a specific input field, the other fields will be blanked out. 
    // Get all input elements
    var inputs = document.getElementsByTagName('input');

    // Loop through the input elements
    for (var i = 0; i < inputs.length; i++) {
        // Add an 'onchange' event listener to each input element
        inputs[i].addEventListener('change', function(e) {
            // Loop through the input elements again
            for (var j = 0; j < inputs.length; j++) {
                // If the current input element is not the one that triggered the event, reset its value
                if (inputs[j] != e.target) {
                    inputs[j].value = '';
                }
            }
        });
    }
</script>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

