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
        <!-- Header Row 1 -->
        <tr>
            <th rowspan="2">Equipment Type</th>
            <th rowspan="2">SubType</th>
            <th rowspan="2">Quantity</th>
            <th rowspan="2">Notes</th>

            <!-- Trailer group -->
            <th colspan="5" class="text-center bg-light">Trailer Properties</th>

            <!-- Forklift group -->
            <th colspan="4" class="text-center bg-light">Forklift Properties</th>

            <!-- Container group -->
            <th colspan="5" class="text-center bg-light">Container Properties</th>
        </tr>

        <!-- Header Row 2 -->
        <tr>
            <!-- Trailer -->
            <th>Length</th>
            <th>Width</th>
            <th>Height</th>
            <th>Axles</th>
            <th>Deck Type</th>

            <!-- Forklift -->
            <th>Fuel Type</th>
            <th>Mast Height</th>
            <th>Capacity</th>
            <th>Fork Length</th>

            <!-- Container -->
            <th>Length</th>
            <th>Width</th>
            <th>Height</th>
            <th>Door Type</th>
            <th>Ventilation</th>
        </tr>
    </thead>

    <tbody>

        <!-- Trailer row -->
        <tr>
            <td>Trailer</td>
            <td>Flatbed</td>
            <td>2</td>
            <td>Urgent</td>

            <!-- Trailer props -->
            <td>20</td>
            <td>8</td>
            <td>8</td>
            <td>2</td>
            <td>Steel</td>

            <!-- Forklift props (blank) -->
            <td></td>
            <td></td>
            <td></td>
            <td></td>

            <!-- Container props (blank) -->
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>

        <!-- Forklift row -->
        <tr>
            <td>Forklift</td>
            <td>Electric</td>
            <td>1</td>
            <td>Indoor use</td>

            <!-- Trailer props (blank) -->
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>

            <!-- Forklift props -->
            <td>Electric</td>
            <td>15 ft</td>
            <td>5000 lb</td>
            <td>48 in</td>

            <!-- Container props (blank) -->
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>

        <!-- Container row -->
        <tr>
            <td>Container</td>
            <td>40ft</td>
            <td>3</td>
            <td></td>

            <!-- Trailer props (blank) -->
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>

            <!-- Forklift props (blank) -->
            <td></td>
            <td></td>
            <td></td>
            <td></td>

            <!-- Container props -->
            <td>40</td>
            <td>8</td>
            <td>8.5</td>
            <td>Double</td>
            <td>Passive</td>
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