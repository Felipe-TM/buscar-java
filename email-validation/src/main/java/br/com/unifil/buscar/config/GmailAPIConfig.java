package br.com.unifil.buscar.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;

/**
 * GmailAPIConfig is responsible for configuring and connecting to Google cloud
 * Gmail Api service.
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 */

@Configuration
public class GmailAPIConfig {

	@Value("${spring.mail.host}")
	private String HOST;
	@Value("${spring.mail.port}")
	private int PORT;
	@Value("${spring.mail.username}")
	private String USERNAME;
	/**
	 * Global instance of the JSON factory.
	 */
	private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	/**
	 * Directory to store authorization tokens for this application.
	 */
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	private static final List<String> SCOPES = Collections.singletonList("https://mail.google.com/");
	private static final String CREDENTIALS_FILE_PATH = "credentials.json";

	@Bean
	public NetHttpTransport getNetHttpTransport() throws GeneralSecurityException, IOException {
		return GoogleNetHttpTransport.newTrustedTransport();
	}

	/**
	 * Creates an authorized Credential object.
	 *
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException              If the credentials.json file cannot be
	 *                                  found.
	 * @throws GeneralSecurityException
	 * @since 1.0
	 */
	@Bean
	public Credential getCredentials() throws IOException, GeneralSecurityException {
		// Load client secrets.
		InputStream in = getClass().getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(getNetHttpTransport(), JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
		// returns an authorized Credential object.
		return credential;
	}
	
	/**
	 * Instantiates a JavaMailSender implementation.
	 *
	 * @return An JavaMailSender implementation.
	 * @throws IOException              
	 * @throws GeneralSecurityException
	 * @throws MessagingException
	 * @since 1.0
	 */	
	@Bean
	public JavaMailSender javaMailSenderO2Auth() throws MessagingException, IOException, GeneralSecurityException {
		
		//Refreshes access token if expired
		if (getCredentials().getExpiresInSeconds() < 0) getCredentials().refreshToken();

		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setProtocol("smtp");
		javaMailSenderImpl.setHost(HOST);
		javaMailSenderImpl.setPort(PORT);
		javaMailSenderImpl.setUsername(USERNAME);
		javaMailSenderImpl.setPassword(getCredentials().getAccessToken());

		Properties props = javaMailSenderImpl.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");		
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth.mechanisms", "XOAUTH2");

		return javaMailSenderImpl;
	}

}
