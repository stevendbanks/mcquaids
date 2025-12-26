 <%@ taglib prefix="s" uri="/struts-tags"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <div class="container  mt-5">
        <h1 class="mt-4 mb-4"><s:property value="title"/></h1>
        <h1 class="mb-4">Reports</h1>
        <div class="list-group">
            <a href="/mcquaids/reports/equipmentAvailabilityReport" class="list-group-item list-group-item-action">
                <h5 class="mb-1">Equipment Summary Report</h5>
                <p class="mb-1">Displays number of each type of Equipment, broken down by Equipment Subtype </p>
            </a>
            <a href="/mcquaids/reports/equipmentInspectionDueReport?daysToExpiry=30" class="list-group-item list-group-item-action">
                <h5 class="mb-1">Equipment Needing Inspection</h5>
                <p class="mb-1">Report lists all the equipment that will expire in the next 30 days or has already expired.</p>
            </a>
            <a href="#" class="list-group-item list-group-item-action">
                <h5 class="mb-1">Report 3</h5>
                <p class="mb-1">This is a brief description of Report 3. It covers the methodology and data sources used.</p>
            </a>
        </div>
    </div>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>