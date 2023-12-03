package br.com.unifil.buscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RideSearchController {
	
	private RideSearchService searchService;

	@Autowired
	public RideSearchController(@Qualifier("service.v1") RideSearchService searchService) {
			this.searchService = searchService;
	}
	
	@GetMapping(value = "api/v1/search-ride", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> searchRide(@RequestBody SearchQuery query){
				
		return ResponseEntity.status(HttpStatus.OK).body(searchService.searchRide(query).toString());
	}
	
}
