package com.example.demo.Managers;


import com.example.demo.levels.LevelOne;
import com.example.demo.levels.LevelParent;
import com.example.demo.levels.LevelTutorial;
import com.example.demo.levels.LevelTwo;

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

    public void checkLevelState()
    {
        checkIfGameOver();
        checkIfLevelCompleted();
    }
}
