package com.example.demo.managers;

import com.example.demo.entities.destructibles.ActiveActorDestructible;
import com.example.demo.entities.planes.UserPlane;
import java.util.List;

/**
 * The CollisionManager class manage and handles all collision in the game.
 */
public class CollisionManager
{
    private final GameActorManager gameActorManager;
    private final UserPlane user;
    private final double screenWidth;

    /**
     * Constructs a CollisionManager with the specified game actor manager, user, and screen width.
     *
     * @param gameActorManager the GameActorManager managing all game actors.
     * @param user             the UserPlane representing the player.
     * @param screenWidth      the width of the game screen for boundary checks.
     */
    public CollisionManager(GameActorManager gameActorManager, UserPlane user, double screenWidth)
    {
        this.user = user;
        this.screenWidth = screenWidth;
        this.gameActorManager = gameActorManager;
    }

    /**
     * Handles collisions between user and enemy unit.
     */
    private void handlePlaneCollisions()
    {
        handleCollisions(gameActorManager.getFriendlyUnits(), gameActorManager.getEnemyUnits());
    }

    /**
     * Handles collisions between enemy units and user projectiles.
     */
    private void handleUserProjectileCollisions()
    {
        handleCollisions(gameActorManager.getEnemyUnits(), gameActorManager.getUserProjectiles());
    }

    /**
     * Handles collisions between user and enemy projectiles.
     */
    private void handleEnemyProjectileCollisions()
    {
        handleCollisions(gameActorManager.getFriendlyUnits(), gameActorManager.getEnemyProjectiles());
    }

    /**
     * Checks for collisions between two lists of destructible actors and damage
     * both actors upon collision.
     *
     * @param actors1 the first list of ActiveActorDestructible.
     * @param actors2 the second list of ActiveActorDestructible.
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1,
                                  List<ActiveActorDestructible> actors2)
    {
        for (ActiveActorDestructible actor : actors2)
        {
            for (ActiveActorDestructible otherActor : actors1)
            {
                if (actor.getHitBox().intersects(otherActor.getHitBox().getBoundsInParent()))
                {
                    actor.takeDamage();
                    otherActor.takeDamage();
                    break;
                }
            }
        }
    }

    /**
     * Checks if an enemy has penetrated the player's defenses
     * by moving beyond the left side of the screen.
     *
     * @param enemy the enemy actor.
     * @return true if the enemy has penetrated the defenses, false otherwise.
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy)
    {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    /**
     * Handles enemy units that has breached player's defense by damaging player plane.
     */
    private void handleEnemyPenetration()
    {
        for (ActiveActorDestructible enemy : gameActorManager.getEnemyUnits())
        {
            if (enemyHasPenetratedDefenses(enemy))
            {
                user.takeDamage();
            }
        }
    }

    /**
     * Handles all collision scenarios within the game.
     */
    public void handleAllCollisions()
    {
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePlaneCollisions();
        handleEnemyPenetration();
    }
}
