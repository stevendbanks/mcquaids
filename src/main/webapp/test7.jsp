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
        <h3>Review Bulk Movement Order</h3>
        <button class="btn btn-secondary">Back to Edit</button>
    </div>

    <!-- Summary Card -->
    <div class="card mb-4">
        <div class="card-header bg-light">
            <strong>Movement Summary</strong>
        </div>

        <div class="card-body">

            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Movement Type:</strong><br>
                    Reposition
                </div>
                <div class="col-md-4">
                    <strong>Priority:</strong><br>
                    Normal
                </div>
                <div class="col-md-4">
                    <strong>Requested By:</strong><br>
                    Dispatcher Name
                </div>
            </div>

            <hr>

            <!-- Movement Path -->
            <h5 class="mb-3">Movement Path</h5>

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

            <hr>

            <!-- Notes -->
            <div class="mb-3">
                <strong>Notes:</strong><br>
                Move all available dry vans and reefers to Charlottetown for weekend demand.
            </div>

        </div>
    </div>

    <!-- Equipment List -->
    <div class="card mb-4">
        <div class="card-header bg-light d-flex justify-content-between align-items-center">
            <strong>Equipment to Move (12)</strong>
            <button class="btn btn-sm btn-outline-primary">Add More Equipment</button>
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

                <tbody>

                    <!-- Example Row 1 -->
                    <tr>
                        <td>12345</td>
                        <td>Dry Van</td>
                        <td>Summerside Yard</td>
                        <td>Available</td>
                        <td>
                            <button class="btn btn-sm btn-outline-danger">Remove</button>
                        </td>
                    </tr>

                    <!-- Example Row 2 -->
                    <tr>
                        <td>55110</td>
                        <td>Flatbed</td>
                        <td>Summerside Yard</td>
                        <td>Available</td>
                        <td>
                            <button class="btn btn-sm btn-outline-danger">Remove</button>
                        </td>
                    </tr>

                    <!-- Example Row 3 -->
                    <tr>
                        <td>99812</td>
                        <td>Reefer</td>
                        <td>Summerside Yard</td>
                        <td>Available</td>
                        <td>
                            <button class="btn btn-sm btn-outline-danger">Remove</button>
                        </td>
                    </tr>

                    <!-- Additional rows would follow the same pattern -->

                </tbody>
            </table>
        </div>
    </div>

    <!-- Confirmation -->
    <div class="card mb-5">
        <div class="card-header bg-light">
            <strong>Confirm Movement Order</strong>
        </div>

        <div class="card-body">

            <p>Please review the details above. Once confirmed, a separate dispatch will be created for each equipment unit.</p>

            <div class="alert alert-warning">
                <strong>Note:</strong> After confirmation, dispatches will be generated and drivers can be assigned.
            </div>

            <div class="text-end">
                <button class="btn btn-secondary me-2">Back to Edit</button>
                <button class="btn btn-primary btn-lg">Confirm & Create Movement Order</button>
            </div>

        </div>
    </div>

</div>



</body>
</html>