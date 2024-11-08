package edu.unbosque.adiana.database.client.repository;

import edu.unbosque.adiana.database.client.entity.ClientEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ClientRepository {

	void saveClient(final @NotNull ClientEntity client);

	void updateClient(
		final int clientId,
		final @NotNull ClientEntity updatedClient
	);

	void deleteClient(final int clientId);

	@Nullable ClientEntity getClientById(final int clientId);

	@Nullable ClientEntity getClientByEmail(final @NotNull String email);

	@NotNull Collection<ClientEntity> getClients();

}
