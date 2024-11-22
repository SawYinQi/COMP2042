package com.example.demo.levels;

import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.UserPlane;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class UserInputHandler {
    public void onKeyPressed(KeyEvent e, UserPlane user, Group root, List<ActiveActorDestructible> userProjectiles)
    {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP) user.moves(UserPlane.Direction.up);
        if (kc == KeyCode.DOWN) user.moves(UserPlane.Direction.down);
        if (kc == KeyCode.SPACE && user.getAmmunition() > 0) fireProjectile(user, root, userProjectiles);
    }

    public void onKeyReleased(KeyEvent e, UserPlane user) {
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


