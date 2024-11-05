package com.example.demo.entities;

public abstract class Projectile extends ActiveActorDestructible
{
	private final double horizontalVelocity;

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, double horizontalVelocity)
	{
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.horizontalVelocity = horizontalVelocity;
	}

	@Override
	public void takeDamage()
	{
		this.destroy();
	}

	@Override
	public void updateActor()
	{
		updatePosition();
	}

	@Override
	public void updatePosition()
	{
		moveHorizontally(horizontalVelocity);
	}

}
