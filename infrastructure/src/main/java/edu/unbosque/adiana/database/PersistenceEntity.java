package edu.unbosque.adiana.database;

/**
 * Interface for entities that require persistence functionality.
 * This interface provides a contract for entities to define a method
 * to retrieve their unique identifier, which is commonly used in persistence
 * frameworks like Hibernate for entity management.
 *
 */
public interface PersistenceEntity {

	/**
	 * Retrieves the unique identifier of the entity.
	 *
	 * @return the identifier of the entity, typically mapped to a primary key in a database.
	 */
	int getIdentifier();
}
