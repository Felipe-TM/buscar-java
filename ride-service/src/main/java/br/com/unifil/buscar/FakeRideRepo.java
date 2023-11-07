package br.com.unifil.buscar;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
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

}
