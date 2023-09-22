package br.com.unifil.buscar;

import java.util.List;

import com.google.gson.Gson;
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.Unit;

public record RideRecord(
		String destination,
		String origin,
		boolean alternatives,
		String arrivalTime,
		List<RouteRestriction> avoid,
		String departureTime,
		String language,
		List<String> mode,
		String region,
		TrafficModel trafficModel,
		List<TransitMode> transitMode,
		TransitRoutingPreference transitRoutingPreference,
		Unit units,
		List<String> waypoints,
		RideStatus rideStatus,
		String driverId,
		String userId,
		String rideID
		) {
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
