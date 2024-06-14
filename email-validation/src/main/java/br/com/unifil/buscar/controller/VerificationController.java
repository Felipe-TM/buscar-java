                                                                                    package br.com.unifil.buscar.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unifil.buscar.dto.EmailVerificationRecord;
import br.com.unifil.buscar.service.VerificationService;
import br.com.unifil.buscar.utils.VerificationCodeGenerator;
import jakarta.mail.MessagingException;

/**
 * VerificationController is the main gateway of the microservice, it exposes 
 * all the API endpoints so that other applications are allowed to make
 * email verification requests. 
 * 
 * @author Felipe Torres
 * @version 2.0
 * @since 1.0
 */
@RestController
public class VerificationController {

	private final VerificationService service;
	
	/**
	 * Class constructor specifying the dependencies to be injected.
	 * 
	 * @param service a {@link VerificationService} implementation, as specified in the {@code @Qualifier} annotation
	 * @since 1.0
	 * */
	
	@Autowired
	public VerificationController(@Qualifier("GmailService") VerificationService service) {
		this.service = service;
	}
	
	/**
	 * Gets the Json containing the username and email from the request body and calls
	 * the service to send the verification email.  
	 * <p>
	 * This method expects a Json from the request body, converts it to an {@link EmailVerificationRecord},
	 * calls the service layer class to generate the verification code, then sends the email containing the
	 * Url the user needs to access to verify his email address.
	 * 
	 * @param verificationRequest the EmailVerificationRecord from the Json in the request body
	 * @return {@link ResponseEntity}
	 * @since 1.0 
	 * */
	
	@PostMapping("api/v1/generateValidationLink")
	public ResponseEntity<String> generateVerificationLink(@RequestBody EmailVerificationRecord verificationRequest) {

		EmailVerificationRecord request = new EmailVerificationRecord(verificationRequest.username(),
				verificationRequest.email(), VerificationCodeGenerator.generateVerificationCode());
		
		try {
			service.sendVerificationEmail(request);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("OK");			
		} catch (MessagingException e) {
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage());
		} catch (MailSendException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	/**
	 * Gets the verification code from the path variable and 
	 * validates with the service to check if the credentials are correct.
	 * <p>
	 * This method returns a {@link ResponseEntity}, where if the credentials are right, the status 
	 * is set to <b>HttpStatus.OK</b>, meaning that the email was successfully verified,
	 * otherwise is set to <b>HttpStatus.NOT_FOUND</b>.
	 * 
	 * @param verificationCode a String with the verification code
	 * @return {@link ResponseEntity}
	 * @since 2.0
	 * */
	
	@GetMapping("api/v2/validate/{verificationCode}")
	public ResponseEntity<String> validate(@PathVariable(name = "verificationCode") String verificatioonCode){
		
		try {
			service.processRequest(verificatioonCode);
			return ResponseEntity.status(HttpStatus.OK).body("OK");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		} 
		
		
	}

}
