package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

/**
 * The UserProjectile class extends Projectile represents a projectile fired by the user's plane in the game.
 */
public class UserProjectile extends Projectile
{
	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;
	private static final int HITBOX_X_OFFSET = 80;
	private static final int HITBOX_Y_OFFSET = 60;
	private static final int HITBOX_WIDTH = 15;
	private static final int HITBOX_HEIGHT = 5;

	/**
	 * Constructs a UserProjectile at the specified initial x and y position.
	 *
	 * @param initialXPos the initial x-coordinate of the projectile.
	 * @param initialYPos the initial y-coordinate of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}

	/**
	 * Returns the hitbox of the user's projectile.
	 *
	 * @return a Rectangle object representing the UserProjectile's hitbox.
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
