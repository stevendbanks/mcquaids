<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <!-- Bootstrap 5 CSS -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
        rel="stylesheet"
    >

    <title>Create Movement Order</title>

    <style>
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

<body class="p-4">
<div class="container mt-4">

    <!-- Page Header -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Create Movement Order</h3>
        <button class="btn btn-secondary">Back</button>
    </div>

    <!-- Equipment Selection -->
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
                    <!-- Repeat for each selected equipment -->
                    <tr>
                        <td>12345</td>
                        <td>Dry Van</td>
                        <td>53'</td>
                        <td>Available</td>
                        <td>
                            Customer Site – Walmart Store #18<br>
                            123 Retail Drive, Charlottetown, PE
                        </td>
                    </tr>

                    <tr>
                        <td>67890</td>
                        <td>Reefer</td>
                        <td>48'</td>
                        <td>Available</td>
                        <td>
                            Charlottetown Yard<br>
                            10 Transport Way, Charlottetown, PE
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>

    <!-- Movement Order Form -->
    <div class="card">
        <div class="card-header bg-light">
            <strong>Movement Details</strong>
        </div>

        <div class="card-body">

            <!-- Movement Type / Priority / Requested By -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Movement Type</label>
                    <select class="form-select">
                        <option value="REPOSITION">Reposition</option>
                        <option value="INSPECTION">Move for Inspection</option>
                        <option value="CLEANING">Move for Cleaning</option>
                        <option value="MAINTENANCE">Move for Maintenance</option>
                        <option value="CUSTOMER_REQUEST">Customer Request</option>
                        <option value="SWAP">Swap</option>
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

            <!-- Target Location -->
            <h5 class="mb-3">Target Location</h5>

            <div class="row mb-3">
                <div class="col-md-4">
                    <label class="form-label">Location Type</label>
                    <select class="form-select" id="targetLocationType">
                        <option value="ON_PREMISE">Yard</option>
                        <option value="CUSTOMER_SITE">Customer Site</option>
                    </select>
                </div>
            </div>

            <!-- Yard Selection -->
            <div id="yardSection" class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Select Yard</label>
                    <select class="form-select">
                        <option>Charlottetown Yard</option>
                        <option>Summerside Yard</option>
                        <option>Moncton Yard</option>
                    </select>
                </div>
            </div>

            <!-- Customer Site Address -->
            <div id="customerSection" class="row mb-3" style="display:none;">
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

            <hr>

            <!-- Notes -->
            <div class="mb-3">
                <label class="form-label">Notes</label>
                <textarea class="form-control" rows="3"></textarea>
            </div>

            <!-- Submit -->
            <button class="btn btn-primary">Create Movement Order</button>

        </div>
    </div>
</div>

<script>
    // Toggle Yard vs Customer Site
    document.getElementById("targetLocationType").addEventListener("change", function() {
        const type = this.value;
        document.getElementById("yardSection").style.display = (type === "ON_PREMISE") ? "flex" : "none";
        document.getElementById("customerSection").style.display = (type === "CUSTOMER_SITE") ? "flex" : "none";
    });
</script>

</body>
</html>
