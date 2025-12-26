package com.mcquaids.dao.interfaces;

import java.util.List;

import com.mcquaids.model.Forklift;

public interface IForkliftDAO extends IEquipmentDAO {
	
	Forklift createBlankForklift();
		
	Forklift saveNewTrailer(Forklift pForklift);
	  
	Forklift editTrailer(String pForkliftID);

	Forklift saveTrailer(Forklift pForklift);

	List<Forklift> findByForkliftID(String pForkliftID);  

	List<Forklift> findByForkliftType(String pForkliftType);  

	List<Forklift> findByForkliftLeesse(String pForkliftLeesse);	

}