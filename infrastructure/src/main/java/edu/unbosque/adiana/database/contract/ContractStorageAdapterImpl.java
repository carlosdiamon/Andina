package edu.unbosque.adiana.database.contract;

import edu.unbosque.adiana.contract.Contract;
import edu.unbosque.adiana.contract.ContractStorageAdapter;
import edu.unbosque.adiana.database.contract.entity.ContractEntity;
import edu.unbosque.adiana.database.contract.mapper.ContractMapper;
import edu.unbosque.adiana.database.contract.repository.ContractRepository;
import edu.unbosque.adiana.database.investor.repository.InvestorRepository;
import edu.unbosque.adiana.database.operator.repository.OperatorRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ContractStorageAdapterImpl implements ContractStorageAdapter {

	private final ContractRepository contractRepository;
	private final OperatorRepository operatorRepository;
	private final InvestorRepository investorRepository;

	public ContractStorageAdapterImpl(
		final ContractRepository contractRepository,
		final OperatorRepository operatorRepository,
		final InvestorRepository investorRepository
	) {
		this.contractRepository = contractRepository;
		this.operatorRepository = operatorRepository;
		this.investorRepository = investorRepository;
	}

	@Override
	public void saveContract(final @NotNull Contract contract) {
		final ContractEntity entity = ContractMapper.toContractEntity(
			contract,
			operatorRepository,
			investorRepository
		);
		contractRepository.saveContract(entity);
	}

	@Override
	public void updateContract(
		final int contractId,
		final @NotNull Contract contract
	) {
		final ContractEntity entity = ContractMapper.toContractEntity(
			contract,
			operatorRepository,
			investorRepository
		);

		contractRepository.updateContract(contractId, entity);
	}

	@Override
	public void deleteContract(final int contractId) {
		contractRepository.deleteContract(contractId);
	}

	@Override
	public @Nullable Contract getContract(final int contractId) {
		Contract contract = null;
		final ContractEntity entity = contractRepository.getContract(contractId);
		if (entity != null) {
			contract = ContractMapper.toContract(entity);
		}
		return contract;
	}

	@Override
	public @NotNull Collection<Contract> getContractByOperator(final int operatorId) {
		return contractRepository.getContractsByOperator(operatorId).stream()
			       .map(ContractMapper::toContract)
			       .toList();
	}

	@Override
	public @NotNull Collection<Contract> getContracts() {
		return contractRepository.getContracts().stream()
			       .map(ContractMapper::toContract)
			       .toList();
	}
}
