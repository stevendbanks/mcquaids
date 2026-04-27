<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <!-- Bootstrap 5 CSS -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
        rel="stylesheet"
    >

    <!-- Bootstrap Icons -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" 
        rel="stylesheet"
    >

    <title>Movement Order Mockup</title>
</head>

<body>
<div class="container mt-4">

    <!-- Movement Order Header -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Movement Order #14</h3>
        <a href="/movement/listMovementOrders.action" class="btn btn-secondary">Back to List</a>
    </div>

    <!-- Movement Status / Priority -->
    <div class="alert alert-warning d-flex justify-content-between align-items-center">
        <div>
            <strong>Status:</strong> NEW
            <span class="ms-3"><strong>Priority:</strong> NORMAL</span>
        </div>
    </div>


    <!-- ===================== -->
    <!-- EQUIPMENT LINE #1     -->
    <!-- ===================== -->
    <div class="card mb-3 shadow-sm">
        <div class="card-body">

            <!-- Equipment Header -->
            <div class="d-flex justify-content-between align-items-center mb-2">
                <h5 class="mb-0">Equipment #12345</h5>
                <span class="badge bg-info text-dark">Line Status: ASSIGNED</span>
            </div>

            <!-- Equipment Details -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Type:</strong><br/>
                    Trailer / Flatbed
                </div>
                <div class="col-md-4">
                    <strong>Origin:</strong><br/>
                    Yard – Charlottetown Yard
                </div>
                <div class="col-md-4">
                    <strong>Destination:</strong><br/>
                    Customer Site – 123 Queen St, Charlottetown
                </div>
            </div>

            <hr/>

            <!-- Dispatch Summary -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Dispatch Status:</strong><br/>
                    <span class="badge bg-warning text-dark">ASSIGNED</span>
                </div>
                <div class="col-md-4">
                    <strong>Scheduled:</strong><br/>
                    Apr 22, 2026 14:00
                </div>
                <div class="col-md-4">
                    <strong>Completed:</strong><br/>
                    —
                </div>
            </div>

            <hr/>

            <!-- Actions -->
            <div class="d-flex flex-wrap gap-2">
                <a href="#" class="btn btn-primary btn-sm">
                    <i class="bi bi-calendar-plus"></i> Push to Calendar
                </a>

                <a href="#" class="btn btn-secondary btn-sm">
                    <i class="bi bi-calendar-event"></i> View Calendar
                </a>

                <a href="#" class="btn btn-warning btn-sm">
                    <i class="bi bi-pencil-square"></i> Edit Dispatch
                </a>

                <a href="#" class="btn btn-info btn-sm text-white">
                    <i class="bi bi-truck"></i> View Dispatch
                </a>
            </div>

        </div>
    </div>


    <!-- ===================== -->
    <!-- EQUIPMENT LINE #2     -->
    <!-- ===================== -->
    <div class="card mb-3 shadow-sm">
        <div class="card-body">

            <!-- Equipment Header -->
            <div class="d-flex justify-content-between align-items-center mb-2">
                <h5 class="mb-0">Equipment #67890</h5>
                <span class="badge bg-success">Line Status: COMPLETED</span>
            </div>

            <!-- Equipment Details -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Type:</strong><br/>
                    Forklift / Heavy Duty
                </div>
                <div class="col-md-4">
                    <strong>Origin:</strong><br/>
                    Customer Site – 88 Kensington Rd, Charlottetown
                </div>
                <div class="col-md-4">
                    <strong>Destination:</strong><br/>
                    Yard – Summerside Yard
                </div>
            </div>

            <hr/>

            <!-- Dispatch Summary -->
            <div class="row mb-3">
                <div class="col-md-4">
                    <strong>Dispatch Status:</strong><br/>
                    <span class="badge bg-success">COMPLETED</span>
                </div>
                <div class="col-md-4">
                    <strong>Scheduled:</strong><br/>
                    Apr 21, 2026 09:30
                </div>
                <div class="col-md-4">
                    <strong>Completed:</strong><br/>
                    Apr 21, 2026 11:10
                </div>
            </div>

            <hr/>

            <!-- Actions -->
            <div class="d-flex flex-wrap gap-2">
                <a href="#" class="btn btn-primary btn-sm">
                    <i class="bi bi-calendar-plus"></i> Push to Calendar
                </a>

                <a href="#" class="btn btn-secondary btn-sm">
                    <i class="bi bi-calendar-event"></i> View Calendar
                </a>

                <a href="#" class="btn btn-warning btn-sm">
                    <i class="bi bi-pencil-square"></i> Edit Dispatch
                </a>

                <a href="#" class="btn btn-info btn-sm text-white">
                    <i class="bi bi-truck"></i> View Dispatch
                </a>
            </div>

        </div>
    </div>

</div>
</body>
</html>
