package edu.unbosque.adiana.database.hibernate;

import edu.unbosque.adiana.database.audit.AuditLoggerListener;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HibernateListener {

	private final EntityManagerFactory emf;
	private final AuditLoggerListener listener;
	private final Logger logger = LoggerFactory.getLogger(HibernateListener.class);

	public HibernateListener(
		final EntityManagerFactory emf,
		final AuditLoggerListener listener
	) {
		this.emf = emf;
		this.listener = listener;
	}

	@PostConstruct
	public void init() {
		final SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
		final EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
		if (registry == null) {
			logger.error("Unable to register events in Hibernate.");
			return;
		}

		registry.getEventListenerGroup(EventType.POST_COMMIT_INSERT).appendListener(listener);
		registry.getEventListenerGroup(EventType.POST_COMMIT_UPDATE).appendListener(listener);
		registry.getEventListenerGroup(EventType.POST_COMMIT_DELETE).appendListener(listener);
	}
}
