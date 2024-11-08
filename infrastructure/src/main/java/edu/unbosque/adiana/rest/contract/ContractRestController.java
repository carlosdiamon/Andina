package edu.unbosque.adiana.rest.contract;

import edu.unbosque.adiana.contract.Contract;
import edu.unbosque.adiana.contract.ContractAcceptance;
import edu.unbosque.adiana.contract.ContractService;
import edu.unbosque.adiana.contract.request.ContractRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/contract")
public class ContractRestController {

	private final ContractService contractService;

	public ContractRestController(final ContractService contractService) {
		this.contractService = contractService;
	}

	@PostMapping
	public ResponseEntity<Contract> createContract(@RequestBody ContractRequest contractRequest) {
		Contract createdContract = contractService.registerContract(contractRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdContract);
	}

	@GetMapping
	public ResponseEntity<Collection<Contract>> getAllContracts() {
		Collection<Contract> contracts = contractService.getContracts();
		return ResponseEntity.ok(contracts);
	}

	@GetMapping("/{contractId}")
	public ResponseEntity<Contract> getContractById(@PathVariable int contractId) {
		Contract contract = contractService.getContract(contractId);
		return contract != null ? ResponseEntity.ok(contract) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{contractId}")
	public ResponseEntity<ContractRequest> updateContract(
		@PathVariable int contractId,
		@RequestBody ContractRequest contractRequest
	) {
		contractService.updateContract(contractId, contractRequest);
		return ResponseEntity.ok(contractRequest); // cambiar, tengo sueño :V
	}

	@DeleteMapping("/{contractId}")
	public ResponseEntity<Void> deleteContract(@PathVariable int contractId) {
		contractService.removeContract(contractId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/investor/{investorId}")
	public Collection<Contract> getContractsByInvestor(@PathVariable int investorId) {
		return contractService.getContractsByInvestor(investorId);
	}

	@PutMapping("/{contractId}/accept")
	public ResponseEntity<Void> acceptContract(
		@PathVariable int contractId,
		@RequestParam int operatorId
	) {
		contractService.contractAccepted(contractId, operatorId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/acceptances/operator/{operatorId}")
	public Collection<ContractAcceptance> getAcceptancesByOperator(@PathVariable int operatorId) {
		return contractService.getContractAcceptancesByOperator(operatorId);
	}
}
