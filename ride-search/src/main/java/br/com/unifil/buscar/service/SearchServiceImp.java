package br.com.unifil.buscar.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.unifil.buscar.dto.SearchQuery;
import br.com.unifil.buscar.repositories.RideSearchRepository;

@Service(value = "service.v1")
public class SearchServiceImp implements RideSearchService {

	RideSearchRepository searchRepository;

	@Autowired
	public SearchServiceImp(@Qualifier("MongoDBRepository") RideSearchRepository searchRepository) {
		this.searchRepository = searchRepository;
	}

	@Override
	public List<Document> searchRide(SearchQuery query) throws IllegalArgumentException{
	
		return searchRepository.searchRide(query);
		
	}
}
