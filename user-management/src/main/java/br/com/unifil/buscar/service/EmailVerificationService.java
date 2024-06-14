package br.com.unifil.buscar.service;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.unifil.buscar.dto.UserRecord;

@Service
public class EmailVerificationService {
	
	@Value("${services.email.url}")
	private String emailServiceURL;
	@Value("${services.email.endpoints.sendEmail}")
	private String sendEmailEndpoint;
	private RestTemplate restTemplate;
	
	
	
	@Autowired
	public EmailVerificationService() {
		this.restTemplate = new RestTemplate();
	}




	public void sendEmail(UserRecord user){
		
		Gson gson = new Gson();
		restTemplate.postForEntity(emailServiceURL + sendEmailEndpoint, gson.toJson(user), ResponseEntity.class);		
	}
	

}
