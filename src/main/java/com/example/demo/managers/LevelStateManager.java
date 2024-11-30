package com.example.demo.managers;

import com.example.demo.levels.*;

/**
 * The LevelStateManager manages the state transitions of levels in the game
 * and state of the current level.
 */
public class LevelStateManager
{
    private final LevelParent levelParent;
    private static final String LEVEL_TWO = "com.example.demo.levels.LevelTwo";
    private static final String LEVEL_THREE = "com.example.demo.levels.LevelThree";
    private static final String LEVEL_BOSS = "com.example.demo.levels.LevelBoss";

    /**
     * Constructs a LevelStateManager for the specified level.
     *
     * @param level the LevelParent representing the current level.
     */
    public LevelStateManager(LevelParent level)
    {
        this.levelParent = level;
    }

    /**
     * Checks if the game is over, by checking the destruction status of user plane.
     */
    private void checkIfGameOver()
    {
        if (levelParent.userIsDestroyed())
        {
            levelParent.stopGame();
            levelParent.loseGame();
        }
    }

    /**
     * Checks if the current level is completed and transitions to the next state.
     */
    private void checkIfLevelCompleted()
    {
        switch (levelParent.getClass().getSimpleName())
        {
            case "LevelTutorial":
                LevelTutorial levelTutorial = (LevelTutorial) levelParent;
                if (levelTutorial.getUserHasReachedKillTarget())
                {
                    levelParent.stopGame();
                    levelParent.backToMenu();
                }
                break;
            case "LevelOne":
                LevelOne levelOne = (LevelOne) levelParent;
                if (levelOne.getUserHasReachedKillTarget())
                {
                    levelParent.stopGame();
                    levelParent.goToNextLevel(LEVEL_TWO);
                }
                break;
            case "LevelTwo":
                LevelTwo levelTwo = (LevelTwo) levelParent;
                if (levelTwo.getUserHasReachedKillTarget())
                {
                    levelParent.stopGame();
                    levelParent.goToNextLevel(LEVEL_THREE);
                }
                break;
            case "LevelThree":
                LevelThree levelThree = (LevelThree) levelParent;
                if (levelThree.getUserHasReachedKillTarget())
                {
                    levelParent.stopGame();
                    levelParent.goToNextLevel(LEVEL_BOSS);
                }
                break;
            case "LevelBoss":
                LevelBoss levelBoss = (LevelBoss) levelParent;
                if (levelBoss.getBoss().getIsDestroyed())
                {
                    levelParent.stopGame();
                    levelParent.winGame();
                }
                break;
        }
    }

    /**
     * Public method which serves as a wrapper,
     * allowing LevelParent to invoke LevelStateManager's private methods.
     */
    public void checkLevelState()
    {
        checkIfGameOver();
        checkIfLevelCompleted();
    }
}
