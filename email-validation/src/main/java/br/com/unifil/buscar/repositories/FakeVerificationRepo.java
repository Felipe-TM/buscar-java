package br.com.unifil.buscar.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.unifil.buscar.dto.EmailVerificationRecord;

/**
 * FakeVerificationRepo is an implementation of the {@link VerificationRepository}
 * interface. It provides resources for local testing and should not be used in a 
 * real environment. 
 * 
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * */
@Repository(value = "FakeRepo")
public class FakeVerificationRepo implements VerificationRepository {
	
	private Map<String, EmailVerificationRecord> fakeDB = new HashMap<>();

	@Override
	public Optional<EmailVerificationRecord> getByUsername(String verificationCode) {
		return Optional.ofNullable(fakeDB.get(verificationCode));
	}
	
	@Override
	public void save(EmailVerificationRecord record) {
		fakeDB.putIfAbsent(record.verificationCode(), record);
	}
	
	@Override
	public boolean isDuplicatedRequest(String username) {
		return fakeDB.containsKey(username);
	}

	@Override
	public void delete(String username) {
		fakeDB.remove(username);		
	}

	@Override
	public Optional<EmailVerificationRecord> getByVerificationCode(String verificationCode) {
		return Optional.ofNullable(fakeDB.get(verificationCode));
	}

}
