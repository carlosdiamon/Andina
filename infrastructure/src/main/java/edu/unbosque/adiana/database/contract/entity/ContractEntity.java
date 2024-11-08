package edu.unbosque.adiana.database.contract.entity;

import edu.unbosque.adiana.database.PersistenceEntity;
import edu.unbosque.adiana.database.investor.entity.InvestorEntity;
import edu.unbosque.adiana.database.operator.entity.OperatorEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "contract", schema = "public")
public class ContractEntity implements PersistenceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_id_gen")
	@SequenceGenerator(
		name = "contract_id_gen",
		sequenceName = "contract_contract_id_seq",
		allocationSize = 1
	)
	@Column(name = "contract_id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "investor_id")
	private InvestorEntity investor;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "operator_id")
	private OperatorEntity operator;

	@Column(name = "stock_name", nullable = false, length = 150)
	private String stockName;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@ColumnDefault("'active'")
	@Column(name = "status", nullable = false, length = 20)
	private String status;

	@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(name = "creation_date")
	private Instant creationDate;

	@Column(name = "acquisition_date")
	private Instant acquisitionDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InvestorEntity getInvestor() {
		return investor;
	}

	public void setInvestor(InvestorEntity investor) {
		this.investor = investor;
	}

	public OperatorEntity getOperator() {
		return operator;
	}

	public void setOperator(OperatorEntity operator) {
		this.operator = operator;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public Instant getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(Instant acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	@Override
	public int getIdentifier() {
		return this.id;
	}
}