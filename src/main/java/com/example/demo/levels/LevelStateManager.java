package com.example.demo.levels;


public class LevelStateManager
{
    private final LevelParent levelParent;


    public LevelStateManager(LevelParent level)
    {
        this.levelParent = level;
    }

    protected void checkIfGameOver()
    {
        if (levelParent.userIsDestroyed())
        {
            levelParent.stopGame();
            levelParent.loseGame();
        }
    }

    protected void checkIfLevelCompleted()
    {
        switch (levelParent.getClass().getSimpleName())
        {
            case "LevelOne":
                LevelOne levelOne = (LevelOne) levelParent;
                if (levelOne.userHasReachedKillTarget())
                {
                    levelParent.stopGame();
                    levelParent.goToNextLevel("com.example.demo.levels.LevelTwo");
                }
                break;
            case "LevelTwo":
                LevelTwo levelTwo = (LevelTwo) levelParent;
                if (levelTwo.getBoss().isDestroyed())
                {
                    levelParent.stopGame();
                    levelParent.winGame();
                }
                break;
        }
    }
}
