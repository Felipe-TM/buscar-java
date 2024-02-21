package br.com.unifil.buscar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.unifil.buscar.dto.SearchQuery;
import br.com.unifil.buscar.service.RideSearchService;

/**
 * RideSearchController is responsible for exposing API endpoints.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

@Controller
public class RideSearchController {
	
	private RideSearchService searchService;

	@Autowired
	public RideSearchController(@Qualifier("service.v1") RideSearchService searchService) {
			this.searchService = searchService;
	}
	
	/**
	 * Retrieves a {@link SearchQuery} from the request body and performs a search within the
	 * database for any matches. It returns a String with a list of JSON objects that matches
	 * the search criteria.
	 * 
	 * 
	 * @param query {@link SearchQuery}
	 * @return {@link ResponseEntity}
	 * @since 1.0
	 */
	
	@GetMapping(value = "api/v1/search-ride", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> searchRide(@RequestBody SearchQuery query){
				
		return ResponseEntity.status(HttpStatus.OK).body(searchService.searchRide(query).toString());
	}
	
}
