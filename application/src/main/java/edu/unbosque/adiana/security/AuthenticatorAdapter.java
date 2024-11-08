package edu.unbosque.adiana.security;

import edu.unbosque.adiana.client.Client;
import org.jetbrains.annotations.NotNull;

public interface AuthenticatorAdapter {

	@NotNull String generateToken(final @NotNull Client client);

	@NotNull String encryptPassword(final @NotNull String password);

	void authenticateClient(
		final @NotNull String identifier,
		final @NotNull String password
	);


}
