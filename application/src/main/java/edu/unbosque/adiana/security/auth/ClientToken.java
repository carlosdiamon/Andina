package edu.unbosque.adiana.security.auth;

import org.jetbrains.annotations.NotNull;

public record ClientToken(
	@NotNull String token,
	@NotNull String email
) {

	@Override
	public String toString() {
		return "ClientToken{" +
		       "token='" + token + '\'' +
		       ", email='" + email + '\'' +
		       '}';
	}
}
