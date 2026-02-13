
// This function is triggered when an element with the class 'customer-row' is clicked.
// It sets the value of the element with id 'customerID' to the data-id of the clicked element and hides the modal.
$(document).on('click', '.customer-row', function() {
    $('#customerID').val($(this).data('id'));
    $('#fullName').val($(this).data('fullname'));
    $('#ModalCustomerSearch').modal('hide');

    // Trigger the onchange event if you still want it
    $('#customerID').trigger('change');

    // 🔥 Run the reservation search immediately
//    searchReservations();
});

// This function is triggered when the modal is hidden.
// It removes the modal backdrop.
$('#ModalCustomerSearch').on('hidden.bs.modal', function() {
	$('.modal-backdrop').remove();
})

// This function is triggered when the 'searchCustomerModalButton' is clicked.
// It prevents the default action and sends an AJAX request to the server with the customer details.
// On success, it updates the search results.
$('#searchCustomerModalButton').on('click', function(e) {
	e.preventDefault();
	$.ajax({
		url: '/mcquaids/customer/search',
		type: 'get',
		data: {
			customerID: $('#modalCustomerID').val(),
			customerName: $('#modalCustomerName').val(),
			phoneNumber: $('#modalPhoneNumber').val(),
			email: $('#modalEmail').val()
		},

		success: function(response) {
			var tbody = $('#modalCustomerSearchResults');
			tbody.empty();

			if (response.length === 0) {
				// No matching records found
				$('#ModalerrorMessage').text('No Matching Records Found').show();
				$('#createCustomer').show();
			} else {
				$('#ModalerrorMessage').hide();
				response.forEach(function(customer) {

				var row = $('<tr>')
				    .addClass('customer-row')
				    .attr('data-id', customer.userID)
				    .attr('data-fullname', customer.fullName);					
					
					row.append($('<td>').text(customer.userID));
					row.append($('<td>').text(customer.lastName + ", " + customer.firstName));
					row.append($('<td>').text(customer.email));
					row.append($('<td>').text(customer.phone));
					tbody.append(row);
				});
			}
		}
	});
});



// This function is triggered when an element with the class 'customer-row' is clicked.
// It sets the value of the element with id 'customerID' to the data-id of the clicked element and hides the modal.
$(document).on('click', '.modal-equipment-row', function() {
	$('#equipmentNumber').val($(this).data('equipmentNumber'));
	$('#modalEquipmentSearchDialog').modal('hide'); // This line closes the modal
});



// This function is triggered when the 'addEquipmentToLease' is clicked.
// It prevents the default action and sends an AJAX request to the server with the customer details.
// On success, it updates the search results.
// This function sends an AJAX request to the server with the equipment details.
// On success, it updates the search results.
function addEquipmentToLease() {
	
	alert("Hello");
	$.ajax({
		url: '/mcquaids/lease/AddEquipmentToLease',
		type: 'get',
		data: {
			equipmentNumber: $('#addequipmentToLeaseInput').val(),
			leaseID: $('#leaseID').val()
		},

		success: function(response) {
			var tbody = $('#modalEquipmentSearchResults');


			if (response.actionErrors != null)	{
				$('#modalErrorMessage').text(response.actionErrors).show();
			} else {
				$('#modalErrorMessage').hide();
				var row = $('<tr>').addClass('modal-equipment-row').attr('data-id', response.equipmentNumber);
				row.append($('<td>').text(response.equipmentNumber));
				row.append($('<td>').text(response.equipmentTypeText));
				row.append($('<td>').text(response.equipmentSubTypeText));
				row.append($('<td>').text(response.notes));
				tbody.append(row);
			}
		}
		


	});
}

// This function is triggered when the 'modalSearchEquipmentButton' is clicked.
// It prevents the default action and calls the searchEquipment function.
$('#modalSearchEquipmentButton').on('click', function(e) {
	e.preventDefault();
	addEquipmentToLease();
});
