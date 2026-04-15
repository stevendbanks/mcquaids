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

    <title>Equipment Reservation Ã¢ÂÂ Excel Style Mock</title>

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
        <h3>Swap Movement Order #MO-2041</h3>
        <button class="btn btn-secondary">Back to List</button>
    </div>

    <!-- Status Banner -->
    <div class="alert alert-info d-flex justify-content-between align-items-center">
        <div>
            <strong>Status:</strong> IN_PROGRESS  
            <span class="ms-3"><strong>Priority:</strong> Normal</span>
        </div>
        <button class="btn btn-sm btn-outline-light">Change Status</button>
    </div>

    <!-- Swap Summary -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Swap Summary</strong>
        </div>
        <div class="card-body">

            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Requested By:</strong><br>
                    Dispatcher Name
                </div>
                <div class="col-md-4">
                    <strong>Requested:</strong><br>
                    Apr 15, 2026 09:45
                </div>
                <div class="col-md-4">
                    <strong>Reason:</strong><br>
                    Customer Requested Swap (Walmart)
                </div>
            </div>

            <hr>

            <!-- Swap Diagram -->
            <div class="row text-center mb-3">
                <div class="col-md-5">
                    <h5>Equipment A</h5>
                    <p><strong>#12345 – Dry Van</strong></p>
                    <p>Current: Walmart Store #18<br>Charlottetown, PE</p>
                </div>

                <div class="col-md-2 d-flex align-items-center justify-content-center">
                    <h3>⇄</h3>
                </div>

                <div class="col-md-5">
                    <h5>Equipment B</h5>
                    <p><strong>#99812 – Reefer</strong></p>
                    <p>Current: Walmart DC #4<br>Summerside, PE</p>
                </div>
            </div>

            <div class="text-center">
                <span class="badge bg-primary">Swap In Progress</span>
            </div>

        </div>
    </div>

    <!-- Equipment A Details -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Equipment A – #12345</strong>
        </div>
        <div class="card-body">

            <div class="row mb-2">
                <div class="col-md-3"><strong>Type:</strong> Dry Van</div>
                <div class="col-md-3"><strong>Subtype:</strong> 53'</div>
                <div class="col-md-3"><strong>Status:</strong> Available</div>
                <div class="col-md-3"><strong>Driver:</strong> John Smith</div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <strong>From:</strong><br>
                    Walmart Store #18<br>
                    Charlottetown, PE
                </div>
                <div class="col-md-6">
                    <strong>To:</strong><br>
                    Walmart DC #4<br>
                    Summerside, PE
                </div>
            </div>

            <div class="row mb-2">
                <div class="col-md-4"><strong>Dispatch #:</strong> DP-8812</div>
                <div class="col-md-4"><strong>Status:</strong> <span class="badge bg-warning text-dark">IN_TRANSIT</span></div>
                <div class="col-md-4"><strong>Started:</strong> Apr 15, 2026 10:10</div>
            </div>

            <button class="btn btn-outline-primary btn-sm">View Dispatch</button>

        </div>
    </div>

    <!-- Equipment B Details -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Equipment B – #99812</strong>
        </div>
        <div class="card-body">

            <div class="row mb-2">
                <div class="col-md-3"><strong>Type:</strong> Reefer</div>
                <div class="col-md-3"><strong>Subtype:</strong> 48'</div>
                <div class="col-md-3"><strong>Status:</strong> Available</div>
                <div class="col-md-3"><strong>Driver:</strong> Sarah Lee</div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <strong>From:</strong><br>
                    Walmart DC #4<br>
                    Summerside, PE
                </div>
                <div class="col-md-6">
                    <strong>To:</strong><br>
                    Walmart Store #18<br>
                    Charlottetown, PE
                </div>
            </div>

            <div class="row mb-2">
                <div class="col-md-4"><strong>Dispatch #:</strong> DP-8813</div>
                <div class="col-md-4"><strong>Status:</strong> <span class="badge bg-secondary">ASSIGNED</span></div>
                <div class="col-md-4"><strong>Scheduled:</strong> Apr 15, 2026 10:30</div>
            </div>

            <button class="btn btn-outline-primary btn-sm">View Dispatch</button>

        </div>
    </div>

    <!-- Actions -->
    <div class="card">
        <div class="card-header bg-light">
            <strong>Actions</strong>
        </div>
        <div class="card-body">

            <button class="btn btn-success me-2">Mark Swap as Completed</button>
            <button class="btn btn-danger me-2">Cancel Swap</button>
            <button class="btn btn-outline-secondary">Add Note</button>

        </div>
    </div>

</div>


</body>
</html>