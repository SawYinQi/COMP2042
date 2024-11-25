package com.example.demo.Managers;

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

    public GameActorManager(Group root)
    {
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.root = root;
    }

    protected void updateActors()
    {
        friendlyUnits.forEach(ActiveActor::updateActor);
        enemyUnits.forEach(ActiveActor::updateActor);
        userProjectiles.forEach(ActiveActor::updateActor);
        enemyProjectiles.forEach(ActiveActor::updateActor);
    }

    protected void removeAllDestroyedActors(double screenWidth)
    {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
        removePenetratedEnemies(enemyUnits,screenWidth);
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

    protected void removePenetratedEnemies(List<ActiveActorDestructible> enemyUnits, double screenWidth)
    {
        List<ActiveActorDestructible> penetratedDefense = enemyUnits.stream().filter(enemy -> Math.abs(enemy.getTranslateX()) > screenWidth)
                .toList();
        root.getChildren().removeAll(penetratedDefense);
        enemyUnits.removeAll(penetratedDefense);
    }

    protected void updateKillCount(UserPlane user)
    {
        List<ActiveActorDestructible> destroyedEnemy = getEnemyUnits().stream().filter(ActiveActorDestructible::isDestroyed)
                .toList();

        destroyedEnemy.forEach(enemy -> user.incrementKillCount());
    }

    public void updateAllActors(GameActorManager gameActorManager, UserPlane user, double screenWidth)
    {
        gameActorManager.updateKillCount(user);
        gameActorManager.removeAllDestroyedActors(screenWidth);
        gameActorManager.updateActors();
        gameActorManager.generateEnemyFire();
    }

    public void setAddEnemyUnit(ActiveActorDestructible enemy)
    {
        addEnemyUnit(enemy);
    }

    public int getCurrentNumberOfEnemies()
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
