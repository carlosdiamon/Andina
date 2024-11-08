package edu.unbosque.adiana.database.investor.repository;

import edu.unbosque.adiana.database.investor.entity.InvestorEntity;
import edu.unbosque.adiana.investor.Investor;
import edu.unbosque.adiana.investor.exceptions.InvestorNotFound;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class InvestorRepositoryImpl implements InvestorRepository {

	private final SessionFactory sessionFactory;

	public InvestorRepositoryImpl(final SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

	@Override
	public void saveInvestor(final @NotNull InvestorEntity investor) {
		sessionFactory.getCurrentSession().persist(investor);
	}

	@Override
	public void updateInvestor(final int investorId, final @NotNull InvestorEntity updatedInvestor) {
		InvestorEntity investorEntity = getInvestor(investorId);
		if (investorEntity == null) {
			throw new InvestorNotFound("Investor with id " + investorId + " not found");
		}
		investorEntity.setRegistrationDate(updatedInvestor.getRegistrationDate());
		investorEntity.setRegistrationDate(updatedInvestor.getRegistrationDate());
		investorEntity.setClient(updatedInvestor.getClient());
		sessionFactory.getCurrentSession().merge(investorEntity);
	}

	@Override
	public void deleteInvestor(final int investorId) {
		final InvestorEntity entity = getInvestor(investorId);
		if (entity != null) {
			sessionFactory.getCurrentSession().remove(entity);
		}
	}

	@Override
	public @Nullable InvestorEntity getInvestor(final int investorId) {
		return sessionFactory.getCurrentSession().get(InvestorEntity.class, investorId);
	}

	@Override
	public @NotNull Collection<InvestorEntity> getAllInvestors() {
		return sessionFactory.getCurrentSession().createQuery("FROM InvestorEntity", InvestorEntity.class)
				.getResultList();
	}
}
