package com.example.demo.entities.behaviors;

/**
 * The EnemyPlaneVerTwoMovement class extends MovementPattern
 * representing the movement pattern of EnemyPlaneVerTwo in the game.
 */
public class EnemyPlaneVerTwoMovement extends MovementPattern
{
    private static final int VERTICAL_VELOCITY = 8;
    private static final int MAX_MOVES_SINCE_LAST_SHUFFLE = 10;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

    /**
     * Constructs an EnemyPlaneVerTwoMovement object and initialise superclass.
     */
    public EnemyPlaneVerTwoMovement()
    {
        super(MAX_MOVES_SINCE_LAST_SHUFFLE);
    }

    /**
     * Populates the move pattern with down, up and stay movement.
     */
    @Override
    protected void  populateMovePattern()
    {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++)
        {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(getZero());
        }
    }

    /**
     * Returns the next move in the pattern list, reshuffles the pattern if conditions are met,
     * and resets the index when reaching the end of the list.
     *
     * @return the next move as an integer representing vertical movement direction.
     */
    @Override
    protected Integer getNextMove()
    {
        int currentMove = movePattern.get(indexOfCurrentMove);
        shuffleMovePattern();
        resetCurrentIndex();
        return currentMove;
    }

    /**
     * Public accessor method for getting next move for enemy plane version two.
     *
     * @return the method getNextMove().
     */
    public int getEnemyNextMove()
    {
        return getNextMove();
    }

}
