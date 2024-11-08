package edu.unbosque.adiana.database.audit.repository;

import edu.unbosque.adiana.database.audit.entity.AuditEntity;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public class AuditRepositoryImpl implements AuditRepository {

	private final SessionFactory sessionFactory;

	public AuditRepositoryImpl(final SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

	@Override
	public void saveAudit(final @NotNull AuditEntity entity) {
		sessionFactory.getCurrentSession().persist(entity);
	}

	@Override
	public void updateAudit(final @NotNull AuditEntity entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	@Override
	public void deleteAudit(final @NotNull AuditEntity entity) {
		sessionFactory.getCurrentSession().remove(entity);
	}

	@Override
	public @Nullable AuditEntity getAudit(final int id) {
		return sessionFactory.getCurrentSession().get(AuditEntity.class, id);
	}

	@Override
	public @NotNull Collection<AuditEntity> getAllAudits() {
		return sessionFactory.getCurrentSession().createQuery("FROM AuditEntity", AuditEntity.class)
			       .list();
	}

}
