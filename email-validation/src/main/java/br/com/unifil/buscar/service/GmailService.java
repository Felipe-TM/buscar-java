package br.com.unifil.buscar.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.unifil.buscar.dto.EmailVerificationRecord;
import br.com.unifil.buscar.repositories.VerificationRepository;
import br.com.unifil.buscar.utils.DefaultEmailTemplate;
import br.com.unifil.buscar.utils.EmailTemplateProvider;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * GmailSMTPService is the main service layer class, it implements the
 * {@link VerificationService} interface, it offers all the implementation
 * required to verify an email.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */
@Service(value = "GmailService")
public class GmailService implements VerificationService {

	@Value("${spring.mail.username}")
	private String COMPANY_EMAIL_ADDRESS;
	@Value("${secrets.name}")
	private String COMPANY_NAME;
	@Value("${secrets.baseUrl}")
	private String BASE_URL;

	private final VerificationRepository repository;
	private final JavaMailSender mailSender;
	private final EmailTemplateProvider templateProvider;

	/**
	 * Class constructor specifying the dependencies to be injected.
	 * 
	 * @param repository 		A {@link VerificationRepository} implementation, as
	 *                   		specified in the {@code @Qualifier} annotation.
	 * @param mailSender 		A {@link JavaMailSender} implementation.
	 * @param templateProvider	An implementation of {@link EmailTemplateProvider}
	 * @since 1.0
	 */

	@Autowired
	public GmailService(@Qualifier("FakeRepo") VerificationRepository repository, JavaMailSender mailSender,
			@Qualifier("DefaultTemplateProvider") EmailTemplateProvider templateProvider) {
		this.repository = repository;
		this.mailSender = mailSender;
		this.templateProvider = templateProvider;
	}

	@Override
	public void sendVerificationEmail(EmailVerificationRecord request) throws MessagingException {

		repository.save(request);
		String verifyURL = BASE_URL + "/" + request.verificationCode();
		String subject = request.username() + ", please verify your registration";
		String content = templateProvider.loadEmailTemplate("assets/templates/email.html");

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(COMPANY_EMAIL_ADDRESS);
		helper.setTo(request.email());
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
