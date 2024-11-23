package com.example.demo.levels;

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
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent()))
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
}
