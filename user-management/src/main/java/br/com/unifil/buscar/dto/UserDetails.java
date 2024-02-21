package br.com.unifil.buscar.dto;

/**
 * UserDetails is the interface that specifies the required methods to have in
 * order to perform user authentication.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

public interface UserDetails {

    public boolean isAccountNonExpired();

    public boolean isAccountNonLocked();

    public boolean isCredentialsNonExpired();

    public boolean isEnabled();

    public String getUsername();

    public String getPassword();
}
