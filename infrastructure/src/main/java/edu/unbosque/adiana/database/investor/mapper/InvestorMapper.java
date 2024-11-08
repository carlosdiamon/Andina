package edu.unbosque.adiana.database.investor.mapper;

import edu.unbosque.adiana.database.client.entity.ClientEntity;
import edu.unbosque.adiana.database.client.repository.ClientRepository;
import edu.unbosque.adiana.database.exceptions.EntityMapperException;
import edu.unbosque.adiana.database.investor.entity.InvestorEntity;
import edu.unbosque.adiana.investor.Investor;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for mapping between {@link Investor} and {@link InvestorEntity}.
 */
public final class InvestorMapper {

	private InvestorMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	/**
	 * Converts an {@link Investor} to an {@link InvestorEntity}.
	 *
	 * @param investor the investor to convert
	 * @param clientRepository repository to find the client and assign it to the entity in the database.
	 * @return the corresponding InvestorEntity
	 */
	public static @NotNull InvestorEntity toInvestorEntity(
		final @NotNull Investor investor,
		final @NotNull ClientRepository clientRepository
		) {
		final InvestorEntity entity = new InvestorEntity();

		final ClientEntity clientEntity = clientRepository.getClientById(investor.clientId());

		if (clientEntity == null) {
			throw new EntityMapperException("The customer referred to by the Investor," + investor.clientId() + " was not found.");
		}

		entity.setBalance(investor.balance());
		entity.setClient(clientEntity);
		entity.setRegistrationDate(investor.createdAt());
		return entity;
	}

	/**
	 * Converts an {@link InvestorEntity} to an {@link Investor}.
	 *
	 * @param entity the investor entity to convert
	 * @return the corresponding Investor
	 */
	public static @NotNull Investor toInvestor(final @NotNull InvestorEntity entity) {
		return new Investor(entity.getClient().getId(), entity.getBalance(), entity.getRegistrationDate());
	}
}
