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

    <title>Equipment Reservation â Excel Style Mock</title>

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

<body class="p-4">
<div class="container mt-4">

    <!-- Page Header -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Create Movement Order</h3>
        <button class="btn btn-secondary">Back to Equipment</button>
    </div>

    <!-- Equipment Summary Card -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Equipment Details</strong>
        </div>
        <div class="card-body">
            <div class="row mb-2">
                <div class="col-md-3"><strong>Equipment #:</strong> 12345</div>
                <div class="col-md-3"><strong>Type:</strong> Dry Van</div>
                <div class="col-md-3"><strong>Subtype:</strong> 53'</div>
                <div class="col-md-3"><strong>Status:</strong> Available</div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <strong>Current Location:</strong><br>
                    Customer Site – Walmart Store #18<br>
                    123 Retail Drive, Charlottetown, PE
                </div>
            </div>
        </div>
    </div>

    <!-- Movement Order Form -->
    <div class="card">
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
    // Simple toggle logic for mockup
    document.getElementById("targetLocationType").addEventListener("change", function() {
        const type = this.value;
        document.getElementById("yardSection").style.display = (type === "ON_PREMISE") ? "flex" : "none";
        document.getElementById("customerSection").style.display = (type === "CUSTOMER_SITE") ? "flex" : "none";
    });
</script>



</body>
</html>