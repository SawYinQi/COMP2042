package com.example.demo.levels;

import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelTutorialView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.entities.planes.EnemyPlane;

/**
 * The LevelTutorial class extends LevelParent and
 * represents the demo level which spawns a stationary and harmless enemy plane,
 * to introduce player to the game controls, and returns user to main menu once objective is completed.
 */
public class LevelTutorial extends LevelParent
{
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int KILLS_TO_ADVANCE = 1;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private LevelTutorialView levelView;

    /**
     * Constructs a LevelTutorial instance with the specified screen height, width and main controller.
     *
     * @param screenHeight   the height of the game screen.
     * @param screenWidth    the width of the game screen.
     * @param mainController the MainController managing the game flow.
     */
    public LevelTutorial(double screenHeight, double screenWidth, MainController mainController)
    {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, mainController);
        initializeLevelView();
    }

    /**
     * Spawns a single stationary and harmless enemy unit for the tutorial level.
     */
    @Override
    protected void spawnEnemyUnits()
    {
        if(getGameActorManager().getCurrentNumberOfEnemies() == 0)
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
            getGameActorManager().setAddEnemyUnit(newEnemy);
        }
    }

    /**
     * Instantiates the LevelTutorialView for level tutorial.
     *
     * @return the LevelTutorialView of the tutorial level.
     */
    @Override
    protected LevelView instantiateLevelView()
    {
        levelView = new LevelTutorialView(getRoot(), PLAYER_INITIAL_HEALTH, KILLS_TO_ADVANCE);
        return levelView;
    }

    /**
     * Checks if the player has reached the required number of kills to return to main menu in level tutorial.
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
