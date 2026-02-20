// javascript/common/workflow.js

function safeGet(id) {
    const el = document.getElementById(id);
    return el ? el.value : "";
}

function buildReservationParams() {
    const params = new URLSearchParams();

    // Identify the workflow
    params.set("caller", "RESERVE");

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

    console.log("Built reservation params:", params.toString());


    return params;
}

function buildReturnParamsFromJson() {
    const params = new URLSearchParams();

    // Always include caller
    params.set("caller", "RESERVE");

    const json = document.getElementById("returnParams")?.value;
    if (!json) {
        console.warn("No returnParams found");
        return params;
    }

    let data;
    try {
        data = JSON.parse(json);
    } catch (e) {
        console.error("Invalid returnParams JSON", e);
        return params;
    }

    // Rebuild flat parameters
    for (const key in data) {
        if (data.hasOwnProperty(key)) {
            params.set(key, data[key] ?? "");
        }
    }

   // Force selector mode
    params.set("fromSelector", "true");

    return params;
}



function navigateToCustomerSearchOLD() {
    console.error("Navigating to customer search");
    const params = buildReservationParams();
    window.location.href = '/mcquaids/customer/index?' + params.toString();
}

function navigateToCustomerSearch() {
    console.log("Navigating to customer search");

    let params;

    // If returnParams exists → we are on a customer screen
    if (document.getElementById("returnParams")) {
        params = buildReturnParamsFromJson();
    } else {
        // Otherwise → we are on a reservation screen
        params = buildReservationParams();
    }

    window.location.href = '/mcquaids/customer/index?' + params.toString();
}



function navigateToEquipmentSearch() {
    const params = buildReservationParams();
    window.location.href = '/mcquaids/equipment/index?' + params.toString();
}



function navigateToCustomerCreate() {
    console.log("Navigating to customer create");
    const params = new URLSearchParams(window.location.search);
    window.location.href = '/mcquaids/customer/create?' + params.toString();
}


function navigateToReservation() {
    console.log("Navigating back to reservation");

    const params = buildReturnParamsFromJson(); // JSON → flat params

    // Determine whether to go to create or edit
    const reservationID = params.get("reservation.reservationID");
    const base = reservationID
        ? "/mcquaids/reservation/edit-reservation?"
        : "/mcquaids/reservation/create?";

    window.location.href = base + params.toString();
}

function navigateBackToReservation() {
    const params = new URLSearchParams(window.location.search);

    // Mark selector return
    params.set("fromSelector", "true");

    const reservationID = params.get("reservation.reservationID");

    const base = reservationID
        ? "/mcquaids/reservation/edit-reservation?"
        : "/mcquaids/reservation/create?";

    window.location.href = base + params.toString();
}

function navigateToCustomerSearchFromReservationSearch() {
    const params = new URLSearchParams();

    // Identify the workflow
    params.set("caller", "SEARCH_RESERVATIONS");

    // Build minimal JSON blob
    const json = JSON.stringify({
        caller: "SEARCH_RESERVATIONS"
    });

    params.set("returnParams", json);

    window.location.href = "/mcquaids/customer/index?" + params.toString();
}

function navigateBackToReservationSearch() {
    // Return to the Reservation Search UI (NOT the AJAX endpoint)
    window.location.href = "/mcquaids/reservation/";
}