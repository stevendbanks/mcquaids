// js/controllers/LeasedEquipmentController.js
import { attachEquipmentToLease } from '../services/LeasedEquipmentService.js';

export function setupAddEquipmentForm() {
  const form = document.getElementById('add-equipment-form');

  form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const leaseId = form.querySelector('#leaseId').value;
    const equipmentId = form.querySelector('#equipmentId').value;
    const reservationData = {
      startDate: form.querySelector('#startDate').value,
      endDate: form.querySelector('#endDate').value
    };

    try {
      const updatedLease = await attachEquipmentToLease(leaseId, equipmentId, reservationData);
      alert("Equipment added successfully!");
      console.log("Updated lease view:", updatedLease);
      // Update UI with updatedLease data
    } catch (err) {
      alert("Failed to add equipment. Please try again.");
    }
  });
}