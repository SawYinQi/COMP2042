package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The entry point of the JavaFX game application.
 */
public class Main extends Application
{
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private MainController mainController;

	/**
	 * Starts the JavaFX application by setting up the main stage and displaying the main menu.
	 *
	 * @param stage 						the primary stage for the JavaFX application.
	 * @throws ClassNotFoundException    	if a required class is not found.
	 * @throws NoSuchMethodException     	if a required method is not found.
	 * @throws SecurityException         	if access to a required method is denied.
	 * @throws InstantiationException    	if an object cannot be instantiated.
	 * @throws IllegalAccessException    	if access to a required constructor or method is denied.
	 * @throws IllegalArgumentException  	if invalid arguments are passed during instantiation.
	 * @throws InvocationTargetException 	if an exception occurs during reflective method invocation.
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		try
		{
			stage.setTitle(TITLE);
			stage.setResizable(false);
			stage.setHeight(SCREEN_HEIGHT);
			stage.setWidth(SCREEN_WIDTH);
			mainController = new MainController(stage);
			mainController.showMainMenu();
		}
		catch (Exception e)
		{
			mainController.showError(e);
		}
	}

	public static void main(String[] args)
	{
		launch();
	}
}