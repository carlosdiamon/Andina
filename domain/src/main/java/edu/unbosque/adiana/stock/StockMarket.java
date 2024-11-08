package edu.unbosque.adiana.stock;

import java.util.ArrayList;
import java.util.List;

public class StockMarket {

	private final String company;
	private final List<StockSeries> series;

	public StockMarket(final String company) {
		this.company = company;
		this.series = new ArrayList<StockSeries>();
	}

	public void addSeries(final StockSeries series) {
		this.series.add(series);
	}

	public List<StockSeries> series() {
		return series;
	}

	public String company() {
		return company;
	}

	@Override
	public String toString() {
		return "StockMarket{" +
		       "company='" + company + '\'' +
		       ", series=" + series +
		       '}';
	}
}
