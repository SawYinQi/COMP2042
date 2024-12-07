package com.example.demo.managers;

import com.example.demo.entities.destructibles.ActiveActor;
import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.entities.planes.FighterPlane;
import com.example.demo.entities.planes.UserPlane;
import javafx.scene.Group;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameActorManager class manages all game actors in the game,
 * includes removing actors, adding actors, generating projectiles, updating actors and tracking actors.
 */
public class GameActorManager
{
    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;
    private final Group root;
    private final double screenWidth;
    private final UserPlane user;

    /**
     * Constructs a GameActorManager with the specified root, screen width, and user plane.
     *
     * @param root        the root group which game actors are added.
     * @param screenWidth the width of the game screen for boundary checks.
     * @param user        the UserPlane representing the player.
     */
    public GameActorManager(Group root, double screenWidth, UserPlane user)
    {
        this.root = root;
        this.screenWidth = screenWidth;
        this.user = user;
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
    }

    /**
     * Initializes the friendly units by adding user plane to root group and list.
     */
    private void initializeFriendlyUnits()
    {
        root.getChildren().add(user);
        friendlyUnits.add(user);
    }

    /**
     * Updates all actors in the game by invoking their updateActor methods.
     */
    private void updateActors()
    {
        friendlyUnits.forEach(ActiveActorDestructible::updateActor);
        enemyUnits.forEach(ActiveActorDestructible::updateActor);
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * Removes all destroyed actors, actors that has breached players defense
     * and all projectiles off-screen.
     */
    private void removeAllDestroyedActors()
    {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
        removePenetratedEnemies();
        removeOffScreenUserProjectiles();
    }

    /**
     * Removes all destroyed actors from the root group
     * and resets the list removing all destroyed actors from the list.
     *
     * @param actors the list of ActiveActorDestructible to be filter base on destruction state.
     */
    private void removeDestroyedActors(List<ActiveActorDestructible> actors)
    {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::getIsDestroyed)
                .toList();
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Adds an enemy unit to the root group and the list of enemy units.
     *
     * @param enemy the ActiveActorDestructible enemy to add.
     */
    private void addEnemyUnit(ActiveActorDestructible enemy)
    {
        getEnemyUnits().add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Spawns a projectile for an enemy by adding it to the root group and list of enemy projectiles,
     * if it is not null.
     *
     * @param projectile the enemy projectile to be added.
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile)

    {
        if (projectile != null)
        {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    /**
     * Generates projectiles for each enemy units by invoking their fireProjectile methods.
     */
    private void generateEnemyFire()
    {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    /**
     * Removes all enemy units that have moved beyond the screen's left boundary.
     */
    private void removePenetratedEnemies()
    {
        List<ActiveActorDestructible> penetratedDefense = enemyUnits.stream().filter(enemy -> Math.abs(enemy.getTranslateX()) > screenWidth)
                .toList();
        root.getChildren().removeAll(penetratedDefense);
        enemyUnits.removeAll(penetratedDefense);
    }

    /**
     * Removes all projectiles from root group and list that have moved off-screen.
     */
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

    /**
     * Updates the player's kill count by invoking incrementKillCount for each destroyed enemy units.
     */
    private void updateKillCount()
    {
        List<ActiveActorDestructible> destroyedEnemy = getEnemyUnits().stream().filter(ActiveActorDestructible::getIsDestroyed)
                .toList();

        destroyedEnemy.forEach(enemy -> user.incrementKillCount());
    }

    /**
     * Updates all actors, removes destroyed and off-screen actors, and generates enemy fire.
     */
    public void updateAllActors()
    {
        updateKillCount();
        removeAllDestroyedActors();
        updateActors();
        generateEnemyFire();
        //displayAllHitbox(); // For checking hitbox of actors.
    }

    /**
     * Adds an enemy unit to the root group and the list of enemy units.
     *
     * @param enemy the enemy unit to be added.
     */
    public void setAddEnemyUnit(ActiveActorDestructible enemy)
    {
        addEnemyUnit(enemy);
    }

    /**
     * Initializes friendly units and adds them to root group and list.
     */
    public void getInitializeFriendlyUnit()
    {
        initializeFriendlyUnits();
    }

    /**
     * Returns the current number of enemy units.
     *
     * @return the number of enemy units in the list.
     */
    public int getCurrentNumberOfEnemies()
    {
        return getEnemyUnits().size();
    }

    /**
     * Returns the list of friendly units.
     *
     * @return a list of ActiveActorDestructible friendly units.
     */
    public List<ActiveActorDestructible> getFriendlyUnits() {
        return friendlyUnits;
    }

    /**
     * Returns the list of enemy units.
     *
     * @return a list of ActiveActorDestructible enemy units.
     */
    public List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    /**
     * Returns the list of enemy projectiles.
     *
     * @return a list of ActiveActorDestructible enemy projectiles.
     */
    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    /**
     * Returns the list of user projectiles.
     *
     * @return a list of ActiveActorDestructible user projectiles.
     */
    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    /*
    private void displayAllHitbox() // Display hitbox of all actors
    {
        // Remove duplicate instance
        root.getChildren().removeIf(node -> node instanceof Rectangle);

        // For each friendly units hitbox, set the outline of the hitbox red,
        // fill hitbox as transparent, and add to root group.
        friendlyUnits.forEach(actor ->
        {
            Rectangle hitbox = actor.getHitBox();
            hitbox.setStroke(Color.RED);
            hitbox.setFill(Color.TRANSPARENT);
            root.getChildren().add(hitbox);
        });

        // For each enemy units's hitbox, set the outline of the hitbox blue,
        // fill hitbox as transparent, and add to root group.
        enemyUnits.forEach(actor ->
        {
            Rectangle hitbox = actor.getHitBox();
            hitbox.setStroke(Color.BLUE);
            hitbox.setFill(Color.TRANSPARENT);
            root.getChildren().add(hitbox);
        });

        // For each user projectile hitbox, set the outline of the hitbox green,
        // fill hitbox as transparent, and add to root group.
        userProjectiles.forEach(projectile ->
        {
            Rectangle hitbox = projectile.getHitBox();
            hitbox.setStroke(Color.GREEN);
            hitbox.setFill(Color.TRANSPARENT);
            root.getChildren().add(hitbox);
        });

         // For each enemy projectile hitbox, set the outline of the hitbox yellow,
         // fill hitbox as transparent, and add to root group.
        enemyProjectiles.forEach(projectile ->
        {
            Rectangle hitbox = projectile.getHitBox();
            hitbox.setStroke(Color.YELLOW);
            hitbox.setFill(Color.TRANSPARENT);
            root.getChildren().add(hitbox);
        });
    }
     */

}
