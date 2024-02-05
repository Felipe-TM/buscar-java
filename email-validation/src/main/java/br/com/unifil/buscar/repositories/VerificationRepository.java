package br.com.unifil.buscar.repositories;

import java.util.Optional;

import br.com.unifil.buscar.dto.EmailVerificationRecord;

/**
 * VerificationRepository interface provides a common set of
 * methods required to do email verification.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * */

public interface VerificationRepository{
	
	/**
	 * Gets a {@link EmailVerificationRecord} from the database with given 
	 * username.
	 * <p>
	 * It returns either an EmailVerificationRecord or {@code null} 
	 *  
	 * @param username
	 * @return {@link Optional}
	 * @since 1.0
	 * */
	public Optional<EmailVerificationRecord> getByUsername(String username);
	
	/**
	 * Saves the provided {@link EmailVerificationRecord} to the database.
	 * 
	 * @param record {@code EmailVerificationRecord}
	 * @since 1.0
	 * */
	public void save(EmailVerificationRecord record);
	
	/**
	 * Checks in the database if there is already a {@link EmailVerificationRecord} stored 
	 * with given username.
	 * <p>
	 * If there is already a request with that username, returns {@code true},
	 * otherwise returns {@code false}. 
	 *  
	 * @param username
	 * @return {@code boolean}
	 * @since 1.0
	 * */
	public boolean isDuplicatedRequest(String username);
	
	/**
	 * Deletes a {@link EmailVerificationRecord} from the database.
	 *  
	 * @param username
	 * @since 1.0s
	 * */
	public void delete(String verificationCode);
	
	/**
	 * Gets a {@link EmailVerificationRecord} from the database with given 
	 * verification code.
	 * <p>
	 * It returns a nullable Optinal of an EmailVerificationRecord.  
	 *  
	 * @param {@link String} verificationCode
	 * @return {@link Optional}
	 * @since 2.0
	 * */
	public Optional<EmailVerificationRecord> getByVerificationCode(String verificationCode);
	
}
