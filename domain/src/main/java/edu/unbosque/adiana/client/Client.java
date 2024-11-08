package edu.unbosque.adiana.client;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Set;

public record Client(
	Integer id,
	@NotNull String username,
	@NotNull String email,
	@NotNull String password,
	@NotNull ClientRole role,
	@NotNull Instant createdAt
) {

	@Override
	public String toString() {
		return "Client{" +
		       "id=" + id +
		       ", username='" + username + '\'' +
		       ", email='" + email + '\'' +
		       ", password='" + password + '\'' +
		       ", role=" + role +
		       ", createdAt=" + createdAt +
		       '}';
	}
}
