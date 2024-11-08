package edu.unbosque.adiana.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ClientStorageAdapter {

	void saveClient(final @NotNull Client client);

	void updateClient(
		final int clientId,
		final @NotNull Client client
	);

	void deleteClient(final int clientId);

	@Nullable Client getClient(final int clientId);

	@Nullable Client getClientByEmail(final @NotNull String email);

	@NotNull Collection<Client> getClients();

}
