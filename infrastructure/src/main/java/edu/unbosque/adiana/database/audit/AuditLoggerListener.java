package edu.unbosque.adiana.database.audit;

import edu.unbosque.adiana.audit.AuditEvent;
import edu.unbosque.adiana.database.PersistenceEntity;
import edu.unbosque.adiana.database.audit.service.AuditLogger;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

@Component
public class AuditLoggerListener implements PostInsertEventListener, PostUpdateEventListener,
                                            PostDeleteEventListener {

	private final AuditLogger logger;

	public AuditLoggerListener(final AuditLogger logger) { this.logger = logger; }

	@Override
	public void onPostDelete(final PostDeleteEvent event) {
		if (event.getEntity() instanceof PersistenceEntity entity) {
			logger.registerLog(AuditEvent.DELETE, entity);
		}
	}

	@Override
	public void onPostInsert(final PostInsertEvent event) {
		if (event.getEntity() instanceof PersistenceEntity entity) {
			logger.registerLog(AuditEvent.INSERT, entity);
		}
	}

	@Override
	public void onPostUpdate(final PostUpdateEvent event) {
		if (event.getEntity() instanceof PersistenceEntity entity) {
			logger.registerLog(AuditEvent.UPDATE, entity);
		}
	}

	@Override
	public boolean requiresPostCommitHandling(final EntityPersister persister) {
		return true;
	}
}
