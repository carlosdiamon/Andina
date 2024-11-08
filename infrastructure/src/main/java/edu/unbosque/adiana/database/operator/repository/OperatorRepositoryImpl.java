package edu.unbosque.adiana.database.operator.repository;

import edu.unbosque.adiana.database.operator.entity.OperatorEntity;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class OperatorRepositoryImpl implements OperatorRepository {

	private final SessionFactory sessionFactory;

	public OperatorRepositoryImpl(final SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

	@Override
	public void saveOperator(final @NotNull OperatorEntity operator) {
		sessionFactory.getCurrentSession().persist(operator);
	}

	@Override
	public void updateOperator(final @NotNull OperatorEntity operator) {
		sessionFactory.getCurrentSession().merge(operator);
	}

	@Override
	public void deleteOperator(final int operatorId) {
		final OperatorEntity entity = getOperator(operatorId);
		if (entity != null) {
			sessionFactory.getCurrentSession().remove(entity);
		}
	}

	@Override
	public @Nullable OperatorEntity getOperator(final int operatorId) {
		return sessionFactory.getCurrentSession().get(OperatorEntity.class, operatorId);
	}

	@Override
	public @NotNull Collection<OperatorEntity> getOperators() {
		return sessionFactory.getCurrentSession().createQuery("FROM OperatorEntity", OperatorEntity.class)
				.getResultList();
	}
}
