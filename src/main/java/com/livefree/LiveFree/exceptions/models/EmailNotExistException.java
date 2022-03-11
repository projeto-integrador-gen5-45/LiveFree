package com.livefree.LiveFree.exceptions.models;

public class EmailNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailNotExistException(String email) {
		super("O email "+ email +" não esta cadastrado!");
	}

}
