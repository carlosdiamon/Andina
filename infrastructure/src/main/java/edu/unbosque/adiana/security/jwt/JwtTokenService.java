package edu.unbosque.adiana.security.jwt;

import edu.unbosque.adiana.client.Client;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtTokenService {

	@NotNull String generateToken(final @NotNull Client client);

	@NotNull String generateToken(
		final @NotNull Client client,
		final @NotNull Map<String, Object> claims
	);

	@NotNull<T> T extractClaims(
		final @NotNull String token,
		final @NotNull Function<Claims, T> resolver
	);

	@NotNull String extractIdentifier(final @NotNull String token);

	boolean isValidToken(
		final @NotNull String token,
		final @NotNull String identifier
	);

}
