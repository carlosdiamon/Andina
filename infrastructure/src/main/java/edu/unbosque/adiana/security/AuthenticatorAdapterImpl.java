package edu.unbosque.adiana.security;

import edu.unbosque.adiana.client.Client;
import edu.unbosque.adiana.security.jwt.JwtTokenService;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorAdapterImpl implements AuthenticatorAdapter {

	private final BCryptPasswordEncoder encoder;
	private final AuthenticationManager manager;
	private final JwtTokenService service;

	public AuthenticatorAdapterImpl(
		final BCryptPasswordEncoder encoder,
		final AuthenticationManager manager,
		final JwtTokenService service
	) {
		this.encoder = encoder;
		this.manager = manager;
		this.service = service;
	}


	@Override
	public @NotNull String generateToken(final @NotNull Client client) {
		return service.generateToken(client);
	}

	@Override
	public @NotNull String encryptPassword(final @NotNull String password) {
		return encoder.encode(password);
	}

	@Override
	public void authenticateClient(
		final @NotNull String identifier,
		final @NotNull String password
	) {
		manager.authenticate(new UsernamePasswordAuthenticationToken(identifier, encryptPassword(password)));
	}
}
