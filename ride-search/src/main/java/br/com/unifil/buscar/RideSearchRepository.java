package br.com.unifil.buscar;

import java.util.List;

import org.bson.Document;

public interface RideSearchRepository {
	
	List<Document> searchRide(SearchQuery query);
}
