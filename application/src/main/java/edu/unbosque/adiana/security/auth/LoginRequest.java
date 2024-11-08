package edu.unbosque.adiana.security.auth;

import org.jetbrains.annotations.NotNull;

public record LoginRequest(
	@NotNull String identifier,
	@NotNull String password
) {

	@Override
	public String toString() {
		return "LoginRequest{" +
		       "identifier='" + identifier + '\'' +
		       ", password='" + password + '\'' +
		       '}';
	}
}
