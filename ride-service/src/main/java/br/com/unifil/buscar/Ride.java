package br.com.unifil.buscar;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.Unit;

public class Ride {

	private static final Unit DEFAULT_UNITS = Unit.METRIC;
	private static final TrafficModel DEFAULT_TRAFFIC_MODEL = TrafficModel.BEST_GUESS;
	private static final TransitRoutingPreference DEFAULT_ROUTING_PREFERENCE = TransitRoutingPreference.LESS_WALKING;
	private static final RideStatus DEFAULT_RIDE_STATUS = RideStatus.PENDING;

	private String destination;
	private String origin;
	private boolean alternatives;
	private String arrivalTime;
	private List<RouteRestriction> avoid;
	private String departureTime;
	private String language;
	private List<String> mode;
	private String region;
	private TrafficModel trafficModel;
	private List<TransitMode> transitMode;
	private TransitRoutingPreference transitRoutingPreference;
	private Unit units;
	private List<String> waypoints;

	private RideStatus rideStatus;
	private String driverId;
	private List<String> enrolledPassengers = new LinkedList<String>();
	private String rideId;
	
	public Ride() {
	    this.rideId = UUID.randomUUID().toString();
	}

	public Ride(Builder builder) {
		this.destination = builder.destination;
		this.origin = builder.origin;
		this.alternatives = builder.alternatives;
		this.arrivalTime = builder.arrivalTime;
		this.avoid = builder.avoid;
		this.departureTime = builder.departureTime;
		this.language = builder.language;
		this.mode = builder.mode;
		this.region = builder.region;
		this.trafficModel = builder.trafficModel;
		this.transitMode = builder.transitMode;
		this.transitRoutingPreference = builder.transitRoutingPreference;
		this.units = builder.units;
		this.waypoints = builder.waypoints;
		this.rideStatus = builder.rideStatus;
		this.driverId = builder.driverID;
		this.rideId = builder.rideID;
	}

	public static Builder builder() {
		return new Builder();
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

	public void setEnrolledPassengers(String enrolledUsers) {
		this.enrolledPassengers.add(enrolledUsers);
	}
	
	public String getRideId() {
		return rideId;
	}

	static class Builder {

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
		private List<String> waypoints;

		private RideStatus rideStatus = DEFAULT_RIDE_STATUS;
		private String driverID;
		private String rideID;

		public Builder destination(String destination) {
			this.destination = destination;
			return this;
		}

		public Builder origin(String origin) {
			this.origin = origin;
			return this;
		}

		public Builder alternatives(boolean alternatives) {
			this.alternatives = alternatives;
			return this;
		}

		public Builder arrivalTime(String arrivalTime) {
			this.arrivalTime = arrivalTime;
			return this;
		}

		public Builder avoid(RouteRestriction... avoid) {
			for (RouteRestriction routeRestriction : avoid) {
				this.avoid.add(routeRestriction);
			}
			return this;
		}

		public Builder departureTime(String departureTime) {
			this.departureTime = departureTime;
			return this;
		}

		public Builder language(String language) {
			this.language = language;
			return this;
		}

		public Builder mode(String... modes) {
			for (String mode : modes) {
				this.mode.add(mode);
			}
			return this;
		}

		public Builder region(String region) {
			this.region = region;
			return this;
		}

		public Builder trafficModel(TrafficModel trafficModel) {
			this.trafficModel = trafficModel;
			return this;
		}

		public Builder transitMode(TransitMode... transitModes) {
			for (TransitMode transitMode : transitModes) {
				this.transitMode.add(transitMode);
			}
			return this;
		}

		public Builder transitRoutingPreference(TransitRoutingPreference transitRoutingPreference) {
			this.transitRoutingPreference = transitRoutingPreference;
			return this;
		}

		public Builder units(Unit units) {
			this.units = units;
			return this;
		}

		public Builder waypoints(List<String> waypoints) {
			this.waypoints = waypoints;
			return this;
		}

		public Builder rideStatus(RideStatus rideStatus) {
			this.rideStatus = rideStatus;
			return this;
		}

		public Builder driverId(String driverID) {
			this.driverID = driverID;
			return this;
		}
		
		public Builder rideId(String rideID) {
			this.rideID = rideID;
			return this;
		}

		public Ride build() {
			return new Ride(this);
		}

	}
}
