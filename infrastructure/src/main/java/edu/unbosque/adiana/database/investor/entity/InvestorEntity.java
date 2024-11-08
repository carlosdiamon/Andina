package edu.unbosque.adiana.database.investor.entity;

import edu.unbosque.adiana.database.PersistenceEntity;
import edu.unbosque.adiana.database.client.entity.ClientEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "investor", schema = "public")
public class InvestorEntity
	implements PersistenceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investor_id_gen")
	@SequenceGenerator(
		name = "investor_id_gen",
		sequenceName = "investor_investor_id_seq",
		allocationSize = 1
	)
	@Column(name = "investor_id", nullable = false)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "client_id")
	private ClientEntity client;

	@ColumnDefault("0.00")
	@Column(name = "balance", nullable = false, precision = 15, scale = 2)
	private BigDecimal balance;

	@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(name = "registration_date")
	private Instant registrationDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Instant getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Instant registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public int getIdentifier() {
		return this.id;
	}
}