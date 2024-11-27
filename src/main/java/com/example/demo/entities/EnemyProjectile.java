package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

public class EnemyProjectile extends Projectile
{
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	public EnemyProjectile(double initialXPos, double initialYPos)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}
	@Override
	public Rectangle getHitBox()
	{
		return new Rectangle(
				getLayoutX() + getTranslateX() + 5,
				getLayoutY() + getTranslateY() + 16,
				35,
				15
		);
	}

}
