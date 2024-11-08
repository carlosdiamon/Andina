package edu.unbosque.adiana.database.operator.entity;

import edu.unbosque.adiana.database.PersistenceEntity;
import edu.unbosque.adiana.database.data.entity.CityEntity;
import edu.unbosque.adiana.database.data.entity.CountryEntity;
import edu.unbosque.adiana.database.data.entity.CurrencyEntity;
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

@Entity
@Table(name = "operator", schema = "public")
public class OperatorEntity implements PersistenceEntity {

	@Id
	@ColumnDefault("nextval('operator_operator_id_seq')")
	@Column(name = "operator_id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "mic", nullable = false, length = 50)
	private String mic;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(name = "country_id")
	private CountryEntity countryEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(name = "city_id")
	private CityEntity cityEntity;

	@Column(name = "website", length = 150)
	private String website;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currency_code", referencedColumnName = "code")
	private CurrencyEntity currencyEntityCode;

	@ColumnDefault("0.00")
	@Column(name = "commission_rate", nullable = false, precision = 5, scale = 2)
	private BigDecimal commissionRate;

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

	public String getMic() {
		return mic;
	}

	public void setMic(String mic) {
		this.mic = mic;
	}

	public CountryEntity getCountry() {
		return countryEntity;
	}

	public void setCountry(CountryEntity countryEntity) {
		this.countryEntity = countryEntity;
	}

	public CityEntity getCity() {
		return cityEntity;
	}

	public void setCity(CityEntity cityEntity) {
		this.cityEntity = cityEntity;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public CurrencyEntity getCurrencyCode() {
		return currencyEntityCode;
	}

	public void setCurrencyCode(CurrencyEntity currencyEntityCode) {
		this.currencyEntityCode = currencyEntityCode;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	@Override
	public int getIdentifier() {
		return this.id;
	}
}