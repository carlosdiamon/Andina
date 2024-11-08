package edu.unbosque.adiana.security;

import edu.unbosque.adiana.security.jwt.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final String BEARER = "Bearer";

	private final JwtTokenService jwtService;
	private final UserDetailsService userDetailsService;

	public JwtAuthenticationFilter(
		final JwtTokenService jwtService,
		final UserDetailsService userDetailsService
	) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(
		final @NotNull HttpServletRequest request,
		final @NotNull HttpServletResponse response,
		final @NotNull FilterChain filterChain
	) throws ServletException, IOException {
		if (request.getServletPath().contains("/auth")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String token = getTokenFromRequest(request);

		if (token == null) {
			filterChain.doFilter(request, response);
			return;
		}

		final String email = jwtService.extractIdentifier(token);
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!StringUtils.hasText(email) || auth != null) {
			filterChain.doFilter(request, response);
			return;
		}

		// Username is email
		final UserDetails user = userDetailsService.loadUserByUsername(email); // Added in authService

		if (user == null) {
			filterChain.doFilter(request, response);
			return;
		}

		if (jwtService.isValidToken(token, user.getUsername())) {
			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				user,
				null,
				user.getAuthorities()
			);
			authentication.setDetails(new WebAuthenticationDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private @Nullable String getTokenFromRequest(final @NotNull HttpServletRequest request){
		final String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		String token = null;

		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)){
			token = (bearerToken.substring(7)).trim();
		}

		return token;
	}

}
