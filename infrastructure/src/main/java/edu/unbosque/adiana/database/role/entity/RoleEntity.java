package edu.unbosque.adiana.database.role.entity;

import edu.unbosque.adiana.client.ClientRole;
import edu.unbosque.adiana.database.PersistenceEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "role", schema = "public")
public class RoleEntity implements PersistenceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_gen")
	@SequenceGenerator(name = "role_id_gen", sequenceName = "role_role_id_seq", allocationSize = 1)
	@Column(name = "role_id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientRole getClientRole() {
		return ClientRole.valueOf(name);
	}

	@Override
	public int getIdentifier() {
		return this.id;
	}
}