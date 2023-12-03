package br.com.unifil.buscar.ride;

import java.util.List;

public class Location {

	private final static String DEFAULT_GEO_TYPE = "Point";

	private List<Double> coordinates;
	private String type = DEFAULT_GEO_TYPE;

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
