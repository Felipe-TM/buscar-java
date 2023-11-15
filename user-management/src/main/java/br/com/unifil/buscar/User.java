package br.com.unifil.buscar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

/**
 * User is the POJO class to store all the users information, it also makes the
 * object relational mapping through the Jakarta Persistence API.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

@Entity(name = "user_table")
public class User implements UserDetails {

    @Transient
    private static final boolean DEFAULT_IS_ACCOUNT_NON_EXPIRED = true;
    @Transient
    private static final boolean DEFAULT_IS_ACCOUNT_NON_LOCKED = true;
    @Transient
    private static final boolean DEFAULT_IS_CREDENTIALS_NON_EXPIRED = true;
    @Transient
    private static final boolean DEFAULT_IS_ENABLED = false;

    @Id
    private int id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;
    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;
    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;
    @Column(name = "is_enabled")
    private boolean isEnabled;

    public User() {
    }

    /**
     * Class constructor that implements the Builder pattern.
     * 
     * @param builder the Builder class
     * @since 1.0
     */

    private User(Builder builder) {
	this.id = builder.id;
	this.name = builder.name;
	this.phoneNumber = builder.phoneNumber;
	this.email = builder.email;
	this.username = builder.username;
	this.password = builder.password;
	this.isAccountNonExpired = builder.isAccountNonExpired;
	this.isAccountNonLocked = builder.isAccountNonLocked;
	this.isCredentialsNonExpired = builder.isCredentialsNonExpired;
	this.isEnabled = builder.isEnabled;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public String getUsername() {
	return username;
    }

    @Override
    public boolean isAccountNonExpired() {
	return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
	return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
	return isEnabled;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public static Builder builder() {
	return new Builder();
    }

    /**
     * Builder is the class that implements the Builder pattern for the User class.
     * 
     * @author Felipe Torres
     * @version 1.0
     * @since 1.0
     */

    static class Builder {

	private boolean isAccountNonExpired = DEFAULT_IS_ACCOUNT_NON_EXPIRED;
	private boolean isAccountNonLocked = DEFAULT_IS_ACCOUNT_NON_LOCKED;
	private boolean isCredentialsNonExpired = DEFAULT_IS_CREDENTIALS_NON_EXPIRED;
	private boolean isEnabled = DEFAULT_IS_ENABLED;

	private int id;
	private String name;
	private String phoneNumber;
	private String email;
	private String username;
	private String password;

	public Builder isAccountNonExpired(boolean b) {
	    this.isAccountNonExpired = b;
	    return this;
	}

	public Builder isAccountNonLocked(boolean b) {
	    this.isAccountNonExpired = b;
	    return this;
	}

	public Builder isCredentialsNonExpired(boolean b) {
	    this.isAccountNonExpired = b;
	    return this;
	}

	public Builder isEnabled(boolean b) {
	    this.isAccountNonExpired = b;
	    return this;
	}

	public Builder id(int id) {
	    this.id = id;
	    return this;
	}

	public Builder name(String name) {
	    this.name = name;
	    return this;
	}

	public Builder username(String username) {
	    this.username = username;
	    return this;
	}

	public Builder password(String password) {
	    this.password = password;
	    return this;
	}

	public Builder phoneNumber(String phoneNumber) {
	    this.phoneNumber = phoneNumber;
	    return this;
	}

	public Builder email(String email) {
	    this.email = email;
	    return this;
	}

	public User build() {
	    return new User(this);
	}

    }
}
