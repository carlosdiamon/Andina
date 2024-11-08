package edu.unbosque.adiana.contract;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ContractAcceptanceStorageAdapter {

	void saveContract(final @NotNull ContractAcceptance contract);

	void updateContract(
		final int contractId,
		final @NotNull ContractAcceptance updatedContract
	);

	void deleteContract(final int contractId);

	@Nullable ContractAcceptance getContract(final int contractId);

	@NotNull Collection<ContractAcceptance> getContractsByOperator(final int operatorId);

	@NotNull Collection<ContractAcceptance> getContracts();


}
