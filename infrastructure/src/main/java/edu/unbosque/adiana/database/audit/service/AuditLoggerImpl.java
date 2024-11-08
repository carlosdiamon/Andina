package edu.unbosque.adiana.database.audit.service;

import edu.unbosque.adiana.audit.Audit;
import edu.unbosque.adiana.audit.AuditEvent;
import edu.unbosque.adiana.audit.AuditStorageAdapter;
import edu.unbosque.adiana.database.PersistenceEntity;
import edu.unbosque.adiana.database.exceptions.AuditLoggerException;
import jakarta.persistence.Table;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AuditLoggerImpl implements AuditLogger {

	private final AuditStorageAdapter storage;

	public AuditLoggerImpl(final AuditStorageAdapter storage) { this.storage = storage; }

	@Override
	public void registerLog(
		final @NotNull AuditEvent event,
		final @NotNull PersistenceEntity entity
	) {
		final Table table = entity.getClass().getAnnotation(Table.class);
		if (table == null) {
			throw new AuditLoggerException( entity + " does not have a Table annotation");
		}

		String user = SecurityContextHolder.getContext().getAuthentication().getName();

		// TODO: Luego ver la integracion de la id del usuario
		storage.saveAudit(new Audit(event, user, table.name(), Instant.now()));
	}

}
