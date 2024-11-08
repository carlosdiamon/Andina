package edu.unbosque.adiana.database.contract;

import edu.unbosque.adiana.contract.ContractAcceptance;
import edu.unbosque.adiana.contract.ContractAcceptanceStorageAdapter;
import edu.unbosque.adiana.database.client.repository.ClientRepository;
import edu.unbosque.adiana.database.contract.entity.ContractAcceptanceEntity;
import edu.unbosque.adiana.database.contract.mapper.ContractAcceptanceMapper;
import edu.unbosque.adiana.database.contract.repository.ContractAcceptanceRepository;
import edu.unbosque.adiana.database.contract.repository.ContractRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ContractAccStorageAdapterImpl implements ContractAcceptanceStorageAdapter {

	private final ContractAcceptanceRepository contractAccRepository;
	private final ContractRepository contractRepository;
	private final ClientRepository clientRepository;

	public ContractAccStorageAdapterImpl(
		final ContractAcceptanceRepository contractAccRepository,
		final ContractRepository contractRepository,
		final ClientRepository clientRepository
	) {
		this.contractAccRepository = contractAccRepository;
		this.contractRepository = contractRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	public void saveContract(final @NotNull ContractAcceptance contract) {
		contractAccRepository.saveContract(ContractAcceptanceMapper.toContractAcceptanceEntity(
			contract,
			contractRepository,
			clientRepository
		));
	}

	@Override
	public void updateContract(
		final int contractId,
		final @NotNull ContractAcceptance updatedContract
	) {
		final ContractAcceptanceEntity entity = ContractAcceptanceMapper.toContractAcceptanceEntity(
			updatedContract,
			contractRepository,
			clientRepository
		);

		contractAccRepository.updateContract(contractId, entity);
	}

	@Override
	public void deleteContract(final int contractId) {
		contractAccRepository.deleteContract(contractId);
	}

	@Override
	public @Nullable ContractAcceptance getContract(final int contractId) {
		ContractAcceptance contract = null;
		final ContractAcceptanceEntity entity = contractAccRepository.getContract(contractId);

		if (entity != null) {
			contract = ContractAcceptanceMapper.toContractAcceptance(entity);
		}

		return contract;
	}

	@Override
	public @NotNull Collection<ContractAcceptance> getContractsByOperator(final int operatorId) {
		return contractAccRepository.getContractsByOperator(operatorId).stream()
			       .map(ContractAcceptanceMapper::toContractAcceptance)
			       .toList();
	}

	@Override
	public @NotNull Collection<ContractAcceptance> getContracts() {
		return contractAccRepository.getContracts().stream()
			              .map(ContractAcceptanceMapper::toContractAcceptance)
			              .toList();
	}
}
