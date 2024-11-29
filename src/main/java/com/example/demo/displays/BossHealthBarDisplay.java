package com.example.demo.displays;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The BossHealthBarDisplay class is responsible for the display of boss health bar.
 * It consists of progress bar for boss health and label for boss health identification.
 */
public class BossHealthBarDisplay
{
    private static final double HP_BAR_WIDTH = 500;
    private static final double HP_BAR_HEIGHT = 50;
    private static final double BOSS_LABEL_X_POSITION = 805;
    private static final double BOSS_LABEL_Y_POSITION = 20;
    private static final double BOSS_LABEL_OPACITY = .7;
    private final Label bossLabel;
    private final ProgressBar healthBar;
    private final double bossInitialHealth;

    /**
     * Constructs a BossHealthBarDisplay at the specified x and y position with the boss's initial health.
     *
     * @param xPosition        the x-coordinate for the health bar.
     * @param yPosition        the y-coordinate for the health bar.
     * @param bossInitialHealth the initial health of the boss.
     */
    public BossHealthBarDisplay(double xPosition, double yPosition, double bossInitialHealth)
    {
        this.bossInitialHealth = bossInitialHealth;
        healthBar = new ProgressBar(1.0);
        healthBar.setVisible(true);
        healthBar.setStyle("-fx-accent: red;");
        healthBar.setLayoutX(xPosition);
        healthBar.setLayoutY(yPosition);
        healthBar.setPrefWidth(HP_BAR_WIDTH);
        healthBar.setPrefHeight(HP_BAR_HEIGHT);

        bossLabel = new Label("Boss");
        bossLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        bossLabel.setLayoutX(BOSS_LABEL_X_POSITION);
        bossLabel.setLayoutY(BOSS_LABEL_Y_POSITION);
        bossLabel.setOpacity(BOSS_LABEL_OPACITY);
    }

    /**
     * Updates the boss's health bar based on the current health percentage.
     *
     * @param currentBossHP the current health of the boss.
     */
    public void updateBossHealth(double currentBossHP)
    {
        double currentHealthPercentage = currentBossHP / bossInitialHealth;
        healthBar.setProgress(currentHealthPercentage);
    }

    /**
     * Returns the ProgressBar object
     *
     * @return the ProgressBar object representing the boss's health bar.
     */
    public ProgressBar getHealthBar()
    {
        return healthBar;
    }

    /**
     * Returns Label object.
     *
     * @return the Label object associated with boss's health bar.
     */
    public Label getBossLabel()
    {
        return bossLabel;
    }
}
