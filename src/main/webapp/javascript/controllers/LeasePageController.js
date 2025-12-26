// js/controllers/LeasePageController.js
import { setupAddEquipmentForm } from './LeasedEquipmentController.js';
import { searchEquipment, resetOtherFields, fetchSubTypes, reserve } from './EquipmentReservationController.js';

export function initLeasePage(leaseID) {
  // Initialize lease-related form logic
  // setupAddEquipmentForm();

  // Wire up reservation/search UI
  $('#equipmentNumber').on('change', () => {
    resetOtherFields('equipmentNumber');
    searchEquipment(null, leaseID);
  });

  $('#equipmentType').on('change', () => {
    resetOtherFields('equipmentType');
    fetchSubTypes();
  });

  $('#equipmentSubType, #availabilityStatusCode, #conditionStatusCode').on('change', () => {
    searchEquipment(null, leaseID);
  });

  // Expose reserve globally if needed for inline onclick
  window.reserve = (leaseID, equipmentNumber) => reserve(leaseID, equipmentNumber);
}