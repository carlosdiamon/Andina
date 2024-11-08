package edu.unbosque.adiana.database.investor.repository;

import edu.unbosque.adiana.database.investor.entity.InvestorEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface InvestorRepository {

	void saveInvestor(final @NotNull InvestorEntity investor);

	void updateInvestor(
		final int investorId,
		final @NotNull InvestorEntity updatedInvestor
	);

	void deleteInvestor(final int investorId);

	@Nullable InvestorEntity getInvestor(final int investorId);

	@NotNull Collection<InvestorEntity> getAllInvestors();

}
