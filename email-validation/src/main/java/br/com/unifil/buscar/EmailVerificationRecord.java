package br.com.unifil.buscar;

/**
 *  EmailVerificationRecord stores the verification request
 *  information, such as username and the email to send the 
 *  verification.
 *  
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
