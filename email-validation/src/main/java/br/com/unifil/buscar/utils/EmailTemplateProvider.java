package br.com.unifil.buscar.utils;

/**
 * It is responsible for providing methods to load html documents. 
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * */
public interface EmailTemplateProvider {
	
	/**
	 * 	Loads an email template from given path and returns a String of it.
	 * 
	 * @param 	filePath		Path to the file as a String
	 * @return 	String 			A String of the loaded html document.
	 * */
	String loadEmailTemplate(String filePath) throws IllegalArgumentException;
}
