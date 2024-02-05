package br.com.unifil.buscar.service;

import java.util.NoSuchElementException;

import br.com.unifil.buscar.dto.EmailVerificationRecord;
import br.com.unifil.buscar.exceptions.DuplicatedRequestException;
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
	 * @since 1.0
	 **/	
	public void sendVerificationEmail(EmailVerificationRecord record) throws DuplicatedRequestException, MessagingException;
	
	/**
	 * Gets the verification code and compares it with the one stored
	 * in the database for the given username.
	 * <p>
	 * Returns true if the verification is successful, otherwise returns false.
	 * @param verificationCode the String with the code
	 * @param username the String with the username
	 * @return boolean
	 * @since 1.0
	 * */
	@Deprecated
	public boolean processRequest(String verificationCode, String username);
	
	/**
	 * Retrives an {@link EmailVerificationRecord} from the database with 
	 * the given username.
	 * @param username a String with the username
	 * @return {@link EmailVerificationRecord}
	 * @since 1.0
	 * */
	public EmailVerificationRecord getByUsername(String username);
	
	/**
	 * Gets the verification code and compares it with the one stored
	 * in the database.
	 * <p>
	 * Returns true if the verification is successful, otherwise returns false.
	 * @param verificationCode the String with the code
	 * @return boolean
	 * @since 2.0
	 * */
	public boolean processRequest(String verificationCode) throws NoSuchElementException;
	
}
