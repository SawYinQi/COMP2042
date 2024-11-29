package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

/**
 * The EnemyProjectile class extends Projectile represents a projectile fired by enemy planes in the game.
 */
public class EnemyProjectile extends Projectile
{
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final int HITBOX_X_OFFSET = 5;
	private static final int HITBOX_Y_OFFSET = 16;
	private static final int HITBOX_WIDTH = 35;
	private static final int HITBOX_HEIGHT = 15;

	/**
	 * Constructs an EnemyProjectile at the specified initial x and y position.
	 *
	 * @param initialXPos the initial x-coordinate of the projectile.
	 * @param initialYPos the initial y-coordinate of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}

	/**
	 * Returns the hitbox of the enemy's projectile.
	 *
	 * @return a Rectangle object representing the EnemyProjectile's hitbox.
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
