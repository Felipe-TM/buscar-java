package br.com.unifil.buscar.utils;

import java.io.InputStream;

public interface EmailTemplateProvider {
	
	InputStream loadEmailTemplate(String filePath) throws IllegalArgumentException;
}
