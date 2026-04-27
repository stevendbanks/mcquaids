// dispatch-calendar.js
import { showActionMessage } from './ui-messages.js';

export function initDispatchCalendarButtons() {
    document.querySelectorAll('.push-to-calendar').forEach(btn => {
        btn.addEventListener('click', async () => {
            const id = btn.dataset.dispatchActionId;

            const response = await fetch(`/mcquaids/reservation/pushToCalendar?dispatchActionId=${id}`, {
                method: 'POST'
            });

            const data = await response.json();

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

    document.querySelectorAll('.remove-from-calendar').forEach(btn => {
        btn.addEventListener('click', async () => {
            const id = btn.dataset.dispatchActionId;

            const response = await fetch(`/mcquaids/reservation/removeFromCalendar?dispatchActionId=${id}`, {
                method: 'POST'
            });

            const data = await response.json();

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
    pushBtn.textContent = 'Push to Calendar';

    oldBtn.replaceWith(pushBtn);

    // Re-bind the click handler for the new button
    pushBtn.addEventListener('click', async () => {
        const response = await fetch(`/mcquaids/reservation/pushToCalendar?dispatchActionId=${id}`, {
            method: 'POST'
        });

        const data = await response.json();

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