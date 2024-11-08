package edu.unbosque.adiana.operator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Repository in charge of obtaining the Operators.
 * This repository should not accept to save or update suppliers since they are fixed
 * in the database and the requirements do not consider to manage this from the web application.
 */
public interface OperatorStorageAdapter {

	default void saveOperator(final @NotNull Operator operator) {
		throw new UnsupportedOperationException("The operation is not supported.");
	}

	default void updateOperator(final @NotNull Operator operator) {
		throw new UnsupportedOperationException("The operation is not supported.");
	}

	default void deleteOperator(final int operatorId) {
		throw new UnsupportedOperationException("The operation is not supported.");
	}

	@Nullable Operator getOperator(final int operatorId);

	@NotNull Collection<Operator> getOperators();

}
