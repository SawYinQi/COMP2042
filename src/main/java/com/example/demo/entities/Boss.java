package com.example.demo.entities;

public class Boss extends FighterPlane
{

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final int IMAGE_HEIGHT = 300;
	private static final int HEALTH = 1; //change later easier for testing
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private final BossShield  bossShield;
	private final BossMovementPattern bossMovement;

	public Boss()
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.bossShield = new BossShield();
		this.bossMovement =new BossMovementPattern();
	}

	@Override
	public void updatePosition()
	{
		double initialTranslateY = getTranslateY();
		moveVertically(bossMovement.getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND)
		{
			setTranslateY(initialTranslateY);
		}
	}
	
	@Override
	public void updateActor()
	{
		updatePosition();
		bossShield.updateShield();
	}

	@Override
	public void takeDamage()
	{
		if (!bossShield.shielded())
		{
			super.takeDamage();
		}
	}

	public ActiveActorDestructible fireProjectile()
	{
		if (projectileShouldFire())
		{
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new BossProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	private boolean projectileShouldFire()
	{
		return Math.random() < BOSS_FIRE_RATE;
	}
}
