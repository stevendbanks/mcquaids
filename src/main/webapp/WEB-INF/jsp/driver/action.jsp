<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Action</title>

    <!-- Bootstrap 4.6.2 -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body class="bg-light">

<div class="container py-4">

    <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Action Details</h4>
        </div>

        <div class="card-body">

            <h5 class="card-title">
                <s:property value="action.actionType"/>
                - Equipment <s:property value="action.equipmentNumber"/>
                - <s:property value="action.reservationLineItemDTO.equipmentTypeText"/>;
                - <s:property value="action.reservationLineItemDTO.equipmentSubTypeText"/>;
                - <s:property value="action.reservationLineItemDTO.equipmentNotes"/>;
            </h5>

            <p class="mb-2">
                <strong>From:</strong>
                <s:property value="action.fromDisplay"/>
            </p>

            <p class="mb-2">
                <strong>To:</strong>
               <s:property value="action.toDisplay"/>
            </p>

            <p class="mb-2">
                <strong>Scheduled:</strong>
                <s:property value="action.scheduledDateTimeDisplay"/>
            </p>

            <p class="mb-3">
                <strong>Notes:</strong>
                <s:property value="action.notes"/>
            </p>
            
<!-- Struts Action Errors -->
<s:if test="hasActionErrors()">
    <div class="alert alert-danger" role="alert">
        <s:actionerror/>
    </div>
</s:if>
            
            
            

            <!-- Complete Action Form -->
            <s:form action="action-complete" method="post" cssClass="mt-3">
                <s:hidden name="id" value="%{id}"/>
                <s:hidden name="token" value="%{token}"/>

                <div class="form-group">
                    <label>Driver Notes (optional)</label>
                    <s:textarea name="notes" cssClass="form-control" rows="3"/>
                </div>

                <button type="submit" class="btn btn-success btn-lg btn-block">
                    Complete Action
                </button>
            </s:form>

        </div>
    </div>

</div>

<!-- Bootstrap 4 JS bundle -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>