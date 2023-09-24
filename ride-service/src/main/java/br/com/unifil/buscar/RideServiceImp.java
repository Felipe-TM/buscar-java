package br.com.unifil.buscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImp implements RideService {

	private RideRepository rideRepository;
	private EnrollRepository enrollRepository;

	@Autowired
	public RideServiceImp(RideRepository rideRepository, EnrollRepository enrollRepository) {
		this.rideRepository = rideRepository;
		this.enrollRepository = enrollRepository;
	}

	@Override
	public void publishRide(Ride ride) {
		rideRepository.saveRide(ride);
	}

	@Override
	public RideRecord getRide(String requesterId, String rideId) throws IllegalArgumentException {
		Ride ride = rideRepository.getRideById(rideId);

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
				ride.getDriverId(),
				ride.getEnrolledPassengers(),
				ride.getRideId());
	}

	@Override
	public boolean cancelRide(String requesterId, String rideId) throws IllegalArgumentException {
		
		Ride ride = rideRepository.getRideById(rideId);
		
		return false;
	}

	@Override
	public void acceptRide(String driverId, String rideId) throws IllegalArgumentException {
		Ride ride = rideRepository.getRideById(rideId);
		ride.setRideStatus(RideStatus.WAITING_DRIVER);
		rideRepository.saveRide(ride);		
	}

	@Override
	public void enrollUserToRide(String rideId, String passangerId) {
		Ride ride = rideRepository.getRideById(rideId);
		ride.setEnrolledPassengers(passangerId);
		rideRepository.saveRide(ride);
		EnrollResquest resquest = new EnrollResquest(passangerId, rideId, EnrollStatus.SENT);
		enrollRepository.saveRequest(resquest);
		
	}

}
