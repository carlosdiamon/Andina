package edu.unbosque.adiana.security;

import edu.unbosque.adiana.security.auth.ClientToken;
import edu.unbosque.adiana.security.auth.LoginRequest;
import edu.unbosque.adiana.security.auth.RegisterRequest;
import org.jetbrains.annotations.NotNull;

public interface AuthenticatorService {

	@NotNull ClientToken registerClient(final @NotNull RegisterRequest request);

	@NotNull ClientToken authenticateClient(final @NotNull LoginRequest request);

	@NotNull ClientToken updateClient(
		final int clientId,
		final @NotNull RegisterRequest request
	);

	void recoverPassword(final @NotNull String email);

}
