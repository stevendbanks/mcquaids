    <div class="container">
        <h1 class="mt-4 mb-4">Move Equipment</h1>
        
        <!-- Error Message -->
        <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>
				<div class="form-row">
					<div class="form-group col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="equipmentNumberToFind"
								placeholder="Equipment Number"
								onchange="searchEquipment();">
						</div>
					</div>

				</div>

					<div class="form-group col-sm-2">
				        <!-- Create New Trailer Button -->
				        <button id="createTrailer" class="btn btn-success" onclick="pickupEquipment()">Pickup Equipment</button>
        		    </div>					
        
					<div class="form-group col-sm-2">
				        <!-- Create New Trailer Button -->
				        <button id="createTrailer" class="btn btn-success" onclick="dropOffEquipment()">Drop Off Equipment</button>
        		    </div>			

********
        
				<div class="form-row">
					<div class="form-group col-sm-2">
					        <label for="equipmentNumber">Equipment Number</label>
							<input type="text" class="form-control" id="equipmentNumber"  readonly="readonly"/>
					</div>
					
                <div class="form-group col-sm-2">
                    <label for="equipmentTypeText">Equipment Type</label>
                	<input type="text" class="form-control" id="equipmentTypeText"  readonly="readonly"/>
                </div>        					

					<div class="form-group col-sm-2">
    	                <label for="equipmentSubTypeText">Equipment SubType</label>
						<input type="text" class="form-control" id="equipmentSubTypeText"  readonly="readonly"/>							
					</div>

					<div class="form-group col-sm-2">
	                    <label for="conditionStatusCodeText">Condition</label>
						<input type="text" class="form-control" id="conditionStatusCodeText"  readonly="readonly"/>
					</div>
					<div class="form-group col-sm-2">
	                    <label for="length">length</label>
						<input type="text" class="form-control" id="length"  readonly="readonly"/>
					</div>
				</div>            

				<div class="form-row">
					<div class="form-group col-sm-12">
	                    <label for="LeasedEquipmentNotes">LeasedEquipmentNotes</label>
						<input type="text" class="form-control" id="LeasedEquipmentNotes"  readonly="readonly"/>
					</div>
            </div>
            
    </div>
    
    
   <script>
    	  function searchEquipment(e) {
              console.log($('#equipmentNumberToFind').val());
              if (e) {
                  e.preventDefault(); // Prevent the default form submission if 'e' is provided
              }
              
              $.ajax({
                  url: '/mcquaids/equipment/searchByEN',
                  type: 'get',
                  data: {
                      equipmentNumber: $('#equipmentNumberToFind').val()
                  },
              
                  success: function(response) {
                	    var tSearchResults = $('#searchResults');
                	    tSearchResults.empty();
                	    
                	    if (response.actionErrors != null) {
                	        $('#errorMessage').text(response.actionErrors).show();
                	    } else {
                	        $('#errorMessage').hide();
                	        response.forEach(function(searchItem) {
                	            $('#equipmentNumber').val(searchItem.equipmentNumber);
                	            $('#equipmentTypeText').val(searchItem.equipmentTypeText);
                	            $('#equipmentSubTypeText').val(searchItem.equipmentSubTypeText);
                	            $('#conditionStatusCodeText').val(searchItem.conditionStatusText);
                	            // Accessing properties
                	            $('#insulated').val(searchItem.properties.insulated);
                	            $('#colour').val(searchItem.properties.colour);
                	            $('#axel').val(searchItem.properties.axel);
                	            $('#length').val(searchItem.properties.length);
                	            $('#doorType').val(searchItem.properties.doorType);
                	            $('#doorLocation').val(searchItem.properties.doorLocation);
                	            $('#floor').val(searchItem.properties.floor);
                	            $('#tieDown').val(searchItem.properties.tieDown);                	            
                	            $('#LeasedEquipmentNotes').val(searchItem.leasedEquipmentNotes);                	            
                	            
                	        });
                	    }
                	}


              });

   }
    	  
    	  
          function pickupEquipment() {
              var equipmentNumber = document.getElementById("equipmentNumber").value;
              var url = '/mcquaids/equipment/pickup?equipmentNumber=' + encodeURIComponent(equipmentNumber);
              window.location.href = url;
          }
          
          function dropOffEquipment() {
              var equipmentNumber = document.getElementById("equipmentNumber").value;
              var url = '/mcquaids/equipment/dropoff?equipmentNumber=' + encodeURIComponent(equipmentNumber);
              window.location.href = url;
          }
    	  
    </script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
