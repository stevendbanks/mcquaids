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

    <title>Equipment Reservation – Excel Style Mock</title>

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

    <div class="container">

        <h3 class="mb-4">Requested Equipment (Excel Style Layout)</h3>

<table class="table table-bordered table-sm">
    <thead>
        <tr>
            <th>Equipment Type</th>
            <th>SubType</th>
            <th>Quantity</th>
            <th>Notes</th>
            <th></th>
        </tr>
    </thead>

    <tbody>

        <!-- Main row -->
        <tr class="line-item">
            <td>Trailer</td>
            <td>Flatbed</td>
            <td>2</td>
            <td>Urgent</td>
            <td>
                <button class="btn btn-sm btn-secondary">Details</button>
            </td>
        </tr>

        <!-- Expandable details row -->
        <tr class="properties-panel" style="display:none;">
            <td colspan="5">
                <div class="row g-2">
                    <div class="col-3"><strong>Length:</strong> 20</div>
                    <div class="col-3"><strong>Width:</strong> 8</div>
                    <div class="col-3"><strong>Height:</strong> 8</div>
                    <div class="col-3"><strong>Axles:</strong> 2</div>
                    <div class="col-3"><strong>Deck Type:</strong> Steel</div>
                </div>
            </td>
        </tr>

        <!-- Another main row -->
        <tr class="line-item">
            <td>Forklift</td>
            <td>Electric</td>
            <td>1</td>
            <td>Indoor</td>
            <td>
                <button class="btn btn-sm btn-secondary">Details</button>
            </td>
        </tr>

        <!-- Forklift details -->
        <tr class="properties-panel" style="display:none;">
            <td colspan="5">
                <div class="row g-2">
                    <div class="col-3"><strong>Fuel Type:</strong> Electric</div>
                    <div class="col-3"><strong>Mast Height:</strong> 15 ft</div>
                    <div class="col-3"><strong>Capacity:</strong> 5000 lb</div>
                    <div class="col-3"><strong>Fork Length:</strong> 48 in</div>
                </div>
            </td>
        </tr>

    </tbody>
</table>
    </div>

    <!-- Bootstrap 5 JS (optional, only needed for components like modals) -->
    <script 
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
    </script>

</body>
</html>