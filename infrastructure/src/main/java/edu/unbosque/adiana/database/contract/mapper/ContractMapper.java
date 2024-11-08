package edu.unbosque.adiana.database.contract.mapper;

import edu.unbosque.adiana.contract.Contract;
import edu.unbosque.adiana.database.client.entity.ClientEntity;
import edu.unbosque.adiana.database.client.repository.ClientRepository;
import edu.unbosque.adiana.database.contract.entity.ContractEntity;
import edu.unbosque.adiana.database.exceptions.EntityMapperException;
import org.jetbrains.annotations.NotNull;

public final class ContractMapper {

	private ContractMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static @NotNull ContractEntity toContractEntity(
		final @NotNull Contract contract,
		final @NotNull ClientRepository clientRepository
	) {
		final ClientEntity clientEntity = clientRepository.getClientById(contract.id());

		if (clientEntity == null) {
			throw new EntityMapperException("The client was not found in the database, the id is: " + contract.id());
		}

		final ContractEntity entity = new ContractEntity();
		entity.setInvestor(clientEntity);
		entity.setQuantity(contract.quantity());
		entity.setStatus(contract.status());
		entity.setCreatedAt(contract.createdAt());
		entity.setUpdatedAt(contract.updatedAt());
		entity.setDate(contract.date());
		return entity;
	}

	public static @NotNull Contract toContract(final @NotNull ContractEntity entity) {
		return new Contract(
			entity.getId(),
			entity.getInvestor().getId(),
			entity.getDate(),
			entity.getCompany(),
			entity.getStockValue(),
			entity.getQuantity(),
			entity.getStatus(),
			entity.getCreatedAt(),
			entity.getUpdatedAt()
		);
	}

}
