package edu.unbosque.adiana.database.client;

import edu.unbosque.adiana.client.Client;
import edu.unbosque.adiana.client.ClientStorageAdapter;
import edu.unbosque.adiana.database.client.entity.ClientEntity;
import edu.unbosque.adiana.database.client.mapper.ClientMapper;
import edu.unbosque.adiana.database.client.repository.ClientRepository;
import edu.unbosque.adiana.database.role.repository.RoleRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Function;

@Component
public class ClientStorageAdapterImpl implements ClientStorageAdapter {

	private final ClientRepository clientRepository;
	private final RoleRepository roleRepository;

	public ClientStorageAdapterImpl(
		final ClientRepository clientRepository,
		final RoleRepository roleRepository
	) {
		this.clientRepository = clientRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void saveClient(final @NotNull Client client) {
		final ClientEntity entity = ClientMapper.toClientEntity(client, roleRepository);
		clientRepository.saveClient(entity);
	}

	@Override
	public void updateClient(
		final int clientId,
		final @NotNull Client client
	) {
		clientRepository.updateClient(clientId, ClientMapper.toClientEntity(client, roleRepository));
	}

	@Override
	public void deleteClient(final int clientId) {
		clientRepository.deleteClient(clientId);
	}

	@Override
	public @Nullable Client getClient(final int clientId) {
		return getClientBy(clientRepository::getClientById, clientId);
	}

	@Override
	public @Nullable Client getClientByEmail(final @NotNull String email) {
		return getClientBy(clientRepository::getClientByEmail, email);
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
		return clientRepository.getClients().stream()
			       .map(ClientMapper::toClient)
			       .toList();
	}
}
