package edu.unbosque.adiana.database.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "country", schema = "public")
public class CountryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_gen")
	@SequenceGenerator(
		name = "country_id_gen",
		sequenceName = "country_country_id_seq",
		allocationSize = 1
	)
	@Column(name = "country_id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "economic_status", length = Integer.MAX_VALUE)
	private String economicStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEconomicStatus() {
		return economicStatus;
	}

	public void setEconomicStatus(String economicStatus) {
		this.economicStatus = economicStatus;
	}

}