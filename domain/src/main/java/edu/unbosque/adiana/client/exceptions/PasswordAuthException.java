package edu.unbosque.adiana.client.exceptions;

public class PasswordAuthException extends AuthException {
	public PasswordAuthException(String message) {
		super(message);
	}

	public PasswordAuthException(String message, String reason) {
		super(message, reason);
	}
}