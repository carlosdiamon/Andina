package edu.unbosque.adiana.database.investor;

import edu.unbosque.adiana.database.client.repository.ClientRepository;
import edu.unbosque.adiana.database.investor.entity.InvestorEntity;
import edu.unbosque.adiana.database.investor.mapper.InvestorMapper;
import edu.unbosque.adiana.database.investor.repository.InvestorRepository;
import edu.unbosque.adiana.investor.Investor;
import edu.unbosque.adiana.investor.InvestorStorageAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Class responsible for saving investors in the database.
 * Requires prior saving of the {@link edu.unbosque.adiana.client.Client} in the database using {@link edu.unbosque.adiana.client.ClientStorageAdapter},
 * as it may throw an {@link edu.unbosque.adiana.database.exceptions.EntityMapperException} if the client ID associated with the investor does not exist.
 */

@Component
public class InvestorStorageAdapterImpl implements InvestorStorageAdapter {

	private final InvestorRepository investorRepository;
	private final ClientRepository clientRepository;

	public InvestorStorageAdapterImpl(
		final InvestorRepository investorRepository,
		final ClientRepository clientRepository
	) {
		this.investorRepository = investorRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	public void saveInvestor(final @NotNull Investor investor) {
		investorRepository.saveInvestor(InvestorMapper.toInvestorEntity(investor, clientRepository));
	}

	@Override
	public void updateInvestor(
		final int investorId,
		final @NotNull Investor investor
	) {
		investorRepository.updateInvestor(investorId, InvestorMapper.toInvestorEntity(investor, clientRepository));
	}

	@Override
	public void deleteInvestor(final int investorId) {
		investorRepository.deleteInvestor(investorId);
	}

	@Override
	public @Nullable Investor getInvestor(final int investorId) {
		Investor investor = null;
		final InvestorEntity entity = investorRepository.getInvestor(investorId);
		if (entity != null) {
			investor = InvestorMapper.toInvestor(entity);
		}
		return investor;
	}

	@Override
	public @NotNull Collection<Investor> getInvestors() {
		return investorRepository.getAllInvestors().stream()
			       .map(InvestorMapper::toInvestor)
			       .toList();
	}
}
