package edu.unbosque.adiana.contract.request;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContractRequest(
	int investorId,
	@NotNull LocalDate date,
	@NotNull String company,
	@NotNull BigDecimal quantity
) {

	@Override
	public String toString() {
		return "ContractRequest{" +
		       "investorId=" + investorId +
		       ", date=" + date +
		       ", company='" + company + '\'' +
		       ", quantity=" + quantity +
		       '}';
	}
}
