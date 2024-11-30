package com.example.demo.entities.planes;

import com.example.demo.entities.projectiles.UserProjectile;
import com.example.demo.managers.TimelineManager;
import com.example.demo.entities.destructibles.ActiveActorDestructible;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;

/**
 * The UserPlane class extends FighterPlane represents the user's plane in the game which moves vertically.
 */
public class UserPlane extends FighterPlane
{
	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = 0.0;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 10;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private static final int AMMO_INCREMENT_INTERVAL = 1000;
	private static final int MAX_AMMUNITION = 20;
	private static final int HITBOX_X_OFFSET = 25;
	private static final int HITBOX_Y_OFFSET = 55;
	private static final int HITBOX_WIDTH = 150;
	private static final int HITBOX_HEIGHT = 40;
	private final TimelineManager timelineManager;
	private int velocityMultiplier;
	private int numberOfKills;
	private int ammunition;

	/**
	 * Defines movement directions for the user's plane.
	 */
	public enum Direction
	{
		up,down,stop
	}

	/**
	 * Constructs a UserPlane with the specified initial health.
	 *
	 * @param initialHealth the initial health of the user's plane.
	 */
	public UserPlane(int initialHealth)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
		ammunition = 10;
		this.timelineManager = new TimelineManager(AMMO_INCREMENT_INTERVAL,this::incrementAmmunition);
		timelineManager.play();
	}

	/**
	 * Updates the position of the user's plane based on its current velocity multiplier within set vertical boundaries.
	 */
	@Override
	public void updatePosition()
	{
		if (isMoving())
		{
			double initialTranslateY = getTranslateY();
			move(0.0, VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND)
			{
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	/**
	 * Fires a projectile from the user's plane if ammunition more than zero.
	 *
	 * @return a UserProjectile object if ammunition is available, or null otherwise.
	 */
	@Override
	public ActiveActorDestructible fireProjectile()
	{
		if(ammunition > 0)
		{
			ammunition--;
			return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
		}
		return null;
	}

	/**
	 * Determines if user's plane is moving base on velocity multiplier.
	 *
	 * @return true if the user's plane is moving, false otherwise.
	 */
	private boolean isMoving()
	{
		return velocityMultiplier != 0;
	}

	/**
	 * Sets the movement direction of the user's plane.
	 *
	 * @param direction indicates the desired movement.
	 */
	public void moves(Direction direction)
	{
		switch (direction)
		{
			case up:
				velocityMultiplier = -1;
				break;
			case down:
				velocityMultiplier = 1;
				break;
			case stop:
				velocityMultiplier = 0;
				break;
		}
	}

	/**
	 * Returns the number of kills achieved by the user.
	 *
	 * @return the number of kills.
	 */
	public int getNumberOfKills()
	{
		return numberOfKills;
	}

	/**
	 * Increments the user's kill count by 1.
	 */
	public void incrementKillCount()
	{
		numberOfKills++;
	}

	/**
	 * Increments the plane's ammunition count, if ammunition count is less than maximum capacity.
	 */
	private void incrementAmmunition()
	{
		if(ammunition < MAX_AMMUNITION)
		{
			ammunition++;
		}
	}


	/**
	 * Returns the current ammunition count of the user's plane.
	 *
	 * @return the current ammunition count.
	 */
	public int getAmmunition()
	{
		return ammunition;
	}

	/**
	 * Returns the timeline responsible for regenerating ammunition.
	 *
	 * @return the Timeline for ammo replenishment.
	 */
	public Timeline getTimeline()
	{
		return timelineManager.getTimeline();
	}

	/**
	 * Returns the hitbox of the user's plane.
	 *
	 * @return a Rectangle object representing the user's plane's hitbox.
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

}
