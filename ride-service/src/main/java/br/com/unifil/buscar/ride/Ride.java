package br.com.unifil.buscar.ride;

import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.Unit;

public class Ride {

	@BsonIgnore
	private static final Unit DEFAULT_UNITS = Unit.METRIC;
	@BsonIgnore
	private static final TrafficModel DEFAULT_TRAFFIC_MODEL = TrafficModel.BEST_GUESS;
	@BsonIgnore
	private static final TransitRoutingPreference DEFAULT_ROUTING_PREFERENCE = TransitRoutingPreference.LESS_WALKING;
	@BsonIgnore
	private static final RideStatus DEFAULT_RIDE_STATUS = RideStatus.PENDING;

	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String rideId;
	private String destination;
	private String origin;
	private boolean alternatives;
	private String arrivalTime;
	private List<RouteRestriction> avoid;
	private String departureTime;
	private String language;
	private List<String> mode;
	private String region;
	private TrafficModel trafficModel = DEFAULT_TRAFFIC_MODEL;
	private List<TransitMode> transitMode;
	private TransitRoutingPreference transitRoutingPreference = DEFAULT_ROUTING_PREFERENCE;
	private Unit units = DEFAULT_UNITS;
	private RideStatus rideStatus = DEFAULT_RIDE_STATUS;
	private String driverId;
	private List<String> enrolledPassengers;
	private List<String> waypoints;

	public Ride() {
	}
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public boolean getAlternatives() {
		return alternatives;
	}

	public void setAlternatives(boolean alternatives) {
		this.alternatives = alternatives;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public List<RouteRestriction> getAvoid() {
		return avoid;
	}

	public void setAvoid(List<RouteRestriction> avoid) {
		this.avoid = avoid;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getMode() {
		return mode;
	}

	public void setMode(List<String> mode) {
		this.mode = mode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public TrafficModel getTrafficModel() {
		return trafficModel;
	}

	public void setTrafficModel(TrafficModel trafficModel) {
		this.trafficModel = trafficModel;
	}

	public List<TransitMode> getTransitMode() {
		return transitMode;
	}

	public void setTransitMode(List<TransitMode> transitMode) {
		this.transitMode = transitMode;
	}

	public TransitRoutingPreference getTransitRoutingPreference() {
		return transitRoutingPreference;
	}

	public void setTransitRoutingPreference(TransitRoutingPreference transitRoutingPreference) {
		this.transitRoutingPreference = transitRoutingPreference;
	}

	public Unit getUnits() {
		return units;
	}

	public void setUnits(Unit units) {
		this.units = units;
	}

	public List<String> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(List<String> waypoints) {
		this.waypoints = waypoints;
	}

	public RideStatus getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(RideStatus rideStatus) {
		this.rideStatus = rideStatus;
	}

	public String getDriverId() {
		return driverId;
	}

	public List<String> getEnrolledPassengers() {
		return enrolledPassengers;
	}

	public void setEnrolledPassengers(List<String> enrolledPassengers) {
		this.enrolledPassengers = enrolledPassengers;
	}

	public String getRideId() {
		return rideId;
	}	

	public void setRideId(String rideId) {
		this.rideId = rideId;
	}

	public void addPassenger(String passenger) {
		this.enrolledPassengers.add(passenger);
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	
}
