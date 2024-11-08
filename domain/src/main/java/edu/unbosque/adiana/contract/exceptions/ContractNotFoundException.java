package edu.unbosque.adiana.contract.exceptions;

public class ContractNotFoundException
	extends RuntimeException {

	public ContractNotFoundException() {
	}

	public ContractNotFoundException(final String message) {
		super(message);
	}
}
