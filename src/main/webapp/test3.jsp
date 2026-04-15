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

<body >
<div class="container mt-4">

    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Movement Order #MO-1023</h3>
        <button class="btn btn-secondary">Back to List</button>
    </div>

    <!-- Status Banner -->
    <div class="alert alert-warning d-flex justify-content-between align-items-center">
        <div>
            <strong>Status:</strong> IN_TRANSIT  
            <span class="ms-3"><strong>Priority:</strong> Urgent</span>
        </div>
        <button class="btn btn-sm btn-outline-light">Change Status</button>
    </div>

    <!-- Movement Summary -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Movement Summary</strong>
        </div>
        <div class="card-body">

            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Movement Type:</strong><br>
                    Inspection Move
                </div>
                <div class="col-md-4">
                    <strong>Requested By:</strong><br>
                    Dispatcher Name
                </div>
                <div class="col-md-4">
                    <strong>Requested:</strong><br>
                    Apr 15, 2026 09:12
                </div>
            </div>

            <hr>

            <div class="row mb-3">
                <div class="col-md-6">
                    <strong>From:</strong><br>
                    Walmart Store #18<br>
                    123 Retail Drive, Charlottetown, PE
                </div>
                <div class="col-md-6">
                    <strong>To:</strong><br>
                    Charlottetown Yard<br>
                    45 Industrial Road, Charlottetown, PE
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <strong>Notes:</strong><br>
                    Customer reported trailer door damage. Move to yard for inspection.
                </div>
            </div>

        </div>
    </div>

    <!-- Equipment Details -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Equipment</strong>
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
                    In Transit (Driver: John Smith)
                </div>
            </div>

        </div>
    </div>

    <!-- Dispatch Information -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Dispatch</strong>
        </div>
        <div class="card-body">

            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Dispatch #:</strong><br>
                    DP-7781
                </div>
                <div class="col-md-4">
                    <strong>Driver:</strong><br>
                    John Smith
                </div>
                <div class="col-md-4">
                    <strong>Scheduled:</strong><br>
                    Apr 15, 2026 10:00
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Started:</strong><br>
                    Apr 15, 2026 10:22
                </div>
                <div class="col-md-4">
                    <strong>Completed:</strong><br>
                    —
                </div>
                <div class="col-md-4">
                    <strong>Dispatch Status:</strong><br>
                    <span class="badge bg-warning text-dark">IN_TRANSIT</span>
                </div>
            </div>

            <button class="btn btn-primary">View Dispatch</button>

        </div>
    </div>

    <!-- Actions -->
    <div class="card">
        <div class="card-header bg-light">
            <strong>Actions</strong>
        </div>
        <div class="card-body">

            <button class="btn btn-success me-2">Mark as Completed</button>
            <button class="btn btn-danger me-2">Cancel Movement Order</button>
            <button class="btn btn-outline-secondary">Add Note</button>

        </div>
    </div>

</div>

</body>
</html>