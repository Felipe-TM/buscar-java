package br.com.unifil.buscar.ride;

public interface RideRepository {
	
	void saveRide(Ride ride);
	Ride getRideById(String rideId);
	void cancelRide(String rideId);
	void updateRide(Ride ride);
}
