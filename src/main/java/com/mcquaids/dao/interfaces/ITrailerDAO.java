package com.mcquaids.dao.interfaces;

import java.util.List;

import com.mcquaids.model.Trailer;

public interface ITrailerDAO extends IEquipmentDAO {
	
	  Trailer createBlankTrailer();
		
	  int saveNewTrailer(Trailer pTrailer);
	  
	  Trailer editTrailer(String pTrailerID);

	  int saveTrailer(Trailer pTrailer);

	  Trailer findByTrailerID(String pTrailerID);  

	  List<Trailer> findByTrailerType(String pTrailerType);  

}