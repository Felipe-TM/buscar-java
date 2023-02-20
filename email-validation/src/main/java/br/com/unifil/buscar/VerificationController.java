package br.com.unifil.buscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;

/**
 * VerificationController is the main gateway of the microservice, it exposes 
 * all the API endpoints so that other applications are allowed to make
 * email verification requests. 
 * 
 * @author Felipe Torres
 * @version 1.0
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
	public VerificationController(@Qualifier("VerificationServiceImp") VerificationService service) {
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
	 * @since 1.0 
	 * */
	
	@PostMapping("api/v1/generateValidationLink")
	public ResponseEntity<String> generateVerificationLink(@RequestBody EmailVerificationRecord verificationRequest) {

		EmailVerificationRecord request = new EmailVerificationRecord(verificationRequest.username(),
				verificationRequest.email(), service.generateVerificationCode());
		
		try {
			service.sendVerificationEmail(request);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
			
		} catch (DuplicatedRequestException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (MessagingException e) {
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
		}
	}
	
	/**
	 * Gets the verification code and the username from the path variable and 
	 * validates with the service to check if the credentials are correct.
	 * <p>
	 * This method returns a {@link ResponseEntity}, where if the credentials are right, the status 
	 * is set to <b>HttpStatus.CREATED</b>, meaning that the email was successfully verified,
	 * otherwise is set to <b>HttpStatus.FORBIDDEN</b> thus denying the connection.
	 * 
	 * @param verificationCode a String with the verification code
	 * @param username a String with the username
	 * @since 1.0
	 * */
	
	@GetMapping("api/v1/validate/{verificationCode}:{username}")
	public ResponseEntity<String> validate(@PathVariable(name = "verificationCode") String verificationCode,
			@PathVariable(name = "username") String username) {
		
		boolean requestResult = service.processRequest(verificationCode, username);
		
		if (requestResult) {
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		}		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

}
