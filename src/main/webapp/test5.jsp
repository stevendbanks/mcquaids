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

    <title>Equipment Reservation ÃÂ¢ÃÂÃÂ Excel Style Mock</title>

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
        <h3>Bulk Movement Order #MO-3107</h3>
        <button class="btn btn-secondary">Back to List</button>
    </div>

    <!-- Status Banner -->
    <div class="alert alert-primary d-flex justify-content-between align-items-center">
        <div>
            <strong>Status:</strong> IN_PROGRESS  
            <span class="ms-3"><strong>Priority:</strong> Normal</span>
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
                    <strong>Requested By:</strong><br>
                    Dispatcher Name
                </div>
                <div class="col-md-4">
                    <strong>Requested:</strong><br>
                    Apr 15, 2026 08:55
                </div>
                <div class="col-md-4">
                    <strong>Reason:</strong><br>
                    Yard Rebalancing (Summerside → Charlottetown)
                </div>
            </div>

            <hr>

            <div class="row mb-3">
                <div class="col-md-6">
                    <strong>From:</strong><br>
                    Summerside Yard<br>
                    200 Industrial Park Drive, Summerside, PE
                </div>
                <div class="col-md-6">
                    <strong>To:</strong><br>
                    Charlottetown Yard<br>
                    45 Industrial Road, Charlottetown, PE
                </div>
            </div>

            <div>
                <strong>Notes:</strong><br>
                Move all available dry vans and reefers to Charlottetown for weekend demand.
            </div>

        </div>
    </div>

    <!-- Equipment Lines -->
    <div class="card mb-4">
        <div class="card-header bg-light d-flex justify-content-between align-items-center">
            <strong>Equipment Lines (12)</strong>
            <button class="btn btn-sm btn-outline-primary">Add Equipment</button>
        </div>

        <div class="card-body p-0">
            <table class="table table-striped mb-0">
                <thead class="table-light">
                    <tr>
                        <th>Equipment #</th>
                        <th>Type</th>
                        <th>Current Status</th>
                        <th>Driver</th>
                        <th>Dispatch #</th>
                        <th>Dispatch Status</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>

                    <!-- Example Row 1 -->
                    <tr>
                        <td>12345</td>
                        <td>Dry Van</td>
                        <td>Available</td>
                        <td>John Smith</td>
                        <td>DP-9001</td>
                        <td><span class="badge bg-warning text-dark">IN_TRANSIT</span></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary">View Dispatch</button>
                        </td>
                    </tr>

                    <!-- Example Row 2 -->
                    <tr>
                        <td>55110</td>
                        <td>Flatbed</td>
                        <td>Available</td>
                        <td>Unassigned</td>
                        <td>DP-9002</td>
                        <td><span class="badge bg-secondary">NEW</span></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary">Assign Driver</button>
                        </td>
                    </tr>

                    <!-- Example Row 3 -->
                    <tr>
                        <td>99812</td>
                        <td>Reefer</td>
                        <td>Available</td>
                        <td>Sarah Lee</td>
                        <td>DP-9003</td>
                        <td><span class="badge bg-success">COMPLETED</span></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary">View Dispatch</button>
                        </td>
                    </tr>

                    <!-- Additional rows would follow the same pattern -->

                </tbody>
            </table>
        </div>
    </div>

    <!-- Bulk Actions -->
    <div class="card">
        <div class="card-header bg-light">
            <strong>Bulk Actions</strong>
        </div>
        <div class="card-body">

            <button class="btn btn-success me-2">Mark All Completed</button>
            <button class="btn btn-danger me-2">Cancel Entire Order</button>
            <button class="btn btn-outline-secondary">Add Note</button>

        </div>
    </div>

</div>


</body>
</html>