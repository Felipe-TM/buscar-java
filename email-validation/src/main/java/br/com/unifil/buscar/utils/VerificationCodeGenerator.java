package br.com.unifil.buscar.utils;

import java.util.UUID;

/**
 * Utility class to generate a unique verification code.
 * 
 * @author Felipe Torres
 * @version 1.0	
 * @since 1.0
 * */

public class VerificationCodeGenerator {
	
	/**
	 * Generates the verification code using UUID utility class.
	 * 
	 * @return String 	the verification code as a String.
	 * */	
	public static String generateVerificationCode() {
		return UUID.randomUUID().toString();
		
	}

}
