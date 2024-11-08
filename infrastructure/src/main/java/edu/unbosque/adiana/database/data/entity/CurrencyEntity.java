package edu.unbosque.adiana.database.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "currency", schema = "public")
public class CurrencyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_id_gen")
	@SequenceGenerator(
		name = "currency_id_gen",
		sequenceName = "currency_currency_id_seq",
		allocationSize = 1
	)
	@Column(name = "currency_id", nullable = false)
	private Integer id;

	@Column(name = "code", nullable = false, length = 3)
	private String code;

	@Column(name = "symbol", nullable = false, length = 10)
	private String symbol;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}