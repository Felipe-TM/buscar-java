package br.com.unifil.buscar.service;

import java.util.List;

import org.bson.Document;

import br.com.unifil.buscar.dto.SearchQuery;

public interface RideSearchService {
	
	List<Document> searchRide(SearchQuery query) throws IllegalArgumentException;
}
