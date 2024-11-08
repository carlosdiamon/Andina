package edu.unbosque.adiana.database.audit.mapper;

import edu.unbosque.adiana.audit.Audit;
import edu.unbosque.adiana.audit.AuditEvent;
import edu.unbosque.adiana.database.audit.entity.AuditEntity;
import org.jetbrains.annotations.NotNull;

public final class AuditMapper {

	private AuditMapper() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static @NotNull AuditEntity toAuditEntity(final @NotNull Audit audit) {
		final AuditEntity entity = new AuditEntity();
		entity.setUsername(audit.username());
		entity.setEvent(audit.event().toString());
		entity.setTableName(audit.tableName());
		entity.setLogDate(audit.infoDate());
		return entity;
	}

	public static @NotNull Audit toAudit(final @NotNull AuditEntity entity) {
		final AuditEvent event = AuditEvent.valueOf(entity.getEvent());
		return new Audit(event, entity.getUsername(), entity.getTableName(), entity.getLogDate());
	}

}
