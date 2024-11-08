package edu.unbosque.adiana.database.data.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "city", schema = "public")
public class CityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_gen")
	@SequenceGenerator(name = "city_id_gen", sequenceName = "city_city_id_seq", allocationSize = 1)
	@Column(name = "city_id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "country_id")
	private CountryEntity countryEntity;

	@Column(name = "economic_indicators", length = Integer.MAX_VALUE)
	private String economicIndicators;

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

	public CountryEntity getCountry() {
		return countryEntity;
	}

	public void setCountry(CountryEntity countryEntity) {
		this.countryEntity = countryEntity;
	}

	public String getEconomicIndicators() {
		return economicIndicators;
	}

	public void setEconomicIndicators(String economicIndicators) {
		this.economicIndicators = economicIndicators;
	}

}