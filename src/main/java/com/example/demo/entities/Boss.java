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
	private static final int HEALTH = 5; //change later easier for testing
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int X_POSITION_LEFT_BOUND = 700;
	private static final int X_POSITION_RIGHT_BOUND = 1000;
	private final BossShield  bossShield;
	private final BossMovementPattern bossMovement;
	private double initialTranslateY;
	private double initialTranslateX;
	private double currentYPosition;
	private double currentXPosition;

	public Boss()
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.bossShield = new BossShield();
		this.bossMovement = new BossMovementPattern();
	}

	@Override
	public void updatePosition()
	{
		initialTranslateY = getTranslateY();
		initialTranslateX = getTranslateX();
		int[] nextMove = bossMovement.getNextMove();
		double x = nextMove[0];
		double y = nextMove[1];
		move(x, y);
		currentYPosition = getLayoutY() + getTranslateY();
		currentXPosition = getLayoutX() + getTranslateX();
		if (currentYPosition < Y_POSITION_UPPER_BOUND || currentYPosition > Y_POSITION_LOWER_BOUND)
		{
			setTranslateY(initialTranslateY);
		}
		if (currentXPosition < X_POSITION_LEFT_BOUND || currentXPosition > X_POSITION_RIGHT_BOUND) {
			setTranslateX(initialTranslateX);
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

	public BossShield getBossShield()
	{
		return bossShield;
	}

	public double getCurrentXPosition()
	{
		return currentXPosition;
	}

	public double getCurrentYPosition()
	{
		return currentYPosition;
	}
}
