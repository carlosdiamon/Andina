package edu.unbosque.adiana.database.role.repository;

import edu.unbosque.adiana.database.role.entity.RoleEntity;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

	private final SessionFactory sessionFactory;

	public RoleRepositoryImpl(final SessionFactory sessionFactory) { this.sessionFactory = sessionFactory;
	}

	@Override
	public @Nullable RoleEntity getRoleByName(final @NotNull String name) {
		return sessionFactory.getCurrentSession()
			       .createQuery("FROM RoleEntity WHERE name = :name", RoleEntity.class)
			       .setParameter("name", name)
			       .uniqueResult();
	}
}
