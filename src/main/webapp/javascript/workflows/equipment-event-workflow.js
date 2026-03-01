/**
 * 
 */
import { submitEquipmentEvent } from '/js/services/equipment-events.js';

document.addEventListener('DOMContentLoaded', () => {

    const eventTypeSelect = document.querySelector('#eventType');
    const movementFields = document.querySelector('#movement-fields');
    const submitBtn = document.querySelector('#submit-event-btn');

    // Show/hide movement fields
    eventTypeSelect.addEventListener('change', () => {
        const type = eventTypeSelect.value;
        const isMovement = ['PICKUP', 'DROPOFF', 'TRANSFER', 'RETURN'].includes(type);
        movementFields.style.display = isMovement ? 'block' : 'none';
    });

    // Submit event
    submitBtn.addEventListener('click', async () => {

        const payload = {
            equipmentNumber: window.currentEquipmentNumber, // you already use this pattern
            eventType: eventTypeSelect.value,
            notes: document.querySelector('#eventNotes').value,
            reservationId: window.currentReservationId || null
        };

        // Add movement fields only when needed
        if (['PICKUP', 'DROPOFF', 'TRANSFER', 'RETURN'].includes(payload.eventType)) {
            payload.fromStreet = document.querySelector('#fromStreet').value;
            payload.fromCity = document.querySelector('#fromCity').value;
            payload.fromProvince = document.querySelector('#fromProvince').value;
            payload.fromPostal = document.querySelector('#fromPostal').value;
            payload.fromCountry = document.querySelector('#fromCountry').value;

            payload.toStreet = document.querySelector('#toStreet').value;
            payload.toCity = document.querySelector('#toCity').value;
            payload.toProvince = document.querySelector('#toProvince').value;
            payload.toPostal = document.querySelector('#toPostal').value;
            payload.toCountry = document.querySelector('#toCountry').value;
        }

        await submitEquipmentEvent(payload);
    });
});