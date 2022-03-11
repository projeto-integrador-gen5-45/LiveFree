package com.livefree.LiveFree.exceptions.models;

public class EmailExsitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailExsitException(String email) {
		super("O email "+ email +" ja existe, tente outro!");
	}

}
