package br.com.unifil.buscar.ride;

import java.time.ZonedDateTime;
import java.util.Date;

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
	Ride ride = rideRepository.getRideById(rideId);
	EnrollRequest request = enrollRepository.getRequest(requestId);

	if (!driverId.equals(ride.getDriverId()) && ride.getRideId().equals(request.getRideId()))
	    throw new IllegalArgumentException();

	request.setStatus(EnrollStatus.ACCEPTED);
	enrollRepository.saveRequest(request);

	ride.addPassenger(request.getPassangerId());
	ride.setRideStatus(RideStatus.WAITING_DRIVER);
	rideRepository.updateRide(ride);
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

	if (!rideRepository.getRideById(rideId).getDriverId().equals(driverId))
	    throw new IllegalArgumentException();

	Ride ride = rideRepository.getRideById(rideId);

	switch (ride.getRideStatus()) {

	case WAITING_DRIVER: {
	    ride.setRideStatus(RideStatus.IN_PROGRESS);
	    ride.setDepartureTime(ZonedDateTime.now().toString());
	    rideRepository.updateRide(ride);
	    break;
	}
	default:
	    throw new IllegalArgumentException("Canno't end a ride with status: " + ride.getRideStatus()
		    + ". It has to be with status: " + RideStatus.WAITING_DRIVER);
	}

    }

    @Override
    public void endRide(String rideId, String driverId) throws IllegalArgumentException {

	if (!rideRepository.getRideById(rideId).getDriverId().equals(driverId))
	    throw new IllegalArgumentException();

	Ride ride = rideRepository.getRideById(rideId);

	switch (ride.getRideStatus()) {

	case IN_PROGRESS: {
	    ride.setRideStatus(RideStatus.FINISHED);
	    ride.setArrivalTime(ZonedDateTime.now().toString());
	    rideRepository.updateRide(ride);
	    break;
	}
	default:
	    throw new IllegalArgumentException("Canno't end a ride with status: " + ride.getRideStatus()
		    + ". It has to be with status: " + RideStatus.IN_PROGRESS);
	}

    }

}
