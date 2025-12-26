package com.mcquaids.service;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.EquipmentLocationDAO;
import com.mcquaids.model.EquipmentLocation;

public class EquipmentLocationService {

    private EquipmentLocationDAO equipmentLocationDAO;

    public EquipmentLocationService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.equipmentLocationDAO = new EquipmentLocationDAO(jdbcTemplate);
    }
    
    public void addEquipmentLocation(EquipmentLocation equipmentLocation) {
        equipmentLocationDAO.insertEquipmentLocation(equipmentLocation);
    }

    public EquipmentLocation getEquipmentsCurrentLocationByEquipmentNumber(String equipmentNumber) {
        return equipmentLocationDAO.findEquipmentsCurrentLocationByEquipmentNumber(equipmentNumber);
    }

}
