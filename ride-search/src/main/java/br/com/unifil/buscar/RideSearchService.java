package br.com.unifil.buscar;

import java.util.List;

import org.bson.Document;

public interface RideSearchService {
	
	List<Document> searchRide(SearchQuery query) throws IllegalArgumentException;
}
