<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <!-- Bootstrap 5 CSS -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
        rel="stylesheet"
    >

    <!-- Optional: Bootstrap Icons (if you want icons later) -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" 
        rel="stylesheet"
    >

    <title>Equipment Reservation Excel Style Mock</title>

    <style>
        /* Optional: Make the table feel more Excel-like */
        table.table-sm th, 
        table.table-sm td {
            padding: 0.35rem 0.5rem;
            white-space: nowrap;
        }

        thead th {
            background-color: #f1f3f5;
            font-weight: 600;
            border-bottom: 2px solid #dee2e6;
        }
    </style>
</head>

<body >

<div class="container mt-4">

    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Create Bulk Movement Order</h3>
        <button class="btn btn-secondary">Back to Movement Orders</button>
    </div>

    <!-- Movement Order Header -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Movement Details</strong>
        </div>

        <div class="card-body">

            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Movement Type</label>
                    <select class="form-select">
                        <option value="REPOSITION">Reposition</option>
                        <option value="INSPECTION">Move for Inspection</option>
                        <option value="CLEANING">Move for Cleaning</option>
                        <option value="MAINTENANCE">Move for Maintenance</option>
                        <option value="CUSTOMER_REQUEST">Customer Request</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Priority</label>
                    <select class="form-select">
                        <option value="NORMAL">Normal</option>
                        <option value="URGENT">Urgent</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Requested By</label>
                    <input type="text" class="form-control" value="Dispatcher Name">
                </div>
            </div>

            <hr>

            <!-- From / To -->
            <h5 class="mb-3">Movement Path</h5>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">From Location</label>
                    <select class="form-select">
                        <option>Summerside Yard</option>
                        <option>Charlottetown Yard</option>
                        <option>Moncton Yard</option>
                        <option>Customer Site (Enter Manually)</option>
                    </select>
                </div>

                <div class="col-md-6">
                    <label class="form-label">To Location</label>
                    <select class="form-select">
                        <option>Charlottetown Yard</option>
                        <option>Summerside Yard</option>
                        <option>Moncton Yard</option>
                        <option>Customer Site (Enter Manually)</option>
                    </select>
                </div>
            </div>

            <!-- Optional customer site address -->
            <div id="customerAddressSection" style="display:none;">
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label">Street</label>
                        <input type="text" class="form-control">
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">City</label>
                        <input type="text" class="form-control">
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Province</label>
                        <input type="text" class="form-control">
                    </div>
                    <div class="col-md-3 mt-3">
                        <label class="form-label">Postal</label>
                        <input type="text" class="form-control">
                    </div>
                    <div class="col-md-3 mt-3">
                        <label class="form-label">Country</label>
                        <input type="text" class="form-control" value="Canada">
                    </div>
                </div>
            </div>

            <hr>

            <!-- Notes -->
            <div class="mb-3">
                <label class="form-label">Notes</label>
                <textarea class="form-control" rows="3" placeholder="Optional instructions for drivers or dispatchers"></textarea>
            </div>

        </div>
    </div>

    <!-- Equipment Selection -->
    <div class="card mb-4">
        <div class="card-header bg-light d-flex justify-content-between align-items-center">
            <strong>Select Equipment</strong>
            <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#addEquipmentModal">
                Add Equipment
            </button>
        </div>

        <div class="card-body p-0">
            <table class="table table-striped mb-0">
                <thead class="table-light">
                    <tr>
                        <th>Equipment #</th>
                        <th>Type</th>
                        <th>Current Location</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="equipmentList">
                    <!-- Example row -->
                    <tr>
                        <td>12345</td>
                        <td>Dry Van</td>
                        <td>Summerside Yard</td>
                        <td>Available</td>
                        <td>
                            <button class="btn btn-sm btn-outline-danger">Remove</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Submit -->
    <div class="text-end mb-5">
        <button class="btn btn-primary btn-lg">Create Bulk Movement Order</button>
    </div>

</div>

<!-- Add Equipment Modal -->
<div class="modal fade" id="addEquipmentModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">Add Equipment to Movement Order</h5>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">

                <div class="row mb-3">
                    <div class="col-md-4">
                        <label class="form-label">Equipment #</label>
                        <input type="text" class="form-control" placeholder="Search by number">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Type</label>
                        <select class="form-select">
                            <option value="">All</option>
                            <option>Dry Van</option>
                            <option>Reefer</option>
                            <option>Flatbed</option>
                            <option>Container</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Location</label>
                        <select class="form-select">
                            <option value="">All</option>
                            <option>Summerside Yard</option>
                            <option>Charlottetown Yard</option>
                            <option>Moncton Yard</option>
                            <option>Customer Sites</option>
                        </select>
                    </div>
                </div>

                <table class="table table-hover">
                    <thead class="table-light">
                        <tr>
                            <th></th>
                            <th>Equipment #</th>
                            <th>Type</th>
                            <th>Location</th>
                            <th>Status</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>55110</td>
                            <td>Flatbed</td>
                            <td>Summerside Yard</td>
                            <td>Available</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>99812</td>
                            <td>Reefer</td>
                            <td>Summerside Yard</td>
                            <td>Available</td>
                        </tr>
                    </tbody>
                </table>

            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button class="btn btn-primary">Add Selected</button>
            </div>

        </div>
    </div>
</div>

<script>
    // Toggle customer site address fields (simple mockup logic)
    const fromSelect = document.querySelectorAll("select")[1];
    const toSelect = document.querySelectorAll("select")[2];
    const customerSection = document.getElementById("customerAddressSection");

    function toggleCustomerFields() {
        if (fromSelect.value.includes("Customer") || toSelect.value.includes("Customer")) {
            customerSection.style.display = "block";
        } else {
            customerSection.style.display = "none";
        }
    }

    fromSelect.addEventListener("change", toggleCustomerFields);
    toSelect.addEventListener("change", toggleCustomerFields);
</script>

</body>
</html>