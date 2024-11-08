package edu.unbosque.adiana.operator;

import edu.unbosque.adiana.operator.data.City;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public record Operator(
	@NotNull String name,
	@NotNull String mic,
	@NotNull City city,
	@NotNull String website,
	@NotNull String currencyIcon,
	@NotNull BigDecimal commissionRate
) {

	@Override
	public String toString() {
		return "Operator{" +
		       "name='" + name + '\'' +
		       ", mic='" + mic + '\'' +
		       ", city=" + city +
		       ", website='" + website + '\'' +
		       ", currencyIcon='" + currencyIcon + '\'' +
		       ", commissionRate=" + commissionRate +
		       '}';
	}
}
