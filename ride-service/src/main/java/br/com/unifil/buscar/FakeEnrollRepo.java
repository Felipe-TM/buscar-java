package br.com.unifil.buscar;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository(value = "fakeEnroll")
public class FakeEnrollRepo implements EnrollRepository{
	
	private Map<String, EnrollRequest> fakeDB = new HashMap<String, EnrollRequest>();

	@Override
	public void saveRequest(EnrollRequest request) {
		request.setStatus(EnrollStatus.WAITING_DRIVER_CONFIRMATION);
		fakeDB.put(request.getRequestId(), request);
	}

	@Override
	public void updateRequest(String requestId, EnrollStatus status) {
		fakeDB.get(requestId).setStatus(status);
		
	}

	@Override
	public EnrollRequest getRequest(String requestId) {
		return fakeDB.get(requestId);		
	}

}
