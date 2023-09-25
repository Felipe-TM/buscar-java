package br.com.unifil.buscar;

import java.util.UUID;

public class EnrollRequest {
	
	private final String requestId;
	private final String rideId;
	private final String passangerId;
	private EnrollStatus status;
	
	public EnrollRequest(String rideId, String passangerId, EnrollStatus status) {
		this.rideId = rideId;
		this.passangerId = passangerId;
		this.status = status;
		this.requestId = UUID.randomUUID().toString();
	}

	public EnrollStatus getStatus() {
		return status;
	}

	public void setStatus(EnrollStatus status) {
		this.status = status;
	}

	public String getRideId() {
		return rideId;
	}

	public String getPassangerId() {
		return passangerId;
	}
	
	public String getRequestId() {
		return requestId;
	}
	
	
}
