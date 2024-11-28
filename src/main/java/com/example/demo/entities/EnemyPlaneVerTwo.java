package com.example.demo.entities;

import javafx.scene.shape.Rectangle;

public class EnemyPlaneVerTwo extends FighterPlane
{
    private static final String IMAGE_NAME = "enemyplane2.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 10.0;
    private static final int Y_POSITION_UPPER_BOUND = 0;
    private static final int Y_POSITION_LOWER_BOUND = 650;
    private static final int X_POSITION_LEFT_BOUND = 1000;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .02;
    private static final int HITBOX_X_OFFSET = 20;
    private static final int HITBOX_Y_OFFSET = 20;
    private static final int HITBOX_WIDTH = 210;
    private static final int HITBOX_HEIGHT = 30;
    private final EnemyPlaneVerTwoMovement enemyPlaneVerTwoMovement;

    public EnemyPlaneVerTwo(double initialXPos, double initialYPos)
    {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        this.enemyPlaneVerTwoMovement = new EnemyPlaneVerTwoMovement();
    }

    @Override
    public void updatePosition()
    {
        double initialTranslateY = getTranslateY();
        double initialTranslateX = getTranslateX();
        if (isOffScreen())
        {
            move(HORIZONTAL_VELOCITY, 0);
        }
        else
        {
            move(0,enemyPlaneVerTwoMovement.getNextMove());
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

    private boolean projectileShouldFire()
    {
        return Math.random() < FIRE_RATE;
    }

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

    private boolean isOffScreen()
    {
        double currentPosition = getLayoutX() + getTranslateX();
        return currentPosition >= X_POSITION_LEFT_BOUND;
    }
}
