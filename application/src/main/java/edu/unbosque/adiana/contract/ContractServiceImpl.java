package edu.unbosque.adiana.contract;

import edu.unbosque.adiana.contract.exceptions.ContractNotFound;
import edu.unbosque.adiana.contract.exceptions.InsufficientFundException;
import edu.unbosque.adiana.investor.Investor;
import edu.unbosque.adiana.investor.InvestorStorageAdapter;
import edu.unbosque.adiana.investor.exceptions.InvestorNotFound;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Collection;

public class ContractServiceImpl implements ContractService {

	private final InvestorStorageAdapter investorStorage;
	private final ContractStorageAdapter contractStorage;

	public ContractServiceImpl(
		final InvestorStorageAdapter investorStorage,
		final ContractStorageAdapter contractStorage
	) {
		this.investorStorage = investorStorage;
		this.contractStorage = contractStorage;
	}

	@Override
	public @NotNull Contract applyContract(final int investorId, final int contractId) {
		Investor investor = investorStorage.getInvestor(investorId);

		if (investor == null) {
			throw new InvestorNotFound("Investor not found: " + investorId);
		}

		Contract contract = contractStorage.getContract(contractId);

		if (contract == null) {
			throw new ContractNotFound("Contract not found: " + contractId);
		}

		if (investor.balance().compareTo(contract.price()) >= 0) {
			BigDecimal newBalance = investor.balance().subtract(contract.price());
			investor = new Investor(investor.clientId(), newBalance, investor.createdAt());
			investorStorage.updateInvestor(investorId, investor);

			contract = new Contract(
				contract.operatorId(),
				investorId,
				contract.name(),
				contract.status(),
				contract.price(),
				contract.amount(),
				contract.creationAt(),
				contract.acquisitionAt()
			);

			contractStorage.updateContract(contractId, contract);
		} else {
			throw new InsufficientFundException("I did not reach the funds to: id-" + investorId);
		}

		return contract;
	}

	@Override
	public @NotNull Contract getContract(final int contractId) {
		final Contract contract = contractStorage.getContract(contractId);
		if (contract == null) {
			throw new ContractNotFound("Contract not found: " + contractId);
		}
		return contract;
	}

	@Override
	public @NotNull Collection<Contract> getContractsByOperator(final int operatorId) {
		return contractStorage.getContractByOperator(operatorId);
	}

	@Override
	public @NotNull Collection<Contract> getContracts() {
		return contractStorage.getContracts();
	}
}
