<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="container">
<div style="margin-bottom: 20px;">
	<h1 class="mt-4 mb-4">
		<s:property value='equipmentTypeText'/>
	</h1>

	<s:if test="hasActionErrors()">
		<div id="errorMessage" class="alert alert-danger">
			<s:actionerror />
		</div> 
	</s:if>  

	<s:form action="save" method="post" namespace="/equipment"
		theme="bootstrap">
		<s:hidden name="equipment.equipmentType" />
		<s:hidden name="equipmentSaveActionType" />

		<jsp:include page="equipment-subform.jsp" />
		
    <div class="card mb-4">
    <div class="card-body">   
		<div class="row">
		
			<div class="col-sm-2">
				<div class="form-group">
					<label for="length">Trailer Length</label>
					<s:select id="length" name="equipment.properties['length']" 
					          list="codeValues.trailerLengths" listKey="key" listValue="value"
					          headerKey="" headerValue="Select TrailerLength"
					          cssClass="form-control" />
		                  
		                  
		                  				
				</div>
			</div>		
			<div class="col-sm-2">
				<div class="form-group">
					<label for="doorLocation">Door Location</label>
					<s:select id="doorLocation" name="equipment.properties['doorLocation']"  value="%{equipment.properties['doorLocation']}"
		                 list="codeValues.doorLocations" listKey="key" listValue="value"
		                 headerKey=""
		                 headerValue="Select Door Location"
		                  cssClass="form-control" />							
				</div>
			</div>

			<div class="col-sm-2">
				<div class="form-group">
					<label for="doorType">Door Type</label>
					<s:select id="doorType" name="equipment.properties['doorType']" 
		                 list="codeValues.doorTypes" listKey="key" listValue="value"
		                 headerKey=""
		                 headerValue="Select Door Type"
		                  cssClass="form-control" />						
				</div>
			</div>
			<div class="col-sm-2">
				<div class="form-group">
					<label for="floor">Floor</label>
					<s:select id="floor" name="equipment.properties['floor']" 
		                 list="codeValues.floorTypes" listKey="key" listValue="value"
		                 headerKey=""
		                 headerValue="Select Floor Type"
		                  cssClass="form-control" />							
				</div>
			</div>
			<div class="col-sm-2">
				<div class="form-group">
					<label for="axel">Axel</label>
					<s:select id="axel" name="equipment.properties['axel']" value="%{equipment.properties['axel']}"
		                 list="codeValues.axelTypes" listKey="key" listValue="value"
		                 headerKey=""
		                 headerValue="Select Axels"
		                  cssClass="form-control" />							
				</div>
			</div>
			<div class="col-sm-2">
				<div class="form-group">
					<label for="colour">Colour</label>
					<s:textfield name="equipment.properties['colour']" value="%{equipment.properties['colour']}" />
				</div>
			</div>			
			
		</div>

		<div class="row">

<div class="col-sm-3">
    <div class="form-group">
        <label for="Aligning the checkbox that follows">Insulation</label>
        <s:checkbox name="equipment.properties['insulated']"   cssErrorClass="fieldError" cssClass="form-control" label="Insulated"/>
    </div>
</div>

			<div class="col-sm-3">
				<div class="form-group">
					<label for="tieDown">Tie Down</label>
        			<s:checkbox name="equipment.properties['tieDown']"     cssErrorClass="fieldError" cssClass="form-control" label="Tie Down"/>
				</div>
			</div>
		</div>
</div>
</div>

		<!-- Add the rest of the fields in a similar manner -->
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<s:submit value="Save" cssClass="btn btn-primary" />
			</div>
		</div>
	</s:form>
</div>
</div>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

