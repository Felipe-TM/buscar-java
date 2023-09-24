package br.com.unifil.buscar;

public interface RideService {
	
	public RideRecord getRide(String requesterId, String rideId) throws IllegalArgumentException;
	public boolean cancelRide(String requesterId, String rideId) throws IllegalArgumentException;
	public void acceptRide(String requesterId, String rideId) throws IllegalArgumentException;
	public void publishRide(Ride ride);
	public void enrollUserToRide(String rideId, String userId);
}
