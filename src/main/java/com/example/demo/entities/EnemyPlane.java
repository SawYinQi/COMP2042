package com.example.demo.entities;

public class EnemyPlane extends FighterPlane
{

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	@Override
	public void updatePosition()
	{
		move(HORIZONTAL_VELOCITY, 0.0);
	}

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

	private boolean projectileShouldFire()
	{
		return Math.random() < FIRE_RATE;
	}

}
