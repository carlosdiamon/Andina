package edu.unbosque.adiana.stock;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public record StockSeries(
	@NotNull BigDecimal open,
	@NotNull BigDecimal high,
	@NotNull BigDecimal low,
	@NotNull BigDecimal close,
	@NotNull BigDecimal volume
) {

	@Override
	public String toString() {
		return "StockSeries{" +
		       "open=" + open +
		       ", high=" + high +
		       ", low=" + low +
		       ", close=" + close +
		       ", volume=" + volume +
		       '}';
	}
}
