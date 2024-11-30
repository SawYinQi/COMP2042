package com.example.demo.levels;

import java.util.*;
import com.example.demo.managers.*;
import com.example.demo.controller.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.UserPlane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;

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

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	protected void initializeLevelView()
	{
		this.levelView = instantiateLevelView();
		initializeUserInputHandler();
		initializeLevelViewHandler();
	}

	private void initializeUserInputHandler()
	{
		this.userInputHandler = new UserInputHandler(user, root, gameActorManager.getUserProjectiles(), timelineManager.getTimeline(), levelView);
	}

	private void initializeLevelViewHandler()
	{
		this.levelViewHandler = new LevelViewHandler(user, levelView);
	}

	public Scene initializeScene()
	{
		initializeBackground();
		gameActorManager.getInitializeFriendlyUnit();
		levelView.showGameDisplays();
		return scene;
	}

	public void startGame()
	{
		background.requestFocus();
		timelineManager.play();
	}

	public void stopGame()
	{
		timelineManager.stop();
	}

	public StringProperty nextLevelProperty()
	{
		return nextLevel;
	}

	public void goToNextLevel(String levelName)
	{
		nextLevel.set(levelName);
	}

	private void updateScene()
	{
		spawnEnemyUnits();
		collisionManager.handleAllCollisions();
		gameActorManager.updateAllActors();
		levelViewHandler.getUpdateLevelView();
		levelStateManager.checkLevelState();
	}

	private void initializeBackground()
	{
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(userInputHandler::handleKeyPressed);
		background.setOnKeyReleased(userInputHandler::handleKeyReleased);
		root.getChildren().add(background);
	}

	public void winGame()
	{
		timelineManager.stop();
		user.getTimeline().stop();
		mainController.showWinScreen();
	}

	public void loseGame()
	{
		timelineManager.stop();
		user.getTimeline().stop();
		mainController.showLoseScreen();
	}

	public void backToMenu()
	{
		timelineManager.getTimeline().stop();
		user.getTimeline().stop();
		mainController.showMainMenu();
	}

	public UserPlane getUser()
	{
		return user;
	}

	protected Group getRoot()
	{
		return root;
	}

	protected double getEnemyMaximumYPosition()
	{
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth()
	{
		return screenWidth;
	}

	public boolean userIsDestroyed()
	{
		return user.getIsDestroyed();
	}

	protected GameActorManager getGameActorManager()
	{
		return gameActorManager;
	}
}
