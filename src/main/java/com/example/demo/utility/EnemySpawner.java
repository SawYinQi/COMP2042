package com.example.demo.utility;

import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.managers.GameActorManager;

/**
 * The EnemySpawner class is used for spawning enemy units in the game.
 */
public class EnemySpawner
{

    private static final double SCREEN_HEIGHT_OFFSET = 50.0;

    /**
     * Spawns enemy units of a specified enemy type and adds them to the game through game actor manager.
     *
     * @param enemyType             the class type of the enemy to spawn, extending ActiveActorDestructible.
     * @param totalEnemies          the total number of enemies allowed to spawn on screen.
     * @param enemySpawnProbability the probability of spawning an enemy during each iteration.
     * @param screenWidth           the screen width, used to position the enemy.
     * @param enemyMaximumYPosition the maximum y-coordinate for spawning enemies.
     * @param gameActorManager      the GameActorManager responsible for managing game actors.
     */
    private static void spawnEnemies (Class<? extends ActiveActorDestructible> enemyType,
                                      int totalEnemies,
                                      double enemySpawnProbability,
                                      double screenWidth,
                                      double enemyMaximumYPosition,
                                      GameActorManager gameActorManager)
    {
        int currentNumberOfEnemies = gameActorManager.getCurrentNumberOfEnemies();

        for (int i = 0; i < totalEnemies - currentNumberOfEnemies; i++) {
            if (Math.random() < enemySpawnProbability)
            {
                double newEnemyMaximumYPosition = Math.random() * enemyMaximumYPosition;
                if (newEnemyMaximumYPosition > SCREEN_HEIGHT_OFFSET)
                {
                    try
                    {
                        ActiveActorDestructible enemy = enemyType.getDeclaredConstructor(double.class, double.class)
                                    .newInstance(screenWidth, newEnemyMaximumYPosition);
                        gameActorManager.setAddEnemyUnit(enemy);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Public method serving as wrapper for access to EnemySpawner private spawnEnemies method.
     *
     * @param enemyType             the class type of the enemy to spawn, extending ActiveActorDestructible.
     * @param totalEnemies          the total number of enemies allowed to spawn on screen.
     * @param enemySpawnProbability the probability of spawning an enemy during each iteration.
     * @param screenWidth           the screen width, used to position the enemy.
     * @param enemyMaximumYPosition the maximum y-coordinate for spawning enemies.
     * @param gameActorManager      the GameActorManager responsible for managing game actors.
     */
    public static void spawnEnemyUnits (Class<? extends ActiveActorDestructible> enemyType,
                                        int totalEnemies,
                                        double enemySpawnProbability,
                                        double screenWidth,
                                        double enemyMaximumYPosition,
                                        GameActorManager gameActorManager)
    {
        spawnEnemies(enemyType,
                totalEnemies,
                enemySpawnProbability,
                screenWidth,
                enemyMaximumYPosition,
                gameActorManager);
    }

}

