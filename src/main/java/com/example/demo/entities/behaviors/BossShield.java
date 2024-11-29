package com.example.demo.entities.behaviors;

/**
 * The BossShield class represents the shield behavior for the boss.
 */
public class BossShield
{
    private boolean isShielded;
    private int shieldActiveTime;
    private static final double BOSS_SHIELD_PROBABILITY = 0.005;
    private static final int SHIELD_DURATION = 500;

    /**
     * Constructs a BossShield instance with an inactive shield.
     */
    public BossShield()
    {
        this.isShielded = false;
    }

    /**
     * Updates the shield's state based on its current status,
     * if the shield is active shield activation time will increment,
     * otherwise if shield should be active base on random probability, shield will be activated,
     * if shield is exhausted meaning shield activation time reached shield duration, shield will deactivate.
     */
    private void updateShield()
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

    /**
     * Determines whether the shield should be activated based on a random probability.
     *
     * @return true if the shield should be activated, false otherwise.
     */
    private boolean shieldShouldBeActivated()
    {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    /**
     * Checks whether the shield's active duration has been reached or exceeded.
     *
     * @return true if the shield has been active for its maximum duration, false otherwise.
     */
    private boolean shieldExhausted()
    {
        return shieldActiveTime >= SHIELD_DURATION;
    }

    /**
     * Activates the shield, setting its state to active.
     */
    private void activateShield()
    {
        isShielded = true;
    }

    /**
     * Deactivates the shield, resetting its active time and setting its state to inactive.
     */
    private void deactivateShield()
    {
        isShielded = false;
        shieldActiveTime = 0;

    }

    /**
     * Returns whether the shield is currently active.
     *
     * @return true if the shield is active, false otherwise.
     */
    public boolean shielded()
    {
        return isShielded;
    }

    /**
     * Public accessor method getting update shield
     */
    public void getUpdateShield()
    {
        updateShield();
    }

}
