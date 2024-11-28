package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

public class BossProjectile extends Projectile
{
	
	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int HITBOX_X_OFFSET = 5;
	private static final int HITBOX_Y_OFFSET = 20;
	private static final int HITBOX_WIDTH = 100;
	private static final int HITBOX_HEIGHT = 55;

	public BossProjectile(double initialXPos, double initialYPos)
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
