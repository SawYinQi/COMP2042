package com.example.demo.entities.behaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The MovementPattern class is an abstract base class for setting the random movement of game entities.
 */
public abstract class MovementPattern
{
    private static final int ZERO = 0;
    protected final List<Integer> movePattern;
    protected int movesExecuted;
    protected int indexOfCurrentMove;
    private final int maxMovesSinceLastShuffle;

    /**
     * Constructs a MovementPattern with the specified maximum number of times moves can be executed since last shuffle
     *
     * @param maxMovesSinceLastShuffle the maximum number times the move can be executed before re-shuffling.
     */
    protected MovementPattern(int maxMovesSinceLastShuffle)
    {
        this.movesExecuted = 0;
        this.indexOfCurrentMove = 0;
        this.movePattern = new ArrayList<>();
        this.maxMovesSinceLastShuffle = maxMovesSinceLastShuffle;
        initializeMovePattern();
    }

    /**
     * Abstract method to be implemented by its subclass to populates the move pattern list.
     */
    protected abstract void populateMovePattern();

    /**
     * Initializes the move pattern by populating it with subclass determined moves and shuffling it.
     */
    private void initializeMovePattern()
    {
        populateMovePattern();
        Collections.shuffle(movePattern);
    }

    /**
     * Returns the next move in the move pattern.
     *
     * @return the next move, represented as an object, e.g. an integer or an array.
     */
    protected abstract Object getNextMove();

    /**
     * Increment moves executed , and if moves executed reached the maximum moves since the last shuffle
     * move pattern list will be re-shuffled, index of current move is incremented and moves executed is reset to 0.
     */
    protected void shuffleMovePattern()
    {
        movesExecuted++;
        if (movesExecuted >= maxMovesSinceLastShuffle)
        {
            Collections.shuffle(movePattern);
            movesExecuted = 0;
            indexOfCurrentMove++;
        }
    }

    /**
     * Resets the index of current move to the beginning of the move pattern if it reaches the end.
     */
    protected void resetCurrentIndex()
    {
        if (indexOfCurrentMove >= movePattern.size())
        {
            indexOfCurrentMove = 0;
        }
    }

    /**
     * Returns int zero which is shared among subclasses
     *
     * @return the int value 0.
     */
    protected int getZero()
    {
        return ZERO;
    }
}

