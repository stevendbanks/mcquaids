<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<div class="container">
<div style="margin-bottom: 20px;">
	<h1 class="mt-4 mb-4">
		<s:property value="title" />
	</h1>


<!-- Errors -->
<s:if test="hasActionErrors()">
    <div id="errorMessage" class="alert alert-danger">
        <s:actionerror/>
    </div>
</s:if>

<!-- Success -->
<s:if test="hasActionMessages()">
    <div id="successMessage" class="alert alert-success">
        <s:actionmessage/>
    </div>
</s:if>

    <div class="card mb-4">
    <div class="card-body">   
    <div class="row">
        <div class="col-sm-3">
            <div class="form-group">
                <label for="leaseID">Lease ID</label>
                <s:textfield id="leaseID" name="leasedEquipmentView.leaseID" cssErrorClass="fieldError" cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="equipmentNumber">Equipment Number</label>
                <s:textfield id="equipmentNumber" name="leasedEquipmentView.equipmentNumber" cssErrorClass="fieldError" cssClass="form-control" readonly="true" style="background-color: #e9ecef;"  />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="leaseID">Serial Number</label>
                <s:textfield id="serialNumber" name="leasedEquipmentView.serialNumber" cssErrorClass="fieldError" cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="inspectionDate">Inspection Date</label>
                 <s:textfield type="date" format="yyyy-MM-dd" id="inspectionDate" name="leasedEquipmentView.inspectionDate" cssErrorClass="fieldError"  cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>
                   
    </div>
    
    <div class="row">
    </div>    

    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="equipmentTypeText">Equipment Type</label>
                <s:textfield id="equipmentTypeText" name="leasedEquipmentView.equipmentTypeText" cssErrorClass="fieldError" cssClass="form-control" readonly="true" style="background-color: #e9ecef;"  />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="equipmentSubTypeText">Equipment Sub-Type</label>
                <s:textfield id="equipmentSubTypeText" name="leasedEquipmentView.equipmentSubTypeText" cssErrorClass="fieldError" cssClass="form-control" readonly="true" style="background-color: #e9ecef;"  />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="manufacturer">Manufacturer</label>
                <s:textfield id="manufacturer" name="leasedEquipmentView.manufacturer" cssErrorClass="fieldError"  cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>        
    </div>

    <div class="row">

        <div class="col-sm-4">
            <div class="form-group">
                <label for="maintenanceStatusText">Maintenance Status</label>
                <s:textfield id="maintenanceStatusText" name="leasedEquipmentView.maintenanceStatusText" cssErrorClass="fieldError"   cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="conditionStatusText">Condition Status</label>
                <s:textfield id="conditionStatusText" name="leasedEquipmentView.conditionStatusText" cssErrorClass="fieldError"   cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="cleaningStatusTest">Cleaning Status</label>
                <s:textfield id="cleaningStatusTest" name="leasedEquipmentView.cleaningStatusTest" cssErrorClass="fieldError"   cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="form-group">
                <label for="notes">Equipment Notes</label>
                <s:textarea id="notes" name="leasedEquipmentView.notes" cssErrorClass="fieldError" cssClass="form-control" readonly="true"  placeholder="Enter additional notes here..."/>
            </div>
        </div>
    </div>       
  </div>
  </div>  

<s:if test="%{leasedEquipmentView.equipmentTypeText == 'Trailer'}">

    <div class="card mb-4">
    <div class="card-body">  
    <h5 class="card-title">Trailer Information</h5>
		<div class="row">

        <div class="col-sm-2">
            <div class="form-group">
                <label for="trailerLength">Trailer Length</label>
			<s:textfield 
			    id="trailerLength" 
			    name="leasedEquipmentView.length" 
			    cssClass="form-control" 
			    readonly="true" 
			    style="background-color: #e9ecef;" />
            </div>
        </div>		
		

        <div class="col-sm-2">
            <div class="form-group">
                <label for="doorLocation">Door Location</label>
                <s:textfield id="doorLocation" name="leasedEquipmentView.doorLocation" cssErrorClass="fieldError"   cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>		
			
        <div class="col-sm-2">
            <div class="form-group">
                <label for="doorLocation">Door Type</label>
                <s:textfield id="doorType" name="leasedEquipmentView.doorType" cssErrorClass="fieldError"   cssClass="form-control" readonly="true" style="background-color: #e9ecef;" />
            </div>
        </div>		
        
<!-- Floor -->
<div class="col-sm-2">
    <div class="form-group">
        <label for="floor">Floor</label>
        <s:textfield id="floor" name="leasedEquipmentView.floor"
                     cssErrorClass="fieldError" cssClass="form-control"
                     readonly="true" style="background-color: #e9ecef;" />
    </div>
</div>
 

<!-- Axel -->
<div class="col-sm-2">
    <div class="form-group">
        <label for="axel">Axel</label>
        <s:textfield id="axel" name="leasedEquipmentView.axel"
                     cssErrorClass="fieldError" cssClass="form-control"
                     readonly="true" style="background-color: #e9ecef;" />
    </div>
</div>
<!-- Colour -->
<div class="col-sm-2">
    <div class="form-group">
        <label for="colour">Colour</label>
        <s:textfield id="colour" name="leasedEquipmentView.colour"
                     cssErrorClass="fieldError" cssClass="form-control"
                     readonly="true" style="background-color: #e9ecef;" />
    </div>
</div>
        		
			
		</div>

		<div class="row">

<div class="col-sm-3">
    <div class="form-group">
        <label for="Aligning the checkbox that follows">Insulation</label>
        <s:checkbox name="leasedEquipmentView.insulated"   cssErrorClass="fieldError" cssClass="form-control" label="Insulated" readonly="true"  style="background-color: #e9ecef;" />
    </div>
</div>

			<div class="col-sm-3">
				<div class="form-group">
					<label for="tieDown">Tie Down</label>
        			<s:checkbox name="leasedEquipmentView.tieDown"     cssErrorClass="fieldError" cssClass="form-control" label="Tie Down" readonly="true" style="background-color: #e9ecef;" />
				</div>
			</div>
		</div>


</div>
</div>
</s:if>
<s:if test="%{leasedEquipmentView.equipmentTypeText == 'Forklift'}">
    <div class="card mb-4">
    <div class="card-body"> 
    <h5 class="card-title">Forklift Information</h5>
		<div class="row">
			<div class="col-sm-2">
				<div class="form-group">
					<label for="loadCapacity">Load Capacity (lbs.)</label>
                       <s:textfield id="loadCapacity" name="leasedEquipmentView.loadCapacity"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />
				</div>
			</div>
			<div class="col-sm-2">
				<div class="form-group">
					<label for="maximumForkHeight">Max Fork Height</label>
                       <s:textfield id="maximumForkHeight" name="leasedEquipmentView.maximumForkHeight"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />					
				</div>
			</div>
			<div class="col-sm-2">
				<div class="form-group">
					<label for="freeLift">Free Lift (Std. LBR)</label>
                       <s:textfield id="freeLift" name="leasedEquipmentView.freeLift"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />						
				</div>
			</div>

			<div class="col-sm-2">
				<div class="form-group">
					<label for="overallWidth">Overall Width</label>
                       <s:textfield id="overallWidth" name="leasedEquipmentView.overallWidth"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />							
				</div>
			</div>

			<div class="col-sm-2">
				<div class="form-group">
					<label for="mastLoweredHeight">Mast Lowered Height</label>
                       <s:textfield id="mastLoweredHeight" name="leasedEquipmentView.mastLoweredHeight"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />								
				</div>
			</div>
			<div class="col-sm-2">
				<div class="form-group">
					<label for="mastExtendedHeight">Mast Ext. Height</label>
                   <s:textfield id="mastExtendedHeight" name="leasedEquipmentView.mastExtendedHeight"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />								
				</div>
			</div>

			<div class="col-sm-2">
				<div class="form-group">
					<label for="overheadGuardHeight">Overhead Guard Height</label>
                   <s:textfield id="overheadGuardHeight" name="leasedEquipmentView.overheadGuardHeight"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />								
				</div>
			</div>


		</div>
</div>
</div>
</s:if>


<s:if test="%{leasedEquipmentView.equipmentTypeText == 'Flatbed'}">
    <div class="card mb-4">
    <div class="card-body">  
        <h5 class="card-title">Flatbed Information</h5> 
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="size">Size</label>
                   <s:textfield id="size" name="leasedEquipmentView.size"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />						
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="fuelType">Fuel Type</label>
                   <s:textfield id="fuelType" name="leasedEquipmentView.fuelType"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />						
				</div>
			</div>

			<div class="col-sm-4">
				<div class="form-group">
					<label for="floor">Floor</label>
                   <s:textfield id="floor" name="leasedEquipmentView.forkliftFloor"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />						
				</div>
			</div>
		</div>

</div>
</div>
</s:if>
<s:if test="%{leasedEquipmentView.equipmentTypeText == 'Container'}"> 
    <div class="card mb-4">
    <div class="card-body">  
        <h5 class="card-title">Container Information</h5> 
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="size">Size</label>
                   <s:textfield id="size" name="leasedEquipmentView.containerSize"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />						
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="capacity">Capacity</label>
                   <s:textfield id="capacity" name="leasedEquipmentView.capacity"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />						
				</div>
			</div>

			<div class="col-sm-4">
				<div class="form-group">
					<label for="weightLimit">Weight Limit</label>
                   <s:textfield id="weight" name="leasedEquipmentView.weightLimit"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />						
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="containerDoors">Container Doors</label>
                   <s:textfield id="containerDoors" name="leasedEquipmentView.containerDoors"
		                     cssErrorClass="fieldError" cssClass="form-control"
		                     readonly="true" style="background-color: #e9ecef;" />						
				</div>
			</div>			
			
			
		</div>

</div>
</div>
</s:if>
<s:form id="leasedEquipmentForm" action="saveLeasedEquipmentAdditionalNote" namespace="/lease" theme="bootstrap">

    <div class="card mb-4">
    <div class="card-body">   
    <div class="row">
        <div class="col-sm-6">
            <div class="form-group">
                <label for="dateAddedToLease">Date Equipment Added To Lease</label>
                 <s:textfield type="date" format="yyyy-MM-dd" id="dateAddedToLease" name="leasedEquipmentView.dateAddedToLease" cssErrorClass="fieldError" cssClass="form-control" />
            </div>
        </div>
        <div class="col-sm-6">
            <div class="form-group">
                <label for="leaseID">Leased Equipment Instructions</label>
                <s:textfield id="specialNotes" name="leasedEquipmentView.notes" cssErrorClass="fieldError" cssClass="form-control"  />
            </div>
        </div>   
        

    </div>
    </div>
    </div>
    
    <s:hidden name="SaveActionType" />
    <div id="action-buttons" class="d-flex"> <!-- Action Buttons  -->
		<!-- Flat fields for EditLeaseAction -->
		<s:hidden name="leaseID" value="%{leasedEquipmentView.leaseID}"/>
		<s:hidden name="equipmentNumber" value="%{leasedEquipmentView.equipmentNumber}"/>
			
		<s:url var="saveNotesUrl" action="saveLeasedEquipmentAdditionalNote" namespace="/lease"/>
		<s:url var="removeUrl" action="removeEquipmentFromLease" namespace="/lease"/>
		<s:url var="editLeaseUrl" action="edit-lease" namespace="/lease"/>
		
        <input type="button" value="Save Notes" class="btn btn-primary mr-2"
               onclick="submitForm('${saveNotesUrl}')"/>
        <input type="button" value="Remove From Lease" class="btn btn-danger mr-2"
               onclick="submitForm('${removeUrl}')"/>
        <input type="button" value="Return to Lease" class="btn btn-secondary"
               onclick="submitForm('${editLeaseUrl}')"/>
    </div>  <!-- Action Buttons  End -->
</s:form>

<script>
function submitForm(actionUrl) {
    const form = document.getElementById('leasedEquipmentForm');
    form.action = actionUrl;   // Struts will resolve this against the /lease namespace
    form.submit();
}
</script>




</div>
</div> 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<script>
function GetEquipmentForLease() {
	var input = document.getElementById("addequipmentToLeaseInput").value;
	if(input.trim() !== "") {
		addEquipmentToLease();
	} else {
		alert("Please enter an equipment number.");
	}
}

</script>

