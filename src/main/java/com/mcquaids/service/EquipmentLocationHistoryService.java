package com.mcquaids.service;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.EquipmentLocationHistoryDAO;
import com.mcquaids.model.EquipmentLocationHistory;

import java.util.List;

public class EquipmentLocationHistoryService {

    private final EquipmentLocationHistoryDAO locationDao;

    public EquipmentLocationHistoryService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate; 
        this.locationDao = new EquipmentLocationHistoryDAO(jdbcTemplate);
    }

    /**
     * Returns the currently open (active) location interval for a piece of equipment.
     * This represents where the equipment is right now.
     *
     * @param equipmentNumber the equipment identifier
     * @return the open EquipmentLocationHistory entry, or null if none exists
     */
    public EquipmentLocationHistory getCurrentLocation(int equipmentNumber) {
        return locationDao.findOpenLocation(equipmentNumber);
    }

    /**
     * Returns the full location history for a piece of equipment.
     *
     * @param equipmentNumber the equipment identifier
     * @return list of all historical location intervals
     */
    public List<EquipmentLocationHistory> getHistoryForEquipment(int equipmentNumber) {
        return locationDao.findHistoryForEquipment(equipmentNumber);
    }


    /**
     * Returns all location history entries associated with a reservation.
     *
     * @param reservationId the reservation identifier
     * @return list of location intervals tied to the reservation
     */
    public List<EquipmentLocationHistory> getHistoryForReservation(int reservationId) {
        return locationDao.findHistoryForReservation(reservationId);
    }
}