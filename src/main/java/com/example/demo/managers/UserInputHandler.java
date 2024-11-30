package com.example.demo.managers;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.entities.UserPlane;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class UserInputHandler
{
    private final Timeline timeline;
    private final UserPlane user;
    private final Group root;
    private final List<ActiveActorDestructible> userProjectiles;
    private final LevelView levelView;
    private boolean isNotPlaying = true;

    public UserInputHandler(UserPlane user, Group root, List<ActiveActorDestructible> userProjectiles, Timeline timeline, LevelView levelView)
    {
        this.user = user;
        this.root = root;
        this.userProjectiles = userProjectiles;
        this.timeline = timeline;
        this.levelView = levelView;
    }

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

    private void onKeyReleased(KeyEvent e)
    {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.moves(UserPlane.Direction.stop);
    }

    public void handleKeyPressed(KeyEvent event)
    {
        onKeyPressed(event);
    }

    public void handleKeyReleased(KeyEvent event)
    {
        onKeyReleased(event);
    }

    private void fireProjectile(UserPlane user, Group root, List<ActiveActorDestructible> userProjectiles)
    {
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }
}


