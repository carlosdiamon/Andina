package edu.unbosque.adiana.contract;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

public final class ContractBuilder {

	private final int operatorId;

	private Integer investorId;
	private String stockName;
	private int quantity;
	private BigDecimal price;
	private ContractStatus status;
	private Instant creationDate;
	private Instant acquisitionDate;

	public ContractBuilder(final @NotNull Contract contract) {
		this.investorId = contract.investorId();
		this.operatorId = contract.operatorId();
		this.stockName = contract.name();
		this.quantity = contract.amount();
		this.price = contract.price();
		this.status = contract.status();
		this.creationDate = contract.creationAt();
		this.acquisitionDate = contract.acquisitionAt();
	}

	public ContractBuilder(final int operatorId) {
		if (operatorId < 0) {
			throw new IllegalArgumentException("operatorId must be greater than or equal to 0.");
		}
		this.operatorId = operatorId;
	}

	public @NotNull ContractBuilder setInvestorId(final Integer investorId) {
		this.investorId = investorId;
		return this;
	}

	public @NotNull ContractBuilder setStockName(final String stockName) {
		this.stockName = stockName;
		return this;
	}

	public @NotNull ContractBuilder setQuantity(final int quantity) {
		this.quantity = quantity;
		return this;
	}

	public @NotNull ContractBuilder setPrice(final BigDecimal price) {
		this.price = price;
		return this;
	}

	public @NotNull ContractBuilder setStatus(final ContractStatus status) {
		this.status = status;
		return this;
	}

	public @NotNull ContractBuilder setCreationDate(final Instant creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public @NotNull ContractBuilder setAcquisitionDate(final Instant acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
		return this;
	}

	public @NotNull Contract build() {
		return new Contract(
			operatorId,
			investorId,
			stockName,
			status,
			price,
			quantity,
			creationDate,
			acquisitionDate
		);
	}
}
