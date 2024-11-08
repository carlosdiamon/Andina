package edu.unbosque.adiana.investor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface InvestorStorageAdapter {

	void saveInvestor(final @NotNull Investor investor);

	void updateInvestor(
		final int investorId,
		final @NotNull Investor investor
	);

	void deleteInvestor(final int investorId);

	@Nullable Investor getInvestor(final int investorId);

	@NotNull Collection<Investor> getInvestors();

}
