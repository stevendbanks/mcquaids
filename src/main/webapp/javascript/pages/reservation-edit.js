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
    console.error("addEquipmentToReservation called");

    const reservationID = document.getElementById("reservationID").value;
    const equipmentNumber = document.getElementById("selectedEquipmentInput").value;
    const equipmentQty = document.getElementById("equipmentQty").value;
    const equipmentNotes = document.getElementById("equipmentNotes").value;

    const payload = new URLSearchParams();
    payload.append("reservationID", reservationID);
    payload.append("equipmentNumber", equipmentNumber);
    payload.append("equipmentQty", equipmentQty);
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
            return;
        }

        const data = await response.json();

        // data.reservedEquipmentView contains the new line item
        appendRequestedEquipmentRow(data.reservedEquipmentView);

    } catch (err) {
        console.error("Fetch error:", err);
    }
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


window.removeLineItem = removeLineItem;
window.substituteLineItem = substituteLineItem;
window.markReturned = markReturned;
window.expandAllDetails = expandAllDetails;
window.collapseAllDetails = collapseAllDetails;
