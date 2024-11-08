package edu.unbosque.adiana.database.client.entity;

import edu.unbosque.adiana.database.PersistenceEntity;
import edu.unbosque.adiana.database.role.entity.RoleEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client", schema = "public")
public class ClientEntity implements PersistenceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_gen")
	@SequenceGenerator(
		name = "client_id_gen",
		sequenceName = "client_client_id_seq",
		allocationSize = 1
	)
	@Column(name = "client_id", nullable = false)
	private Integer id;

	@Column(name = "username", nullable = false, length = 100)
	private String username;

	@Column(name = "email", unique = true, nullable = false, length = 150)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(name = "created_at")
	private Instant createdAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id") private RoleEntity role;

	public RoleEntity getRole() { return role; }

	public void setRole(RoleEntity role) { this.role = role; }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int getIdentifier() {
		return this.id;
	}
}