// js/controllers/EquipmentReservationController.js

export function reserve(leaseID, equipmentNumber) {
  alert(`Reserving equipment with number: ${leaseID}, ${equipmentNumber}`);
  // TODO: Add reservation logic here
}   

export function searchEquipment(e, leaseID) {
  if (e) e.preventDefault();

  $.ajax({
    url: '/mcquaids/equipment/search',
    type: 'get',
    data: {
      equipmentNumber: $('#equipmentNumber').val(),
      equipmentType: $('#equipmentType').val(),
      equipmentSubType: $('#equipmentSubType').val(),
      availabilityStatusCode: $('#availabilityStatusCode').val(),
      conditionStatusCode: $('#conditionStatusCode').val()
    },
    success: function(response) {
      const tbody = $('#searchResults');
      tbody.empty();

      if (response.actionErrors != null) {
        $('#errorMessage').text(response.actionErrors).show();
      } else {
        $('#errorMessage').hide();
        response.forEach(function(searchItem) {
          const row = $('<tr>');
          row.append($('<td>').html(
            `<a href="/mcquaids/equipment/edit?equipmentNumber=${searchItem.equipmentNumber}">${searchItem.equipmentNumber}</a>`
          ));
          row.append($('<td>').text(searchItem.equipmentSubTypeText));
          row.append($('<td>').text(searchItem.availabilityStatusText));
          row.append($('<td>').text(searchItem.specialNotes));

          row.append(
            $('<td>').html(
              `<button class="btn btn-primary btn-sm" onclick="reserve(${leaseIDFromJsp}, '${searchItem.equipmentNumber}')">Reserve</button>` 
            )
          );
          tbody.append(row);
        });
      }
    }
  });
}

export function resetOtherFields(changedElementId) {
  const ids = ['equipmentType', 'equipmentNumber', 'availabilityStatusCode', 'conditionStatusCode'];
  ids.forEach(function(id) {
    if (id !== changedElementId) {
      $('#' + id).val('');
    }
    if (changedElementId === 'equipmentType') {
      $('#searchResults').empty();
    }
  });
}

export function fetchSubTypes() {
  const equipmentType = $('#equipmentType').val();
  if (equipmentType) {
    $.ajax({
      url: '/mcquaids/equipment/fetchEquipmentSubTypes',
      type: 'get',
      data: { equipmentType },
      success: function(response) {
        if (response && typeof response === 'object') {
          const subTypeSelect = $('#equipmentSubType');
          subTypeSelect.empty();
          subTypeSelect.append('<option value="">All SubTypes</option>');
          $.each(response, function(key, value) {
            subTypeSelect.append(`<option value="${key}">${value}</option>`);
          });
        } else {
          console.error('Invalid response format:', response);
        }
      },
      error: function(xhr, status, error) {
        console.error('Error fetching subtypes:', error);
      }
    });
  } else {
    $('#equipmentSubType').empty().append('<option value="">All SubTypes</option>');
  }
}