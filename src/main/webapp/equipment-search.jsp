<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search For a Trailer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<div class="container">
    <c:set var="caller" value="${param.caller}" />
    <c:set var="reservationId" value="${param.reservationId}" />
    <c:set var="reservationLineItemID" value="${param.reservationLineItemID}" />

    <!-- Header + Return aligned to same grid as filters -->
    <div class="row mt-4 mb-3 align-items-center">
        <div class="col-sm-10">
            <h1 class="mb-0">Equipment Search</h1>
        </div>
        <div class="col-sm-2 text-right">
            <c:if test="${not empty caller}">
                <button class="btn btn-secondary" onclick="returnToCaller()">Return</button>
            </c:if>
        </div>
    </div>

    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>

    <!-- Filters + Add Equipment (same right edge as Return) -->
    <div class="form-row">
        <div class="form-group col-sm-2">
            <div class="input-group">
                <input type="text" class="form-control" id="equipmentNumber"
                       placeholder="Equipment Number"
                       onchange="resetOtherFields('equipmentNumber'); searchEquipment();">
            </div>
        </div>

        <div class="form-group col-sm-2">
            <select id="equipmentType" name="equipmentType" class="form-control"
                    onchange="resetOtherFields('equipmentType'); fetchSubTypes()">
                <option value="">Equipment Type</option>
                <s:iterator value="codeValues.equipmentTypes" var="status">
                    <option value="<s:property value='#status.key'/>">
                        <s:property value="#status.value"/>
                    </option>
                </s:iterator>
            </select>
        </div>

        <div class="form-group col-sm-2">
            <select id="equipmentSubType" name="equipmentSubType" class="form-control"
                    onchange="searchEquipment();">
                <option value="">All SubTypes</option>
                <s:iterator value="codeValues.trailerTypes" var="status">
                    <option value="<s:property value='#status.key'/>">
                        <s:property value="#status.value"/>
                    </option>
                </s:iterator>
            </select>
        </div>

        <div class="form-group col-sm-2">
            <select id="availabilityStatusCode" name="availabilityStatusCode" class="form-control"
                    onchange="searchEquipment();">
                <option value="">All Availability</option>
                <s:iterator value="codeValues.availabilityStatuses" var="status">
                    <option value="<s:property value='#status.key'/>">
                        <s:property value="#status.value"/>
                    </option>
                </s:iterator>
            </select>
        </div>

        <div class="form-group col-sm-2">
            <select id="conditionStatusCode" name="conditionStatusCode" class="form-control"
                    onchange="searchEquipment();">
                <option value="">All Conditions</option>
                <s:iterator value="codeValues.conditionStatuses" var="status">
                    <option value="<s:property value='#status.key'/>">
                        <s:property value="#status.value"/>
                    </option>
                </s:iterator>
            </select>
        </div>

        <!-- This col-sm-2 aligns with header col-sm-2 -->
        <div class="form-group col-sm-2 text-right">
            <button id="createTrailer" class="btn btn-success" onclick="createEquipment()">Add Equipment</button>
        </div>
    </div>

    <table class="table mt-4">
        <thead>
        <tr>
            <th scope="col">Equipment Number</th>
            <th scope="col">Equipment SubType</th>
            <th scope="col">Availability</th>
            <th scope="col">Special Notes</th>
            <c:if test="${caller == 'RESERVE' || caller == 'LEASE'  || caller == 'SUBSTITUTE'}">
                <th scope="col">Action</th>
            </c:if>
        </tr>
        </thead>
        <tbody id="searchResults">
        <!-- Search results will be inserted here -->
        </tbody>
    </table>
</div>

<script>


function searchEquipment(e) {

    console.log($('#equipmentNumber').val());
    if (e) {
        e.preventDefault();
    }

    $.ajax({
        url: '/mcquaids/equipment/search',
        type: 'get',
        data: {
            equipmentNumber: $('#equipmentNumber').val(),
            equipmentType:   $('#equipmentType').val(),
            equipmentSubType: $('#equipmentSubType').val(),
            availabilityStatusCode: $('#availabilityStatusCode').val(),
            conditionStatusCode: $('#conditionStatusCode').val()
        },

        success: function(response) {
            var tbody = $('#searchResults');
            tbody.empty();

            if (response.actionErrors != null) {
                $('#errorMessage').text(response.actionErrors).show();
            } else {
                $('#errorMessage').hide();
                response.forEach(function(searchItem) {
                    var row = $('<tr>');
                    row.append($('<td>').html('<a href="/mcquaids/equipment/edit?equipmentNumber='
                        + searchItem.equipmentNumber + '">' + searchItem.equipmentNumber + '</a>'));
                    row.append($('<td>').text(searchItem.equipmentSubTypeText));
                    row.append($('<td>').text(searchItem.availabilityStatusText));
                    row.append($('<td>').text(searchItem.specialNotes));

                    var actionCell = buildActionCell(
                        searchItem.equipmentNumber,
                        '${caller}',
                        '${reservationId}',
                        '${leaseId}',
                        '${reservationLineItemID}',
                    );

                    if (actionCell) {
                        row.append(actionCell);
                    }
                    tbody.append(row);
                });
            }
        }
    });
}



function buildActionCell(equipmentNumber, caller, reservationId, leaseId, reservationLineItemID) {

    // Normalize caller to avoid null/undefined issues
    caller = (caller || "").trim();

    console.log("buildActionCell: caller=" + caller + 
                ", reservationId=" + reservationId + 
                ", leaseId=" + leaseId + 
                ", reservationLineItemID=" + reservationLineItemID);

    // RESERVE workflow
    if (caller === 'RESERVE') {
        return $('<td>').html(
            '<a class="btn btn-primary btn-sm" ' +
            'href="/mcquaids/reservation/edit-reservation?reservationID=' +
            reservationId + '&reservedEquipmentID=' + equipmentNumber + '">' +
            'Reserve</a>'
        );
    }

    // LEASE workflow
    if (caller === 'LEASE') {
        return $('<td>').html(
            '<a class="btn btn-primary btn-sm" ' +
            'href="/mcquaids/lease/edit-lease?leaseID=' +
            leaseId + '&leasedEquipmentID=' + equipmentNumber + '">' +
            'Add to Lease</a>'
        );
    }

    // SUBSTITUTE workflow
    if (caller === 'SUBSTITUTE') {
        return $('<td>').html(
            '<a class="btn btn-primary btn-sm" ' +
            'href="/mcquaids/reservation/substituteEquipment?' +
            'oldReservationLineItemID=' + reservationLineItemID +
            '&newEquipmentNumber=' + equipmentNumber + '">' +
            'Substitute</a>'
        );
    }

    // Default: no action
    return $('<td>').text('');
}


</script>

<script>
function resetOtherFields(changedElementId) {
    var ids = ['equipmentType', 'equipmentNumber', 'availabilityStatusCode', 'conditionStatusCode'];
    ids.forEach(function(id) {
        if (id !== changedElementId) {
            $('#' + id).val('');
        }

        if (changedElementId == 'equipmentType') {
            var tbody = $('#searchResults');
            tbody.empty();
        }
    });
}

function fetchSubTypes() {
    var equipmentType = $('#equipmentType').val();
    if (equipmentType) {
        $.ajax({
            url: '/mcquaids/equipment/fetchEquipmentSubTypes',
            type: 'get',
            data: {
                equipmentType: equipmentType
            },
            success: function(response) {
                if (response && typeof response === 'object') {
                    var subTypeSelect = $('#equipmentSubType');
                    subTypeSelect.empty();
                    subTypeSelect.append('<option value="">All SubTypes</option>');
                    $.each(response, function(key, value) {
                        subTypeSelect.append('<option value="' + key + '">' + value + '</option>');
                    });
                } else {
                    console.error('Invalid response format:', response);
                }
            },
            error: function(xhr, status, error) {
                console.error('Error fetching subtypes:', error);
            }
        });
    } else {
        $('#equipmentSubType').empty().append('<option value="">All SubTypes</option>');
    }
}
</script>

<script>
function createEquipment() {
    var equipmentType = document.getElementById("equipmentType").value;
    var url = '/mcquaids/equipment/createEquipment?equipmentType=' + encodeURIComponent(equipmentType);
    window.location.href = url;
    alert(url);
}
</script>

<script>
function returnToCaller() {
    const caller = '${caller}';
    const reservationId = '${reservationId}';
    const leaseId = '${leaseId}';

    if (caller === 'RESERVE') {
        window.location.href = '/mcquaids/reservation/edit-reservation?reservationID=' + reservationId;
        return;
    }

    if (caller === 'LEASE') {
        window.location.href = '/mcquaids/lease/edit-lease?leaseID=' + leaseId;
        return;
    }

    window.history.back();
}
</script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>