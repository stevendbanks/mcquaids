export async function addEquipmentToLease(leaseId, equipmentId, reservationData) {
	
  const payload = {
    leaseId,
    equipmentId,
    reservationData
  };
  
  const response = await fetch('/AddEquipmentToLease.action', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });

  if (!response.ok) {
	throw new Error(`Failed to add equipment: ${response.statusText}`);

  }

  return response.json(); // returns leasedEquipmentView JSON

  }