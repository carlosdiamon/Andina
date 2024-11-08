package edu.unbosque.adiana.database.transaction.entity;

import edu.unbosque.adiana.database.contract.entity.ContractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transaction", schema = "public")
public class Transaction {

	@Id
	@ColumnDefault("nextval('transaction_transaction_id_seq')")
	@Column(name = "transaction_id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "contract_id")
	private ContractEntity contractEntity;

	@Column(name = "transaction_type", nullable = false, length = 20)
	private String transactionType;

	@Column(name = "amount", nullable = false, precision = 15, scale = 2)
	private BigDecimal amount;

	@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(name = "transaction_date")
	private Instant transactionDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ContractEntity getContract() {
		return contractEntity;
	}

	public void setContract(ContractEntity contractEntity) {
		this.contractEntity = contractEntity;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Instant getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Instant transactionDate) {
		this.transactionDate = transactionDate;
	}
}