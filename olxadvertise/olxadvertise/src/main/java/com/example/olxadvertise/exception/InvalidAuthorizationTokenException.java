package com.example.olxadvertise.exception;

public class InvalidAuthorizationTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5879937147551865268L;

	public InvalidAuthorizationTokenException(String message) {
		super(message);
	}

}
