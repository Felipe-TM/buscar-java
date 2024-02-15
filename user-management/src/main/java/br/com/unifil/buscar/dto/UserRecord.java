package br.com.unifil.buscar.dto;

/**
 * UserRocord stores the user details before being 
 * converted to the User POJO.
 * 
 * @param name
 * @param phoneNumber
 * @param email
 * @param username
 * @param passord
 * */

public record UserRecord(
		String name,
		String phoneNumber,
		String email,
		String username,
		String passowrd
		) {

}
