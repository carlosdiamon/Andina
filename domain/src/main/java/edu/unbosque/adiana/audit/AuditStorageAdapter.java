package edu.unbosque.adiana.audit;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface AuditStorageAdapter {

	void saveAudit(final @NotNull Audit audit);

	void removeAudit(final @NotNull Audit audit);

	@Nullable Audit getAudit(final int auditId);

	@NotNull Collection<Audit> getAudits();

}
