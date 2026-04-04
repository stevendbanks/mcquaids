import { showActionMessage } from '/mcquaids/javascript/common/ui-messages.js';

document.addEventListener('DOMContentLoaded', () => {

    // Radio buttons
    const useCustomerAddress = document.querySelector('#useCustomerAddress');
    const useAlternativeAddress = document.querySelector('#useAlternativeAddress');

    // Delivery address fields
    const deliveryStreet = document.querySelector('#deliveryStreet');
    const deliveryCity = document.querySelector('#deliveryCity');
    const deliveryProvince = document.querySelector('#deliveryProvince');
    const deliveryPostal = document.querySelector('#deliveryPostal');
    const deliveryCountry = document.querySelector('#deliveryCountry');

    // Save button
    const saveDeliveryAddressBtn = document.querySelector('#save-delivery-address-btn');

    // Hidden fields
    const reservationID = document.querySelector('#reservationID')?.value;
//    const customerId = document.querySelector('#customerID')?.value;

function getCustomerId() {
    return document.querySelector('#customerID')?.value;
}


    // -----------------------------
    // Load customer address (AJAX)
    // -----------------------------
    async function loadCustomerAddress() {
        try {
            console.warn("Loading customer address for customerID:", getCustomerId());

            const response = await fetch(`/mcquaids/customer/customerAddress?customerID=${getCustomerId()}`);
            const data = await response.json();

            if (data.actionErrors?.length) {
                showActionMessage(data.actionErrors.join(', '), 'danger');
                return;
            }

            const c = data.customer || {};

            deliveryStreet.value   = c.street     || '';
            deliveryCity.value     = c.city       || '';
            deliveryProvince.value = c.province   || '';
            deliveryPostal.value   = c.postalCode || '';
            deliveryCountry.value  = c.country    || '';

            if (data.actionMessages?.length) {
                showActionMessage(data.actionMessages.join(', '), 'success');
            }

        } catch (err) {
            console.error('Error loading customer address:', err);
            showActionMessage('Unable to load customer address', 'danger');
        }
    }

    // -----------------------------
    // Enable/Disable fields
    // -----------------------------
    function setFieldsDisabled(disabled) {
        deliveryStreet.readOnly = disabled;
        deliveryCity.readOnly = disabled;
        deliveryProvince.readOnly = disabled;
        deliveryPostal.readOnly = disabled;
        deliveryCountry.readOnly = disabled;
    }
window.setFieldsDisabled = setFieldsDisabled;


    // -----------------------------
    // Radio toggle behavior
    // -----------------------------
    useCustomerAddress?.addEventListener('change', async () => {
        if (useCustomerAddress.checked) {
            await loadCustomerAddress();
            setFieldsDisabled(true);
        }
    });

    useAlternativeAddress?.addEventListener('change', () => {
        if (useAlternativeAddress.checked) {
            setFieldsDisabled(false);
        }
    });

    // -----------------------------
    // Save Delivery Address
    // -----------------------------
saveDeliveryAddressBtn?.addEventListener("click", (e) => {
    e.preventDefault();
    saveDeliveryAddress();
});

    async function saveDeliveryAddress() {
        try {
            console.warn("SDBANKS-" + reservationID);
            const payload = {
                reservationID: reservationID, 
                deliveryAddressSource: useCustomerAddress.checked ? 'CUSTOMER' : 'ALTERNATIVE',
                street: deliveryStreet.value.trim(),
                city: deliveryCity.value.trim(),
                province: deliveryProvince.value.trim(),
                postalCode: deliveryPostal.value.trim(),
                country: deliveryCountry.value.trim()
            };

            const response = await fetch('/mcquaids/customer/saveDeliveryAddress', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            const data = await response.json();

            if (data.actionErrors?.length) {
                showActionMessage(data.actionErrors.join(', '), 'danger');
                return;
            }

            if (data.actionMessages?.length) {
                showActionMessage(data.actionMessages.join(', '), 'success');
            }

        } catch (err) {
            console.error('Error saving delivery address:', err);
            showActionMessage('Unexpected error saving delivery address', 'danger');
        }
    }

});