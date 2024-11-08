package edu.unbosque.adiana.database.contract.repository;

import edu.unbosque.adiana.contract.exceptions.ContractNotFoundException;
import edu.unbosque.adiana.database.contract.entity.ContractAcceptanceEntity;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class ContractAcceptanceRepositoryImpl implements ContractAcceptanceRepository {

	private final SessionFactory sessionFactory;

	public ContractAcceptanceRepositoryImpl(final SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

	@Override
	public void saveContract(final @NotNull ContractAcceptanceEntity contract) {
		sessionFactory.getCurrentSession().persist(contract);
	}

	@Override
	public void updateContract(
		final int contractId,
		final @NotNull ContractAcceptanceEntity updatedContract
	) {
		final ContractAcceptanceEntity reference = getContract(contractId);
		if (reference == null) {
			throw new ContractNotFoundException("The contractAcceptance was not found to be able to update, the id is: " + contractId);
		}

		sessionFactory.getCurrentSession().merge(reference);
	}

	@Override
	public void deleteContract(final int contractId) {
		final ContractAcceptanceEntity reference = getContract(contractId);
		if (reference == null) {
			throw new ContractNotFoundException("The contractAcceptance was not found to be able to delete, the id is: " + contractId);
		}

		sessionFactory.getCurrentSession().remove(reference);
	}

	@Override
	public @Nullable ContractAcceptanceEntity getContract(final int contractId) {
		return sessionFactory.getCurrentSession().get(ContractAcceptanceEntity.class, contractId);
	}

	@Override
	public @NotNull Collection<ContractAcceptanceEntity> getContractsByOperator(final int operatorId) {
		return sessionFactory.getCurrentSession()
			       .createQuery("FROM ContractAcceptanceEntity WHERE operator.id = :operatorId", ContractAcceptanceEntity.class)
			       .setParameter("operatorId", operatorId)
			       .getResultList();
	}

	@Override
	public @NotNull Collection<ContractAcceptanceEntity> getContracts() {
		return sessionFactory.getCurrentSession()
			       .createQuery("FROM ContractAcceptanceEntity", ContractAcceptanceEntity.class)
			       .getResultList();
	}

}
