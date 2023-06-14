package br.com.unifil.buscar;

import java.util.List;

public interface RideService {
	
	public Ride callRide ();
	public List<Ride> searchRide();
	public void answerRide ();
}
