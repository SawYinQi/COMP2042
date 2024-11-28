package com.example.demo.entities;

import com.example.demo.Destructible;
import javafx.scene.shape.Rectangle;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible
{

	private boolean isDestroyed;

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos)
	{
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	@Override
	public abstract void takeDamage();

	@Override
	public void destroy()
	{
		this.isDestroyed = true;
	}

	public boolean getIsDestroyed()
	{
		return isDestroyed;
	}

	public abstract Rectangle getHitBox();

}
