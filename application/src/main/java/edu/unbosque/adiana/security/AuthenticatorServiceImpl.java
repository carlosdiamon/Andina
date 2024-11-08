package edu.unbosque.adiana.security;

import edu.unbosque.adiana.client.Client;
import edu.unbosque.adiana.client.ClientStorageAdapter;
import edu.unbosque.adiana.client.exceptions.AuthException;
import edu.unbosque.adiana.client.exceptions.EmailAuthException;
import edu.unbosque.adiana.client.exceptions.PasswordAuthException;
import edu.unbosque.adiana.security.auth.ClientToken;
import edu.unbosque.adiana.security.auth.LoginRequest;
import edu.unbosque.adiana.security.auth.RegisterRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.env.Environment;

import java.time.Instant;
import java.util.regex.Pattern;

public class AuthenticatorServiceImpl implements AuthenticatorService {

	private final String ENVIRONMENT_PATH = "spring.andinaExchange.authentication.";
	private final String PASSWORD_REGEX = ENVIRONMENT_PATH + "password-regex";
	private final String EMAIL_REGEX = ENVIRONMENT_PATH + "email-regex";
	private final String LOGIN_ATTEMPTS = ENVIRONMENT_PATH + "login-attempts";

	private final String DEFAULT_PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{9,}$";
	private final String DEFAULT_EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

	private final Environment environment;

	private final ClientStorageAdapter clientStorage;
	private final AuthenticatorAdapter auth;

	public AuthenticatorServiceImpl(
		final Environment environment,
		final ClientStorageAdapter clientStorage,
		final AuthenticatorAdapter auth
	) {
		this.environment = environment;
		this.clientStorage = clientStorage;
		this.auth = auth;
	}

	@Override
	public @NotNull ClientToken registerClient(final @NotNull RegisterRequest request) {
		final Pattern emailPattern = Pattern.compile(environment.getProperty(EMAIL_REGEX, DEFAULT_EMAIL_REGEX));

		if (!emailPattern.matcher(request.email()).matches()) {
			throw new EmailAuthException("The email provided does not use a valid format.", request.email());
		}

		Client client = clientStorage.getClientByEmail(request.email());

		if (client != null) {
			throw new EmailAuthException("The e-mail address provided is already in use.", request.email());
		}

		final String encoded = validatePassword(request.password());

		// Null is already assigned to it when it is saved in the database, in this context
		// it is not necessary to know what its identifier is.
		client = new Client(null, request.username(), request.email(), encoded, Instant.now());
		clientStorage.saveClient(client);

		final String jwtToken = auth.generateToken(client);
		return new ClientToken(jwtToken, client.email());
	}

	@Override
	public @NotNull ClientToken authenticateClient(final @NotNull LoginRequest request) {
		auth.authenticateClient(request.identifier(), request.password());

		final Client client = clientStorage.getClientByEmail(request.identifier());

		if (client == null) {
			throw new AuthException("The user you are trying to access is not found");
		}

		final String jwtToken = auth.generateToken(client);
		return new ClientToken(jwtToken, client.email());
	}

	@Override
	public @NotNull ClientToken updateClient(
		final int clientId,
		final @NotNull RegisterRequest request
	) {
		final Client client = clientStorage.getClient(clientId);

		if (client == null) {
			throw new AuthException("No user found to update.", request.email());
		}

		final String encoded = validatePassword(request.password());

		final Client updatedClient = new Client(
			clientId,
			request.username(),
			request.email(),
			encoded,
			client.createdAt()
		);

		clientStorage.updateClient(clientId, updatedClient);
		final String jwtToken = auth.generateToken(updatedClient);
		return new ClientToken(jwtToken, request.email());
	}

	@Override
	public void recoverPassword(final @NotNull String email) {
		//TODO: Luego implementar logica que aun falta implementar el e-mail service :V
	}

	private @NotNull String validatePassword(final String password) {
		final String passwordRegex = environment.getProperty(PASSWORD_REGEX, DEFAULT_PASSWORD_REGEX);
		final Pattern passwordPattern = Pattern.compile(passwordRegex);

		if (!passwordPattern.matcher(password).matches()) {
			throw new PasswordAuthException("The password entered is incorrect", password);
		}

		return auth.encryptPassword(password);
	}

}
