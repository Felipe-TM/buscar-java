package br.com.unifil.buscar.service;

import java.io.IOException;
import java.util.NoSuchElementException;

import br.com.unifil.buscar.dto.EmailVerificationRecord;
import jakarta.mail.MessagingException;

/**
 * VerificationService interface provides a common set of 
 * methods to implement email verification.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

public interface VerificationService {
	
	/**
	 * Sends a verification to the email address provided
	 * in the email record.
	 * @param record an {@link EmailVerificationRecord}
	 * @throws DuplicatedRequestException
	 * @throws MessagingException
	 * @throws IOException 
	 * @since 1.0
	 **/	
	public void sendVerificationEmail(EmailVerificationRecord record) throws MessagingException, IOException;
	
	/**
	 * Gets the verification code and compares it with the one stored
	 * in the database for the given username.
	 * <p>
	 * Returns true if the verification is successful, otherwise returns false.
	 * 
	 * @Deprecated
	 * @param verificationCode the String with the code
	 * @param username the String with the username
	 * @return boolean
	 * @since 1.0
	 * */

	public boolean processRequest(String verificationCode) throws NoSuchElementException;
	
}
