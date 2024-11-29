package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

/**
 * The EnemyPlane class extends FighterPlane and represents enemy planes in the game which moves horizontally.
 */
public class EnemyPlane extends FighterPlane
{

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;
	private static final int HITBOX_X_OFFSET = 20;
	private static final int HITBOX_Y_OFFSET = 55;
	private static final int HITBOX_WIDTH = 150;
	private static final int HITBOX_HEIGHT = 50;

	/**
	 * Constructs an EnemyPlane at the specified initial x and y position.
	 *
	 * @param initialXPos the initial x-coordinate of the enemy plane.
	 * @param initialYPos the initial y-coordinate of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 */
	@Override
	public void updatePosition()
	{
		move(HORIZONTAL_VELOCITY, 0.0);
	}

	/**
	 * Fires a projectile from the enemy plane if the firing conditions are met.
	 *
	 * @return an EnemyProjectile if the enemy decides to fire, or null otherwise.
	 */
	@Override
	public ActiveActorDestructible fireProjectile()
	{
		if (projectileShouldFire())
		{
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Determines whether the enemy plane should fire a projectile based on a random probability.
	 *
	 * @return true if the enemy should fire, false otherwise.
	 */
	private boolean projectileShouldFire()
	{
		return Math.random() < FIRE_RATE;
	}

	/**
	 * Returns the hitbox of the enemy plane.
	 *
	 * @return a Rectangle object representing the enemy plane's hitbox.
	 */
	@Override
	public Rectangle getHitBox()
	{
		return new Rectangle(
				getLayoutX() + getTranslateX() + HITBOX_X_OFFSET,
				getLayoutY() + getTranslateY() + HITBOX_Y_OFFSET,
				HITBOX_WIDTH,
				HITBOX_HEIGHT
		);
	}

}
