// Extracts Struts2 JSON messages in a consistent way
export function extractJsonMessage(data) {
    if (data.actionMessages && data.actionMessages.length > 0) {
        return { text: data.actionMessages[0], type: "success" };
    }

    if (data.actionErrors && data.actionErrors.length > 0) {
        return { text: data.actionErrors[0], type: "danger" };
    }

    return { text: "Operation completed.", type: "info" };
}

// Displays a Bootstrap-style alert message
export function showActionMessage(message, type = "success") {
    const msgBox = document.getElementById("errorMessage");

    if (!msgBox) {
        console.warn("showActionMessage: #errorMessage element not found.");
        return;
    }

    msgBox.classList.remove("alert-danger", "alert-success", "alert-info");
    msgBox.classList.add("alert-" + type);
    msgBox.textContent = message;
    msgBox.style.display = "block";

    setTimeout(() => {
        msgBox.style.display = "none";
    }, 4000);
}


window.showActionMessage = showActionMessage;