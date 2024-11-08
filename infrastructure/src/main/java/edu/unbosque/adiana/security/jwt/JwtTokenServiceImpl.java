package edu.unbosque.adiana.security.jwt;

import edu.unbosque.adiana.client.Client;
import edu.unbosque.adiana.security.wrapper.DotenvWrapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenServiceImpl
	implements JwtTokenService {

	private final SecretKey key;
	private final long expiration;

	public JwtTokenServiceImpl(final DotenvWrapper env) {
		final byte[] decoded = Decoders.BASE64.decode(env.getString("JWT_SECRET"));
		this.key = Keys.hmacShaKeyFor(decoded);
		this.expiration = env.getValueOrDefault(Long.class, "JWT_EXPIRATION", 3600000L);
	}

	@Override
	public @NotNull String generateToken(final @NotNull Client client) {
		return generateToken(client, Collections.emptyMap());
	}

	@Override
	public @NotNull String generateToken(
		final @NotNull Client client,
		final @NotNull Map<String, Object> claims
	) {
		return buildToken(client, claims);
	}

	@Override
	public <T> @NotNull T extractClaims(
		final @NotNull String token,
		final @NotNull Function<Claims, T> resolver
	) {
		return resolver.apply(extractClaim(token));
	}

	@Override
	public @NotNull String extractIdentifier(final @NotNull String token) {
		return extractClaims(token, Claims::getSubject);
	}

	@Override
	public boolean isValidToken(
		final @NotNull String token,
		final @NotNull Client client
	) {
		final Claims claims = extractClaim(token);
		final String email = claims.getSubject();
		final Instant expiration = claims.getExpiration().toInstant();

		return client.username().equals(email) && expiration.isAfter(Instant.now());
	}

	private @NotNull String buildToken(
		final @NotNull Client client,
		final @NotNull Map<String, Object> claims
	) {
		final Instant now = Instant.now();
		return Jwts.builder()
			       .claims(claims)
			       .subject(client.email())
			       .issuedAt(Date.from(now))
			       .expiration(Date.from(now.plusMillis(expiration)))
			       .signWith(key)
			       .compact();
	}


	private @NotNull Claims extractClaim(final @NotNull String token) {
		return Jwts.parser()
			       .decryptWith(key)
			       .build()
			       .parseSignedClaims(token)
			       .getPayload();
	}
}
