// js/services/LeasedEquipmentService.js
import { addEquipmentToLease } from '../api/leasedEquipmentApi.js';

export async function attachEquipmentToLease(leaseId, equipmentId, reservationData) {
  if (!leaseId || !equipmentId) {
    throw new Error("Lease ID and Equipment ID are required");
  }

  try {
    const result = await addEquipmentToLease(leaseId, equipmentId, reservationData);
    // Optionally transform or enrich result before returning
    return result;
  } catch (err) {
    console.error("Error attaching equipment:", err);
    throw err;
  }
}