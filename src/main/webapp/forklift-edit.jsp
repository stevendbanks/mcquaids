<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="container" style="margin-bottom: 20px;">
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



    
    <s:form action="save" method="post" namespace="/equipment" theme="bootstrap">
		<s:textfield name="equipment.equipmentType" />
		<s:hidden name="equipmentSaveActionType" />
		<jsp:include page="equipment-subform.jsp">
		    <jsp:param name="formBeanName" value="shippingContainer"/>
		</jsp:include>
    <div class="card mb-4">
    <div class="card-body">   
		<div class="row">
			<div class="col-sm-3">
				<div class="form-group">
					<label for="LoadCapacity">Load Capacity (lbs.)</label>
                    <s:textfield id="LoadCapacity" name="equipment.properties['LoadCapacity']" value="%{equipment.properties['LoadCapacity']}" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group">
					<label for="MaximumForkHeight">Maximum Fork Height (MFH)</label>
					<s:textfield id="MaximumForkHeight" name="equipment.properties['MaximumForkHeight']" value="%{equipment.properties['MaximumForkHeight']}" />
				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group">
					<label for="FreeLift">Free Lift (with Std. LBR)</label>
					<s:textfield id="FreeLift" name="equipment.properties['FreeLift']" value="%{equipment.properties['FreeLift']}" />
				</div>
			</div>

			<div class="col-sm-3">
				<div class="form-group">
					<label for="OverallWidth">Overall Width</label>
					<s:textfield id="OverallWidth" name="equipment.properties['OverallWidth']" value="%{equipment.properties['OverallWidth']}" />
				</div>
			</div>

			<div class="col-sm-3">
				<div class="form-group">
					<label for="MastLoweredHeight">Mast Lowered Height</label>
					<s:textfield id="MastLoweredHeight" name="equipment.properties['MastLoweredHeight']" value="%{equipment.properties['MastLoweredHeight']}" />

				</div>
			</div>
			<div class="col-sm-3">
				<div class="form-group">
					<label for="mastExtendedHeight">Mast Extended Height</label>
					<s:textfield   id="mastExtendedHeight" name="equipment.properties['mastExtendedHeight']" cssErrorClass="fieldError"
						cssClass="form-control" />
				</div>
			</div>

			<div class="col-sm-3">
				<div class="form-group">
					<label for="overheadGuardHeight">Overhead Guard Height</label>
					<s:textfield   id="overheadGuardHeight" name="equipment.properties['overheadGuardHeight']" cssErrorClass="fieldError"
						cssClass="form-control" />					
				</div>
			</div>


		</div>
</div>
</div>


		<!-- Add the rest of the fields in a similar manner -->
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<s:submit value="Submit" cssClass="btn btn-primary" />
			</div>
		</div>
	</s:form>
</div>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

