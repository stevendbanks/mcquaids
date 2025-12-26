 <%@ taglib prefix="s" uri="/struts-tags"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <div class="container"  style=" margin-bottom: 20px;">
        <h1 class="mt-4 mb-4"><s:property value="title"/></h1>
        <s:form action="save" namespace="/customer">

		<s:hidden name="saveActionType" />
		
              <div class="form-group">
                <label for="userID">User ID</label>
                 <s:textfield id="userID" name="customer.userID" cssClass="form-control" theme="simple"/> 
            </div>               
              <div class="form-group">
                <label for="firstName">First Name</label>
                 <s:textfield id="firstName" name="customer.firstName" cssClass="form-control" theme="simple"/> 
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <s:textfield name="customer.lastName" cssClass="form-control" id="lastName" theme="simple" />
            </div>            
            <div class="form-group">
                <label for="street">Street</label>
                <s:textfield id="street" name="customer.street" cssClass="form-control"  theme="simple" />
            </div>
            <div class="form-group">
                <label for="city">City</label>
                <s:textfield  id="city" name="customer.city" cssClass="form-control" theme="simple" />
            </div>
            <div class="form-group">
                <label for="provinceState">Province/State</label>
				<s:select id="provinceState" name="customer.province" list="codeValues.provinces"
					listKey="key" listValue="value" cssClass="form-control"
					 headerKey="" headerValue="Select a Province" theme="simple"  />

		</div>
            
           <div class="form-group">
                <label for="country">Country</label>
                <s:textfield id="country" name="customer.country" cssClass="form-control" theme="simple" />
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <s:textfield  id="phone" name="customer.phone" cssClass="form-control" theme="simple" />
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <s:textfield id="email" name="customer.email" cssClass="form-control"   theme="simple" />
            </div>
             
            
            <div class="form-group">
            <s:submit value="Update" cssClass="btn btn-primary"/>
            </div>
        </s:form>
    </div>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>



