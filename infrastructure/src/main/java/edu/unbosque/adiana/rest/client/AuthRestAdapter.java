package edu.unbosque.adiana.rest.client;

import edu.unbosque.adiana.security.auth.ClientToken;
import edu.unbosque.adiana.security.auth.LoginRequest;
import edu.unbosque.adiana.security.auth.RegisterRequest;
import edu.unbosque.adiana.security.AuthenticatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthRestAdapter {

	private final AuthenticatorService authenticator;

	public AuthRestAdapter(final AuthenticatorService authenticator) { this.authenticator = authenticator; }

	@PostMapping("/register")
	public ResponseEntity<ClientToken> register(final @RequestBody RegisterRequest request) {
		final ClientToken token = authenticator.registerClient(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			       .body(token);
	}

	@PostMapping("/login")
	public ResponseEntity<ClientToken> login(final @RequestBody LoginRequest request) {
		final ClientToken token = authenticator.authenticateClient(request);
		return ResponseEntity.ok(token);
	}

}
