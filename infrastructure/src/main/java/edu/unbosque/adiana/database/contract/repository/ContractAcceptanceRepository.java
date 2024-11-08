package edu.unbosque.adiana.database.contract.repository;

import edu.unbosque.adiana.database.contract.entity.ContractAcceptanceEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ContractAcceptanceRepository {

	void saveContract(final @NotNull ContractAcceptanceEntity contract);

	void updateContract(
		final int contractId,
		final @NotNull ContractAcceptanceEntity updatedContract
	);

	void deleteContract(final int contractId);

	@Nullable ContractAcceptanceEntity getContract(final int contractId);

	@NotNull Collection<ContractAcceptanceEntity> getContractsByOperator(final int investorId);

	@NotNull Collection<ContractAcceptanceEntity> getContracts();

}
