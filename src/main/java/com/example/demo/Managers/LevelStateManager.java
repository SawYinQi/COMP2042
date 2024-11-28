package com.example.demo.Managers;


import com.example.demo.levels.*;

public class LevelStateManager
{
    private final LevelParent levelParent;

    public LevelStateManager(LevelParent level)
    {
        this.levelParent = level;
    }

    private void checkIfGameOver()
    {
        if (levelParent.userIsDestroyed())
        {
            levelParent.stopGame();
            levelParent.loseGame();
        }
    }

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
                    levelParent.goToNextLevel("com.example.demo.levels.LevelTwo");
                }
                break;
            case "LevelTwo":
                LevelTwo levelTwo = (LevelTwo) levelParent;
                if (levelTwo.getUserHasReachedKillTarget())
                {
                    levelParent.stopGame();
                    levelParent.goToNextLevel("com.example.demo.levels.LevelThree");
                }
                break;
            case "LevelThree":
                LevelThree levelThree = (LevelThree) levelParent;
                if (levelThree.getUserHasReachedKillTarget())
                {
                    levelParent.stopGame();
                    levelParent.goToNextLevel("com.example.demo.levels.LevelBoss");
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

    public void checkLevelState()
    {
        checkIfGameOver();
        checkIfLevelCompleted();
    }
}
