package com.example.demo.entities;

public class BossShield
{
    private boolean isShielded;
    private int shieldActiveTime;
    private static final double BOSS_SHIELD_PROBABILITY = 1;
    private static final int SHIELD_DURATION = 500;

    public BossShield()
    {
        this.isShielded = false;
    }

    protected void updateShield()
    {
        if (isShielded)
        {
            shieldActiveTime++;
        }
        else if (shieldShouldBeActivated())
        {
            activateShield();

        }
        if (shieldExhausted())
        {
            deactivateShield();
        }
    }

    private boolean shieldShouldBeActivated()
    {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    private boolean shieldExhausted()
    {
        return shieldActiveTime == SHIELD_DURATION;
    }

    private void activateShield()
    {
        isShielded = true;
    }

    private void deactivateShield()
    {
        isShielded = false;
        shieldActiveTime = 0;

    }

    public boolean shielded()
    {
        return isShielded;
    }

}
