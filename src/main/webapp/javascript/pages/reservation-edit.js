import { extractJsonMessage, showActionMessage } from "../common/ui-messages.js";

// ------------------------------
// DOMContentLoaded: only event binding
// ------------------------------
document.addEventListener("DOMContentLoaded", function () {

    document.querySelectorAll('.toggle-properties').forEach(btn => {
        btn.addEventListener('click', function () {

            const row = this.closest('.line-item');
            const id = row.dataset.id;
            const type = row.dataset.type;
            const panel = document.getElementById('props-' + id);

            if (panel.style.display === 'none') {
                panel.style.display = '';
                loadPropertiesPanel(panel, type, id);
            } else {
                panel.style.display = 'none';
            }
        });
    });

});


// ------------------------------------------------------------
// Toggle Panels (Delivery + Secondary Delivery)
// ------------------------------------------------------------
document.addEventListener("DOMContentLoaded", function () {

    document.querySelectorAll(".delivery-toggle").forEach(function (btn) {

        btn.addEventListener("click", function () {
            const targetSelector = btn.getAttribute("data-target");
            const target = document.querySelector(targetSelector);

            if (!target) return;

            if (target.style.display === "none" || target.classList.contains("collapsed")) {
                target.style.display = "block";
                target.classList.remove("collapsed");
            } else {
                target.style.display = "none";
                target.classList.add("collapsed");
            }
        });

    });

});




document.addEventListener("DOMContentLoaded", function () {
    const row = document.getElementById("add-equipment-row");
    if (!row) return;

    const panel = document.getElementById("props-add");

    loadPropertiesPanel(panel, "equipment", "add");
    panel.style.display = "table-row";
});

// ------------------------------
// Global functions (visible to HTML onclick)
// ------------------------------

function expandAllDetails() {
    document.querySelectorAll('.line-item').forEach(row => {
        const id = row.dataset.id;
        const panel = document.querySelector(`#props-${id}`);
        if (panel) {
            loadPropertiesPanel(panel, row.dataset.type, id);
            panel.style.display = 'table-row';
        }
    });
}

function collapseAllDetails() {
    document.querySelectorAll('.properties-panel').forEach(panel => {
        panel.style.display = 'none';
    });

    document.querySelectorAll('.line-item, .properties-panel')
        .forEach(row => row.classList.remove('highlight-row', 'highlight-border'));
}

function loadPropertiesPanel(panel, type, id) {

    document.querySelectorAll('.line-item, .properties-panel')
        .forEach(row => row.classList.remove('highlight-row', 'highlight-border'));

    const mainRow = document.querySelector(`.line-item[data-id="${id}"]`);
    if (mainRow) {
        mainRow.classList.add('highlight-row', 'highlight-border');
    }

    const propsRow = document.getElementById(`props-${id}`);
    if (propsRow) {
        propsRow.classList.add('highlight-row');
    }

    const propsJson = mainRow.dataset.props || '{}';
    const props = JSON.parse(propsJson);

    const html = renderPropertiesFragment(props);

    panel.querySelector('td').innerHTML = html;
}

function renderPropertiesFragment(props) {
    let html = `
        <div class="spec-panel enhanced-spec-panel">
            <div class="spec-header enhanced-spec-header">Specifications</div>
            <div class="spec-grid enhanced-spec-grid">
    `;

    for (const [key, value] of Object.entries(props)) {
        html += `
            <div class="spec-item enhanced-spec-item">
                <span class="spec-key">${formatKey(key)}:</span>
                <span class="spec-value">${value}</span>
            </div>
        `;
    }

    html += `
            </div>
        </div>
    `;

    return html;
}

function formatKey(key) {
    return key
        .replace(/([a-z])([A-Z])/g, '$1 $2')
        .replace(/^./, c => c.toUpperCase());
}



async function addEquipmentToReservation() {
    console.log("addEquipmentToReservation called");

    const reservationID = document.getElementById("reservationID").value;
    const equipmentNumber = document.getElementById("selectedEquipmentInput").value;
    const equipmentNotes = document.getElementById("equipmentNotes").value;

    const payload = new URLSearchParams();
    payload.append("reservationID", reservationID);
    payload.append("equipmentNumber", equipmentNumber);
    payload.append("equipmentNotes", equipmentNotes);

    try {
        const response = await fetch("/mcquaids/reservation/AddEquipmentToReservation", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: payload.toString()
        });

        if (!response.ok) {
            console.error("Server error:", response.status);
            showActionMessage("Server error while adding equipment.", "danger");
            return;
        }

        const data = await response.json();

        // 1. Add the new line item to the table
        appendRequestedEquipmentRow(data.reservationLineItemDTO);

        // 2. Hide the Add Equipment card
        const card = document.getElementById("add-equipment-card");
        if (card) {
            card.style.display = "none";
        }

        // 3. Show success message using your existing alert system
        if (data.actionMessages && data.actionMessages.length > 0) {
            showActionMessage(data.actionMessages[0], "success");
        }

    } catch (err) {
        console.error("Fetch error:", err);
        showActionMessage("Network error while adding equipment.", "danger");
    }
}

function appendRequestedEquipmentRow(item) {
    const tbody = document.getElementById("reservationLineItemsTable");

    // --- 1. Create MAIN row ---
    const mainRow = document.createElement("tr");
    mainRow.classList.add("line-item");
    mainRow.dataset.id = item.reservationLineItemID;
    mainRow.dataset.type = item.equipmentTypeText;
    mainRow.dataset.props = item.equipmentPropertiesAsJson;

    mainRow.innerHTML = `
        <td>${item.equipmentNumber}</td>
        <td>${item.equipmentTypeText}</td>
        <td>${item.equipmentSubTypeText}</td>
        <td>${item.lineItemNotes || ""}</td>

        <td class="text-right">
            <button type="button" class="btn btn-sm btn-secondary toggle-properties">
                Details
            </button>
        </td>

        <td class="text-right">
            <div class="btn-group">
                <button type="button"
                        class="btn btn-sm btn-outline-dark dropdown-toggle"
                        data-toggle="dropdown">
                    Actions
                </button>

                <div class="dropdown-menu dropdown-menu-right">
                    <button class="dropdown-item"
                            type="button"
                            onclick="removeLineItem('${item.reservationLineItemID}')">
                        Remove
                    </button>

                    <button class="dropdown-item"
                            type="button"
                            onclick="substituteLineItem('${item.reservationLineItemID}')">
                        Substitute
                    </button>

                    <button class="dropdown-item"
                            type="button"
                            onclick="markReturned('${item.reservationLineItemID}')">
                        Mark Returned
                    </button>
                </div>
            </div>
        </td>
    `;

    // --- 2. Create PROPERTIES row ---
    const propsRow = document.createElement("tr");
    propsRow.id = `props-${item.reservationLineItemID}`;
    propsRow.classList.add("properties-panel");
    propsRow.style.display = "none";
    propsRow.innerHTML = `<td colspan="5"></td>`;

    // --- 3. Append both rows ---
    tbody.appendChild(mainRow);
    tbody.appendChild(propsRow);

    // --- 4. Re-bind the Details toggle ---
    mainRow.querySelector(".toggle-properties").addEventListener("click", function () {
        const panel = propsRow;
        if (panel.style.display === "none") {
            panel.style.display = "";
            loadPropertiesPanel(panel, item.equipmentTypeText, item.reservationLineItemID);
        } else {
            panel.style.display = "none";
        }
    });
}




// Section 2.0  this section is about removing line items, which is only allowed in Draft status. The server will enforce this as well, but we want to prevent the user from trying if they can't do it.
function removeLineItem(lineItemId) {
    const status = document.getElementById("reservationStatusCode").value;

    console.warn(`Attempting to remove line item ${lineItemId} with reservation status ${status}`);
    // Only allow removal in Draft status
    if (status !== "1001-01") {
        showActionMessage("You can only remove equipment when the reservation is in Draft status.", "danger");
        return;
    }

    sendRemoveRequest(lineItemId);
}

function sendRemoveRequest(lineItemId) {
    fetch("/mcquaids/reservation/removeEquipmentFromReservation", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "reservationLineItemID=" + encodeURIComponent(lineItemId)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Server returned an error");
        }
        return response.json();
    })
    .then(data => {
        const msg = extractJsonMessage(data);

        if (msg.type === "success") {
            removeLineItemRow(lineItemId);
        }

        showActionMessage(msg.text, msg.type);
    })
    .catch(error => {
        console.error("Remove failed:", error);
        showActionMessage("Unable to remove equipment at this time.", "danger");
    });
}

function removeLineItemRow(lineItemId) {
    // Remove main row
    const mainRow = document.querySelector(`tr.line-item[data-id="${lineItemId}"]`);
    if (mainRow) mainRow.remove();

    // Remove the expandable properties row
    const propsRow = document.getElementById("props-" + lineItemId);
    if (propsRow) propsRow.remove();
}

//  Section 3.0  Substitute line item - this is a more complex operation that will likely require a modal dialog to select the new equipment and confirm the change. The server will also need to handle this properly, ensuring that the substitution is valid and updating the reservation accordingly.
function substituteLineItem(lineItemId) {
    const status = document.getElementById("reservationStatusCode").value;

    // In DRAFT mode, substitution is unnecessary
    if (status === "1001-01") {
        showActionMessage(
            "In Draft mode, simply remove the item and add a new one.",
            "info"
        );
        return;
    }

    // Otherwise, launch substitute workflow
    window.location.href =
        `/mcquaids/equipment/index?caller=SUBSTITUTE` +
        `&reservationLineItemID=${lineItemId}`;
}

function markReturned(lineItemId) {
    console.warn("markReturned is not implemented yet. ID:", lineItemId);
    showActionMessage("Mark Returned is not implemented yet.", "info");
}


function DisplayCustomerSearch() {
    const params = buildReservationParams();
    window.location.href = '/mcquaids/customer/index?' + params.toString();
}
  
function DisplayEquipmentSearch() {
    const params = buildReservationParams();
    window.location.href = '/mcquaids/equipment/index?' + params.toString();
}  
  
function buildReservationParams() {
    const params = new URLSearchParams();

    // Identify the workflow
    params.set("caller", "RESERVE");

    // Helper to safely read field values
    function safeGet(id) {
        const el = document.getElementById(id);
        return el ? el.value : "";
    }

    // Reservation ID
    const reservationID = safeGet("reservationID");
    if (reservationID) {
        params.set("reservation.reservationID", reservationID);
    }

    // Reservation fields
    params.set("reservation.startDate", safeGet("reservationStartDate"));
    params.set("reservation.endDate", safeGet("reservationEndDate"));
    params.set("reservation.instructions", safeGet("instructions"));
    params.set("reservation.reservationStatusCode", safeGet("reservationStatusCode"));

    // Customer fields
    params.set("reservation.customerID", safeGet("customerID"));
    params.set("reservation.customer.fullName", safeGet("fullName"));

    return params;
}

    function safeGet(id) {
        const el = document.getElementById(id);
        return el ? el.value : "";
    }   

function createDispatchPlan() {
    const reservationId = document.getElementById("reservationID").value;
    window.location.href = `/mcquaids/reservation/createDispatchPlan.action?reservationID=${reservationId}`;
}



function openCustomerLookup() {
    console.info("Opening customer lookup modal");
    // Load the customer search index into the modal body
    // We add layout=none to ensure we don't get the header/footer inside the modal
    $('#customerModalBody').load('/mcquaids/customer/?isModal=true&layout=none', function() {
        $('#customerModal').modal('show');
    });
}

// Attach to window so it's globally visible
window.selectCustomerForReservation = function(id, name) {
    console.info("selectCustomerForReservation called with ID:", id, "Name:", name);
    
    // Update the fields on your reservation form
    $('#customerID').val(id); 
    $('#fullName').val(name); 
    
    // Close the modal 
    $('#customerModal').modal('hide');

    // Visual feedback
    $('#fullName').css('background-color', '#d4edda')
                 .delay(1000)
                 .queue(function(next){
                     $(this).css('background-color', '');
                     next();
                 });

    console.log("Customer selected and modal closed: " + name);
};

async function ajaxSaveCustomerFromModal() {
    console.log("ajaxSaveCustomerFromModal called");

    // 1. Grab the form from the modal
    const form = document.getElementById("customerForm"); 
    if (!form) return;

    // 2. Serialize form data automatically
    const formData = new FormData(form);
    const payload = new URLSearchParams(formData);

    try {
        const response = await fetch("/mcquaids/customer/ajaxSave", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: payload.toString()
        });

        if (!response.ok) {
            throw new Error(`Server responded with ${response.status}`);
        }

        const data = await response.json();

        if (data.status === "success") {
            // 3. Use the global function to update the main Reservation page
            if (window.selectCustomerForReservation) {
                window.selectCustomerForReservation(data.userID, data.fullName);
                // The select function already handles hiding the modal
            }
            
            showActionMessage("Customer created and assigned to reservation.", "success");
        } else {
            // Handle validation errors or logic errors from Java
            showActionMessage(data.message || "Error saving customer.", "danger");
        }

    } catch (err) {
        console.error("Fetch error:", err);
        showActionMessage("Network error while saving customer.", "danger");
    }
}




window.removeLineItem = removeLineItem;
window.substituteLineItem = substituteLineItem;
window.markReturned = markReturned;
window.expandAllDetails = expandAllDetails;
window.collapseAllDetails = collapseAllDetails;
window.DisplayCustomerSearch = DisplayCustomerSearch;
window.DisplayEquipmentSearch = DisplayEquipmentSearch;
window.addEquipmentToReservation = addEquipmentToReservation;
window.createDispatchPlan = createDispatchPlan; 

window.openCustomerLookup = openCustomerLookup;
window.ajaxSaveCustomerFromModal = ajaxSaveCustomerFromModal;


