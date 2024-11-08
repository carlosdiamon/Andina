package edu.unbosque.adiana.database.contract;

import edu.unbosque.adiana.contract.Contract;
import edu.unbosque.adiana.contract.ContractStorageAdapter;
import edu.unbosque.adiana.database.client.repository.ClientRepository;
import edu.unbosque.adiana.database.contract.entity.ContractEntity;
import edu.unbosque.adiana.database.contract.mapper.ContractMapper;
import edu.unbosque.adiana.database.contract.repository.ContractRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ContractStorageAdapterImpl implements ContractStorageAdapter {

    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;

    public ContractStorageAdapterImpl(ContractRepository contractRepository, ClientRepository clientRepository) {
        this.contractRepository = contractRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public @NotNull Contract saveContract(final @NotNull Contract contract) {
		final ContractEntity entity = ContractMapper.toContractEntity(contract, clientRepository);
		contractRepository.saveContract(entity); // xd
        return ContractMapper.toContract(entity);
    }

    @Override
    public void updateContract(final int contractId, final @NotNull Contract contract) {
        final ContractEntity entity = ContractMapper.toContractEntity(contract, clientRepository);
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
    public @NotNull Collection<Contract> getContractsByInvestor(final int investorId) {
        return contractRepository.getContractsByInvestor(investorId).stream()
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
