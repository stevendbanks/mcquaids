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
    
    
    <s:form action="save" method="post" namespace="/equipment" theme="bootstrap">
		<s:hidden name="equipment.equipmentType" />
		<s:hidden name="equipmentSaveActionType" />
		<jsp:include page="equipment-subform.jsp">
		    <jsp:param name="formBeanName" value="shippingContainer"/>
		    <jsp:param name="editable" value="%{!editable}"/>
		</jsp:include>

    <div class="card mb-4">
    <div class="card-body">   
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="size">Size</label>
      			<s:select id="Size" name="equipment.properties['size']"  value="%{equipment.properties['size']}"
		                 list="codeValues.containerSizes" listKey="key" listValue="value"
		                 headerKey=""
		                 headerValue="Select Size"
		                  cssClass="form-control" 
		                  disabled="%{!editable}" />	                
                
                
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="Capacity">Capacity (Volume)</label>
            			<s:textfield id="Capacity" name="equipment.properties['Capacity']" value="%{equipment.properties['Capacity']}" disabled="%{!editable}" />
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="Weight">Weight Limit</label>
            <s:textfield id="Weight" name="equipment.properties['Weight']" value="%{equipment.properties['Weight']}" disabled="%{!editable}" />
        </div>
    </div>


    <div class="col-sm-4">
        <div class="form-group">
            <label for="Doors">Doors</label>
      			<s:select id="Doors" name="equipment.properties['Doors']"  value="%{equipment.properties['Doors']}"
		                 list="codeValues.containerDoors" listKey="key" listValue="value"
		                 headerKey=""
		                 headerValue="Select Door Type"
		                  cssClass="form-control" disabled="%{!editable}" />	            
            
            
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

 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
