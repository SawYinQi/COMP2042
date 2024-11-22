package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BossMovementPattern
{
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HORIZONTAL_VELOCITY = 10;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private final List<Integer> movePattern;
    private int framesWithSameMove;
    private int indexOfCurrentMove;


    public BossMovementPattern()
    {
        this.framesWithSameMove = 0;
        this.indexOfCurrentMove = 0;
        this.movePattern = new ArrayList<>();
        initializeMovePattern();
    }

    private void initializeMovePattern()
    {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++)
        {
            movePattern.add(1);
            movePattern.add(2);
            movePattern.add(3);
            movePattern.add(4);
            movePattern.add(5);
        }
        Collections.shuffle(movePattern);
    }

    protected int[] getNextMove()
    {
        int currentMove = movePattern.get(indexOfCurrentMove);
        int x = ZERO;
        int y = ZERO;
        switch (currentMove)
        {
            case 1:
                y = VERTICAL_VELOCITY;
                break;
            case 2:
                y = -VERTICAL_VELOCITY;
                break;
            case 3:
                x = HORIZONTAL_VELOCITY;
                break;
            case 4:
                x = -HORIZONTAL_VELOCITY;
                break;
            default:
                break;
        }
        int[] nextMove = {x,y};
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
        return nextMove;

    }

}
