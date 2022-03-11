package com.livefree.LiveFree.exceptions.models;

public class WrongPasswordException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongPasswordException() {
		super("A senha esta incorreta!");
	}
}
