import { postForm } from "./movement-order.js";

export function initMovementOrderLines() {
    document.querySelectorAll("[data-line-status-button]").forEach(btn => {
        btn.addEventListener("click", async () => {
            const lineId = btn.getAttribute("data-line-id");
            const status = btn.getAttribute("data-new-status");

            const data = await postForm("/movement/updateMovementOrderLineStatus.action", {
                lineId,
                status
            });

            if (data && data.success) {
                // Optionally refresh or update row UI
                // e.g., location.reload();
            }
        });
    });
}

window.initMovementOrderLines = initMovementOrderLines;
