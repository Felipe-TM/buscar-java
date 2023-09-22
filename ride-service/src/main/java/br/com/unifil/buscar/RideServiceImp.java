package br.com.unifil.buscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImp implements RideService {

	private RideRepository rideRepository;

	@Autowired
	public RideServiceImp(RideRepository rideRepository) {
		this.rideRepository = rideRepository;
	}

	@Override
	public void callRide(Ride ride) {
		rideRepository.saveRide(ride);
	}

	@Override
	public RideRecord getRide(String requesterId, String rideId) throws IllegalArgumentException {
		Ride ride = rideRepository.getRideById(rideId);

		if (!requesterId.equals(ride.getUserID()) && !requesterId.equals(ride.getDriverID()))
			throw new IllegalArgumentException("access denied");

		return new RideRecord(ride.getDestination(),
				ride.getOrigin(),
				ride.getAlternatives(),
				ride.getArrivalTime(),
				ride.getAvoid(),
				ride.getDepartureTime(),
				ride.getLanguage(),
				ride.getMode(),
				ride.getRegion(),				
				ride.getTrafficModel(),
				ride.getTransitMode(),
				ride.getTransitRoutingPreference(),
				ride.getUnits(),
				ride.getWaypoints(),
				ride.getRideStatus(),
				ride.getDriverID(),
				ride.getUserID(),
				ride.getRideID());
	}

	@Override
	public boolean cancelRide(String requesterId, String rideId) throws IllegalArgumentException {
		
		Ride ride = rideRepository.getRideById(rideId);
		
		if (!requesterId.equals(ride.getUserID()) && !requesterId.equals(ride.getDriverID()))
			throw new IllegalArgumentException("access denied");
		return false;
	}

	@Override
	public void acceptRide(String driverId, String rideId) throws IllegalArgumentException {
		Ride ride = rideRepository.getRideById(rideId);
		ride.setDriverID(driverId);
		ride.setRideStatus(RideStatus.WAITING_DRIVER);
		rideRepository.saveRide(ride);		
	}

}
