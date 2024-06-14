package br.com.unifil.buscar.service;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.unifil.buscar.config.SecurityConfig;
import br.com.unifil.buscar.dto.User;
import br.com.unifil.buscar.dto.UserRecord;
import br.com.unifil.buscar.repositories.UserRepository;

/**
 * UserService is the main service layer class, it has all the business logic
 * required to make the microservice work.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

@Service("UserService")
public class UserService implements UserDetailsService {

	private final UserRepository repository;
	@Autowired
	private SecurityConfig config;
	@Autowired
	private EmailVerificationService emailService;

	/**
	 * Class constructor specifying the dependencies to be injected.
	 * <p>
	 * Currently it's only using the {@link UserRepository} implementation, however
	 * it can be easily changed by modifying the {@code @Qualifier} annotation
	 * value.
	 * 
	 * @param repository the repository layer class to be injected
	 * @since 1.0
	 * @see UserRepository
	 */

	public UserService(@Qualifier("MariaDB") UserRepository repository) {
		this.repository = repository;
	}

	/**
	 * Sends a request to the repository dependency to search for the given
	 * username.
	 * <p>
	 * This method expects a String with the username to call the search request in
	 * the database. If no user with the provided username is found, it throws an
	 * {@link UsernameNotFoundException}
	 * 
	 * @param username the string with the username to perform the search
	 * @return {@link UserRecord}
	 * @since 1.0
	 * @see UserRepository
	 */

	public UserDetails loadUserByUsername(String username)
			throws org.springframework.security.core.userdetails.UsernameNotFoundException {

		return repository.loadByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not Found!"));

	}

	/**
	 * Sends a request to the repository dependency to save the new user to the
	 * database.
	 * <p>
	 * This method expects an {@link UserRecord} and converts it to a {@link User}
	 * POJO class in order to call the save request in the database.
	 * 
	 * @param userRecord the new user to be saved
	 * @throws ConnectException 
	 * @since 1.0
	 * @see User
	 * @see UserRecord
	 */

	public void save(UserRecord userRecord){

			emailService.sendEmail(userRecord);
		

//			repository.save(User.builder().name(userRecord.name()).phoneNumber(userRecord.phoneNumber())
//					.username(userRecord.username()).password(config.passwordEncoder().encode(userRecord.passowrd()))
//					.build());

	}

	public boolean isNotUniqueUser(String username) {
		try {
			loadUserByUsername(username);
			return true;
		} catch (UsernameNotFoundException e) {
			return false;
		}
	}
}
