package edu.unbosque.adiana.contract;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ContractStorageAdapter {

	@NotNull Contract saveContract(final @NotNull Contract contract);

	void updateContract(
		final int contractId,
		final @NotNull Contract contract
	);

	void deleteContract(final int contractId);

	@Nullable Contract getContract(final int contractId);

	@NotNull Collection<Contract> getContractsByInvestor(final int investorId);

	@NotNull Collection<Contract> getContracts();

}
