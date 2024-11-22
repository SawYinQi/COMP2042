package com.example.demo.levels;

import com.example.demo.entities.ActiveActor;
import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.FighterPlane;
import com.example.demo.entities.UserPlane;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class GameActorManager
{
    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;
    private final Group root;
    private int previousNumberOfEnemies;

    public GameActorManager(Group root)
    {
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.previousNumberOfEnemies = 0;
        this.root = root;
    }

    protected void updateKillCount(UserPlane user)
    {
        int kills = previousNumberOfEnemies - getCurrentNumberOfEnemies();
        previousNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < kills; i++) {
            user.incrementKillCount();
        }

    }

    protected void updateActors()
    {
        friendlyUnits.forEach(ActiveActor::updateActor);
        enemyUnits.forEach(ActiveActor::updateActor);
        userProjectiles.forEach(ActiveActor::updateActor);
        enemyProjectiles.forEach(ActiveActor::updateActor);
    }

    protected void removeAllDestroyedActors()
    {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    private void removeDestroyedActors(List<ActiveActorDestructible> actors)
    {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed)
                .toList();
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    protected void addEnemyUnit(ActiveActorDestructible enemy)
    {
        getEnemyUnits().add(enemy);
        root.getChildren().add(enemy);
    }

    protected void spawnEnemyProjectile(ActiveActorDestructible projectile)

    {
        if (projectile != null)
        {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    protected void generateEnemyFire()
    {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    protected int getCurrentNumberOfEnemies()
    {
        return getEnemyUnits().size();
    }


    public List<ActiveActorDestructible> getFriendlyUnits() {
        return friendlyUnits;
    }

    public List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

}
