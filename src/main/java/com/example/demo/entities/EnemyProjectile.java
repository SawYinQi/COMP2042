package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

public class EnemyProjectile extends Projectile
{
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final int HITBOX_X_OFFSET = 5;
	private static final int HITBOX_Y_OFFSET = 16;
	private static final int HITBOX_WIDTH = 35;
	private static final int HITBOX_HEIGHT = 15;

	public EnemyProjectile(double initialXPos, double initialYPos)
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
