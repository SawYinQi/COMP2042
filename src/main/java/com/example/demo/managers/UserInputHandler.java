package com.example.demo.managers;

import com.example.demo.displays.LevelView;
import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.entities.UserPlane;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.List;

/**
 * The UserInputHandler class handles the user input event to control user's plane.
 */
public class UserInputHandler
{
    private final Timeline timeline;
    private final UserPlane user;
    private final Group root;
    private final List<ActiveActorDestructible> userProjectiles;
    private final LevelView levelView;
    private boolean isNotPlaying = true;

    /**
     * Constructs a UserInputHandler with the specified
     * user plane, root group, list of user projectiles, timeline and level view.
     *
     * @param user            the UserPlane representing the user.
     * @param root            the root group containing game elements.
     * @param userProjectiles the list of projectiles fired by the user.
     * @param timeline        the Timeline controlling the game's updates.
     * @param levelView       the LevelView displaying the current level's displays.
     */
    public UserInputHandler(UserPlane user, Group root, List<ActiveActorDestructible> userProjectiles, Timeline timeline, LevelView levelView)
    {
        this.user = user;
        this.root = root;
        this.userProjectiles = userProjectiles;
        this.timeline = timeline;
        this.levelView = levelView;
    }

    /**
     * Handles key press events from user to control
     * user plane's movement, game state, and firing.
     *
     * @param e the KeyEvent triggered by key press.
     */
    private void onKeyPressed(KeyEvent e)
    {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP) user.moves(UserPlane.Direction.up);
        if (kc == KeyCode.DOWN) user.moves(UserPlane.Direction.down);
        if (kc == KeyCode.SPACE && user.getAmmunition() > 0)
        {
            fireProjectile(user, root, userProjectiles);
        }
        if (kc == KeyCode.P)
        {
            if(isNotPlaying)
            {
                levelView.showPauseImage();
                timeline.pause();
                user.getTimeline().pause();
            } else
            {
                levelView.hidePauseImage();
                timeline.play();
                user.getTimeline().play();
            }
            isNotPlaying = !isNotPlaying;
        }
    }

    /**
     * Handles key release events to stop the user plane's movement.
     *
     * @param e the KeyEvent triggered by the key release.
     */
    private void onKeyReleased(KeyEvent e)
    {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.moves(UserPlane.Direction.stop);
    }

    /**
     * Public method serving as wrapper for access to private class in UserInputHandler.
     *
     * @param event the KebEvent triggered by key press.
     */
    public void handleKeyPressed(KeyEvent event)
    {
        onKeyPressed(event);
    }

    /**
     * Public method serving as wrapper for access to private class in UserInputHandler.
     *
     * @param event the KebEvent triggered by key release.
     */
    public void handleKeyReleased(KeyEvent event)
    {
        onKeyReleased(event);
    }

    /**
     * Fires a projectile from the user plane, adding it to the root group and projectile list.
     *
     * @param user            the UserPlane firing the projectile.
     * @param root            the root group containing game elements.
     * @param userProjectiles the list of projectiles fired by the user.
     */
    private void fireProjectile(UserPlane user, Group root, List<ActiveActorDestructible> userProjectiles)
    {
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }
}


