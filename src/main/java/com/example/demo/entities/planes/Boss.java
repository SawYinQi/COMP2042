package com.example.demo.entities.planes;

import com.example.demo.entities.projectiles.BossProjectile;
import com.example.demo.entities.behaviors.BossMovementPattern;
import com.example.demo.entities.behaviors.BossShield;
import com.example.demo.entities.destructibles.ActiveActorDestructible;
import javafx.scene.shape.Rectangle;

/**
 * The Boss class extends FighterPlane which serves as a boss unit in the game.
 */
public class Boss extends FighterPlane
{

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double BOSS_FIRE_RATE = .03;
	private static final int IMAGE_HEIGHT = 300;
	private static final int HEALTH = 10;
	private static final int Y_POSITION_UPPER_BOUND = -50;
	private static final int Y_POSITION_LOWER_BOUND = 550;
	private static final int X_POSITION_LEFT_BOUND = 700;
	private static final int X_POSITION_RIGHT_BOUND = 1000;
	private static final int HITBOX_X_OFFSET = 60;
	private static final int HITBOX_Y_OFFSET = 130;
	private static final int HITBOX_WIDTH = 280;
	private static final int HITBOX_HEIGHT = 40;
	private final BossShield bossShield;
	private final BossMovementPattern bossMovement;
	private double initialTranslateY;
	private double initialTranslateX;
	private double currentYPosition;
	private double currentXPosition;

	/**
	 * Constructs a Boss instance with predefined image name, image height, health and initial x and y position.
	 */
	public Boss()
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.bossShield = new BossShield();
		this.bossMovement = new BossMovementPattern();
	}

	/**
	 * Updates the position of the boss based on its movement pattern within set boundaries.
	 */
	@Override
	public void updatePosition()
	{
		initialTranslateY = getTranslateY();
		initialTranslateX = getTranslateX();
		int[] nextMove = bossMovement.getBossNextMove();
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

	/**
	 * Updates the boss's position and shield state.
	 */
	@Override
	public void updateActor()
	{
		updatePosition();
		bossShield.getUpdateShield();
	}

	/**
	 * Handles damage taken by the boss, ensuring it only takes damage if its shield is deactivated.
	 */
	@Override
	public void takeDamage()
	{
		if (!bossShield.shielded())
		{
			super.takeDamage();
		}
	}

	/**
	 * Fires a projectile from the boss, if the firing conditions are met.
	 *
	 * @return a BossProjectile object if the boss decides to fire, or null otherwise.
	 */
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

	/**
	 * Determines if the boss should fire a projectile based on a random probability.
	 *
	 * @return true if the boss should fire, false otherwise.
	 */
	private boolean projectileShouldFire()
	{
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Returns the hitbox of the boss.
	 *
	 * @return a Rectangle object representing the boss's hitbox.
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

	/**
	 * Public accessor for getting BossShield.
	 *
	 * @return BossShield instance of boss.
	 */
	public BossShield getBossShield()
	{
		return bossShield;
	}

	/**
	 * Returns the current x position of boss.
	 *
	 * @return current x position.
	 */
	public double getCurrentXPosition()
	{
		return currentXPosition;
	}

	/**
	 * Returns the current y position of boss.
	 *
	 * @return current y position.
	 */
	public double getCurrentYPosition()
	{
		return currentYPosition;
	}
}
