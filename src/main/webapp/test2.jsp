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

<body>

<div class="container mt-4">

    <!-- Page Header -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Movement Orders</h3>
        <button class="btn btn-primary">Create Movement Order</button>
    </div>

    <!-- Filters -->
    <div class="card mb-4">
        <div class="card-body">
            <form class="row g-3">

                <div class="col-md-3">
                    <label class="form-label">Status</label>
                    <select class="form-select">
                        <option value="">All</option>
                        <option>NEW</option>
                        <option>ASSIGNED</option>
                        <option>IN_TRANSIT</option>
                        <option>COMPLETED</option>
                        <option>CANCELLED</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label class="form-label">Movement Type</label>
                    <select class="form-select">
                        <option value="">All</option>
                        <option>Reposition</option>
                        <option>Inspection</option>
                        <option>Cleaning</option>
                        <option>Maintenance</option>
                        <option>Customer Request</option>
                        <option>Swap</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label class="form-label">Equipment #</label>
                    <input type="text" class="form-control" placeholder="12345">
                </div>

                <div class="col-md-3">
                    <label class="form-label">Driver</label>
                    <input type="text" class="form-control" placeholder="Driver name">
                </div>

                <div class="col-md-12 text-end">
                    <button class="btn btn-secondary">Clear</button>
                    <button class="btn btn-primary">Search</button>
                </div>

            </form>
        </div>
    </div>

    <!-- Movement Orders Table -->
    <div class="card">
        <div class="card-body p-0">

            <table class="table table-striped mb-0">
                <thead class="table-light">
                    <tr>
                        <th>Order #</th>
                        <th>Equipment</th>
                        <th>Type</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Driver</th>
                        <th>Status</th>
                        <th>Requested</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <!-- Example Row 1 -->
                    <tr>
                        <td>MO-1023</td>
                        <td>12345 (Dry Van)</td>
                        <td>Inspection</td>
                        <td>Walmart Store #18</td>
                        <td>Charlottetown Yard</td>
                        <td>John Smith</td>
                        <td><span class="badge bg-warning text-dark">IN_TRANSIT</span></td>
                        <td>Apr 15, 2026 09:12</td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary">View</button>
                        </td>
                    </tr>

                    <!-- Example Row 2 -->
                    <tr>
                        <td>MO-1024</td>
                        <td>99812 (Reefer)</td>
                        <td>Customer Request</td>
                        <td>Summerside Yard</td>
                        <td>Customer Site – ABC Foods</td>
                        <td>Unassigned</td>
                        <td><span class="badge bg-secondary">NEW</span></td>
                        <td>Apr 15, 2026 10:01</td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary">View</button>
                        </td>
                    </tr>

                    <!-- Example Row 3 -->
                    <tr>
                        <td>MO-1025</td>
                        <td>55110 (Flatbed)</td>
                        <td>Reposition</td>
                        <td>Moncton Yard</td>
                        <td>Charlottetown Yard</td>
                        <td>Sarah Lee</td>
                        <td><span class="badge bg-success">COMPLETED</span></td>
                        <td>Apr 14, 2026 14:22</td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary">View</button>
                        </td>
                    </tr>

                </tbody>
            </table>

        </div>
    </div>

</div>


</body>
</html>