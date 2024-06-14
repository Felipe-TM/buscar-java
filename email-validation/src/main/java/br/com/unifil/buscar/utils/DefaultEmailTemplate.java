package br.com.unifil.buscar.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service("DefaultTemplateProvider")
public class DefaultEmailTemplate implements EmailTemplateProvider {

	@Override
	public String loadEmailTemplate(String filePath) throws IllegalArgumentException {

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);		
		String text;
		
		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + filePath);
		}
		
		text = new BufferedReader(new InputStreamReader(inputStream))
        .lines()
        .collect(Collectors.joining("\n"));
		
		return text;
	}

}
