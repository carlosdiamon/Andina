package edu.unbosque.adiana.database.contract.mapper;

import edu.unbosque.adiana.contract.Contract;
import edu.unbosque.adiana.contract.ContractBuilder;
import edu.unbosque.adiana.contract.ContractStatus;
import edu.unbosque.adiana.database.contract.entity.ContractEntity;
import edu.unbosque.adiana.database.exceptions.EntityMapperException;
import edu.unbosque.adiana.database.investor.entity.InvestorEntity;
import edu.unbosque.adiana.database.investor.repository.InvestorRepository;
import edu.unbosque.adiana.database.operator.entity.OperatorEntity;
import edu.unbosque.adiana.database.operator.repository.OperatorRepository;
import org.jetbrains.annotations.NotNull;

public final class ContractMapper {

	private ContractMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	/**
	 * Converts a {@link Contract} instance to a {@link ContractEntity}.
	 *
	 * @param contract the {@link Contract} object to be converted
	 * @param operatorRepository the repository used to fetch {@link OperatorEntity}
	 * @param investorRepository the repository used to fetch {@link InvestorEntity}
	 * @return the constructed {@link ContractEntity} with mapped values from the {@link Contract}
	 * @throws EntityMapperException if the operator ID from the contract is not found
	 */
	public static ContractEntity toContractEntity(
		final @NotNull Contract contract,
		final @NotNull OperatorRepository operatorRepository,
		final @NotNull InvestorRepository investorRepository
	) {
		final ContractEntity contractEntity = new ContractEntity();
		final int operatorId = contract.operatorId();
		final OperatorEntity operatorEntity = operatorRepository.getOperator(operatorId);

		if (operatorEntity == null) {
			throw new EntityMapperException("The managed operator's identifier is not found in the database: {operatorID = " + operatorId);
		}

		contractEntity.setOperator(operatorEntity);

		final Integer investorId = contract.investorId();
		if (investorId != null) {
			final InvestorEntity investorEntity = investorRepository.getInvestor(investorId);
			contractEntity.setInvestor(investorEntity);
		}

		contractEntity.setStockName(contract.name());
		contractEntity.setStatus(contract.status().name());
		contractEntity.setQuantity(contract.amount());
		contractEntity.setPrice(contract.price());
		contractEntity.setCreationDate(contract.creationAt());
		contractEntity.setAcquisitionDate(contract.acquisitionAt());

		return contractEntity;
	}

	/**
	 * Converts a {@link ContractEntity} instance to a {@link Contract} using {@link ContractBuilder}.
	 *
	 * @param entity the {@link ContractEntity} object to be converted
	 * @return the constructed {@link Contract} with mapped values from the {@link ContractEntity}
	 */
	public static Contract toContract(final @NotNull ContractEntity entity) {
		final ContractBuilder builder = Contract.builder(entity.getOperator().getId());

		if (entity.getInvestor() != null) {
			builder.setInvestorId(entity.getInvestor().getId());
		}

		if (entity.getAcquisitionDate() != null) {
			builder.setAcquisitionDate(entity.getAcquisitionDate());
		}

		builder.setQuantity(entity.getQuantity());
		builder.setStatus(ContractStatus.valueOf(entity.getStatus()));
		builder.setPrice(entity.getPrice());
		builder.setCreationDate(entity.getCreationDate());
		return builder.build();
	}

}
