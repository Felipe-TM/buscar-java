package br.com.unifil.buscar.enroll;

public interface EnrollRepository {
	
	public void saveRequest(EnrollRequest resquest);
	public void updateRequest(String requestId, EnrollStatus status);
	public EnrollRequest getRequest(String requestId);
}
