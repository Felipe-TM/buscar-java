package br.com.unifil.buscar.utils;

import java.io.InputStream;

public class DefaultEmailTemplate implements EmailTemplateProvider {

	@Override
	public InputStream loadEmailTemplate(String filePath) throws IllegalArgumentException {

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(filePath);

		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + filePath);
		} else {
			return inputStream;
		}
	}

}
