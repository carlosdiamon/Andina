package edu.unbosque.adiana.database.contract.repository;

import edu.unbosque.adiana.contract.exceptions.ContractNotFoundException;
import edu.unbosque.adiana.database.contract.entity.ContractEntity;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class ContractRepositoryImpl implements ContractRepository {

	private final SessionFactory sessionFactory;

	public ContractRepositoryImpl(final SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

	@Override
	public void saveContract(final @NotNull ContractEntity contract) {
		sessionFactory.getCurrentSession().persist(contract);
	}

	@Override
	public void updateContract(
		final int contractId,
		final @NotNull ContractEntity updatedContract
	) {
		final ContractEntity reference = getContract(contractId);
		if (reference == null) {
			throw new ContractNotFoundException("The contract was not found to be able to update, the id is: " + contractId);
		}

		sessionFactory.getCurrentSession().merge(reference);
	}

	@Override
	public void deleteContract(final int contractId) {
		final ContractEntity reference = getContract(contractId);
		if (reference == null) {
			throw new ContractNotFoundException("The contract was not found to be able to delete, the id is: " + contractId);
		}
		sessionFactory.getCurrentSession().remove(reference);
	}

	@Override
	public @Nullable ContractEntity getContract(final int contractId) {
		return sessionFactory.getCurrentSession().get(ContractEntity.class, contractId);
	}

	@Override
	public @NotNull Collection<ContractEntity> getContractsByInvestor(final int investorId) {
		return sessionFactory.getCurrentSession()
			       .createQuery("FROM ContractEntity WHERE investor.id = :investorId", ContractEntity.class)
			       .setParameter("investorId", investorId)
			       .getResultList();
	}

	@Override
	public @NotNull Collection<ContractEntity> getContracts() {
		return sessionFactory.getCurrentSession().createQuery("FROM ContractEntity", ContractEntity.class)
			       .getResultList();
	}
}
