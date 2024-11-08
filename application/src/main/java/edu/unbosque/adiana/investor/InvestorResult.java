package edu.unbosque.adiana.investor;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public record InvestorResult(
	int clientId,
	@NotNull String username,
	@NotNull String email,
	@NotNull BigDecimal balance
) {

	@Override
	public String toString() {
		return "InvestorResult{" +
		       "clientId=" + clientId +
		       ", username='" + username + '\'' +
		       ", email='" + email + '\'' +
		       ", balance=" + balance +
		       '}';
	}
}
