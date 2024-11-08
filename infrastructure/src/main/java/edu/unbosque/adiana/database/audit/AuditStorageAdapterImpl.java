package edu.unbosque.adiana.database.audit;

import edu.unbosque.adiana.audit.Audit;
import edu.unbosque.adiana.audit.AuditStorageAdapter;
import edu.unbosque.adiana.database.audit.entity.AuditEntity;
import edu.unbosque.adiana.database.audit.mapper.AuditMapper;
import edu.unbosque.adiana.database.audit.repository.AuditRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuditStorageAdapterImpl implements AuditStorageAdapter {

	private final AuditRepository repository;

	public AuditStorageAdapterImpl(final AuditRepository repository) { this.repository = repository; }

	@Override
	public void saveAudit(final @NotNull Audit audit) {
		repository.saveAudit(AuditMapper.toAuditEntity(audit));
	}

	@Override
	public void removeAudit(final @NotNull Audit audit) {
		repository.updateAudit(AuditMapper.toAuditEntity(audit));
	}

	@Override
	public @Nullable Audit getAudit(final int auditId) {
		Audit audit = null;
		final AuditEntity entity = repository.getAudit(auditId);
		if (entity != null) {
			audit = AuditMapper.toAudit(entity);
		}
		return audit;
	}

	@Override
	public @NotNull Collection<Audit> getAudits() {
		return repository.getAllAudits().stream()
			       .map(AuditMapper::toAudit)
			       .toList();
	}
}
