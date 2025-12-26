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
    <s:form id="orderForm" action="saveOrder" namespace="/order" theme="bootstrap">
      <s:hidden name="SaveActionType" />
    <!-- Order Capture Card -->
    <div class="card mb-4">
      <div class="card-body">

        <div class="row">
          <div class="col-sm-6">
            <div class="form-group">
              <label for="orderItem">What (Item/Service Requested)</label>
              <s:textfield id="orderItem" name="orderView.orderItem" cssClass="form-control" placeholder="e.g., 2 new flatbed trailers"/>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="form-group">
              <label for="deliveryLocation">Where (Delivery/Service Location)</label>
              <s:textfield id="deliveryLocation" name="orderView.deliveryLocation" cssClass="form-control" placeholder="e.g., Walmart parking lot"/>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-6">
            <div class="form-group">
              <label for="contactPerson">Who (Contact Person)</label>
              <s:textfield id="contactPerson" name="orderView.contactPerson" cssClass="form-control" placeholder="e.g., Steven"/>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="form-group">
              <label for="contactNumber">Contact Details</label>
              <s:textfield id="contactNumber" name="orderView.contactNumber" cssClass="form-control" placeholder="e.g., 902-892-6464"/>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-6">
            <div class="form-group">
              <label for="deliveryDate">When (Date/Time Required)</label>
              <s:textfield type="date" format="yyyy-MM-dd" id="deliveryDate" name="orderView.deliveryDate" cssClass="form-control"/>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="form-group">
              <label for="specialInstructions">Special Instructions</label>
              <s:textarea id="specialInstructions" name="orderView.specialInstructions" cssClass="form-control" placeholder="Any conditions, preferences, or constraints..."/>
            </div>
          </div>
        </div>

      </div>
    </div>

    <!-- Action Buttons -->
      <s:url var="saveUrl" action="saveOrder" namespace="/order"/>
      <s:url var="cancelUrl" action="order-list" namespace="/order"/>

      <div id="action-buttons" class="d-flex">
        <input type="button" value="Save Order" class="btn btn-primary mr-2"
               onclick="submitForm('${saveUrl}')"/>
        <input type="button" value="Cancel" class="btn btn-secondary"
               onclick="submitForm('${cancelUrl}')"/>
      </div>
    </s:form>

    <script>
      function submitForm(actionUrl) {
        const form = document.getElementById('orderForm');
        form.action = actionUrl;
        form.submit();
      }
    </script>
  </div>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>