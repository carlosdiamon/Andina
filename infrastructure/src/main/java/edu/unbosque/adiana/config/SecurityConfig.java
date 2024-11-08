package edu.unbosque.adiana.config;

import edu.unbosque.adiana.client.Client;
import edu.unbosque.adiana.client.ClientStorageAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

	private final ClientStorageAdapter storage;

	public SecurityConfig(
		final ClientStorageAdapter storage
	) {
		this.storage = storage;
	}


	@Bean
	public UserDetailsService userDetailsService() {
		return identifier -> {
			final Client client = storage.getClientByEmail(identifier);

			if (client == null) {
				throw new UsernameNotFoundException("User not found: " + identifier + ".");
			}

			return User.builder()
				       .username(client.email())
				       .password(client.password())
				       .build();
		};
	}

	@Bean
	public AuthenticationProvider authenticationProvider(
		final UserDetailsService service,
		final BCryptPasswordEncoder encoder
	) {
		final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder);
		provider.setUserDetailsService(service);
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration)
		throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
