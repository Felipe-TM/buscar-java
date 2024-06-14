package br.com.unifil.buscar.controller;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import br.com.unifil.buscar.config.SecurityConfig;
import br.com.unifil.buscar.dto.User;
import br.com.unifil.buscar.dto.UserRecord;
import br.com.unifil.buscar.service.UserService;

/**
 * UserController is the main gateway of the microservice, it exposes all the
 * API endpoints so that other applications are allowed to make requests to the
 * User database.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

@RestController
@RequestMapping("api/v1")
public class UserController {

	private final UserService service;
	private final AuthenticationManager authManager;
	private final SecurityConfig config;

	/**
	 * Class constructor specifying the dependencies to be injected.
	 * 
	 * @param service the service layer class to be injected
	 */
	@Autowired
	public UserController(UserService service, AuthenticationManager auth, SecurityConfig config) {
		this.service = service;
		this.authManager = auth;
		this.config = config;
	}

	/**
	 * Exposes the API endpoint and expects a <b>Json</b> with the <b>username</b>
	 * to search in the database. Returns a {@link ResponseEntity} with a
	 * {@link HttpStatus} and a body containing the <b>Json</b> with the user
	 * details.
	 * 
	 * <p>
	 * This method converts the provided Json to a {@link UserRecord} and searches
	 * the database for a match with the injected <b>service layer method</b>, it
	 * has two return outputs, it's either a <b>HttpStatus.OK</b> with the Json
	 * containing the user details on the body, or a <b>HttpStatus.NOT_FOUND</b> if
	 * the user with provided username didn't exist in the database.
	 * 
	 * @param username the <b>Json</b> on the request body, gets converted to the
	 *                 {@link User} POJO class.
	 * @return ResponseEntity with a status and a body
	 * 
	 *         See also
	 * @see UserService
	 * @see UserRecord
	 */

	@GetMapping(value ="/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> login(@RequestBody User user) {
		try {
			Authentication authenticationRequest =
					UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(), user.getPassword());
				Authentication authenticationResponse =
					this.authManager.authenticate(authenticationRequest);
			return ResponseEntity.status(HttpStatus.OK)
					.body("OK");

		} catch (UsernameNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	/**
	 * Exposes the API path and expects a <b>Json</b> with the new user values as
	 * specified in {@link UserRecord}.
	 * <p>
	 * This method gets the request body <b>Json</b>, converts it to a
	 * {@link UserRecord} and saves the new record to the database using the
	 * {@link UserService#save(UserRecord)} method.
	 * 
	 * @param user the Json on the request body, gets converted to the {@link User}
	 *             POJO class.
	 * @see UserService
	 * @see UserRecord
	 */

	@PostMapping(value = "/registrationRequest", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> registrationRequest(@RequestBody User user) {
		try {
			if(service.isNotUniqueUser(user.getUsername())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already in use");
			service.save(user.toRecord());
			return ResponseEntity.status(HttpStatus.OK).body("OK");

		} catch (UsernameNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (ResourceAccessException e) {
			
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage());
		}

	}
}
