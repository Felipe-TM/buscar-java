package br.com.unifil.buscar.ride;

public interface RideRepository {
	
	void saveRide(Ride ride);
	Ride getRideById(String rideId);
	void cancelRide(String rideId);
	void updateRide(Ride ride);
	void updateRide(String rideId, String name, String value);
	RideRecord getFieldsByName(String rideId, String... name);
	void insertIntoArray(String rideId, String name, String value);
}
