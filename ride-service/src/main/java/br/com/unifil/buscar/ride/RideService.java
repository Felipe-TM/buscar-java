package br.com.unifil.buscar.ride;

public interface RideService {
	
	public RideRecord getRide(String requesterId, String rideId) throws IllegalArgumentException;
	public void cancelRide(String requesterId, String rideId) throws IllegalArgumentException;
	public void acceptPassenger(String driverId, String rideId, String requestId) throws IllegalArgumentException;
	public void publishRide(Ride ride);
	public String enrollToRide(String rideId, String userId);
	public void startRide(String rideId, String driverId) throws IllegalArgumentException;
	public void endRide(String rideId, String driverId) throws IllegalArgumentException;
}
