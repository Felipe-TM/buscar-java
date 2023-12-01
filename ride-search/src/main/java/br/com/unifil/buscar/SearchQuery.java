package br.com.unifil.buscar;

import com.mongodb.client.model.geojson.Point;

public class SearchQuery {

    private static final double DEFAULT_SEARCH_RADIUS = 1000.0;

    private Point origin;
    private Point destination;
    private double searchRadiusOrigin = DEFAULT_SEARCH_RADIUS;
    private double searchRadiusDestination = DEFAULT_SEARCH_RADIUS;

    public SearchQuery(Point origin, Point destination, double searchRadiusOrigin, double searchRadiusDestination) {
	this.origin = origin;
	this.destination = destination;
	this.searchRadiusOrigin = searchRadiusOrigin;
	this.searchRadiusDestination = searchRadiusDestination;
    }

    public Point getOrigin() {
	return origin;
    }

    public void setOrigin(Point origin) {
	this.origin = origin;
    }

    public Point getDestination() {
	return destination;
    }

    public void setDestination(Point destination) {
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

}
