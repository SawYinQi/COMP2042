package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

public class UserProjectile extends Projectile
{
	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;
	private static final int HITBOX_X_OFFSET = 80;
	private static final int HITBOX_Y_OFFSET = 60;
	private static final int HITBOX_WIDTH = 15;
	private static final int HITBOX_HEIGHT = 5;

	public UserProjectile(double initialXPos, double initialYPos)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}

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
