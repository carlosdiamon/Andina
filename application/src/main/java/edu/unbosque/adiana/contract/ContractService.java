package edu.unbosque.adiana.contract;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ContractService {

	@NotNull Contract applyContract(
		final int investorId,
		final int contractId
	);

	@NotNull Contract getContract(final int contractId);

	@NotNull Collection<Contract> getContractsByOperator(final int operatorId);

	@NotNull Collection<Contract> getContracts();

}
