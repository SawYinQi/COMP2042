package com.example.demo.managers;

import com.example.demo.displays.LevelBossView;
import com.example.demo.displays.LevelTutorialView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.UserPlane;

public class LevelViewHandler
{
    private final UserPlane user;
    private final LevelView levelView;
    public LevelViewHandler(UserPlane user, LevelView levelView)
    {
        this.user = user;
        this.levelView = levelView;
    }

    private void updateLevelView()
    {
        levelView.removeHearts(user.getHealth());
        levelView.updateAmmunitionDisplay(user.getAmmunition());
        levelView.updateKillTargetDisplay(user.getNumberOfKills());
        if (levelView instanceof LevelBossView)
        {
            ((LevelBossView) levelView).updateLevelTwoView();
        }
        else if(levelView instanceof LevelTutorialView)
        {
            ((LevelTutorialView) levelView).showInstructions();
        }
    }

    public void getUpdateLevelView()
    {
        updateLevelView();
    }
}
