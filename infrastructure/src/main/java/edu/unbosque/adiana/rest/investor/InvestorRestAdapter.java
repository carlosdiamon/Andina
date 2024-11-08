package edu.unbosque.adiana.rest.investor;

import edu.unbosque.adiana.investor.Investor;
import edu.unbosque.adiana.investor.InvestorResult;
import edu.unbosque.adiana.investor.InvestorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/investor")
public class InvestorRestAdapter {

	private final InvestorService investorService;

	public InvestorRestAdapter(final InvestorService investorService) { this.investorService = investorService; }

	@GetMapping("/api")
	public @NotNull Collection<Investor> getInvestors() { return investorService.getInvestors(); }

	@GetMapping("/{id}")
	public @NotNull ResponseEntity<Investor> getInvestor(final @PathVariable int id) {
		final Investor investor = investorService.getInvestor(id);
		return ResponseEntity.ok(investor);
	}

	@GetMapping("/{id}/data")
	public @NotNull ResponseEntity<InvestorResult> getInvestorResult(final @PathVariable int id) {
		final InvestorResult investor = investorService.getInvestorData(id);
		return ResponseEntity.ok(investor);
	}

	@PostMapping("/create")
	public @NotNull ResponseEntity<InvestorResult> create(final @RequestBody int clientId) {
		final InvestorResult result = investorService.registerInvestor(clientId);
		return ResponseEntity.status(HttpStatus.CREATED)
			       .body(result);
	}


}
