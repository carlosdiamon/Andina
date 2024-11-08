package edu.unbosque.adiana.client.exceptions;

public class EmailAuthException extends AuthException {
	public EmailAuthException(String message) {
		super(message);
	}

	public EmailAuthException(String message, String reason) {
		super(message, reason);
	}
}