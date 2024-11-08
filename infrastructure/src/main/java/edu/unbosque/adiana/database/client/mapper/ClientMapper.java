package edu.unbosque.adiana.database.client.mapper;

import edu.unbosque.adiana.client.Client;
import edu.unbosque.adiana.database.client.entity.ClientEntity;
import org.jetbrains.annotations.NotNull;

public final class ClientMapper {

	private ClientMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	/**
	 * Converts a domain Client object to a persistent ClientEntity object.
	 * @param client the domain object to convert
	 * @return a new ClientEntity object
	 */
	public static @NotNull ClientEntity toClientEntity(final @NotNull Client client) {
		final ClientEntity entity = new ClientEntity();
		entity.setUsername(client.username());
		entity.setEmail(client.email());
		entity.setPassword(client.password());
		entity.setCreatedAt(client.createdAt());
		return entity;
	}

	/**
	 * Converts a persistent ClientEntity object to a domain Client object.
	 * @param entity the entity object to convert
	 * @return a new Client object
	 */
	public static @NotNull Client toClient(final @NotNull ClientEntity entity) {
		return new Client(
			entity.getId(),
			entity.getUsername(),
			entity.getEmail(),
			entity.getPassword(),
			entity.getCreatedAt()
		);
	}

}
