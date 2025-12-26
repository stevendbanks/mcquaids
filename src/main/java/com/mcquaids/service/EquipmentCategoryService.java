package com.mcquaids.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.dao.EquipmentCategoryDao;
import com.mcquaids.model.EquipmentCategory;

public class EquipmentCategoryService {
    private EquipmentCategoryDao equipmentCategoryDao;
    private Map<String, String> errors = new HashMap<>();

    public EquipmentCategoryService(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.equipmentCategoryDao = new EquipmentCategoryDao(namedParameterJdbcTemplate);
    }

    public EquipmentCategoryService() {
		// TODO Auto-generated constructor stub
	}

	public EquipmentCategory edit(int categoryId) {
        EquipmentCategory equipmentCategory = equipmentCategoryDao.getEquipmentCategoryById(categoryId);
        return equipmentCategory;
    }

    public List<EquipmentCategory> queryEquipmentCategories() {
        return equipmentCategoryDao.getAllEquipmentCategories();
    }
    
    public List<EquipmentCategory> queryEquipmentCategories(EquipmentCategory equipmentCategory) {
        return equipmentCategoryDao.queryEquipmentCategories(equipmentCategory);
    }

    public void updateEquipmentCategory(EquipmentCategory equipmentCategory) {
        equipmentCategoryDao.saveEquipmentCategory(equipmentCategory);
    }

    public void saveNewEquipmentCategory(EquipmentCategory equipmentCategory) {
        equipmentCategoryDao.saveEquipmentCategory(equipmentCategory);
    }

    /**
     * @return the errors
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
