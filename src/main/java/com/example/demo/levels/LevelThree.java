package com.example.demo.levels;

import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.EnemyPlane;
import com.example.demo.entities.EnemyPlaneVerTwo;

public class LevelThree extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 1;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;


    public LevelThree(double screenHeight, double screenWidth, MainController mainController) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, mainController);
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
                if(newEnemyInitialYPosition > 50)
                {
                    ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                    ActiveActorDestructible newEnemyTwo = new EnemyPlaneVerTwo(getScreenWidth(), newEnemyInitialYPosition);
                    getGameActorManager().setAddEnemyUnit(newEnemy);
                    getGameActorManager().setAddEnemyUnit(newEnemyTwo);
                }
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView()
    {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
    }

    private boolean userHasReachedKillTarget()
    {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    public boolean getUserHasReachedKillTarget()
    {
        return userHasReachedKillTarget();
    }
}