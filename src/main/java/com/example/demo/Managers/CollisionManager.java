package com.example.demo.Managers;

import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.UserPlane;

import java.util.List;

public class CollisionManager
{
    protected void handlePlaneCollisions(List<ActiveActorDestructible> friendlyUnits, List<ActiveActorDestructible> enemyUnits)
    {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    protected void handleUserProjectileCollisions(List<ActiveActorDestructible> userProjectiles, List<ActiveActorDestructible> enemyUnits)
    {
        handleCollisions(enemyUnits, userProjectiles);
    }

    protected void handleEnemyProjectileCollisions(List<ActiveActorDestructible> enemyProjectiles, List<ActiveActorDestructible> friendlyUnits)
    {
        handleCollisions(friendlyUnits, enemyProjectiles);
    }

    protected void handleCollisions(List<ActiveActorDestructible> actors1,
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

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth)
    {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    protected void handleEnemyPenetration(UserPlane user, List<ActiveActorDestructible> enemyUnits, double screenWidth)
    {
        for (ActiveActorDestructible enemy : enemyUnits)
        {
            if (enemyHasPenetratedDefenses(enemy, screenWidth))
            {
                user.takeDamage();
            }
        }
    }

    public void handleAllCollisions(GameActorManager gameActorManager, UserPlane user, double screenWidth)
    {
        handleUserProjectileCollisions(gameActorManager.getUserProjectiles(), gameActorManager.getEnemyUnits());
        handleEnemyProjectileCollisions(gameActorManager.getEnemyProjectiles(), gameActorManager.getFriendlyUnits());
        handlePlaneCollisions(gameActorManager.getFriendlyUnits(), gameActorManager.getEnemyUnits());
        handleEnemyPenetration(user, gameActorManager.getEnemyUnits(), screenWidth);
    }
}
