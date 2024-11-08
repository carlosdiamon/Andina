package edu.unbosque.adiana.database.audit.repository;

import edu.unbosque.adiana.database.audit.entity.AuditEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface AuditRepository {

	void saveAudit(final @NotNull AuditEntity entity);

	void updateAudit(final @NotNull AuditEntity entity);

	void deleteAudit(final @NotNull AuditEntity entity);

	@Nullable AuditEntity getAudit(final int id);

	@NotNull Collection<AuditEntity> getAllAudits();

}
