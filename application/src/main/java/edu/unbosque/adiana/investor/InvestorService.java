package edu.unbosque.adiana.investor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface InvestorService {

	@NotNull InvestorResult registerInvestor(final int clientId);

	@NotNull InvestorResult getInvestorData(final int investorId);

	@Nullable Investor getInvestor(final int investorId);

	@NotNull Collection<Investor> getInvestors();

}
