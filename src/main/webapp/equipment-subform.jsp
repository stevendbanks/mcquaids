<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

    <div class="card mb-4">
    <div class="card-body">   
<div class="row">
	<div class="col-sm-4">
		<div class="form-group">
			<label for="equipmentNumber">Equipment Number</label>
			<s:textfield name="equipment.equipmentNumber" 
				cssErrorClass="fieldError" cssClass="form-control"
				placeholder="Equipment #" disabled="%{!editable}" />
		</div>
	</div>
	<div class="col-sm-4">
		<div class="form-group">

			<label for="EquipmentSubType">Equipment SubType</label>
			<s:select id="EquipmentSubType" name="equipment.equipmentSubType" 
                 list="codeValues.EquipmentSubTypes" listKey="key" listValue="value"
                 headerKey=""
                 headerValue="Select Equipment SubType"
                  cssClass="form-control" disabled="%{!editable}"/>
		</div>
	</div>

	<div class="col-sm-4">
		<div class="form-group">
			<label for="serialNumber">Serial Number</label>
			<!-- Replace with your actual field -->
			<s:textfield   name="equipment.serialNumber" 
				cssErrorClass="fieldError" cssClass="form-control"
				placeholder="Enter Serial Number" disabled="%{!editable}"/>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-sm-4">
		<div class="form-group">
			<label for="manufacturer">Manufacturer</label>
			<s:textfield id="manufacturer"
			     name="equipment.manufacturer" 
			     format="yyyy-MM-dd" 
				cssClass="form-control" disabled="%{!editable}"/>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="form-group">
			<label for="manufacturedDate">Manufactured Date</label>
			<s:textfield type="date" id="manufacturedDateAsString"
			     name="equipment.manufacturedDateAsString" 
			      format="yyyy-MM-dd"
				cssClass="form-control"  onchange="isValidDate(this)" disabled="%{!editable}"/>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="form-group">
			<label for="inspectionDateAsString">Inspection Date</label>
			<s:textfield id="inspectionDateAsString" type="date"
			     name="equipment.inspectionDateAsString" 
			     format="yyyy-MM-dd"
			     cssErrorClass="fieldError"  cssClass="form-control" 
			     onchange="isValidDate(this)" disabled="%{!editable}"/>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-4">
		<div class="form-group">
			<label for="availabilityStatusCode">Availability</label>
			<s:select id="availabilityStatusCode" name="equipment.availabilityStatusCode" 
                 list="codeValues.availabilityStatuses" listKey="key" listValue="value"
                 headerKey=""
                 headerValue="Select Availability"
                  cssClass="form-control" disabled="%{!editable}" />
		</div>
	</div>
	<div class="col-sm-4">
		<div class="form-group">
			<label for="conditionStatusCode">Condition</label>
			<s:select id="conditionStatusCode" name="equipment.conditionStatusCode" 
                 list="codeValues.conditionStatuses" listKey="key" listValue="value"
                 headerKey=""
                 headerValue="Select Condition"
                  cssClass="form-control" disabled="%{!editable}"/>


		</div>
	</div>

	<div class="col-sm-4">
		<div class="form-group">
			<label for="serialNumber">Maintenance Status</label>
			<s:select id="maintenanceStatusCode" name="equipment.maintenanceStatusCode" 
                 list="codeValues.maintenanceStatuses" listKey="key" listValue="value"
                 headerKey=""
                 headerValue="Select Maintenance Status"
                  cssClass="form-control" disabled="%{!editable}" />
		</div>
	</div>
</div>


<div class="row">
	<div class="col-sm-6">
		<div class="form-group">
			<label for="purchasePrice">Purchase Price</label>
			<s:textfield id="purchasePrice"
			     name="equipment.purchasePrice" 
				cssClass="form-control" onchange="isValidDollarAmount(this)" disabled="%{!editable}"/>
		</div>
	</div>
	<div class="col-sm-6">
		<div class="form-group">
			<label for="purchaseDate">Purchased Date</label>
			<s:textfield id="purchaseDateAsString" type="date" 
			    name="equipment.purchaseDateAsString" 
			    format="yyyy-MM-dd"
				cssClass="form-control" 
				onchange="isValidDate(this)" disabled="%{!editable}"/>
		</div>
	</div>
</div>




<div class="form-group">
	<label for="specialNotes">Special Notes</label>
	<div class="row">
		<div class="col-sm-12">
			<s:textarea   name="equipment.specialNotes"
				cssErrorClass="fieldError" cssClass="form-control" disabled="%{!editable}"/>
		</div>
	</div>
</div>
</div>
</div>
