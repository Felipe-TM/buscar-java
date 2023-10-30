package br.com.unifil.buscar;

public interface RideService {
	
	public RideRecord getRide(String requesterId, String rideId) throws IllegalArgumentException;
	public boolean cancelRide(String requesterId, String rideId) throws IllegalArgumentException;
	public void acceptPassenger(String driverId, String rideId, String requestId) throws IllegalArgumentException;
	public void publishRide(Ride ride);
	public String enrollToRide(String rideId, String userId);
}
