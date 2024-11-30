package com.example.demo.levels;

import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelBossView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.planes.Boss;
import com.example.demo.entities.planes.EnemyPlaneVerTwo;
import com.example.demo.utility.EnemySpawner;

/**
 * The LevelBoss class extends LevelParent and
 * represents the final level which spawns the boss unit and additional enemy version two.
 */
public class LevelBoss extends LevelParent
{

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final int NO_KILL_TARGET_OBJECTIVE = 0;
	private static final int TOTAL_ENEMIES = 3;
	private static final double ENEMY_SPAWN_PROBABILITY = .01;
	private LevelBossView levelView;
	private final Boss boss;

	/**
	 * Constructs a LevelBoss instance with the specified screen height, width and main controller.
	 *
	 * @param screenHeight   the height of the game screen.
	 * @param screenWidth    the width of the game screen.
	 * @param mainController the MainController managing the game flow.
	 */
	public LevelBoss(double screenHeight, double screenWidth, MainController mainController)
	{
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, mainController);
		this.boss = new Boss();
		initializeLevelView();
	}

	/**
	 * Spawns additional enemy units and boss for the boss level using the EnemySpawner utility class,
	 * base on enemy type, total enemies, spawn probability, screen width, maximum y spawning position,
	 * and game actor manager.
	 */
	@Override
	protected void spawnEnemyUnits()
	{
		EnemySpawner.spawnEnemyUnits(
				EnemyPlaneVerTwo.class,
				TOTAL_ENEMIES,
				ENEMY_SPAWN_PROBABILITY,
				getScreenWidth(),
				getEnemyMaximumYPosition(),
				getGameActorManager());

		if(!getGameActorManager().getEnemyUnits().contains(boss))
		{
			getGameActorManager().setAddEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates the LevelBossView for the boss level.
	 *
	 * @return the LevelBossView of the boss level.
	 */
	@Override
	protected LevelView instantiateLevelView()
	{
		levelView = new LevelBossView(getRoot(), PLAYER_INITIAL_HEALTH, NO_KILL_TARGET_OBJECTIVE, boss);
		return levelView;
	}

	/**
	 * Returns the Boss object for the level.
	 *
	 * @return the Boss instance.
	 */
	public Boss getBoss()
	{
		return boss;
	}

}
