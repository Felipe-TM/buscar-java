package br.com.unifil.buscar;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.Unit;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

	private static final Unit DEFAULT_UNITS = Unit.METRIC;
	private static final TrafficModel DEFAULT_TRAFFIC_MODEL = TrafficModel.BEST_GUESS;
	private static final TransitRoutingPreference DEFAULT_TRANSIT_ROUTING_PREFERENCE = TransitRoutingPreference.LESS_WALKING;

	private String destination;
	private String origin;
	private boolean alternatives;
	@JsonAlias("arrival_time")
	private String arrivalTime;
	private List<RouteRestriction> avoid;
	@JsonAlias("departure_time")
	private String departureTime;
	private String language;
	private List<String> mode;
	private String region;
	@JsonAlias("traffic_model")
	private TrafficModel trafficModel;
	@JsonAlias("transit_mode")
	private List<TransitMode> transitMode;
	@JsonAlias("transit_routing_preference")
	private TransitRoutingPreference transitRoutingPreference;
	private Unit units;
	private List<String> waypoints;

	public Route(Builder builder) {
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
	}

	public static Builder builder() {
		return new Builder();
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
		private TransitRoutingPreference transitRoutingPreference = DEFAULT_TRANSIT_ROUTING_PREFERENCE;
		private Unit units = DEFAULT_UNITS;
		private List<String> waypoints;

		public void destination(String destination) {
			this.destination = destination;
		}

		public void origin(String origin) {
			this.origin = origin;
		}

		public void alternatives(boolean alternatives) {
			this.alternatives = alternatives;
		}

		public void arrivalTime(String arrivalTime) {
			this.arrivalTime = arrivalTime;
		}

		public void avoid(RouteRestriction... avoid) {
			for (RouteRestriction routeRestriction : avoid) {
				this.avoid.add(routeRestriction);
			}
		}

		public void departureTime(String departureTime) {
			this.departureTime = departureTime;
		}

		public void language(String language) {
			this.language = language;
		}

		public void mode(String... modes) {
			for (String mode : modes) {
				this.mode.add(mode);
			}
		}

		public void region(String region) {
			this.region = region;
		}

		public void trafficModel(TrafficModel trafficModel) {
			this.trafficModel = trafficModel;
		}

		public void transitMode(TransitMode... transitModes) {
			for (TransitMode transitMode : transitModes) {
				this.transitMode.add(transitMode);
			}
		}

		public void transitRoutingPreference(TransitRoutingPreference transitRoutingPreference) {
			this.transitRoutingPreference = transitRoutingPreference;
		}

		public void units(Unit units) {
			this.units = units;
		}

		public void waypoints(List<String> waypoints) {
			this.waypoints = waypoints;
		}

		public Route build() {
			return new Route(this);
		}

	}

}
