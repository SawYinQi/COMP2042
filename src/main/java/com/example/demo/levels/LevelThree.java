package com.example.demo.levels;

import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.planes.EnemyPlane;
import com.example.demo.entities.planes.EnemyPlaneVerTwo;
import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.utility.EnemySpawner;
import java.util.List;

/**
 * The LevelThree class extends LevelParent and
 * represents the third level which spawns mix of enemy plane and enemy plane version two units,
 * and advances to next level once kill target is reached.
 */
public class LevelThree extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 15;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs a new LevelThree instance with the specified screen height, width and main controller.
     *
     * @param screenHeight   the height of the game screen.
     * @param screenWidth    the width of the game screen.
     * @param mainController the MainController managing the game flow.
     */
    public LevelThree(double screenHeight, double screenWidth, MainController mainController) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, mainController);
        initializeLevelView();
    }

    /**
     * Spawns a mix of enemy plane and enemy plane version two units
     * for Level Three using the EnemySpawner utility class,
     * base on enemy type, total enemies, spawn probability, screen width, maximum y spawning position,
     * and game actor manager.
     */
    @Override
    protected void spawnEnemyUnits()
    {
        List<Class<? extends ActiveActorDestructible>> enemyType = List.of(
                EnemyPlane.class,
                EnemyPlaneVerTwo.class);

        for (Class<? extends ActiveActorDestructible> enemy:enemyType)
        {
            EnemySpawner.spawnEnemyUnits(
                    enemy,
                    TOTAL_ENEMIES,
                    ENEMY_SPAWN_PROBABILITY,
                    getScreenWidth(),
                    getEnemyMaximumYPosition(),
                    getGameActorManager());
        }

    }

    /**
     * Instantiates the LevelView for level three.
     *
     * @return the LevelView of level three.
     */
    @Override
    protected LevelView instantiateLevelView()
    {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
    }

    /**
     * Checks if the player has reached the required number of kills to advance to the next level in level three.
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