package com.example.demo.levels;

import com.example.demo.displays.LevelView;
import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.EnemyPlane;

public class LevelOne extends LevelParent
{
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;


	public LevelOne(double screenHeight, double screenWidth)
	{
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void spawnEnemyUnits()
	{
		int currentNumberOfEnemies = getNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++)
		{
			if (Math.random() < ENEMY_SPAWN_PROBABILITY)
			{
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	private int getNumberOfEnemies()
	{
		return getCurrentNumberOfEnemies();
	}

	@Override
	protected LevelView instantiateLevelView()
	{
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	protected boolean userHasReachedKillTarget()
	{
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}
