package edu.unbosque.adiana.contract;

import edu.unbosque.adiana.contract.request.ContractRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ContractService {

	@NotNull Contract registerContract(final @NotNull ContractRequest request);

	void updateContract(
		final int contractId,
		final @NotNull ContractRequest request
	);

	void removeContract(final int contractId);

	@Nullable Contract getContract(final int contractId);

	@NotNull Collection<Contract> getContractsByInvestor(final int investorId);

	@NotNull Collection<Contract> getContracts();

	void contractAccepted(
		final int contractId,
		final int operatorId
	);

	@Nullable ContractAcceptance getContractAcceptance(final int contractId);

	@NotNull Collection<ContractAcceptance> getContractAcceptancesByOperator(final int operatorId);

	@NotNull Collection<ContractAcceptance> getContractAcceptances();

}
