package br.com.unifil.buscar.repositories;

import java.util.List;

import org.bson.Document;

import br.com.unifil.buscar.dto.SearchQuery;

public interface RideSearchRepository {
	
	/**
	 * Takes a {@link SearchQuery} as a parameter and performs a search within the
	 * database for any matches. It returns a {@link List} of {@link Document} that
	 * matches the search criteria.
	 * 
	 * @param query {@link SearchQuery}
	 * @return A List of {@link Document}
	 * @since 1.0
	 */
	
	List<Document> searchRide(SearchQuery query);
}
