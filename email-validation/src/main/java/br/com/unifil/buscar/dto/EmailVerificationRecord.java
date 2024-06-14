package br.com.unifil.buscar.dto;

/**
 *  EmailVerificationRecord stores the verification request
 *  information, such as username and the email to send the 
 *  verification.
 *  
 *  @param username String
 *  @param email String
 *  @param verificationCode String
 *  @author Felipe Torres
 *  @version 1.0
 *  @since 1.0  
 * */

public record EmailVerificationRecord (
		String username,
		String email,
		String verificationCode
		) {

}
