package com.example.demo.entities.destructibles;

import javafx.scene.shape.Rectangle;

/**
 * The ActiveActorDestructible is an abstract base class extends ActiveActor and implements the Destructible interface,
 * representing game entities that can take damage and be destroyed.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible
{
	private boolean isDestroyed;

	/**
	 * Constructs an ActiveActorDestructible with the specified image, size, and initial x and y position.
	 *
	 * @param imageName    the name of the image file (relative to the base image path).
	 * @param imageHeight  the height of the image.
	 * @param initialXPos  the initial x-coordinate position of the actor.
	 * @param initialYPos  the initial y-coordinate position of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos)
	{
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Abstract method to be implemented in its subclass to handle damage taken by the actor.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed.
	 */
	@Override
	public void destroy()
	{
		this.isDestroyed = true;
	}

	/**
	 * Returns the destroyed status of the actor.
	 *
	 * @return true if the actor is destroyed, false otherwise.
	 */
	public boolean getIsDestroyed()
	{
		return isDestroyed;
	}

	/**
	 * Abstract method to be implemented in its subclass to return the hitbox of the actor.
	 * @return the Rectangle object representing the actor's hitbox.
	 */
	public abstract Rectangle getHitBox();

}
