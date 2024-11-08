package edu.unbosque.adiana.security.wrapper;

import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DotenvWrapper {

	private final Dotenv dotenv;
	private static final Map<Class<?>, Function<String, Object>> MAPPERS;

	static {
		MAPPERS = new HashMap<>();
		MAPPERS.put(String.class, s -> s);
		MAPPERS.put(Integer.class, Integer::parseInt);
		MAPPERS.put(Double.class, Double::parseDouble);
		MAPPERS.put(Float.class, Float::parseFloat);
		MAPPERS.put(Boolean.class, Boolean::parseBoolean);
		MAPPERS.put(Long.class, Long::parseLong);
	}

	public DotenvWrapper() {
		this.dotenv = Dotenv.load();
	}

	public @Nullable String getString(final @NotNull String key) {
		return dotenv.get(key);
	}

	public @NotNull <T> T getValueOrDefault(
		final @NotNull Class<T> clazz,
		final @NotNull String key,
		final @NotNull T defaultValue
	) {
		final T value = getValue(clazz, key);
		return value == null ? defaultValue : value;
	}

	@SuppressWarnings("unchecked")
	public @Nullable <T> T getValue(
		final @NotNull Class<T> clazz,
		final @NotNull String key
	) {
		final Function<String, Object> fun = MAPPERS.get(clazz);
		T object = null;
		final String value = getString(key);

		if (fun != null && value != null) {
			object = (T) fun.apply(value);
		}

		return object;
	}
}
