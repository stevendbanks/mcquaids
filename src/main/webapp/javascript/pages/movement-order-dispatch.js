import { postForm } from "./movement-order.js";

export function initMovementOrderDispatch() {
    document.querySelectorAll("[data-assign-dispatch-button]").forEach(btn => {
        btn.addEventListener("click", async () => {
            const lineId = btn.getAttribute("data-line-id");
            const dispatchIdInput = document.querySelector(
                `[data-dispatch-input-for='${lineId}']`
            );

            if (!dispatchIdInput) {
                console.warn("Dispatch input not found for line", lineId);
                return;
            }

            const dispatchId = dispatchIdInput.value;

            await postForm("/movement/assignDispatchToLine.action", {
                lineId,
                dispatchId
            });
        });
    });
}

window.initMovementOrderDispatch = initMovementOrderDispatch;
