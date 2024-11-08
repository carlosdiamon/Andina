package edu.unbosque.adiana.database.role.repository;

import edu.unbosque.adiana.database.role.entity.RoleEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RoleRepository {

	@Nullable RoleEntity getRoleByName(final @NotNull String name);

}
