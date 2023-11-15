package br.com.unifil.buscar;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * UserService is the main service layer class, it has all the business logic
 * required to make the microservice work.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

@Service
public class UserService {

    private final UserRepository repository;

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

    public UserRecord loadByUsername(String username) throws UsernameNotFoundException {

	User user = repository.loadByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
	return new UserRecord(null, null, null, user.getUsername(), user.getPassword());
    }

    /**
     * Sends a request to the repository dependency to save the new user to the
     * database.
     * <p>
     * This method expects an {@link UserRecord} and converts it to a {@link User}
     * POJO class in order to call the save request in the database.
     * 
     * @param userRecord the new user to be saved
     * @since 1.0
     * @see User
     * @see UserRecord
     */

    public void save(UserRecord userRecord) {
	repository.save(User.builder().name(userRecord.name()).phoneNumber(userRecord.phoneNumber())
		.username(userRecord.username()).password(userRecord.passowrd()).build());

    }

}
