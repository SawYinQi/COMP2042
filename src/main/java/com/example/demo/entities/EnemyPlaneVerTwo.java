package com.example.demo.entities;

import com.example.demo.entities.behaviors.EnemyPlaneVerTwoMovement;
import javafx.scene.shape.Rectangle;

/**
 * The EnemyPlaneVerTwo class extends FighterPlane and represents
 * enemy planes in the game which transition onto screen horizontally and moves vertically.
 */
public class EnemyPlaneVerTwo extends FighterPlane
{
    private static final String IMAGE_NAME = "enemyplane2.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 10.0;
    private static final int Y_POSITION_UPPER_BOUND = 50;
    private static final int Y_POSITION_LOWER_BOUND = 650;
    private static final int X_POSITION_LEFT_BOUND = 1000;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .02;
    private static final int HITBOX_X_OFFSET = 5;
    private static final int HITBOX_Y_OFFSET = 5;
    private static final int HITBOX_WIDTH = 210;
    private static final int HITBOX_HEIGHT = 50;
    private final EnemyPlaneVerTwoMovement enemyPlaneVerTwoMovement;

    /**
     * Constructs an EnemyPlaneVerTwo at the specified initial x and y position.
     *
     * @param initialXPos the initial x-coordinate of the enemy plane.
     * @param initialYPos the initial y-coordinate of the enemy plane.
     */
    public EnemyPlaneVerTwo(double initialXPos, double initialYPos)
    {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        this.enemyPlaneVerTwoMovement = new EnemyPlaneVerTwoMovement();
    }

    /**
     * Updates the position of the enemy plane based on its movement pattern within set boundaries.
     */
    @Override
    public void updatePosition()
    {
        double initialTranslateY = getTranslateY();
        double initialTranslateX = getTranslateX();
        // Two movement pattern horizontal before and vertical after enemy plane reach left boundary.
        if (isOffScreen())
        {
            move(HORIZONTAL_VELOCITY, 0);
        }
        else
        {
            move(0,enemyPlaneVerTwoMovement.getEnemyNextMove());
            double currentYPosition = getLayoutY() + getTranslateY();
            double currentXPosition = getLayoutX() + getTranslateX();
            if (currentYPosition < Y_POSITION_UPPER_BOUND || currentYPosition > Y_POSITION_LOWER_BOUND)
            {
                setTranslateY(initialTranslateY);
            }
            if (currentXPosition < X_POSITION_LEFT_BOUND)
            {
                setTranslateX(initialTranslateX);
            }
        }
    }

    /**
     * Fires a projectile from the enemy plane if the firing conditions are met.
     *
     * @return an EnemyProjectile if the plane decides to fire, or null otherwise.
     */
    @Override
    public ActiveActorDestructible fireProjectile()
    {
        if (projectileShouldFire())
        {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    /**
     * Determines whether the enemy plane should fire a projectile based on a random probability.
     *
     * @return true if the enemy should fire, false otherwise.
     */
    private boolean projectileShouldFire()
    {
        return Math.random() < FIRE_RATE;
    }

    /**
     * Returns the hitbox of the enemy plane.
     *
     * @return a Rectangle object representing the enemy plane's hitbox.
     */
    @Override
    public Rectangle getHitBox()
    {
        return new Rectangle(
                getLayoutX() + getTranslateX() + HITBOX_X_OFFSET,
                getLayoutY() + getTranslateY() + HITBOX_Y_OFFSET,
                HITBOX_WIDTH,
                HITBOX_HEIGHT
        );
    }

    /**
     * Checks if the enemy plane is off-screen.
     *
     * @return true if the plane is beyond the left bound, false otherwise.
     */
    private boolean isOffScreen()
    {
        double currentPosition = getLayoutX() + getTranslateX();
        return currentPosition >= X_POSITION_LEFT_BOUND;
    }
}
