package br.com.unifil.buscar;

@SuppressWarnings("serial")
public class DuplicatedRequestException extends RuntimeException {
	
	public DuplicatedRequestException() {}
	
	public DuplicatedRequestException(String msg){
		super(msg);
	}
}
