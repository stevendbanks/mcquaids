/**
 * 
 */
package com.mcquaids.service.interfaces;

import java.util.List;

import com.mcquaids.model.Trailer;

/**
 * 
 */
public interface ITrailerService {
	
	  Trailer createBlankTrailer();
	
	  Trailer saveNewTrailer(Trailer pTrailer);
	  
	  Trailer editTrailer(String pTrailerNumber);

	  int saveTrailer(Trailer pTrailer);

	  List<Trailer> findByTrailerNumber(String pTrailerNumber);  

	  List<Trailer> findByTrailerType(String pTrailerType);  

	  List<Trailer> findByTrailerLeesse(String pTrailerLeesse);

	List<Trailer> queryTrailers(Integer pTrailerType, Integer pFlatbedTrailerType, Boolean isAvailable,
			Boolean needsServicing);

}
