package br.com.unifil.buscar.utils;

public interface EmailTemplateProvider {
	
	String loadEmailTemplate(String filePath) throws IllegalArgumentException;
}
