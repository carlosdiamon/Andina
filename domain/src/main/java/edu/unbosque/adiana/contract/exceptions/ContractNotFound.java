package edu.unbosque.adiana.contract.exceptions;

public class ContractNotFound
	extends RuntimeException {

	public ContractNotFound() {
	}

	public ContractNotFound(final String message) {
		super(message);
	}
}
