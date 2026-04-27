import { postForm } from "./movement-order.js";

export function initMovementOrderBulk() {
    const addButtons = document.querySelectorAll("[data-bulk-add-button]");
    const removeButtons = document.querySelectorAll("[data-bulk-remove-button]");

    addButtons.forEach(btn => {
        btn.addEventListener("click", async () => {
            const orderId = btn.getAttribute("data-order-id");
            const equipmentInput = document.querySelector("[data-bulk-equipment-number]");

            if (!equipmentInput) {
                console.warn("Bulk equipment input not found.");
                return;
            }

            const equipmentNumber = equipmentInput.value;

            await postForm("/movement/addEquipmentToBulkOrder.action", {
                movementOrderId: orderId,
                equipmentNumber
            });
        });
    });

    removeButtons.forEach(btn => {
        btn.addEventListener("click", async () => {
            const lineId = btn.getAttribute("data-line-id");

            await postForm("/movement/removeEquipmentFromBulkOrder.action", {
                lineId
            });
        });
    });
}

window.initMovementOrderBulk = initMovementOrderBulk;
