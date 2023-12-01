package br.com.unifil.buscar.ride;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository(value = "fakeRide")
public class FakeRideRepo implements RideRepository{

	private Map<String, Ride> fakeDB = new HashMap<String, Ride>();
	
	@Override
	public Ride getRideById(String rideId) {
		return fakeDB.get(rideId);
	}

	@Override
	public void saveRide(Ride ride) {
		fakeDB.put(ride.getRideId(), ride);	
	}

	@Override
	public void cancelRide(String rideId) {
		getRideById(rideId).setRideStatus(RideStatus.CANCELED);
	}

	@Override
	public void updateRide(Ride ride) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRide(String rideId, String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RideRecord getFieldsByName(String rideId, String... name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertIntoArray(String rideId, String name, String values) {
		// TODO Auto-generated method stub
		
	}

}
