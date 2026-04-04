package com.mcquaids.service;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.EquipmentDAO;
import com.mcquaids.dao.EquipmentLocationHistoryDAO;
import com.mcquaids.dao.ReservationDAO;
import com.mcquaids.model.Equipment;
import com.mcquaids.model.EquipmentLocationHistory;
import com.mcquaids.model.SafetyStatus;

public class AvailabilityService {

        private final EquipmentDAO equipmentDAO;
        private final EquipmentLocationHistoryDAO locationHistoryDAO;
        private final ReservationDAO reservationDAO;

        public AvailabilityService() {
            JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
            this.equipmentDAO = new EquipmentDAO(jdbcTemplate);
            this.locationHistoryDAO = new EquipmentLocationHistoryDAO(jdbcTemplate); 
            this.reservationDAO = new ReservationDAO(jdbcTemplate); 
        }

    
    public void recalculate(Integer equipmentNumber) {

        Equipment equipment = equipmentDAO.findByEquipmentNumber(equipmentNumber);
        if (equipment == null) {
            return;
        }

        // 1. Safety status
        boolean safe = SafetyStatus.OK.equals(equipment.getSafetyStatusCode());

        // 2. Location
        EquipmentLocationHistory currentLocation =
                locationHistoryDAO.findOpenLocation(equipmentNumber);

        boolean atYard = currentLocation != null
                && "YARD".equals(currentLocation.getLocationType());

        // 3. Reservation state
        boolean onReservation = reservationDAO.isEquipmentOnActiveReservation(equipmentNumber);

        // 4. Compute availability
        boolean available = safe && atYard && !onReservation;

        // 5. Persist
        equipment.setAvailable(available);
        equipmentDAO.updateEquipment(equipment);
    }
}
