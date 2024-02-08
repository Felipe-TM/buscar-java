package br.com.unifil.buscar.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.unifil.buscar.dto.EmailVerificationRecord;
import br.com.unifil.buscar.exceptions.DuplicatedRequestException;
import br.com.unifil.buscar.repositories.VerificationRepository;
import jakarta.mail.MessagingException;


@Service(value = "LocalTest")
public class LocalTestService implements VerificationService{
	
	private final VerificationRepository repository;
	
	@Autowired
	public LocalTestService(@Qualifier("FakeRepo") VerificationRepository repository) {
		this.repository = repository;
	}

	@Override
	public void sendVerificationEmail(EmailVerificationRecord record)
			throws DuplicatedRequestException, MessagingException {
		repository.save(record);
		
		System.out.print("\nVerification code: ");
		System.out.println(record.verificationCode());
		
	}

	@Override
	public boolean processRequest(String verificationCode) throws NoSuchElementException {
		
		Optional<EmailVerificationRecord> byVerificationCode = repository.getByVerificationCode(verificationCode);
		if (byVerificationCode.isEmpty()) throw new NoSuchElementException();
		repository.delete(verificationCode);
		return true;
	}

}
