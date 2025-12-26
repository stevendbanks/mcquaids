<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Equipment Information</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Equipment Inspection's Due Report</h1>
        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>Equipment Number</th>
                    <th>Equipment Type</th>
                    <th>Equipment SubType</th>
                    <th>Inspection Expire Date</th>
                    <th>Has Expired</th>
                    <th>Days Until Expiration</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="report" var="row">
                    <tr>
                        <td><s:property value="#row.EquipmentNumber" /></td>
                        <td><s:property value="#row.EquipmentTypeText" /></td>
                        <td><s:property value="#row.EquipmentSubTypeText" /></td>
                        <td><s:property value="#row.InspectionExpiryDate" /></td>
                        <td><s:property value="#row.InspectionStatus" /></td>
                        <td><s:property value="#row.DaysUntilExpiration" /></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
