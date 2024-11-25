package com.example.demo.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class UserPlane extends FighterPlane
{
	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 10;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private static final int AMMO_INCREMENT_INTERVAL = 1500;
	private static final int MAX_AMMUNITION = 20;
	private int velocityMultiplier;
	private int numberOfKills;
	private int ammunition;
	private Timeline timeline;

	public enum Direction
	{
		up,down,stop
	}

	public UserPlane(int initialHealth)
	{
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
		ammunition = 5;
		initializeTimeline();
	}
	
	@Override
	public void updatePosition()
	{
		if (isMoving())
		{
			double initialTranslateY = getTranslateY();
			this.move(0.0, VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND)
			{
				this.setTranslateY(initialTranslateY);
			}
		}
	}
	
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

	private boolean isMoving()
	{
		return velocityMultiplier != 0;
	}

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

	public int getNumberOfKills()
	{
		return numberOfKills;
	}

	public void incrementKillCount()
	{
		numberOfKills++;
	}

	private void incrementAmmunition()
	{
		if(ammunition < MAX_AMMUNITION)
		{
			ammunition++;
		}
	}

	private void initializeTimeline()
	{
		timeline = new Timeline(new KeyFrame(Duration.millis(AMMO_INCREMENT_INTERVAL), e -> incrementAmmunition()));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	public int getAmmunition()
	{
		return ammunition;
	}

	public Timeline getTimeline()
	{
		return timeline;
	}

}
