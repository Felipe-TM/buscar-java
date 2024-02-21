package br.com.unifil.buscar.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.unifil.buscar.config.GmailAPIConfig;
import br.com.unifil.buscar.dto.EmailVerificationRecord;
import br.com.unifil.buscar.exceptions.DuplicatedRequestException;
import br.com.unifil.buscar.repositories.VerificationRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
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
	private final String COMPANY_EMAIL_ADDRESS;
	@Value("${secrets.name}")
	private final String COMPANY_NAME;
	@Value("${secrets.baseUrl}")
	private final String BASE_URL;

	private final VerificationRepository repository;
	private final GmailAPIConfig mailSender;

	/**
	 * Class constructor specifying the dependencies to be injected.
	 * 
	 * @param repository A {@link VerificationRepository} implementation, as
	 *                   specified in the {@code @Qualifier} annotation.
	 * @param mailSender A {@link JavaMailSender} implementation.
	 * @since 1.0
	 */

	@Autowired
	public GmailService(@Qualifier("RedisRepository") VerificationRepository repository, GmailAPIConfig mailSender) {
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

		String verifyURL = BASE_URL + "/" + record.verificationCode();
		String toAddress = record.email();
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3><br>[[URL]]<br>" + "Thank you,<br>"
				+ "[[senderName]].";

		MimeMessage msg = new MimeMessage(mailSender.getMailSession());
		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.addHeader("format", "flowed");
		msg.addHeader("Content-Transfer-Encoding", "8bit");
		msg.setText(content, "UTF-8");

		try {
			msg.setFrom(new InternetAddress(COMPANY_EMAIL_ADDRESS, "NoReply-JD"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress, false));
			msg.setSubject(subject, "UTF-8");
			mailSender.getMailTransport().sendMessage(msg, msg.getAllRecipients());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean processRequest(String verificationCode) throws NoSuchElementException {

		repository.getByVerificationCode(verificationCode).orElseThrow();

		repository.delete(verificationCode);

		return true;
	}
}
