import { extractJsonMessage, showActionMessage } from "../ui-messages.js";

export async function postForm(url, payload) {
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams(payload)
        });

        const data = await response.json();
        const msg = extractJsonMessage(data);
        showActionMessage(msg.text, msg.type);

        return data;
    } catch (err) {
        console.error("Fetch error:", err);
        showActionMessage("Server error occurred.", "danger");
        return null;
    }
}

// Optional helper to attach click handlers
export function bindClick(selector, handler) {
    const el = document.querySelector(selector);
    if (el) {
        el.addEventListener("click", handler);
    }
}
