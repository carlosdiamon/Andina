package edu.unbosque.adiana.contract;

import edu.unbosque.adiana.contract.status.ContractStatus;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record Contract(
	Integer id,
	@NotNull Integer investorId,
	@NotNull LocalDate date,
	@NotNull String company,
	@NotNull BigDecimal stockValue,
	int quantity,
	@NotNull ContractStatus status,
	@NotNull Instant createdAt,
	@NotNull Instant updatedAt
) {

	@Override
	public String toString() {
		return "Contract{" +
		       "id=" + id +
		       ", investorId=" + investorId +
		       ", date=" + date +
		       ", company='" + company + '\'' +
		       ", stockValue=" + stockValue +
		       ", quantity=" + quantity +
		       ", status=" + status +
		       ", createdAt=" + createdAt +
		       ", updatedAt=" + updatedAt +
		       '}';
	}
}
