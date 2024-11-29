package com.example.demo.displays;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The LevelTutorialView class extends LevelView to include managing UI elements for LevelTutorial.
 */
public class LevelTutorialView extends LevelView
{
    private static final double INSTRUCTION_LABEL_X_POSITION = 20;
    private static final double INSTRUCTION_LABEL_Y_POSITION = 550;
    private final Group root;

    /**
     * Constructs a LevelTutorialView with the specified root, heart, and kills.
     *
     * @param root            the root group to which UI elements will be added.
     * @param heartsToDisplay the number of hearts to display for the player.
     * @param kills           the number of kills required to advance the level.
     */
    public LevelTutorialView(Group root, int heartsToDisplay, int kills)
    {
        super(root, heartsToDisplay, kills);
        this.root = root;
    }

    /**
     * Displays a set of gameplay instructions on the screen.
     */
    public void showInstructions()
    {
        Label instructions = new Label("""
                Instructions:
                Press up key to move up.
                Press down key to mover down.
                Press space key to fire projectiles.
                Press p key to pause game, press again to resume.
                Objective: Destroy EnemyPlane to go back to main menu.""");
        instructions.setFont(new Font("Arial", 24));
        instructions.setTextFill(Color.BLACK);
        instructions.setLayoutX(INSTRUCTION_LABEL_X_POSITION);
        instructions.setLayoutY(INSTRUCTION_LABEL_Y_POSITION);
        root.getChildren().add(instructions);
    }

}
