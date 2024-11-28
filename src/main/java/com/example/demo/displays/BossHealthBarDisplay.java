package com.example.demo.displays;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BossHealthBarDisplay
{
    private static final double HP_BAR_WIDTH = 500;
    private static final double HP_BAR_HEIGHT = 50;
    private static final double BOSS_LABEL_X_POSITION = 805;
    private static final double BOSS_LABEL_Y_POSITION = 20;
    private static final double BOSS_LABEL_OPACITY = .7;
    private final Label label;
    private final ProgressBar healthBar;
    private final double bossInitialHealth;

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

        label = new Label("Boss");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        label.setLayoutX(BOSS_LABEL_X_POSITION);
        label.setLayoutY(BOSS_LABEL_Y_POSITION);
        label.setOpacity(BOSS_LABEL_OPACITY);
    }

    public void updateBossHealth(double currentBossHP)
    {
        double currentHealthPercentage = currentBossHP / bossInitialHealth;
        healthBar.setProgress(currentHealthPercentage);
    }

    public ProgressBar getHealthBar()
    {
        return healthBar;
    }

    public Label getLabel()
    {
        return label;
    }
}
