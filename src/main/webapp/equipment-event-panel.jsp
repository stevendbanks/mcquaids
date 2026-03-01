<!-- Equipment Event Panel -->
<div class="card mb-3" id="equipment-event-card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <span>Record Equipment Event</span>
        <button class="btn btn-sm btn-outline-secondary toggle-properties" 
                data-target="#equipment-event-body">
            Toggle
        </button>
    </div>

    <div class="card-body" id="equipment-event-body">

        <!-- Event Type -->
        <div class="form-group">
            <label for="eventType">Event Type</label>
            <select id="eventType" class="form-control">
                <option value="">-- Select Event Type --</option>
                <option value="INSPECTED">Inspection</option>
                <option value="DAMAGE_DISCOVERED">Damage Discovered</option>
                <option value="MAINTENANCE">Maintenance</option>
                <option value="PICKUP">Pickup</option>
                <option value="DROPOFF">Dropoff</option>
                <option value="TRANSFER">Transfer</option>
                <option value="RETURN">Return</option>
            </select>
        </div>

        <!-- Notes -->
        <div class="form-group">
            <label for="eventNotes">Notes</label>
            <textarea id="eventNotes" class="form-control" rows="2"></textarea>
        </div>

        <!-- Movement Fields -->
        <div id="movement-fields" style="display:none;">

            <h6 class="mt-3">From Location</h6>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <input id="fromStreet" class="form-control" placeholder="Street">
                </div>
                <div class="form-group col-md-3">
                    <input id="fromCity" class="form-control" placeholder="City">
                </div>
                <div class="form-group col-md-3">
                    <input id="fromProvince" class="form-control" placeholder="Province">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <input id="fromPostal" class="form-control" placeholder="Postal Code">
                </div>
                <div class="form-group col-md-8">
                    <input id="fromCountry" class="form-control" placeholder="Country">
                </div>
            </div>

            <h6 class="mt-3">To Location</h6>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <input id="toStreet" class="form-control" placeholder="Street">
                </div>
                <div class="form-group col-md-3">
                    <input id="toCity" class="form-control" placeholder="City">
                </div>
                <div class="form-group col-md-3">
                    <input id="toProvince" class="form-control" placeholder="Province">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <input id="toPostal" class="form-control" placeholder="Postal Code">
                </div>
                <div class="form-group col-md-8">
                    <input id="toCountry" class="form-control" placeholder="Country">
                </div>
            </div>

        </div>

        <!-- Submit -->
        <button id="submit-event-btn" class="btn btn-primary btn-block mt-3">
            Record Event
        </button>

    </div>
</div>