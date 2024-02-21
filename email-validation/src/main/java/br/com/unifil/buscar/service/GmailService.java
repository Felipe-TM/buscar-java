package br.com.unifil.buscar.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.unifil.buscar.dto.EmailVerificationRecord;
import br.com.unifil.buscar.exceptions.DuplicatedRequestException;
import br.com.unifil.buscar.repositories.VerificationRepository;
import br.com.unifil.buscar.utils.DefaultEmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * GmailSMTPService is the main service layer class, it implements the
 * {@link VerificationService} interface, it offers all the implementation required
 * to verify an email.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */
@Service(value = "GmailService")
public class GmailService implements VerificationService {

	@Value("${spring.mail.username}")
	private final String COMPANY_EMAIL_ADDRESS;
	@Value("${secrets.name}")
	private final String COMPANY_NAME;
	@Value("${secrets.baseUrl}")
	private final String BASE_URL;

	private final VerificationRepository repository;
	private final JavaMailSender mailSender;
	
	/**
	 * Class constructor specifying the dependencies to be injected.
	 * 
	 * @param repository A {@link VerificationRepository} implementation, as specified in the {@code @Qualifier} annotation. 
	 * @param mailSender A {@link JavaMailSender} implementation.
	 * @since 1.0
	 * */

	@Autowired
	public GmailService(@Qualifier("RedisRepository") VerificationRepository repository, JavaMailSender mailSender) {
		this.COMPANY_EMAIL_ADDRESS = "";
		this.COMPANY_NAME = "";
		this.BASE_URL = "";
		this.repository = repository;
		this.mailSender = mailSender;
	}

	@Override
	public void sendVerificationEmail(EmailVerificationRecord record)
			throws DuplicatedRequestException, MessagingException {
		if (repository.isDuplicatedRequest(record.username()))
			throw new DuplicatedRequestException();

		repository.save(record);
		DefaultEmailTemplate template = new DefaultEmailTemplate();
		String verifyURL = BASE_URL + "/" + record.verificationCode();
		String subject = "Please verify your registration";
		String content= template.loadEmailTemplate("assets/templates/email.html");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);		

		helper.setFrom(COMPANY_EMAIL_ADDRESS);
		helper.setTo(record.email());
		helper.setSubject(subject);
		
		content = content.replace("validation_link", verifyURL);

		helper.setText(content, true);		
		
		mailSender.send(message);
	}

	@Override
	public boolean processRequest(String verificationCode) throws NoSuchElementException {
		
		repository.getByVerificationCode(verificationCode).orElseThrow();
		
		repository.delete(verificationCode);
		
		return true;
	}
}
