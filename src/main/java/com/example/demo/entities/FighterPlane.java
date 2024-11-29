package com.example.demo.entities;

/**
 * The FighterPlane class is an abstract base class for plane entities in the game
 * that can fire projectiles, take damage, and be destroyed when their health reaches zero.
 */
public abstract class FighterPlane extends ActiveActorDestructible
{

	private int health;

	/**
	 * Constructs a FighterPlane with the specified image name, image height, initial x and y position..
	 *
	 * @param imageName    the name of the image file relative to the image path.
	 * @param imageHeight  the height of the image.
	 * @param initialXPos  the initial x-coordinate position of the fighter plane.
	 * @param initialYPos  the initial y-coordinate position of the fighter plane.
	 * @param health       the initial health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health)
	{
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Abstract method to be implemented in its subclass to fires a projectile from the fighter plane.
	 *
	 * @return a destructible actor representing the projectile.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the health of the fighter plane by 1 and marked it as destroyed if health is zero.
	 */
	@Override
	public void takeDamage()
	{
		health--;
		if (healthAtZero())
		{
			this.destroy();
		}
	}

	/**
	 * Updates the position of the fighter plane.
	 */
	@Override
	public void updateActor()
	{
		updatePosition();
	}

	/**
	 * Calculates the x-coordinate position for a projectile based on an offset.
	 *
	 * @param xPositionOffset the x offset for the projectile's firing position.
	 * @return the calculated x-coordinate for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset)
	{
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the y-coordinate position for a projectile based on an offset.
	 *
	 * @param yPositionOffset the y offset for the projectile's firing position.
	 * @return the calculated y-coordinate for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset)
	{
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health of the fighter plane has reached zero.
	 *
	 * @return true if the health is zero, false otherwise.
	 */
	private boolean healthAtZero()
	{
		return health == 0;
	}

	/**
	 * Returns int object health.
	 *
	 * @return the current health of the fighter plane.
	 */
	public int getHealth()
	{
		return health;
	}
		
}
