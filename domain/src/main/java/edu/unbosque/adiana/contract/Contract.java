package edu.unbosque.adiana.contract;

import edu.unbosque.adiana.operator.Operator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.Instant;

public record Contract(
	@NotNull Integer operatorId,
	@Nullable Integer investorId,
	@NotNull String name,
	@NotNull ContractStatus status,
	@NotNull BigDecimal price,
	int amount,
	@NotNull Instant creationAt,
	@Nullable Instant acquisitionAt
) {

	public static ContractBuilder builder(Contract contract) {
		return new ContractBuilder(contract);
	}

	public static ContractBuilder builder(int operatorId) {
		return new ContractBuilder(operatorId);
	}

	@Override
	public String toString() {
		return "Contract{" +
		       "operatorId=" + operatorId +
		       ", investorId=" + investorId +
		       ", name='" + name + '\'' +
		       ", status=" + status +
		       ", price=" + price +
		       ", amount=" + amount +
		       ", creationAt=" + creationAt +
		       ", acquisitionAt=" + acquisitionAt +
		       '}';
	}
}
