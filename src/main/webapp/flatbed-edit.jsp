<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="container" style="margin-bottom: 20px;">
	<h1 class="mt-4 mb-4">
		<s:property value="title" />
	</h1>

	<s:if test="hasActionErrors()">
		<div id="errorMessage" class="alert alert-danger">
			<s:actionerror />
		</div>
	</s:if>


	<s:form action="save" method="post" namespace="/equipment"
		theme="bootstrap">
		<s:textfield name="equipment.equipmentType" />
		<s:hidden name="equipmentSaveActionType" />
		
		<jsp:include page="equipment-subform.jsp">
		    <jsp:param name="formBeanName" value="flatbed"/>
		</jsp:include>

    <div class="card mb-4">
    <div class="card-body">   
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label for="size">Size</label>
					<s:textfield   id="size" name="equipment.properties['size']" cssErrorClass="fieldError"
						cssClass="form-control" />					
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="fuelType">Fuel Type</label>
					<s:textfield   id="fuelType" name="equipment.properties['fuelType']" cssErrorClass="fieldError"
						cssClass="form-control" />					
				</div>
			</div>

			<div class="col-sm-4">
				<div class="form-group">
					<label for="floor">Floor</label>
					<s:textfield   id="floor" name="equipment.properties['floor']" cssErrorClass="fieldError"
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

