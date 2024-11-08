package edu.unbosque.adiana.security.auth;

import org.jetbrains.annotations.NotNull;

public record RegisterRequest(
	@NotNull String username,
	@NotNull String email,
	@NotNull String password
) {

	@Override
	public String toString() {
		return "RegisterRequest{" +
		       "username='" + username + '\'' +
		       ", email='" + email + '\'' +
		       ", password='" + password + '\'' +
		       '}';
	}
}
