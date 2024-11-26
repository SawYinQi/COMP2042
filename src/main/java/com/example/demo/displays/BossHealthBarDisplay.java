package com.example.demo.displays;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BossHealthBarDisplay
{
    private final Label label;
    private final ProgressBar healthBar;
    private double bossInitialHealth;

    public BossHealthBarDisplay(double bossInitialHealth)
    {
        this.bossInitialHealth = bossInitialHealth;
        healthBar = new ProgressBar(1.0);
        healthBar.setVisible(true);
        healthBar.setStyle("-fx-accent: red;");
        healthBar.setLayoutX(795);
        healthBar.setLayoutY(10);
        healthBar.setPrefWidth(500);
        healthBar.setPrefHeight(50);

        label = new Label("Boss");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        label.setLayoutX(805);
        label.setLayoutY(20);
        label.setOpacity(0.8);
    }

    public void updateBossHealth(double currentBossHP)
    {
        double currentHealthPercentage = currentBossHP/ bossInitialHealth;
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
