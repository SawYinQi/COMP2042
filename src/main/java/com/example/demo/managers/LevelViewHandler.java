package com.example.demo.managers;

import com.example.demo.displays.LevelBossView;
import com.example.demo.displays.LevelTutorialView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.UserPlane;

/**
 * The LevelViewHandler class handles updates to LevelView for specific levels.
 */
public class LevelViewHandler
{
    private final UserPlane user;
    private final LevelView levelView;

    /**
     * Constructs a LevelViewHandler with the specified user plane and level view.
     *
     * @param user      the UserPlane represents the player.
     * @param levelView the LevelView to be updated.
     */
    public LevelViewHandler(UserPlane user, LevelView levelView)
    {
        this.user = user;
        this.levelView = levelView;
    }

    /**
     * Updates the level view based on the player's current state and
     * specific level.
     */
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

    /**
     * Public methods serving as a wrapper to allow LevelParent to access LevelViewHandler private methods.
     */
    public void getUpdateLevelView()
    {
        updateLevelView();
    }
}
