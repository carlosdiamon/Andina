package edu.unbosque.adiana.database.contract.repository;

import edu.unbosque.adiana.database.contract.entity.ContractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ContractRepository {

	void saveContract(final @NotNull ContractEntity contract);

	void updateContract(
		final int contractId,
		final @NotNull ContractEntity updatedContract
	);

	void deleteContract(final int contractId);

	@Nullable ContractEntity getContract(final int contractId);

	@NotNull Collection<ContractEntity> getContractsByInvestor(final int investorId);

	@NotNull Collection<ContractEntity> getContracts();

}
