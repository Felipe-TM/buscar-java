package br.com.unifil.buscar;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

/**
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * */
@Repository(value = "FakeVerificationRepo")
public class FakeVerificationRepo implements VerificationRepository {
	
	private Map<String, EmailVerificationRecord> fakeDB = new HashMap<>();

	@Override
	public Optional<EmailVerificationRecord> getByUsername(String username) {
		return Optional.ofNullable(fakeDB.get(username));
	}
	
	@Override
	public void save(EmailVerificationRecord record) {
		fakeDB.putIfAbsent(record.username(), record);
	}
	
	@Override
	public boolean isDuplicatedRequest(String username) {
		return fakeDB.containsKey(username);
	}

	@Override
	public void delete(String username) {
		fakeDB.remove(username);		
	}

}
