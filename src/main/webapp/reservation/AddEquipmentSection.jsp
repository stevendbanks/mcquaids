<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- Show full Add Equipment card ONLY when equipment is selected -->
<s:if test="%{reservedEquipment != null 
              && reservedEquipment.equipmentNumber != null 
              && !reservedEquipment.equipmentNumber.isEmpty()}">

    <div id="add-equipment-card" class="card mt-4 no-print">
        <div class="card-body">
            <h5 class="card-title">Add Equipment to Reservation</h5>

            <!-- Begin Struts Form -->
            <s:form action="AddEquipmentToReservation" method="post">

                <!-- Required by the action -->
                <s:hidden name="reservationID" value="%{reservationID}" />
                <s:hidden name="equipmentNumber"
                          id="selectedEquipmentInput"
                          value="%{reservedEquipment.equipmentNumber}" />

                <div class="form-row">
                    <div class="col-sm-2">
                        <label>Equipment Type</label>
                        <input type="text"
                               class="form-control"
                               readonly
                               value="<s:property value='reservedEquipment.equipmentTypeText'/>">
                    </div>

                    <div class="col-sm-2">
                        <label>SubType</label>
                        <input type="text"
                               class="form-control"
                               readonly
                               value="<s:property value='reservedEquipment.equipmentSubTypeText'/>">
                    </div>

                    <div class="col-sm-8">
                        <label for="equipmentNotes">Notes</label>
                        <s:textfield name="equipmentNotes"
                                     id="equipmentNotes"
                                     cssClass="form-control"
                                     placeholder="Optional" />
                    </div>
                </div>

                <!-- Specs panel -->
                <table class="w-100 mt-3">
                    <tbody>

                        <!-- Fake line-item row for JS renderer -->
                        <tr id="add-equipment-row"
                            class="line-item"
                            data-id="add"
                            data-type="equipment"
                            data-props='<s:property value="reservedEquipment.propertiesAsJson" escapeHtml="false"/>'
                            style="display:none;">
                        </tr>

                        <!-- Matching properties panel row -->
                        <tr id="props-add" class="properties-panel" style="display:none;">
                            <td colspan="5"></td>
                        </tr>

                    </tbody>
                </table>

                <!-- Footer with Save + Search buttons -->
                <div class="card-footer d-flex justify-content-between mt-3">
                	<button class="btn btn-success" type="button" onclick="addEquipmentToReservation()">
                        <i class="fa fa-plus"></i> Add Equipment
                    </button>
                </div>

            </s:form>
            <!-- End Struts Form -->

        </div>
    </div>

</s:if>
