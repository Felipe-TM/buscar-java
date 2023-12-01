package br.com.unifil.buscar.ride;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.unifil.buscar.enroll.EnrollRepository;
import br.com.unifil.buscar.enroll.EnrollRequest;
import br.com.unifil.buscar.enroll.EnrollStatus;

@Service
public class RideServiceImp implements RideService {

	private RideRepository rideRepository;
	private EnrollRepository enrollRepository;

	@Autowired
	public RideServiceImp(@Qualifier("MongoDBRepo") RideRepository rideRepository,
			@Qualifier("fakeEnroll") EnrollRepository enrollRepository) {
		this.rideRepository = rideRepository;
		this.enrollRepository = enrollRepository;
	}

	@Override
	public void publishRide(Ride ride) {
		rideRepository.saveRide(ride);
	}

	@Override
	public RideRecord getRide(String requesterId, String rideId) throws IllegalArgumentException {

		return rideRepository.getRideById(rideId).getAsRecord();
	}

	@Override
	public void cancelRide(String requesterId, String rideId) throws IllegalArgumentException {
		RideRecord ride = getRide(requesterId, rideId);

		if (!ride.driverId().equals(requesterId))
			throw new IllegalArgumentException();

		rideRepository.cancelRide(rideId);
	}

	@Override
	public void acceptPassenger(String driverId, String rideId, String requestId) throws IllegalArgumentException {
		RideRecord ride = rideRepository.getFieldsByName(rideId, "rideStatus", "driverId");
		EnrollRequest request = enrollRepository.getRequest(requestId);

		if (!driverId.equals(ride.driverId()) && ride.rideId().equals(request.getRideId()))
			throw new IllegalArgumentException();

		request.setStatus(EnrollStatus.ACCEPTED);
		enrollRepository.saveRequest(request);

		rideRepository.insertIntoArray(rideId, "enrolledPassengers", request.getPassangerId());
		rideRepository.updateRide(rideId, "rideStatus", RideStatus.WAITING_DRIVER.toString());
	}

	@Override
	public String enrollToRide(String rideId, String passangerId) {

		if (rideRepository.getRideById(rideId) == null)
			throw new IllegalArgumentException();

		EnrollRequest resquest = new EnrollRequest(rideId, passangerId, EnrollStatus.SENT);
		enrollRepository.saveRequest(resquest);

		return resquest.getRequestId();

	}

	@Override
	public void startRide(String rideId, String driverId) throws IllegalArgumentException {

		RideRecord ride = rideRepository.getFieldsByName(rideId, "rideStatus", "driverId");

		if (!ride.driverId().equals(driverId))
			throw new IllegalArgumentException();

		switch (ride.rideStatus()) {

		case WAITING_DRIVER: {
			rideRepository.updateRide(rideId, "rideStatus", RideStatus.IN_PROGRESS.toString());
			rideRepository.updateRide(rideId, "departureTime", ZonedDateTime.now().toString());

			break;
		}

		case PENDING: {
			rideRepository.updateRide(rideId, "rideStatus", RideStatus.IN_PROGRESS.toString());
			rideRepository.updateRide(rideId, "departureTime", ZonedDateTime.now().toString());

			break;
		}
		default:
			throw new IllegalArgumentException("Canno't end a ride with status: " + ride.rideStatus()
					+ ". It has to be with status: " + RideStatus.WAITING_DRIVER);
		}

	}

	@Override
	public void endRide(String rideId, String driverId) throws IllegalArgumentException {

		RideRecord ride = rideRepository.getFieldsByName(rideId, "rideStatus", "driverId");

		if (!ride.driverId().equals(driverId))
			throw new IllegalArgumentException();

		switch (ride.rideStatus()) {

		case IN_PROGRESS: {
			rideRepository.updateRide(rideId, "rideStatus", RideStatus.FINISHED.toString());
			rideRepository.updateRide(rideId, "arrivalTime", ZonedDateTime.now().toString());
			break;
		}
		default:
			throw new IllegalArgumentException("Canno't end a ride with status: " + ride.rideStatus()
					+ ". It has to be with status: " + RideStatus.IN_PROGRESS);
		}

	}

}
