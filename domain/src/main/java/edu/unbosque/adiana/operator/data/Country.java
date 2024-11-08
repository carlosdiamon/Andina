package edu.unbosque.adiana.operator.data;

import org.jetbrains.annotations.NotNull;

public record Country(
	@NotNull String name,
	@NotNull String description
) {
	public Country(
		final String name,
		final String description
	) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Country{" +
		       "name='" + name + '\'' +
		       ", description='" + description + '\'' +
		       '}';
	}
}
