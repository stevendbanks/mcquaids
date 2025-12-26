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
        <h1 class="mb-4">Equipment Information</h1>
        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>Equipment Type</th>
                    <th>Total Number</th>
                    <th>Available</th>
                    <th>Needs Maintenance</th>
                    <th>Good Condition</th>
                    <th>Fair Condition</th>
                    <th>Poor Condition</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="report" var="row">
                    <tr>
                            <s:if test="#row.EquipmentSubTypeText != null">
                                <td class="pl-4"><s:property value="#row.EquipmentSubTypeText" /></td>
                            </s:if>
                            <s:else>
                                <td><strong><s:property value="#row.EquipmentTypeText" /></strong></td>
                            </s:else>
                        <td><s:property value="#row.TotalCount" /></td>
                        <td><s:property value="#row.AvailableCount" /></td>
                        <td><s:property value="#row.MaintenanceCount" /></td>
                        <td><s:property value="#row.GoodConditionCount" /></td>
                        <td><s:property value="#row.FairConditionCount" /></td>
                        <td><s:property value="#row.PoorConditionCount" /></td>
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
