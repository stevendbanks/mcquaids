<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<div class="container">
	<h1 class="mt-4 mb-4">
		<s:property value="title" />
	</h1>

<div class="card mb-4">
<div class="card-body">


	<div id="errorMessage" class="alert alert-danger" style="display: none;" >
	</div>

	<s:form action="save" method="post" namespace="/lease"
		theme="bootstrap">
		<s:hidden name="SaveActionType" />

		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="leaseID">Lease ID</label>
					<s:textfield id="leaseID" name="lease.leaseID" cssErrorClass="fieldError"
						cssClass="form-control" />
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
				    <label for="customerID">Customer ID</label>
				    <div class="input-group">
				        <s:textfield id="customerID" name="lease.customerID" cssErrorClass="fieldError" cssClass="form-control" />
				        <div class="input-group-apend">
				            <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#ModalCustomerSearch">
				                <i class="fa fa-search"></i>
				            </button>
				        </div>
				    </div>
				</div>
			</div>
			
			<div class="col-sm-4">
				<div class="form-group">
					<label for="leaseStatusCode">Lease Status</label>
					<s:select id="leaseStatusCode" name="lease.leaseStatusCode" 
		                 list="codeValues.leaseStatuses" listKey="key" listValue="value"
		                 headerKey=""
		                 headerValue="Select Lease Status"
		                  cssClass="form-control" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="leaseStartDate">Lease Start Date</label>
					<s:textfield type="date" name="lease.leaseStartDate" cssErrorClass="fieldError"
						cssClass="form-control" format="yyyy-MM-dd"/>
				</div>
			</div>

			<div class="col-sm-4">
				<div class="form-group">
					<label for="leaseEndDate">Lease End Date (Expected)</label>
					<s:textfield type="date"  name="lease.leaseEndDate" cssErrorClass="fieldError"
						cssClass="form-control" format="yyyy-MM-dd"/>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<label for="instructions">Instructions</label>
					<s:textarea name="lease.instructions" cssErrorClass="fieldError"
						cssClass="form-control" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="leaseSignDate">Lease Sign Date</label>
					<s:textfield type="date" name="lease.leaseSignDate" cssErrorClass="fieldError"
						cssClass="form-control" format="yyyy-MM-dd" />
				</div>

			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="leaseTerminationDate">Lease Termination Date</label>
					<s:textfield type="date" name="lease.leaseTerminationDate"
						cssErrorClass="fieldError" cssClass="form-control" format="yyyy-MM-dd"/>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="leaseTerminationReasonCode">Lease Termination
						Reason Code</label>
					<s:select id="leaseTerminationReasonCode" name="lease.leaseTerminationReasonCode" 
		                 list="codeValues.leaseTerminationReasonCodes" listKey="key" listValue="value"
		                 headerKey=""
		                 headerValue="Select Lease Termination Reason"
		                  cssClass="form-control" />						
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<s:submit value="Save" cssClass="btn btn-primary" />
			</div>
		</div>
	</s:form>
</div>
</div>
<div class="card mb-4">
<div class="card-body">
<h5 class="card-title">Equipment In Lease</h5>
<table id="equipmentTable" class="table mt-4">
    <thead>
        <tr>
            <th scope="col">Equipment Number</th>
            <th scope="col">Type</th>
            <th scope="col">SubType</th>
            <th scope="col">Equipment Notes for Lease</th>
        </tr>
    </thead>
    <tbody id="modalEquipmentSearchResults">
        <!-- Search results will be inserted here -->
        <s:iterator value="leasedEquipmentView">
            <tr>
                <td>
                    <s:a namespace="/lease" action="editLeasedEquipment">
                        <s:param name="leaseID" value="%{leaseID}" />
                        <s:param name="equipmentNumber" value="%{equipmentNumber}" />
                        <s:property value="equipmentNumber" />
                    </s:a>
                </td>
                <td><s:property value="equipmentTypeText" /></td>
                <td><s:property value="equipmentSubTypeText" /></td>
                <td><s:property value="notes" /></td>
            </tr>
        </s:iterator>
    </tbody>
</table>

<div id="modalErrorMessage" class="alert alert-danger mb-4" style="display: none;"></div>
<div class="input-group mt-3">
  <input type="text" class="form-control" id="addequipmentToLeaseInput"
         placeholder="Enter Equipment Number"
         onkeydown="if(event.keyCode == 13) GetEquipmentForLease()">
  <button class="btn btn-outline-primary" type="button" onclick="GetEquipmentForLease()">Add</button>
  <button class="btn btn-primary" type="button" onclick="GetEquipmentForLease()">Search</button>
</div>

</div> <!-- End Card-body -->
</div>   <!-- End Card -->
</div>   <!-- End Container -->


<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!--  Include Javascript for the Customer Search Modal Window. -->
<jsp:include page="ModalCustomerSearch.jsp" />

<jsp:include page="ModalEquipmentSearch.jsp" />


<script>
function GetEquipmentForLease() {
	var input = document.getElementById("addequipmentToLeaseInput").value;
	if(input.trim() !== "") {
		addEquipmentToLease();
	} else {
		var leaseID = document.getElementById("leaseID").value;
		window.location.href = '/mcquaids/reservation/?leaseID=' + leaseID;
	}
}

</script>

