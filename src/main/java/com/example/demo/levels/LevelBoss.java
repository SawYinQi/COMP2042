package com.example.demo.levels;

import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelBossView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.Boss;
import com.example.demo.entities.EnemyPlaneVerTwo;

public class LevelBoss extends LevelParent
{

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final int TOTAL_ENEMIES = 3;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private final Boss boss;
	private LevelBossView levelView;

	public LevelBoss(double screenHeight, double screenWidth, MainController mainController)
	{
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, mainController);
		this.boss = new Boss();
		initializeLevelView();
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
				ActiveActorDestructible newEnemy = new EnemyPlaneVerTwo(getScreenWidth(), newEnemyInitialYPosition);
				getGameActorManager().setAddEnemyUnit(newEnemy);
			}
		}
		if(!getGameActorManager().getEnemyUnits().contains(boss))
		{
			getGameActorManager().setAddEnemyUnit(boss);
		}

	}

	private int getNumberOfEnemies()
	{
		return getGameActorManager().getCurrentNumberOfEnemies();
	}

	@Override
	protected LevelView instantiateLevelView()
	{
		levelView = new LevelBossView(getRoot(), PLAYER_INITIAL_HEALTH, 0, boss);
		return levelView;
	}

	public Boss getBoss()
	{
		return boss;
	}

}
