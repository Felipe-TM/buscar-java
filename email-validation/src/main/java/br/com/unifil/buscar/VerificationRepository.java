package br.com.unifil.buscar;

import java.util.Optional;

/**
 * VerificationRepository interface provides a common set of
 * methods required to implement 
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * */

public interface VerificationRepository {
	
	/**
	 * Gets a {@link EmailVerificationRecord} from the database with given 
	 * username.
	 * <p>
	 * It might return either an EmailVerificationRecord or {@code null} 
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
	 * If there is already a resquest with that username, returns {@code true},
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
	public void delete(String username);
	
}
