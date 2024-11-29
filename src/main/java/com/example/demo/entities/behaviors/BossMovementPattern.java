package com.example.demo.entities.behaviors;

/**
 * The BossMovementPattern class extends MovementPattern
 * represents the movement pattern the boss entity in the game.
 */
public class BossMovementPattern extends MovementPattern
{
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HORIZONTAL_VELOCITY = 10;
    private static final int MAX_MOVES_SINCE_LAST_SHUFFLE = 10;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

    /**
     * Constructs a BossMovementPattern object and initializes the superclass.
     */
    public BossMovementPattern()
    {
        super(MAX_MOVES_SINCE_LAST_SHUFFLE);
    }

    /**
     * Populates the move pattern with specific directions in numbers
     * which will be handled by switch cass later.
     */
    @Override
    protected void populateMovePattern()
    {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++)
        {
            movePattern.add(1);
            movePattern.add(2);
            movePattern.add(3);
            movePattern.add(4);
            movePattern.add(5);
            movePattern.add(6);
        }
    }

    /**
     * Returns the next move from the movement pattern list and determines the corresponding
     * velocity for horizontal or vertical direction,  reshuffles the pattern if conditions are met,
     * and resets the index when reaching the end of the list.
     *
     * @return an array of two integers representing the horizontal and vertical velocities.
     */
    @Override
    protected int[] getNextMove()
    {
        int currentMove = movePattern.get(indexOfCurrentMove);
        // Set x and y velocity to zero.
        int x = getZero();
        int y = getZero();
        switch (currentMove)
        {
            case 1:
                y = VERTICAL_VELOCITY;
                break;
            case 2:
                y = -VERTICAL_VELOCITY;
                break;
            case 3:
                y = getZero();
                break;
            case 4:
                x = HORIZONTAL_VELOCITY;
                break;
            case 5:
                x = -HORIZONTAL_VELOCITY;
                break;
            case 6:
                x = getZero();
                break;
        }
        // After determining the move it will only change x or y velocity, this ensures no diagonal movement
        int[] nextMove = {x,y};
        shuffleMovePattern();
        resetCurrentIndex();
        return nextMove;
    }

    /**
     * Public accessor method for getting next move for boss.
     *
     * @return the method getNextMove().
     */
    public int[] getBossNextMove()
    {
        return getNextMove();
    }
}
