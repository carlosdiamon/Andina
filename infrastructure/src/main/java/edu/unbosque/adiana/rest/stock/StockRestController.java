package edu.unbosque.adiana.rest.stock;

import edu.unbosque.adiana.rest.AlphaVantageService;
import edu.unbosque.adiana.stock.StockMarket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockRestController {

	private AlphaVantageService service;

	private Logger loggger = LoggerFactory.getLogger(StockRestController.class);

	public StockRestController(final AlphaVantageService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<String> getStock(@PathVariable String id){
		final StockMarket market;
		try {
			market = service.getStockData(id);
		} catch (Exception e) {
			loggger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(market.toString());
	}

}
