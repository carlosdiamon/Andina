package edu.unbosque.adiana.contract.exceptions;

public class InsufficientFundException
	extends RuntimeException {

	public InsufficientFundException(String message) {
		super(message);
	}
}
