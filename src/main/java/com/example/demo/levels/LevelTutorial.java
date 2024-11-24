package com.example.demo.levels;

import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelTutorialView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.EnemyPlane;

public class LevelTutorial extends LevelParent
{
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int KILLS_TO_ADVANCE = 1;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private LevelTutorialView levelView;

    public LevelTutorial(double screenHeight, double screenWidth, MainController mainController)
    {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, mainController);
    }

    @Override
    protected void spawnEnemyUnits()
    {
        ActiveActorDestructible newEnemy = new EnemyPlane( 1080, 310)
        {
            @Override
            public void updatePosition()
            {
                move(0.0,0.0);
            }

            @Override
            public ActiveActorDestructible fireProjectile()
            {
                return null;
            }
        };
        getGameActorManager().addEnemyUnit(newEnemy);
    }

    @Override
    protected LevelView instantiateLevelView()
    {
        levelView = new LevelTutorialView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    protected boolean userHasReachedKillTarget()
    {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
