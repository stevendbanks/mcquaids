package com.mcquaids.dao.interfaces;

import java.util.List;

import com.mcquaids.model.Equipment;

public interface IEquipmentDAO {
	List<Equipment> findByEquipmentLeessedBy(String pEquipmentID);			

	Integer getAge();

}