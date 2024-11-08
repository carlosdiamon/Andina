package edu.unbosque.adiana.rest.contract;

import edu.unbosque.adiana.contract.Contract;
import edu.unbosque.adiana.contract.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractRestController {

	private final ContractService contractService;

	public ContractRestController(final ContractService contractService) { this.contractService = contractService; }

	@GetMapping("/{id}")
	public ResponseEntity<Contract> getContractById(final @PathVariable("id") int id) {
		final Contract contract = contractService.getContract(id);
		return ResponseEntity.ok(contract);
	}
	@GetMapping("/operator/{id}")
	public Collection<Contract> getContractByOperator(final @PathVariable("id") int id) {
		return contractService.getContractsByOperator(id);
	}

	@GetMapping("/api")
	public Collection<Contract> getContracts() {
		return contractService.getContracts();
	}

	@PostMapping("/apply/{investorId}/{contractId}")
	public ResponseEntity<Contract> applyContract(
		final @PathVariable("investorId") int investorId,
		final @PathVariable("contractId") int contractId
	) {
		final Contract contract = contractService.applyContract(investorId, contractId);
		return ResponseEntity.ok(contract);
	}

}
