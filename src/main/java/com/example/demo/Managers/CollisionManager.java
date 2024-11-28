package com.example.demo.Managers;

import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.UserPlane;
import java.util.List;

public class CollisionManager
{
    private final GameActorManager gameActorManager;
    private final UserPlane user;
    private final double screenWidth;

    public CollisionManager(GameActorManager gameActorManager, UserPlane user, double screenWidth)
    {
        this.user = user;
        this.screenWidth = screenWidth;
        this.gameActorManager = gameActorManager;
    }
    private void handlePlaneCollisions()
    {
        handleCollisions(gameActorManager.getFriendlyUnits(), gameActorManager.getEnemyUnits());
    }

    private void handleUserProjectileCollisions()
    {
        handleCollisions(gameActorManager.getEnemyUnits(), gameActorManager.getUserProjectiles());
    }

    private void handleEnemyProjectileCollisions()
    {
        handleCollisions(gameActorManager.getFriendlyUnits(), gameActorManager.getEnemyProjectiles());
    }

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

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy)
    {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

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

    public void handleAllCollisions()
    {
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePlaneCollisions();
        handleEnemyPenetration();
    }
}
