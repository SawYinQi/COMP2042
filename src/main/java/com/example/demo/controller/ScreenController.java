package com.example.demo.controller;

import javafx.fxml.FXML;

/**
 * ScreenController class handles user interaction from FXML components and delegate the task to MainController class.
 */
public class ScreenController
{
    private MainController mainController;

    /**
     * Sets the main controller for delegation of game logic and stage management.
     *
     * @param mainController the main controller instance.
     */
    protected void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    /**
     * Starts the game by invoking the main controller's launchGame method.
     */
    @FXML
    private void startGame()
    {
        try
        {
            mainController.launchGame();
        }
        catch (Exception e)
        {
            mainController.showError(e);
        }
    }

    /**
     * Exits the application by closing the main stage.
     */
    @FXML
    private void exitGame()
    {
        mainController.getStage().close();
    }
}

