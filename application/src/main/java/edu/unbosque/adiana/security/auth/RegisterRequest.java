package edu.unbosque.adiana.security.auth;

import edu.unbosque.adiana.client.ClientRole;
import org.jetbrains.annotations.NotNull;

public record RegisterRequest(
	@NotNull String username,
	@NotNull String email,
	@NotNull ClientRole role,
	@NotNull String password
) {

	@Override
	public String toString() {
		return "RegisterRequest{" +
		       "username='" + username + '\'' +
		       ", email='" + email + '\'' +
		       ", role=" + role +
		       ", password='" + password + '\'' +
		       '}';
	}
}
