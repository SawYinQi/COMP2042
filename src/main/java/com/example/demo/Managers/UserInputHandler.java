package com.example.demo.Managers;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.UserPlane;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class UserInputHandler
{
    private boolean isNotPlaying = true;

    public void onKeyPressed(KeyEvent e, UserPlane user, Group root, List<ActiveActorDestructible> userProjectiles, Timeline timeline, LevelView levelView)
    {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP) user.moves(UserPlane.Direction.up);
        if (kc == KeyCode.DOWN) user.moves(UserPlane.Direction.down);
        if (kc == KeyCode.SPACE && user.getAmmunition() > 0 && timeline.getStatus() == Animation.Status.RUNNING)
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

    public void onKeyReleased(KeyEvent e, UserPlane user)
    {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.moves(UserPlane.Direction.stop);
    }

    private void fireProjectile(UserPlane user, Group root, List<ActiveActorDestructible> userProjectiles)
    {
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }
}


