package com.example.demo.entities.destructibles;

/**
 * Represents an entities in the game that can take damage and be destroyed.
 */
public interface Destructible
{

	/**
	 * Damages the actors
	 */
	void takeDamage();

	/**
	 * Destroys the actors
	 */
	void destroy();
	
}
