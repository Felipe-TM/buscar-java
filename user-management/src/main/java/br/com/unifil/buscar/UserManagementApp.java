package br.com.unifil.buscar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 	UserManagementApp class initializes the microservice.
 * */

@SpringBootApplication
public class UserManagementApp {
	public static void main(String[] args) { 
		SpringApplication.run(UserManagementApp.class, args);
	}
}

