package edu.unbosque.adiana.operator.data;

import org.jetbrains.annotations.NotNull;

public record City(
	@NotNull String name,
	@NotNull Country country
) {

	@Override
	public String toString() {
		return "City{" +
		       "name='" + name + '\'' +
		       ", country=" + country +
		       '}';
	}
}
