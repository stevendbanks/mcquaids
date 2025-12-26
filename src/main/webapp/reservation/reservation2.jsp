 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>Equipment - Reservation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Reserve Equipment for Lease ID - <s:property value="leaseID"/></h1>
        
        <!-- Error Message -->
        <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>
				<div class="form-row">
					<div class="form-group col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="equipmentNumber"
								placeholder="Equipment Number"
								onchange="resetOtherFields('equipmentNumber'); searchEquipment();">
						</div>
					</div>
					
					
                <div class="form-group col-sm-2">
				<select id="equipmentType" name="equipmentType"  class="form-control"  onchange="resetOtherFields('equipmentType'); fetchSubTypes()" >
 					    <option value="">Equipment Type</option>
						    <s:iterator value="codeValues.equipmentTypes" var="status">
						        <option value="<s:property value="#status.key"/>"><s:property value="#status.value"/></option>
						    </s:iterator>
                </select>
                     
                </div>        					

					<div class="form-group col-sm-2">
						<select id="equipmentSubType" name="equipmentSubType" class="form-control" onchange="searchEquipment();">
						    <option value="">All SubTypes</option>
						    <s:iterator value="codeValues.trailerTypes" var="status">
						        <option value="<s:property value="#status.key"/>"><s:property value="#status.value"/></option>
						    </s:iterator>
						</select>							
					</div>

					<div class="form-group col-sm-2">
						<select id="availabilityStatusCode" name="availabilityStatusCode" class="form-control" onchange="searchEquipment();">
						    <option value="">All Availability</option>
						    <s:iterator value="codeValues.availabilityStatuses" var="status">
						        <option value="<s:property value="#status.key"/>"><s:property value="#status.value"/></option>
						    </s:iterator>
						</select>
					</div>
					<div class="form-group col-sm-2">
					<select id="conditionStatusCode" name="conditionStatusCode" class="form-control" onchange="searchEquipment();">
						    <option value="">All Conditions</option>
						    <s:iterator value="codeValues.conditionStatuses" var="status">
						        <option value="<s:property value="#status.key"/>"><s:property value="#status.value"/></option>
						    </s:iterator>
						</select>							
					</div>
		
				</div>
        

        
        <table class="table mt-4">
            <thead>
                <tr>
                    <th scope="col">Equipment Number</th>
                    <th scope="col">Equipment SubType</th>
                    <th scope="col">Availability</th>
                    <th scope="col">Special Notes</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="searchResults">
                <!-- Search results will be inserted here -->
            </tbody>
        </table>
    </div>
    
    
   <script>

		   function reserve(leaseID, equipmentNumber) {
		       alert("Reserving equipment with number: " + leaseID + ", " + equipmentNumber);
		       // Add your reservation logic here
		   }
   
   
    	  function searchEquipment(e) {
              console.log($('#equipmentNumber').val());
              if (e) {
                  e.preventDefault(); // Prevent the default form submission if 'e' is provided
              }
              
              $.ajax({
                  url: '/mcquaids/equipment/search',
                  type: 'get',
                  data: {
                      equipmentNumber: $('#equipmentNumber').val(),
                      equipmentType:   $('#equipmentType').val(),
                      equipmentSubType: $('#equipmentSubType').val(),
                      availabilityStatusCode: $('#availabilityStatusCode').val(),
                      conditionStatusCode: $('#conditionStatusCode').val()
                  },
              
                  success: function(response) {
                      var tbody = $('#searchResults');
                      tbody.empty();
                      
          			if (response.actionErrors != null)	{
          				$('#errorMessage').text(response.actionErrors).show();
          			} else {
                          $('#errorMessage').hide();
                          response.forEach(function(searchItem) {
                              var row = $('<tr>');
                              row.append($('<td>').html('<a href="/mcquaids/equipment/edit?equipmentNumber=' + searchItem.equipmentNumber + '">' + searchItem.equipmentNumber + '</a>'));
                              row.append($('<td>').text(searchItem.equipmentSubTypeText));
                              row.append($('<td>').text(searchItem.availabilityStatusText));
                              row.append($('<td>').text(searchItem.specialNotes));
                           // Assuming reserve function signature is reserve(arg1, arg2)
                              row.append($('<td>').html('<button class="btn btn-primary btn-sm" onclick="reserve(\'<s:property value="leaseID"/>\', \'' + searchItem.equipmentNumber + '\')">Reserve</button>'));
                              tbody.append(row);
                          });
                      }
                  }
              });

   }
    </script>
    
  <script>    
function resetOtherFields(changedElementId) {
    var ids = ['equipmentType', 'equipmentNumber', 'availabilityStatusCode', 'conditionStatusCode'];
    ids.forEach(function(id) {
        if (id !== changedElementId) {
            $('#' + id).val('');
        }
        
        if (changedElementId == 'equipmentType') {
            var tbody = $('#searchResults');
            tbody.empty();
        }
    });
}

function fetchSubTypes() {
    var equipmentType = $('#equipmentType').val();
    vac ava
    if (equipmentType) {
        $.ajax({
            url: '/mcquaids/equipment/fetchEquipmentSubTypes',
            type: 'get',
            data: {
                equipmentType: equipmentType
            },
            success: function(response) {
                if (response && typeof response === 'object') {
                    var subTypeSelect = $('#equipmentSubType');
                    subTypeSelect.empty(); // Clear existing options
                    subTypeSelect.append('<option value="">All SubTypes</option>'); // Add default option
                    $.each(response, function(key, value) {
                        subTypeSelect.append('<option value="' + key + '">' + value + '</option>');
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




</script>  



    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
