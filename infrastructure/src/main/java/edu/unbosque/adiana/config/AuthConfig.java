package edu.unbosque.adiana.config;

import edu.unbosque.adiana.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class AuthConfig {

	private final JwtAuthenticationFilter filter;
	private final AuthenticationProvider provider;

	public AuthConfig(
		final JwtAuthenticationFilter filter,
		final AuthenticationProvider provider
	) {
		this.filter = filter;
		this.provider = provider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(req ->
				                       req.requestMatchers("/auth/**")
					                       .permitAll()
					                       .anyRequest()
					                       .authenticated()
			)
			.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
			.authenticationProvider(provider)
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
