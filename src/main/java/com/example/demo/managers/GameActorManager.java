package com.example.demo.managers;

import com.example.demo.entities.destructibles.ActiveActor;
import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.entities.FighterPlane;
import com.example.demo.entities.UserPlane;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class GameActorManager
{
    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;
    private final Group root;
    private final double screenWidth;
    private final UserPlane user;

    public GameActorManager(Group root, double screenWidth, UserPlane user)
    {
        this.root = root;
        this.screenWidth = screenWidth;
        this.user = user;
        this.friendlyUnits = new ArrayList<>(List.of(user));
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
    }

    private void initializeFriendlyUnits()
    {
        root.getChildren().add(user);
    }

    private void updateActors()
    {
        friendlyUnits.forEach(ActiveActor::updateActor);
        enemyUnits.forEach(ActiveActor::updateActor);
        userProjectiles.forEach(ActiveActor::updateActor);
        enemyProjectiles.forEach(ActiveActor::updateActor);
    }

    private void removeAllDestroyedActors()
    {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
        removePenetratedEnemies();
        removeOffScreenUserProjectiles();
    }

    private void removeDestroyedActors(List<ActiveActorDestructible> actors)
    {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::getIsDestroyed)
                .toList();
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    private void addEnemyUnit(ActiveActorDestructible enemy)
    {
        getEnemyUnits().add(enemy);
        root.getChildren().add(enemy);
    }

    private void spawnEnemyProjectile(ActiveActorDestructible projectile)

    {
        if (projectile != null)
        {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    private void generateEnemyFire()
    {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    private void removePenetratedEnemies()
    {
        List<ActiveActorDestructible> penetratedDefense = enemyUnits.stream().filter(enemy -> Math.abs(enemy.getTranslateX()) > screenWidth)
                .toList();
        root.getChildren().removeAll(penetratedDefense);
        enemyUnits.removeAll(penetratedDefense);
    }

    private void removeOffScreenUserProjectiles()
    {
        List<ActiveActorDestructible> offScreenUserProjectiles = userProjectiles.stream().filter(projectile -> Math.abs(projectile.getTranslateX()) > screenWidth)
                .toList();
        List<ActiveActorDestructible> offScreenEnemyProjectiles = enemyProjectiles.stream().filter(projectile -> Math.abs(projectile.getTranslateX()) > screenWidth)
                .toList();

        root.getChildren().removeAll(offScreenUserProjectiles);
        userProjectiles.removeAll(offScreenUserProjectiles);

        root.getChildren().removeAll(offScreenEnemyProjectiles);
        userProjectiles.removeAll(offScreenEnemyProjectiles);
    }

    private void updateKillCount()
    {
        List<ActiveActorDestructible> destroyedEnemy = getEnemyUnits().stream().filter(ActiveActorDestructible::getIsDestroyed)
                .toList();

        destroyedEnemy.forEach(enemy -> user.incrementKillCount());
    }

    public void updateAllActors()
    {
        updateKillCount();
        removeAllDestroyedActors();
        updateActors();
        generateEnemyFire();
        displayAllHitbox();
    }

    public void setAddEnemyUnit(ActiveActorDestructible enemy)
    {
        addEnemyUnit(enemy);
    }

    public void getInitializeFriendlyUnit()
    {
        initializeFriendlyUnits();
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

    private void displayAllHitbox()
    {
        root.getChildren().removeIf(node -> node instanceof Rectangle);

        friendlyUnits.forEach(actor ->
        {
            Rectangle hitbox = actor.getHitBox();
            hitbox.setStroke(Color.RED);
            hitbox.setFill(Color.TRANSPARENT);
            root.getChildren().add(hitbox);
        });

        enemyUnits.forEach(actor ->
        {
            Rectangle hitbox = actor.getHitBox();
            hitbox.setStroke(Color.BLUE);
            hitbox.setFill(Color.TRANSPARENT);
            root.getChildren().add(hitbox);
        });

        userProjectiles.forEach(projectile ->
        {
            Rectangle hitbox = projectile.getHitBox();
            hitbox.setStroke(Color.GREEN);
            hitbox.setFill(Color.TRANSPARENT);
            root.getChildren().add(hitbox);
        });

        enemyProjectiles.forEach(projectile ->
        {
            Rectangle hitbox = projectile.getHitBox();
            hitbox.setStroke(Color.YELLOW);
            hitbox.setFill(Color.TRANSPARENT);
            root.getChildren().add(hitbox);
        });
    }


}
