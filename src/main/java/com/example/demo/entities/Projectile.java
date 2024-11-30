package com.example.demo.entities;

import com.example.demo.entities.destructibles.ActiveActorDestructible;

/**
 * The Projectile class is an abstract base class representing projectiles in the game,
 * capable of moving horizontally and being destroyed upon collision.
 */
public abstract class Projectile extends ActiveActorDestructible
{
	private final double horizontalVelocity;

	/**
	 * Constructs a Projectile with the specified image name, image height, initial x and y position and horizontal velocity.
	 *
	 * @param imageName         the name of the image file relative to the image path.
	 * @param imageHeight       the height of the image.
	 * @param initialXPos       the initial X-coordinate position of the projectile.
	 * @param initialYPos       the initial Y-coordinate position of the projectile.
	 * @param horizontalVelocity the horizontal velocity of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, double horizontalVelocity)
	{
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.horizontalVelocity = horizontalVelocity;
	}

	/**
	 * Handles damage taken by the projectile marking it as destroyed.
	 */
	@Override
	public void takeDamage()
	{
		this.destroy();
	}

	/**
	 * Updates the state of the projectile
	 */
	@Override
	public void updateActor()
	{
		updatePosition();
	}

	/**
	 * Updates the position of the projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition()
	{
		move(horizontalVelocity, 0.0);
	}

}
