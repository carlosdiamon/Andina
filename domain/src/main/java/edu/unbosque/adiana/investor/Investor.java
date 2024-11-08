package edu.unbosque.adiana.investor;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

public record Investor(
	@NotNull Integer clientId,
	@NotNull BigDecimal balance,
	@NotNull Instant createdAt
) {

	@Override
	public String toString() {
		return "Investor{" +
		       "clientId=" + clientId +
		       ", balance=" + balance +
		       ", createdAt=" + createdAt +
		       '}';
	}
}
