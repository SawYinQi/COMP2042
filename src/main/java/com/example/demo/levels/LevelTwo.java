package com.example.demo.levels;

import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelTwoView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.Boss;

public class LevelTwo extends LevelParent
{

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelTwoView levelView;

	public LevelTwo(double screenHeight, double screenWidth, MainController mainController)
	{
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, mainController);
		boss = new Boss();
	}

	@Override
	protected void spawnEnemyUnits()
	{
		if (getGameActorManager().getCurrentNumberOfEnemies() == 0)
		{
			getGameActorManager().addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView()
	{
		levelView = new LevelTwoView(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

	public Boss getBoss()
	{
		return boss;
	}

}
