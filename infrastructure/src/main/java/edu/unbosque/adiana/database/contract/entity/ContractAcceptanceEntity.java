package edu.unbosque.adiana.database.contract.entity;

import edu.unbosque.adiana.database.PersistenceEntity;
import edu.unbosque.adiana.database.client.entity.ClientEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "contract_acceptance", schema = "public")
public class ContractAcceptanceEntity implements PersistenceEntity {

	@Id
	@ColumnDefault("nextval('contract_acceptance_contract_acceptance_id_seq')")
	@Column(name = "contract_acceptance_id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "contract_id")
	private ContractEntity contractEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(name = "operator_id")
	private ClientEntity operator;

	@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

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

	public ClientEntity getOperator() {
		return operator;
	}

	public void setOperator(ClientEntity operator) {
		this.operator = operator;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int getIdentifier() {
		return this.id;
	}

}