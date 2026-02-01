<div class="card mt-4 no-print">
    <div class="card-body">
        <h5 class="card-title">Add Equipment to Reservation xxx</h5>

        <!-- Hidden fields (kept exactly as you had them) -->
        <input type="hidden" id="equipmentTypeID">
        <input type="hidden" id="equipmentSubTypeID">

        <div class="form-row">
            <div class="col-sm-1">
                <label for="selectedEquipmentInput">Equip. ID</label>
                <input type="text" id="selectedEquipmentInput"
                       class="form-control" readonly>
            </div>

            <div class="col-sm-2">
                <label for="equipmentTypeText">Equipment Type</label>
                <input type="text" id="equipmentTypeText"
                       class="form-control" readonly>
            </div>

            <div class="col-sm-2">
                <label for="equipmentSubTypeText">SubType</label>
                <input type="text" id="equipmentSubTypeText"
                       class="form-control" readonly>
            </div>

            <div class="col-sm-2">
                <label for="equipmentQty">Quantity</label>
                <input type="number" id="equipmentQty"
                       class="form-control" min="1" value="1">
            </div>

            <div class="col-sm-5">
                <label for="equipmentNotes">Notes</label>
                <input type="text" id="equipmentNotes"
                       class="form-control" placeholder="Optional">
            </div>
        </div>
    </div>

    <!-- Unified action bar -->
    <div class="card-footer d-flex justify-content-between">
        <button class="btn btn-outline-primary"
                type="button"
                onclick="DisplayEquipmentSearch()">
            <i class="fa fa-search"></i> Search Equipment
        </button>

        <button class="btn btn-success"
                type="button"
                onclick="addEquipmentToReservation()">
            <i class="fa fa-plus"></i> Add Equipment
        </button>
    </div>
</div>