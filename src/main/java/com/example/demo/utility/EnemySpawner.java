package com.example.demo.utility;

import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.managers.GameActorManager;

public class EnemySpawner
{

    private static final double SCREEN_HEIGHT_OFFSET = 50.0;

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

