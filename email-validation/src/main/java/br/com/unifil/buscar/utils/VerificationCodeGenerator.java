package br.com.unifil.buscar.utils;

import java.util.UUID;

public class VerificationCodeGenerator {
	
	public static String generateVerificationCode() {
		return UUID.randomUUID().toString();
		
	}

}
