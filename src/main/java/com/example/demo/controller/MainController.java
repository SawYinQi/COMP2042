package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.demo.levels.LevelParent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * MainController Class is responsible for managing the game flow and scene transitions between levels and screen transitions.
 */
public class MainController
{
	private static final String MENU_PATH = "/menu.fxml";
	private static final String LOSE_PATH = "/lose.fxml";
	private static final String WIN_PATH = "/win.fxml";
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private static final String LEVEL_Tutorial_CLASS_NAME = "com.example.demo.levels.LevelTutorial";
	private final Stage stage;


	/**
	 * Constructs a Controller with the specified stage.
	 *
	 * @param stage the primary stage for displaying scenes in this application.
	 */
	public MainController(Stage stage)
	{
		this.stage = stage;
	}

	/**
	 * Launches the game, transitioning to the LevelOne.
	 *
	 * @throws ClassNotFoundException		if the class cannot be found.
	 * @throws NoSuchMethodException		if a method cannot be found.
	 * @throws SecurityException			if access to the constructor is denied.
	 * @throws InstantiationException		if level class cannot be instantiated
	 * @throws IllegalAccessException		if the constructor for the class cannot be access
	 * @throws IllegalArgumentException		if the invoked method's argument is invalid.
	 * @throws InvocationTargetException	if the constructor invocation throws an exception
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Launches the tutorial, transitioning to the LevelTutorial.
	 *
	 * @throws ClassNotFoundException      if the class cannot be found.
	 * @throws NoSuchMethodException       if a method cannot be found.
	 * @throws SecurityException           if access to the constructor is denied.
	 * @throws InstantiationException      if the level class cannot be instantiated.
	 * @throws IllegalAccessException      if the constructor for the class cannot be accessed.
	 * @throws IllegalArgumentException    if the invoked method's argument is invalid.
	 * @throws InvocationTargetException   if the constructor invocation throws an exception.
	 */
	public void launchTutorial() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		stage.show();
		goToLevel(LEVEL_Tutorial_CLASS_NAME);
	}

	/**
	 * Transition to the specified level.
	 *
	 * @param className 					refers to class and package name of the level to transition to.
	 * @throws ClassNotFoundException		if the specified level class cannot be found.
	 * @throws NoSuchMethodException		if the required method for the level is not found.
	 * @throws SecurityException			if access to the constructor is denied.
	 * @throws InstantiationException		if the level class cannot be instantiated.
	 * @throws IllegalAccessException		if the constructor for the level is not accessible.
	 * @throws IllegalArgumentException		if the invoked method's argument is invalid.
	 * @throws InvocationTargetException	if the constructor invocation throws an exception.
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class, MainController.class);
			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), this);
			myLevel.nextLevelProperty().addListener((observable, oldValue, newValue) ->
			{
			try {
					goToLevel(newValue);
				}
			catch (Exception e)
			{
					e.printStackTrace();
			}
			});
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();
	}

	/**
	 * Loads an FXML file and switches the current screen to the specified screen base on given FXML path.
	 * It also Initialized the ScreenController by setting current MainController instance.
	 *
	 * @param fxmlPath the path to the FXML file to be loaded.
	 */
	private void switchScreen(String fxmlPath)
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();

			Object controller = loader.getController();
			((ScreenController) controller).setMainController(this);

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Displays the main menu screen by switching to the menu FXML.
	 */
	public void showMainMenu()
	{
		switchScreen(MENU_PATH);
	}

	/**
	 * Displays the win screen by switching to the win FXML.
	 */
	public void showWinScreen()
	{
		switchScreen(WIN_PATH);
	}

	/**
	 * Displays the lose screen by switching to the lose FXML
	 */
	public void showLoseScreen()
	{
		switchScreen(LOSE_PATH);
	}

}
