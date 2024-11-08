package edu.unbosque.adiana.rest;

import edu.unbosque.adiana.security.wrapper.DotenvWrapper;
import edu.unbosque.adiana.stock.StockMarket;
import edu.unbosque.adiana.stock.StockSeries;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class AlphaVantageService {

	private final String KEY = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=%s&apikey=%s";
	private final DotenvWrapper env;

	public AlphaVantageService(final DotenvWrapper env) { this.env = env; }

	public StockMarket getStockData(String symbol) throws Exception {
		String apiKey = env.getString("API_TOKEN");
		String urlStr = String.format(KEY, symbol, apiKey);

		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + responseCode);
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		connection.disconnect();

		// Parse and return the stock data
		return parseStockData(symbol, response.toString());
	}


	private StockMarket parseStockData(String symbol, String jsonResponse) {
		StockMarket stockMarket = new StockMarket(symbol);

		String timeSeriesKey = "\"Weekly Time Series\": {";
		int start = jsonResponse.indexOf(timeSeriesKey) + timeSeriesKey.length();
		int end = jsonResponse.indexOf("}", start) + 1;
		String timeSeriesData = jsonResponse.substring(start, end);

		String[] entries = timeSeriesData.split("},\\s*\"");
		for (String entry : entries) {
			int dateEnd = entry.indexOf("\":");
			if (dateEnd == -1) continue;

			StockSeries series = stockSeries(entry, dateEnd);
			stockMarket.addSeries(series);
		}

		return stockMarket;
	}

	private static @NotNull StockSeries stockSeries(String entry, int dateEnd) {
		String data = entry.substring(dateEnd + 2)
			              .replace("{", "")
			              .replace("}", "")
			              .replace("\"", "");

		Map<String, BigDecimal> values = new HashMap<>();
		for (String pair : data.split(",")) {
			String[] keyValue = pair.split(":");
			if (keyValue.length == 2) {
				String key = keyValue[0].trim();
				BigDecimal value = new BigDecimal(keyValue[1].trim());
				values.put(key, value);
			}
		}

		return new StockSeries(
			values.get("1. open"),
			values.get("2. high"),
			values.get("3. low"),
			values.get("4. close"),
			values.get("5. volume")
		);
	}
}
