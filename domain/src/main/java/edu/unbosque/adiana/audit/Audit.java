package edu.unbosque.adiana.audit;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public record Audit(
	@NotNull AuditEvent event,
	@NotNull String username,
	@NotNull String tableName,
	@NotNull Instant infoDate
) {

	@Override
	public String toString() {
		return "Audit{" +
		       "event=" + event +
		       ", username='" + username + '\'' +
		       ", tableName='" + tableName + '\'' +
		       ", infoDate=" + infoDate +
		       '}';
	}
}
