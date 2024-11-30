package com.example.demo.levels;

import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.EnemyPlaneVerTwo;
import com.example.demo.utility.EnemySpawner;

/**
 * The LevelTwo class extends LevelParent and
 * represents the second level which spawns enemy plane version two units,
 * and advances to next level once kill target is reached.
 */
public class LevelTwo extends LevelParent
{
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs a LevelTwo instance with the specified screen height, width and main controller.
     *
     * @param screenHeight   the height of the game screen.
     * @param screenWidth    the width of the game screen.
     * @param mainController the MainController managing the game flow.
     */
    public LevelTwo(double screenHeight, double screenWidth, MainController mainController)
    {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, mainController);
        initializeLevelView();
    }

    /**
     * Spawns enemy version two units for Level Two using the EnemySpawner utility class,
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
    }

    /**
     * Instantiates the LevelView for level two.
     *
     * @return the LevelView of level two.
     */
    @Override
    protected LevelView instantiateLevelView()
    {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
    }

    /**
     * Checks if the player has reached the required number of kills to advance to the next level in level two.
     *
     * @return true if the player has reached or exceeded the kill target, false otherwise.
     */
    private boolean userHasReachedKillTarget()
    {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Public accessor method calling userHasReachedKillTarget method.
     *
     * @return true if the player has reached or exceeded the kill target, false otherwise.
     */
    public boolean getUserHasReachedKillTarget()
    {
        return userHasReachedKillTarget();
    }
}
