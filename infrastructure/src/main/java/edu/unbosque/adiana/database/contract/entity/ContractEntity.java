package edu.unbosque.adiana.database.contract.entity;

import edu.unbosque.adiana.contract.status.ContractStatus;
import edu.unbosque.adiana.database.PersistenceEntity;
import edu.unbosque.adiana.database.client.entity.ClientEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "contract", schema = "public")
public class ContractEntity implements PersistenceEntity {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_id_gen")
	@SequenceGenerator(
		name = "contract_id_gen",
		sequenceName = "contract_contract_id_seq",
		allocationSize = 1
	) @Column(name = "contract_id", nullable = false) private Integer id;

	@ManyToOne(fetch = FetchType.LAZY) @OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(name = "investor_id") private ClientEntity investor;

	@Column(name = "date", nullable = false) private LocalDate date;

	@Column(name = "company", nullable = false, length = 100) private String company;

	@Column(name = "stock_value", precision = 10, scale = 2) private BigDecimal stockValue;

	@Column(name = "quantity", nullable = false) private Integer quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private ContractStatus status;

	@ColumnDefault("CURRENT_TIMESTAMP") @Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@ColumnDefault("CURRENT_TIMESTAMP") @Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }

	public ClientEntity getInvestor() { return investor; }

	public void setInvestor(ClientEntity investor) { this.investor = investor; }

	public LocalDate getDate() { return date; }

	public void setDate(LocalDate date) { this.date = date; }

	public String getCompany() { return company; }

	public void setCompany(String company) { this.company = company; }

	public BigDecimal getStockValue() { return stockValue; }

	public void setStockValue(BigDecimal stockValue) { this.stockValue = stockValue; }

	public Integer getQuantity() { return quantity; }

	public void setQuantity(Integer quantity) { this.quantity = quantity; }

	public ContractStatus getStatus() { return status; }

	public void setStatus(ContractStatus status) { this.status = status; }

	public Instant getCreatedAt() { return createdAt; }

	public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

	public Instant getUpdatedAt() { return updatedAt; }

	public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

	@Override
	public int getIdentifier() {
		return this.id;
	}
}