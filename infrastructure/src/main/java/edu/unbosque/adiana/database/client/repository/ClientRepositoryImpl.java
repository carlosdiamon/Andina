package edu.unbosque.adiana.database.client.repository;

import edu.unbosque.adiana.database.client.entity.ClientEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class ClientRepositoryImpl implements ClientRepository {

	private final SessionFactory sessionFactory;

	public ClientRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveClient(final @NotNull ClientEntity client) {
		sessionFactory.getCurrentSession().persist(client);
	}

	@Override
	public void updateClient(final int clientId, final @NotNull ClientEntity updatedClient) {
		final ClientEntity client = getClientById(clientId);
		if (client != null) {
			client.setUsername(updatedClient.getUsername());
			client.setEmail(updatedClient.getEmail());
			sessionFactory.getCurrentSession().merge(client);
		} else {
			throw new EntityNotFoundException("Client with ID " + clientId + " not found");
		}
	}

	@Override
	public void deleteClient(int clientId) {
		final ClientEntity client = getClientById(clientId);
		if (client != null) {
			sessionFactory.getCurrentSession().remove(client);
		}
	}

	@Override
	public @Nullable ClientEntity getClientById(int clientId) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(ClientEntity.class, clientId);
	}

	@Override
	public @Nullable ClientEntity getClientByEmail(final @NotNull String email) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM ClientEntity c WHERE c.email = :email", ClientEntity.class)
			                      .setParameter("email", email)
			                      .uniqueResult();
	}

	@Override
	public @NotNull Collection<ClientEntity> getClients() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM ClientEntity", ClientEntity.class).getResultList();
	}

}
