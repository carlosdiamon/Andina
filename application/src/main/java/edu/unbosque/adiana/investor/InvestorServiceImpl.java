package edu.unbosque.adiana.investor;

import edu.unbosque.adiana.client.Client;
import edu.unbosque.adiana.client.ClientStorageAdapter;
import edu.unbosque.adiana.client.exceptions.ClientNotFoundException;
import edu.unbosque.adiana.investor.exceptions.InvestorNotFound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;
import java.util.Collection;

public class InvestorServiceImpl implements InvestorService {

	private final String ENVIRONMENT_PATH = "spring.andinaExchange.investor-service";
	private final String INITIAL_BALANCE = ENVIRONMENT_PATH + ".initial-balance";

	private final ClientStorageAdapter clientStorage;
	private final InvestorStorageAdapter investorStorage;
	private final Environment environment;

	public InvestorServiceImpl(
		final ClientStorageAdapter clientStorage,
		final InvestorStorageAdapter investorStorage,
		final Environment environment
	) {
		this.clientStorage = clientStorage;
		this.investorStorage = investorStorage;
		this.environment = environment;
	}

	@Override
	public @NotNull InvestorResult registerInvestor(final int clientId) {
		final Client client = clientStorage.getClient(clientId);
		if (client == null) {
			throw new ClientNotFoundException("The client id was not found: " + clientId);
		}

		final BigDecimal balance = environment.getProperty(INITIAL_BALANCE, BigDecimal.class, BigDecimal.ZERO);

		investorStorage.saveInvestor(new Investor(client.id(), balance, client.createdAt()));
		return new InvestorResult(clientId, client.username(), client.email(), balance);
	}

	@Override
	public @NotNull InvestorResult getInvestorData(final int investorId) {
		final Investor investor = investorStorage.getInvestor(investorId);
		if (investor == null) {
			throw new InvestorNotFound("The investor id was not found: " + investorId);
		}
		final Client client = clientStorage.getClient(investor.clientId());

		if (client == null) {
			throw new ClientNotFoundException("No customer id found: " + investor.clientId() +
			                                  " this entity has an erroneous id of a customer."
			);
		}
		return new InvestorResult(client.id(), client.username(), client.email(), investor.balance());
	}

	@Override
	public @Nullable Investor getInvestor(final int investorId) {
		return investorStorage.getInvestor(investorId);
	}

	@Override
	public @NotNull Collection<Investor> getInvestors() {
		return investorStorage.getInvestors();
	}
}
