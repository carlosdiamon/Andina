package edu.unbosque.adiana.database.audit.service;

import edu.unbosque.adiana.audit.AuditEvent;
import edu.unbosque.adiana.database.PersistenceEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Class in charge of registering in the audit table the events that happen in the other tables.
 */
public interface AuditLogger {

	/**
	 * Register an action in the audit table
	 * @param event action
	 * @param entity table
	 */
	void registerLog(
		final @NotNull AuditEvent event,
		final @NotNull PersistenceEntity entity
	);

}
