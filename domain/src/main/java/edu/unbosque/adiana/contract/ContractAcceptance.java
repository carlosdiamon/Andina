package edu.unbosque.adiana.contract;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public record ContractAcceptance(
	Integer id,
	@NotNull Contract contract,
	@NotNull Integer operatorId,
	@NotNull Instant createdAt
) {
	@Override
	public String toString() {
		return "ContractAcceptance{" +
		       "id=" + id +
		       ", contract=" + contract +
		       ", operatorId=" + operatorId +
		       ", createdAt=" + createdAt +
		       '}';
	}
}
