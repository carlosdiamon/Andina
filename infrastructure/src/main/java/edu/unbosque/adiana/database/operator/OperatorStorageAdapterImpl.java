package edu.unbosque.adiana.database.operator;

import edu.unbosque.adiana.database.operator.entity.OperatorEntity;
import edu.unbosque.adiana.database.operator.mapper.OperatorMapper;
import edu.unbosque.adiana.database.operator.repository.OperatorRepository;
import edu.unbosque.adiana.operator.Operator;
import edu.unbosque.adiana.operator.OperatorStorageAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class OperatorStorageAdapterImpl implements OperatorStorageAdapter {

	private final OperatorRepository repository;

	public OperatorStorageAdapterImpl(final OperatorRepository repository) { this.repository = repository; }

	@Override
	public @Nullable Operator getOperator(final int operatorId) {
		Operator operator = null;
		final OperatorEntity operatorEntity = repository.getOperator(operatorId);
		if (operatorEntity != null) {
			operator = OperatorMapper.toOperator(operatorEntity);
		}
		return operator;
	}

	@Override
	public @NotNull Collection<Operator> getOperators() {
		return repository.getOperators().stream()
			       .map(OperatorMapper::toOperator)
			       .toList();
	}
}
