package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

public class BossProjectile extends Projectile
{
	
	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;

	public BossProjectile(double initialXPos, double initialYPos)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}

	@Override
	public Rectangle getHitBox()
	{
		return new Rectangle(
				getLayoutX() + getTranslateX(),
				getLayoutY() + getTranslateY() + 20,
				100,
				50
		);
	}
	
}
