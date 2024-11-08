package edu.unbosque.adiana.client.exceptions;

public class AuthException
	extends RuntimeException {

	private final String reason;

	public AuthException(String message) {
		super(message);
		this.reason = null;
	}

	public AuthException(String message, String reason) {
		super(message);
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

}
