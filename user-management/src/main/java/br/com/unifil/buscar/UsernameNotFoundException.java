package br.com.unifil.buscar;

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
