package br.com.unifil.buscar;

public interface RideRepository {
	
	void saveRide(Ride ride);
	Ride getRideById(String rideId);
}
