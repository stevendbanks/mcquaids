// dispatch-calendar.js
import { showActionMessage } from './ui-messages.js';

export function initDispatchCalendarButtons() {

    // PUSH TO CALENDAR
    document.querySelectorAll('.push-to-calendar').forEach(btn => {
        btn.addEventListener('click', async () => {
            const id = btn.dataset.dispatchActionId;

            const originalHtml = btn.innerHTML;
            setLoading(btn);

            const response = await fetch(`/mcquaids/dispatch/pushToCalendar?dispatchActionId=${id}`, {
                method: 'POST'
            });

            const data = await response.json();
            clearLoading(btn, originalHtml);

            if (data.actionErrors?.length) {
                showActionMessage(data.actionErrors[0], 'danger');
                return;
            }

            // SUCCESS — swap button
            if (data.calendarEventLink) {
                swapPushWithView(btn, data.calendarEventLink);
            }

            if (data.actionMessages?.length) {
                showActionMessage(data.actionMessages[0], 'success');
            }
        });
    });

    // REMOVE FROM CALENDAR
    document.querySelectorAll('.remove-from-calendar').forEach(btn => {
        btn.addEventListener('click', async () => {
            const id = btn.dataset.dispatchActionId;

            const originalHtml = btn.innerHTML;
            setLoading(btn);

            const response = await fetch(`/mcquaids/dispatch/removeFromCalendar?dispatchActionId=${id}`, {
                method: 'POST'
            });

            const data = await response.json();
            clearLoading(btn, originalHtml);

            if (data.actionErrors?.length) {
                showActionMessage(data.actionErrors[0], 'danger');
                return;
            }

            // SUCCESS — swap back to Push
            swapViewOrRemoveWithPush(btn);

            if (data.actionMessages?.length) {
                showActionMessage(data.actionMessages[0], 'success');
            }
        });
    });
}

// ------------------------------------------------------------
// Button Swap Helpers
// ------------------------------------------------------------

function swapPushWithView(pushBtn, eventLink) {
    const viewBtn = document.createElement('a');
    viewBtn.href = eventLink;
    viewBtn.target = '_blank';
    viewBtn.className = 'btn btn-sm btn-outline-success';
    viewBtn.textContent = 'View Calendar';

    pushBtn.replaceWith(viewBtn);
}

function swapViewOrRemoveWithPush(oldBtn) {
    const id = oldBtn.dataset.dispatchActionId;

    const pushBtn = document.createElement('a');
    pushBtn.href = 'javascript:void(0);';
    pushBtn.className = 'btn btn-sm btn-outline-primary push-to-calendar';
    pushBtn.dataset.dispatchActionId = id;
    pushBtn.textContent = 'Push';

    oldBtn.replaceWith(pushBtn);

    // Re-bind the click handler for the new button
    pushBtn.addEventListener('click', async () => {
        const originalHtml = pushBtn.innerHTML;
        setLoading(pushBtn);

        const response = await fetch(`/mcquaids/dispatch/pushToCalendar?dispatchActionId=${id}`, {
            method: 'POST'
        });

        const data = await response.json();
        clearLoading(pushBtn, originalHtml);

        if (data.actionErrors?.length) {
            showActionMessage(data.actionErrors[0], 'danger');
            return;
        }

        if (data.calendarEventLink) {
            swapPushWithView(pushBtn, data.calendarEventLink);
        }

        if (data.actionMessages?.length) {
            showActionMessage(data.actionMessages[0], 'success');
        }
    });
}

// ------------------------------------------------------------
// Loading Spinner Helpers
// ------------------------------------------------------------

function setLoading(btn) {
    btn.disabled = true;
    btn.innerHTML = `
        <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
        Working...
    `;
}

function clearLoading(btn, originalHtml) {
    btn.disabled = false;
    btn.innerHTML = originalHtml;
}
