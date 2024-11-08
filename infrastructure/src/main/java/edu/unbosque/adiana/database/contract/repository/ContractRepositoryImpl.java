package edu.unbosque.adiana.database.contract.repository;

import edu.unbosque.adiana.contract.exceptions.ContractNotFound;
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

	public ContractRepositoryImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveContract(final @NotNull ContractEntity contract) {
		sessionFactory.getCurrentSession().persist(contract);
	}

	@Override
	public void updateContract(final int contractId, final @NotNull ContractEntity updatedContract) {
		ContractEntity contractEntity = getContract(contractId);

		if (contractEntity == null) {
			throw new ContractNotFound("Contract not found: " + contractId);
		}
		contractEntity.setCreationDate(updatedContract.getCreationDate());
		contractEntity.setPrice(updatedContract.getPrice());
		contractEntity.setAcquisitionDate(updatedContract.getAcquisitionDate());
		contractEntity.setInvestor(updatedContract.getInvestor());
		contractEntity.setStatus(updatedContract.getStatus());
		contractEntity.setQuantity(updatedContract.getQuantity());

		sessionFactory.getCurrentSession().merge(contractEntity);
	}

	@Override
	public void deleteContract(final int contractId) {
		final ContractEntity entity = getContract(contractId);
		if (entity != null) {
			sessionFactory.getCurrentSession().remove(entity);
		}
	}

	@Override
	public @Nullable ContractEntity getContract(final int contractId) {
		return sessionFactory.getCurrentSession().get(ContractEntity.class, contractId);
	}

	@Override
	public @NotNull Collection<ContractEntity> getContractsByOperator(final int operatorId) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM ContractEntity WHERE operator.id = :operatorId", ContractEntity.class)
				.setParameter("operatorId", operatorId)
				.list();
	}


	@Override
	public @NotNull Collection<ContractEntity> getContracts() {
		return sessionFactory.getCurrentSession().createQuery("FROM ContractEntity", ContractEntity.class)
				.getResultList();
	}
}
