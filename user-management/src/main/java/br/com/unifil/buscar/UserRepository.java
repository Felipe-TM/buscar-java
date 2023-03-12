package br.com.unifil.buscar;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * UserRpository is a interface containing all the JPQL
 * queries. It extends the {@link JpaRepository} interface
 * which is provided by the Spring Data Jpa dependency.
 *  
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * @see JpaRepository
 * @see UserService
 * */

@Repository(value = "MariaDB")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Gets an user with given username.
	 * <p>
	 * This metehod returns an Optional, which may be a 
	 * User or Null. 
	 * 
	 * */
	@Query("SELECT user FROM user_table user WHERE user.username = ?1")
	Optional<User> loadByUsername(String username);
	
}
