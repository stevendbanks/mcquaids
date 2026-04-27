// ------------------------------------------------------------
// createMovementOrder.js
// Handles AJAX submission for creating a Movement Order
// ------------------------------------------------------------

// Make customer lookup available globally
window.openCustomerLookup = function () {
    console.info("Opening customer lookup modal");

    $('#customerModalBody').load('/mcquaids/customer/?isModal=true&layout=none', function () {
        $('#customerModal').modal('show');
    });
};


// Attach to window so it's globally visible
selectCustomerForReservation = function(id, name) {
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



// Listen for customer selection event
document.addEventListener("customerSelected", function (e) {
    const cust = e.detail;
    document.getElementById("customerName").value = cust.customerName;
    document.getElementById("customerID").value = cust.customerID;
    $('#customerModal').modal('hide');
});

// ------------------------------------------------------------
// MovementCreate module (classic IIFE, NOT ES module)
// ------------------------------------------------------------
window.MovementCreate = (function () {

    let submitUrl = "";
    let messageContainer = "";

    function init(config) {
        submitUrl = config.submitUrl;
        messageContainer = config.messageContainer;

        document
            .getElementById("btnCreateMovementOrder")
            .addEventListener("click", submitForm);
    }

    async function submitForm() {

        const form = document.getElementById("createMovementOrderForm");
        const formData = new FormData(form);

        try {
            const response = await fetch(submitUrl, {
                method: "POST",
                body: formData
            });

            const data = await response.json();

            const msg = extractJsonMessage(data);
            showActionMessage(msg.text, msg.type, messageContainer);

            if (msg.type === "success" && data.movementOrderId) {
                setTimeout(() => {
                    window.location.href =
                        `/movement/viewMovementOrder.action?movementOrderId=${data.movementOrderId}`;
                }, 800);
            }

        } catch (err) {
            showActionMessage(
                "Unexpected error creating movement order.",
                "danger",
                messageContainer
            );
        }
    }

    return {
        init
    };

})();
