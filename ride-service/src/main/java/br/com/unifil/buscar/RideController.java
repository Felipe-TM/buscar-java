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

    @PostMapping(value = "api/v1/publish-ride", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> publishRide(@RequestBody Ride rideRequest) {

	try {
	    rideService.publishRide(rideRequest);
	    return ResponseEntity.status(HttpStatus.CREATED).body(rideRequest.getRideId());

	} catch (NullPointerException | IllegalArgumentException e) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
    }

    @GetMapping("api/v1/get-ride")
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

    @PostMapping("api/v1/cancel-ride")
    public ResponseEntity<String> cancelRide(@RequestBody String requestBody) {
	JsonObject jsonRequest = JsonParser.parseString(requestBody).getAsJsonObject();
	try {
	    rideService.cancelRide(jsonRequest.get("requesterId").getAsString(),
		    jsonRequest.get("rideId").getAsString());
	    return ResponseEntity.status(HttpStatus.OK).body(null);
	} catch (IllegalArgumentException e) {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
    }

    @PostMapping("api/v1/accept-passenger")
    public ResponseEntity<String> acceptPassenger(@RequestBody String requestBody) {
	JsonObject jsonRequest = JsonParser.parseString(requestBody).getAsJsonObject();
	try {
	    rideService.acceptPassenger(jsonRequest.get("driverId").getAsString(),
		    jsonRequest.get("rideId").getAsString(), jsonRequest.get("requestId").getAsString());
	    return ResponseEntity.status(HttpStatus.OK).body(null);
	} catch (IllegalArgumentException e) {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
    }

    @PostMapping("api/v1/enroll-to-ride")
    public ResponseEntity<String> enrollToRide(@RequestBody String requestBody) {
	JsonObject jsonRequest = JsonParser.parseString(requestBody).getAsJsonObject();

	try {
	    return ResponseEntity.status(HttpStatus.CREATED).body(rideService.enrollToRide(
		    jsonRequest.get("rideId").getAsString(), jsonRequest.get("passengerId").getAsString()));
	} catch (NullPointerException | IllegalArgumentException e) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
    }
}
