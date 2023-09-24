package br.com.unifil.buscar;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitRoutingPreference;

@RestController
public class RideController {

	private RideService rideService;

	@Autowired
	public RideController(RideService rideService) {
		this.rideService = rideService;
	}

	@PostMapping("api/v1/publishride")
	public ResponseEntity<String> publishRide(@RequestBody String rideDetails) {
		JsonObject jsonRequest = JsonParser.parseString(rideDetails).getAsJsonObject();

		try {

			Ride rideRequest = Ride.builder()
					.rideId(UUID.randomUUID().toString())
					.driverId(jsonRequest.get("driverId").getAsString())
					.origin(jsonRequest.get("origin").getAsString())
					.destination(jsonRequest.get("destination").getAsString())
					.arrivalTime(jsonRequest.get("arrivalTime").getAsString())
					.departureTime(jsonRequest.get("departureTime").getAsString())
					.language(jsonRequest.get("language").getAsString())
					.region(jsonRequest.get("region").getAsString())
					.build();

			if (jsonRequest.has("trafficModel"))
				rideRequest.setTrafficModel(TrafficModel.valueOf(jsonRequest.get("trafficModel").getAsString()));
			
			if (jsonRequest.has("transitRoutingPreference"))
				rideRequest.setTransitRoutingPreference(
						TransitRoutingPreference.valueOf(jsonRequest.get("transitRoutingPreference").getAsString()));
			
			rideService.publishRide(rideRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(rideRequest.getRideId());

		} catch (NullPointerException | IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("api/v1/getride")
	public ResponseEntity<String> getRide(@RequestBody String requestBody) {
		JsonObject jsonRequest = JsonParser.parseString(requestBody).getAsJsonObject();

		try {
			RideRecord ride = rideService.getRide(jsonRequest.get("requesterId").getAsString(),
					jsonRequest.get("rideID").getAsString());
			return ResponseEntity.status(HttpStatus.OK).body(ride.toJson());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@PostMapping("api/v1/cancelride")
	public ResponseEntity<String> cancelRide(@RequestBody String requestBody) {
		JsonObject jsonRequest = JsonParser.parseString(requestBody).getAsJsonObject();
		try {
			//rideService.cancelRide(requesterId, r);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@PostMapping("api/v1/acceptuser")
	public ResponseEntity<String> acceptRide(@RequestBody String requestBody) {
		JsonObject jsonRequest = JsonParser.parseString(requestBody).getAsJsonObject();
		try {
			rideService.acceptRide(jsonRequest.get("driverID").getAsString(), jsonRequest.get("rideID").getAsString());
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
	@PostMapping("api/v1/enrolltoride")
	public ResponseEntity<String> enrollToRide(@RequestBody String requestBody) {
		JsonObject jsonRequest = JsonParser.parseString(requestBody).getAsJsonObject();
		
		try {
			rideService.enrollUserToRide(jsonRequest.get("userId").getAsString(), jsonRequest.get("rideId").getAsString());
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (NullPointerException | IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}
