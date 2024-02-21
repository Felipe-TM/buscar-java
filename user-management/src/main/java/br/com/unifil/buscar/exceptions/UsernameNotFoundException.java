package br.com.unifil.buscar.exceptions;

import br.com.unifil.buscar.service.UserService;

/**
 * This is the exception that is thrown when no user with given username is
 * Found
 * 
 * @see {@link UserService}
 */

@SuppressWarnings("serial")
public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException() {
	super();
    }

    public UsernameNotFoundException(String msg) {
	super(msg);
    }
}
