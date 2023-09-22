package br.com.unifil.buscar;

public interface RideService {
	
	public RideRecord getRide(String requesterId, String rideId) throws IllegalArgumentException;
	boolean cancelRide(String requesterId, String rideId) throws IllegalArgumentException;
	void acceptRide(String requesterId, String rideId) throws IllegalArgumentException;
	void callRide(Ride ride);
}
