import { postForm } from "./movement-order.js";

export function initMovementOrderSwap() {
    const swapButton = document.querySelector("[data-swap-link-button]");
    if (!swapButton) {
        return;
    }

    swapButton.addEventListener("click", async () => {
        const orderId = swapButton.getAttribute("data-order-id");
        const lineAInput = document.querySelector("[data-swap-line-a]");
        const lineBInput = document.querySelector("[data-swap-line-b]");

        if (!lineAInput || !lineBInput) {
            console.warn("Swap inputs not found.");
            return;
        }

        const lineAId = lineAInput.value;
        const lineBId = lineBInput.value;

        await postForm("/movement/createSwapLink.action", {
            movementOrderId: orderId,
            lineAId,
            lineBId
        });
    });
}

window.initMovementOrderSwap = initMovementOrderSwap;
