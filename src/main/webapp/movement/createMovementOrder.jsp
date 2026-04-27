<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container mt-4">

    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Create Movement Order</h3>
        <a href="/equipment/searchEquipment.action" class="btn btn-secondary">Back</a>
    </div>

    <!-- Movement Order Form -->
    <s:form action="createMovementOrder" namespace="/movement" method="post">

        <!-- Selected Equipment -->
        <div class="card mb-4">
            <div class="card-header bg-light">
                <strong>Selected Equipment</strong>
            </div>
            <div class="card-body p-0">
                <table class="table table-sm table-striped mb-0">
                    <thead>
                        <tr>
                            <th>Equipment #</th>
                            <th>Type</th>
                            <th>Subtype</th>
                            <th>Status</th>
                            <th>Current Location</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="selectedEquipment">
                            <tr>
                                <td><s:property value="equipmentNumber" /></td>
                                <td><s:property value="equipmentTypeText" /></td>
                                <td><s:property value="equipmentSubTypeText" /></td>
                                <td><s:property value="derivedAvailabilityStatus" /></td>
                                <td>
                                    <s:property value="locationType" /><br/>
                                    <s:property value="street" /><br/>
                                    <s:property value="city" />, 
                                    <s:property value="province" /><br/>
                                    <s:property value="country" />
                                </td>
                            </tr>

                            <!-- Hidden equipmentNumbers[] for POST -->
                            <s:hidden name="equipmentNumbers" value="%{equipmentNumber}" />
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Movement Details -->
        <div class="card">
            <div class="card-header bg-light">
                <strong>Movement Details</strong>
            </div>

            <div class="card-body">

                <div class="row mb-3">

                    <div class="form-group col-md-4">
                        <label class="form-label">Movement Type</label>
                        <s:select
                            name="movementOrderHeader.movementType"
                            list="@com.mcquaids.model.MovementOrderHeader$MovementType@values()"
                            cssClass="form-control"
                            required="true"
                        />
                    </div>

                    <div class="form-group col-md-4">
                        <label class="form-label">Priority</label>
                        <s:select
                            name="movementOrderHeader.priority"
                            list="@com.mcquaids.model.MovementOrderHeader$Priority@values()"
                            cssClass="form-control"
                            required="true"
                        />
                    </div>

                    <div class="form-group col-md-4">
                        <label class="form-label">Requested By</label>
                        <input type="text" class="form-control" value="%{#session.userName}" disabled />
                        <s:hidden name="movementOrderHeader.requestedBy" value="%{#session.userName}" />
                    </div>

                </div>

                <hr/>

                <div class="mb-3">
                    <label class="form-label">Reason Code</label>
                    <s:textfield name="movementOrderHeader.reasonCode" cssClass="form-control"/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Notes</label>
                    <s:textarea name="movementOrderHeader.notes" rows="3" cssClass="form-control"/>
                </div>

                <hr/>

                <!-- Target Location -->
                <h5 class="mb-3">Target Location</h5>

                <div class="row mb-3">
                    <div class="form-group col-md-4">
                        <label class="form-label">Location Type</label>
<s:select
    id="targetLocationType"
    name="movementOrderHeader.targetLocationType"
    list="@com.mcquaids.model.MovementOrderHeader$TargetLocationType@values()"
    listKey="name()"
    listValue="name()"
    cssClass="form-control"
/>

                    </div>
                </div>

                <!-- Yard Section -->
                <div id="yardSection" class="row mb-3">
                    <div class="form-group col-md-6">
                        <label class="form-label">Select Yard</label>
                        <s:select
                            name="movementOrderHeader.targetYardId"
                            list="yards"
                            listKey="yardId"
                            listValue="name"
                            cssClass="form-control"
                        />
                    </div>
                </div>

                <!-- Customer Site Section -->
                <div id="customerSection" class="row mb-3" style="display:none;">

                    <div class="form-group col-md-6">
                        <label class="form-label">Street</label>
                        <s:textfield name="movementOrderHeader.targetStreet" cssClass="form-control"/>
                    </div>

                    <div class="form-group col-md-3">
                        <label class="form-label">City</label>
                        <s:textfield name="movementOrderHeader.targetCity" cssClass="form-control"/>
                    </div>

                    <div class="form-group col-md-3">
                        <label class="form-label">Province</label>
                        <s:textfield name="movementOrderHeader.targetProvince" cssClass="form-control"/>
                    </div>

                    <div class="form-group col-md-3 mt-3">
                        <label class="form-label">Postal</label>
                        <s:textfield name="movementOrderHeader.targetPostal" cssClass="form-control"/>
                    </div>

                    <div class="form-group col-md-3 mt-3">
                        <label class="form-label">Country</label>
                        <s:textfield name="movementOrderHeader.targetCountry" cssClass="form-control" value="Canada"/>
                    </div>

                </div>

                <button class="btn btn-primary">Create Movement Order</button>

            </div>
        </div>

    </s:form>

    <hr/>

    <script>
        const typeSelect = document.getElementById("targetLocationType");
        const yardSection = document.getElementById("yardSection");
        const customerSection = document.getElementById("customerSection");

        function updateTargetVisibility() {
            const type = typeSelect.value;
            yardSection.style.display = (type === "ON_PREMISE") ? "flex" : "none";
            customerSection.style.display = (type === "CUSTOMER_SITE") ? "flex" : "none";
        }

        typeSelect.addEventListener("change", updateTargetVisibility);
        updateTargetVisibility();
    </script>

</div>
