package edu.unbosque.adiana.database.audit.entity;

import edu.unbosque.adiana.database.PersistenceEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "audit_log", schema = "public")
public class AuditEntity implements PersistenceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_log_id_gen")
	@SequenceGenerator(
		name = "audit_log_id_gen",
		sequenceName = "audit_log_log_id_seq",
		allocationSize = 1
	)
	@Column(name = "log_id", nullable = false)
	private Integer id;

	@Column(name = "event", nullable = false, length = 10)
	private String event;

	@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(name = "log_date")
	private Instant logDate;

	@Column(name = "username", nullable = false, length = 100)
	private String username;

	@Column(name = "table_name", nullable = false)
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Instant getLogDate() {
		return logDate;
	}

	public void setLogDate(Instant logDate) {
		this.logDate = logDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int getIdentifier() {
		return this.id;
	}
}