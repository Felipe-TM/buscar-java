package br.com.unifil.buscar;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Config is a configuration class, this is where the 
 * required dependencies are declared.
 * 
 *  @author Felipe Torres
 *  @version 1.0
 *  @since 1.0
 * */

@Configuration
public class Config {
	
	@Value("${spring.mail.host}")
	private String HOST;
	@Value("${spring.mail.username}")
	private String USERNAME;
	@Value("${spring.mail.password}")
	private String PASSWORD;
	
	/**
	 * Declares a {@code @Bean} of the type JavaMailSender to 
	 * be injected.
	 * 
	 * @return {@link JavaMailSender}
	 * @since 1.0
	 * */

	@Bean
	public JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setProtocol("smtp");
		javaMailSenderImpl.setHost(HOST);
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername(USERNAME);
		javaMailSenderImpl.setPassword(PASSWORD);
		
		Properties props = javaMailSenderImpl.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	  
		
		return javaMailSenderImpl;
	}
}
