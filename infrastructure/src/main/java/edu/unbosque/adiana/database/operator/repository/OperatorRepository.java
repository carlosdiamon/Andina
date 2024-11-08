package edu.unbosque.adiana.database.operator.repository;

import edu.unbosque.adiana.database.operator.entity.OperatorEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface OperatorRepository {

	void saveOperator(final @NotNull OperatorEntity operator);

	void updateOperator(final @NotNull OperatorEntity operator);

	void deleteOperator(final int operatorId);

	@Nullable OperatorEntity getOperator(final int operatorId);

	@NotNull Collection<OperatorEntity> getOperators();

}
