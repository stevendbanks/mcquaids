/**
 * 
 */
export async function submitEquipmentEvent(payload) {
    try {
        const response = await fetch('/equipmentEvent.action', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        const data = await response.json();

        if (data.actionErrors?.length) {
            showActionMessage(data.actionErrors.join(', '), 'danger');
            return false;
        }

        if (data.actionMessages?.length) {
            showActionMessage(data.actionMessages.join(', '), 'success');
        }

        return true;

    } catch (err) {
        console.error('Error submitting equipment event:', err);
        showActionMessage('Unexpected error submitting event', 'danger');
        return false;
    }
}