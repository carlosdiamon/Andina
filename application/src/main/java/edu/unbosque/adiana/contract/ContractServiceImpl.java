package edu.unbosque.adiana.contract;

import edu.unbosque.adiana.contract.exceptions.ContractNotFoundException;
import edu.unbosque.adiana.contract.request.ContractRequest;
import edu.unbosque.adiana.contract.status.ContractStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Collection;
import java.util.List;


public class ContractServiceImpl implements ContractService {

	private final ContractAcceptanceStorageAdapter contractAccStorage;
	private final ContractStorageAdapter contractStorage;

	public ContractServiceImpl(
		final ContractAcceptanceStorageAdapter contractAccStorage,
		final ContractStorageAdapter contractStorage
	) {
		this.contractAccStorage = contractAccStorage;
		this.contractStorage = contractStorage;
	}

	/*
	Lo que vas a ver aqui es yo despues de pasarse la noche de largo,
	no se me ocurre ahora otra manera de trabajar con hibernate y obtener la id que el
	esta dando, pido perdon por lo que vas a ver.
	*/

	@Override
	public @NotNull Contract registerContract(final @NotNull ContractRequest request) {
		final Instant now = Instant.now();

		Contract contract = new Contract(
				null,
				request.investorId(),
				request.date(),
				request.company(),
				request.quantity(),
				1,
				ContractStatus.PENDING,
				now,
				now
		);

		return contractStorage.saveContract(contract);
	}

	@Override
	public void updateContract(
		final int contractId,
		final @NotNull ContractRequest request
	) {
		Instant now = Instant.now();

		Contract updatedContract = new Contract(
				null,
				request.investorId(),
				request.date(),
				request.company(),
				request.quantity(),
				1,
				ContractStatus.PENDING,
				now, // este no se tiene encuenta en el update
				now
		);

		contractStorage.updateContract(contractId, updatedContract);
	}

	@Override
	public void removeContract(final int contractId) {
		contractStorage.deleteContract(contractId);
	}

	@Override
	public @Nullable Contract getContract(final int contractId) {
		return contractStorage.getContract(contractId);
	}

	@Override
	public @NotNull Collection<Contract> getContractsByInvestor(final int investorId) {
		return contractStorage.getContractsByInvestor(investorId);
	}

	@Override
	public @NotNull Collection<Contract> getContracts() {
		return contractStorage.getContracts();
	}

	@Override
	public void contractAccepted(final int contractId, final int operatorId) {
		final Contract contract = contractStorage.getContract(contractId);

		if (contract == null) {
			throw new ContractNotFoundException("The contract was not found for acceptance, the id is: " + contractId);
		}

		final Contract updatedContract = new Contract(
				null,
				contract.investorId(),
				contract.date(),
				contract.company(),
				contract.stockValue(),
				contract.quantity(),
				ContractStatus.ACCEPTED,
				contract.createdAt(),
				Instant.now()
		);
		contractStorage.updateContract(contractId, updatedContract);

		contractAccStorage.saveContract(new ContractAcceptance(null, contract, operatorId, Instant.now()));
	}

	@Override
	public @Nullable ContractAcceptance getContractAcceptance(final int contractId) {
		return contractAccStorage.getContract(contractId);
	}

	@Override
	public @NotNull Collection<ContractAcceptance> getContractAcceptancesByOperator(final int operatorId) {
		return contractAccStorage.getContractsByOperator(operatorId);
	}

	@Override
	public @NotNull Collection<ContractAcceptance> getContractAcceptances() {
		return contractAccStorage.getContracts();
	}
}
