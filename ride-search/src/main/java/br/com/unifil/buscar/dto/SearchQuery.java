package br.com.unifil.buscar.dto;

import java.util.List;

import br.com.unifil.buscar.enums.Filter;

/**
 * POJO class to retrieve data from HTTP requests.
 * Some values are established by default if they aren't specified
 * in the HTTP request body.<br>
 *
 * <br>DEFAULT_SEARCH_RADIUS = 1000.0; In meters.
 * <br>DEFAULT_FILTER = Filter.DESTINATION_ONLY
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */


public class SearchQuery {

	private static final double DEFAULT_SEARCH_RADIUS = 1000.0;
	private static final Filter DEFAULT_FILTER = Filter.DESTINATION_ONLY;

	private List<Double> origin;
	private List<Double> destination;
	private double searchRadiusOrigin = DEFAULT_SEARCH_RADIUS;
	private double searchRadiusDestination = DEFAULT_SEARCH_RADIUS;
	private Filter filter = DEFAULT_FILTER;

	public SearchQuery() {
	}

	public List<Double> getOrigin() {
		return origin;
	}



	public void setOrigin(List<Double> origin) {
		this.origin = origin;
	}



	public List<Double> getDestination() {
		return destination;
	}



	public void setDestination(List<Double> destination) {
		this.destination = destination;
	}



	public double getSearchRadiusOrigin() {
		return searchRadiusOrigin;
	}

	public void setSearchRadiusOrigin(double searchRadiusOrigin) {
		this.searchRadiusOrigin = searchRadiusOrigin;
	}

	public double getSearchRadiusDestination() {
		return searchRadiusDestination;
	}

	public void setSearchRadiusDestination(double searchRadiusDestination) {
		this.searchRadiusDestination = searchRadiusDestination;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

}
