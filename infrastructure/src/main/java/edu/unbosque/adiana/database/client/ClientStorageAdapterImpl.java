package edu.unbosque.adiana.database.client;

import edu.unbosque.adiana.client.Client;
import edu.unbosque.adiana.client.ClientStorageAdapter;
import edu.unbosque.adiana.database.client.entity.ClientEntity;
import edu.unbosque.adiana.database.client.mapper.ClientMapper;
import edu.unbosque.adiana.database.client.repository.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Function;

@Component
public class ClientStorageAdapterImpl implements ClientStorageAdapter {

	private final ClientRepository repository;

	public ClientStorageAdapterImpl(final ClientRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveClient(final @NotNull Client client) {
		final ClientEntity entity = ClientMapper.toClientEntity(client);
		repository.saveClient(entity);
	}

	@Override
	public void updateClient(
		final int clientId,
		final @NotNull Client client
	) {
		repository.updateClient(clientId, ClientMapper.toClientEntity(client));
	}

	@Override
	public void deleteClient(final int clientId) {
		repository.deleteClient(clientId);
	}

	@Override
	public @Nullable Client getClient(final int clientId) {
		return getClientBy(repository::getClientById, clientId);
	}

	@Override
	public @Nullable Client getClientByEmail(final @NotNull String email) {
		return getClientBy(repository::getClientByEmail, email);
	}

	private <T> @Nullable Client getClientBy(
		final @NotNull Function<T, ClientEntity> fetchFunction,
		final @NotNull T identifier
	) {
		final ClientEntity entity = fetchFunction.apply(identifier);
		return (entity != null) ? ClientMapper.toClient(entity) : null;
	}

	@Override
	public @NotNull Collection<Client> getClients() {
		return repository.getClients().stream()
			       .map(ClientMapper::toClient)
			       .toList();
	}
}
