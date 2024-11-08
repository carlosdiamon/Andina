package edu.unbosque.adiana.database.contract.mapper;

import edu.unbosque.adiana.contract.Contract;
import edu.unbosque.adiana.contract.ContractAcceptance;
import edu.unbosque.adiana.database.client.entity.ClientEntity;
import edu.unbosque.adiana.database.client.repository.ClientRepository;
import edu.unbosque.adiana.database.contract.entity.ContractAcceptanceEntity;
import edu.unbosque.adiana.database.contract.entity.ContractEntity;
import edu.unbosque.adiana.database.contract.repository.ContractRepository;
import edu.unbosque.adiana.database.exceptions.EntityMapperException;
import org.jetbrains.annotations.NotNull;

public final class ContractAcceptanceMapper {

	public ContractAcceptanceMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static @NotNull ContractAcceptanceEntity toContractAcceptanceEntity(
		final @NotNull ContractAcceptance contractAcceptance,
		final @NotNull ContractRepository contractRepository,
		final @NotNull ClientRepository clientRepository
	) {
		final int contractId = contractAcceptance.contract().id();
		final ContractEntity contractEntity = contractRepository.getContract(contractId);

		if (contractEntity == null) {
			throw new EntityMapperException("The contractAcceptance could not be mapped because the contract id was not found: " + contractId);
		}

		final ClientEntity clientEntity = clientRepository.getClientById(contractAcceptance.operatorId());

		if (clientEntity == null) {
			throw new EntityMapperException("The ContractAccepted could not be mapped because the id operator was not found:" + contractAcceptance.operatorId());
		}

		final ContractAcceptanceEntity entity = new ContractAcceptanceEntity();
		entity.setContract(contractEntity);
		entity.setOperator(clientEntity);
		entity.setId(contractAcceptance.id());
		entity.setCreatedAt(contractAcceptance.createdAt());
		return entity;
	}

	public static @NotNull ContractAcceptance toContractAcceptance(
		final @NotNull ContractAcceptanceEntity entity
	) {
		return new ContractAcceptance(
			entity.getId(),
			ContractMapper.toContract(entity.getContract()),
			entity.getOperator().getId(),
			entity.getCreatedAt()
		);
	}

}
