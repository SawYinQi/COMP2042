package com.example.demo.levels;

import java.util.*;
import com.example.demo.managers.*;
import com.example.demo.controller.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.planes.UserPlane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;

/**
 * The LevelParent class is an abstract base class for each level in the game,
 * which initialize and manages the game level.
 */
public abstract class LevelParent
{
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final StringProperty nextLevel;
	private final CollisionManager collisionManager;
	private final GameActorManager gameActorManager;
	private final LevelStateManager levelStateManager;
	private final TimelineManager timelineManager;
	private final MainController mainController;
	private UserInputHandler userInputHandler;
	private LevelViewHandler levelViewHandler;
	private LevelView levelView;

	/**
	 * Constructs a LevelParent instance with the specified
	 * background image name, screen height, screen width, initial player health, and main controller.
	 *
	 * @param backgroundImageName the name of the background image for the level.
	 * @param screenHeight        the height of the game screen.
	 * @param screenWidth         the width of the game screen.
	 * @param playerInitialHealth the initial health of the user.
	 * @param mainController      the MainController managing the game flow.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, MainController mainController)
	{
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.user = new UserPlane(playerInitialHealth);
		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.gameActorManager = new GameActorManager(root, screenWidth, user);
		this.collisionManager = new CollisionManager(gameActorManager, user, screenWidth);
		this.levelStateManager = new LevelStateManager(this);
		this.mainController = mainController;
		this.nextLevel = new SimpleStringProperty();
		this.timelineManager = new TimelineManager(MILLISECOND_DELAY, this::updateScene);
	}

	/**
	 * Abstract method to be implemented by its subclass for spawning enemy units.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to be implemented by its subclass for instantiating the level view.
	 *
	 * @return the LevelView for the level.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the level view and handler class associated with it
	 * to ensure level view is initialized before handler classes.
	 */
	protected void initializeLevelView()
	{
		this.levelView = instantiateLevelView();
		initializeUserInputHandler();
		initializeLevelViewHandler();
	}

	/**
	 * Initializes the UserInputHandler for handling user input.
	 */
	private void initializeUserInputHandler()
	{
		this.userInputHandler = new UserInputHandler(user, root, gameActorManager.getUserProjectiles(), timelineManager.getTimeline(), levelView);
	}

	/**
	 * Initializes the LevelViewHandler for managing the level view.
	 */
	private void initializeLevelViewHandler()
	{
		this.levelViewHandler = new LevelViewHandler(user, levelView);
	}

	/**
	 * Initializes the scene, background, and user plane.
	 *
	 * @return the initialized scene.
	 */
	public Scene initializeScene()
	{
		initializeBackground();
		gameActorManager.getInitializeFriendlyUnit();
		levelView.showGameDisplays();
		return scene;
	}

	/**
	 * Starts the game by focusing on the background and starting the level's timeline.
	 */
	public void startGame()
	{
		background.requestFocus();
		timelineManager.play();
	}

	/**
	 * Stops the level's timeline.
	 */
	public void stopGame()
	{
		timelineManager.stop();
	}

	/**
	 * Returns the property for the next level to transition to.
	 *
	 * @return the StringProperty for the next level.
	 */
	public StringProperty nextLevelProperty()
	{
		return nextLevel;
	}

	/**
	 * Sets the name of the next level and triggers the level transition.
	 *
	 * @param levelName the name of the next level.
	 */
	public void goToNextLevel(String levelName)
	{
		nextLevel.set(levelName);
	}

	/**
	 * Updates the enemy spawning, collisions, actor updates,
	 * level view updates, and level state checks in the scene.
	 */
	private void updateScene()
	{
		spawnEnemyUnits();
		collisionManager.handleAllCollisions();
		gameActorManager.updateAllActors();
		levelViewHandler.getUpdateLevelView();
		levelStateManager.checkLevelState();
	}

	/**
	 * Initializes the background with the image and input handling.
	 */
	private void initializeBackground()
	{
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(userInputHandler::handleKeyPressed);
		background.setOnKeyReleased(userInputHandler::handleKeyReleased);
		root.getChildren().add(background);
	}

	/**
	 * Stops all timelines and showing the win screen.
	 */
	public void winGame()
	{
		timelineManager.stop();
		user.getTimeline().stop();
		mainController.showWinScreen();
	}

	/**
	 * Stops all timelines and showing the lose screen.
	 */
	public void loseGame()
	{
		timelineManager.stop();
		user.getTimeline().stop();
		mainController.showLoseScreen();
	}

	/**
	 * Stops all timelines and showing the menu screen.
	 */
	public void backToMenu()
	{
		timelineManager.getTimeline().stop();
		user.getTimeline().stop();
		mainController.showMainMenu();
	}

	/**
	 * Returns the UserPlane Object
	 *
	 * @return the UserPlane which represents the user.
	 */
	public UserPlane getUser()
	{
		return user;
	}

	/**
	 * Returns the Group object.
	 *
	 * @return the root group object of the scene.
	 */
	protected Group getRoot()
	{
		return root;
	}

	/**
	 * Returns the maximum y position for enemy spawning.
	 *
	 * @return the maximum y position.
	 */
	protected double getEnemyMaximumYPosition()
	{
		return enemyMaximumYPosition;
	}


	/**
	 * Returns the screen width.
	 *
	 * @return the screen width.
	 */
	protected double getScreenWidth()
	{
		return screenWidth;
	}

	/**
	 * Gets the status of user's plane.
	 *
	 * @return true if the user's plane is destroyed, false otherwise.
	 */
	public boolean userIsDestroyed()
	{
		return user.getIsDestroyed();
	}

	/**
	 * Protected Accessor for subclass to access GameActorManager.
	 *
	 * @return the GameActorManager instance in the level.
	 */
	protected GameActorManager getGameActorManager()
	{
		return gameActorManager;
	}
}
