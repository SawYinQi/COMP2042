package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BossMovement
{
    private static final int VERTICAL_VELOCITY = 8;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private final List<Integer> movePattern;
    private int framesWithSameMove;
    private int indexOfCurrentMove;

    public BossMovement()
    {
        this.indexOfCurrentMove = 0;
        this.framesWithSameMove = 0;
        this.movePattern = new ArrayList<>();
        initializeMovePattern();
    }

    private void initializeMovePattern()
    {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++)
        {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    protected double getNextMove()
    {
        double currentMove = movePattern.get(indexOfCurrentMove);
        framesWithSameMove++;
        if (framesWithSameMove == MAX_FRAMES_WITH_SAME_MOVE)
        {
            Collections.shuffle(movePattern);
            framesWithSameMove = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size())
        {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }
}
