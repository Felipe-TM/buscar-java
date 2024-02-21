package br.com.unifil.buscar.service;

import java.util.List;

import org.bson.Document;

import br.com.unifil.buscar.dto.SearchQuery;


/**
 * 	RideSearchService provides methods to perform operations 
 *  in the repository layer.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * */
public interface RideSearchService {
	
	
	/**
	 * Takes a {@link SearchQuery} as a parameter and calls the repository
	 * to perform a search.
	 * 
	 * @param query {@link SearchQuery}
	 * @return A List of {@link Document}
	 * */
	List<Document> searchRide(SearchQuery query) throws IllegalArgumentException;
}
