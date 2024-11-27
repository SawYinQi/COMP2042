package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

public class UserProjectile extends Projectile
{
	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;

	public UserProjectile(double initialXPos, double initialYPos)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}

	@Override
	public Rectangle getHitBox()
	{
		return new Rectangle(
				getLayoutX() + getTranslateX() + 80,
				getLayoutY() + getTranslateY() + 60,
				15,
				5
		);
	}

}
