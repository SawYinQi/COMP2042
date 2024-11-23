package com.example.demo.levels;

import java.util.*;

import com.example.demo.controller.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import com.example.demo.displays.LevelTwoView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.UserPlane;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;

public abstract class LevelParent
{
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final StringProperty nextLevel;
	private final LevelView levelView;
	private final CollisionManager collisionManager;
	private final UserInputHandler userInputHandler;
	private final GameActorManager gameActorManager;
	private final LevelStateManager levelStateManager;
	private final MainController mainController;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, MainController mainController)
	{
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.collisionManager = new CollisionManager();
		this.userInputHandler = new UserInputHandler();
		this.gameActorManager = new GameActorManager(root);
		this.levelStateManager = new LevelStateManager(this);
		this.mainController = mainController;
		this.nextLevel = new SimpleStringProperty();
		initializeTimeline();
		gameActorManager.getFriendlyUnits().add(user);
	}

	protected void initializeFriendlyUnits()
	{
		getRoot().getChildren().add(getUser());
	}

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene()
	{
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	public void startGame()
	{
		background.requestFocus();
		timeline.play();
	}

	public void stopGame()
	{
		timeline.stop();
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
		handleCollisions();
		updateAllActors();
		updateLevelView();
		if (levelView instanceof LevelTwoView) {
			((LevelTwoView) levelView).updateLevelTwoView();
		}
		checkLevelState();
	}

	private void initializeTimeline()
	{
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground()
	{
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(e -> userInputHandler.onKeyPressed(e, user, root, gameActorManager.getUserProjectiles()));
		background.setOnKeyReleased(e -> userInputHandler.onKeyReleased(e, user));
		root.getChildren().add(background);
	}

	private void updateLevelView()
	{
		levelView.removeHearts(user.getHealth());
	}

	private void updateAllActors()
	{
		gameActorManager.updateKillCount(user);
		gameActorManager.removeAllDestroyedActors(screenWidth);
		gameActorManager.updateActors();
		gameActorManager.generateEnemyFire();
	}

	private void handleCollisions()
	{
		collisionManager.handleUserProjectileCollisions(gameActorManager.getUserProjectiles(), gameActorManager.getEnemyUnits());
		collisionManager.handleEnemyProjectileCollisions(gameActorManager.getEnemyProjectiles(), gameActorManager.getFriendlyUnits());
		collisionManager.handlePlaneCollisions(gameActorManager.getFriendlyUnits(), gameActorManager.getEnemyUnits());
		collisionManager.handleEnemyPenetration(user, gameActorManager.getEnemyUnits(), screenWidth);
	}

	private void checkLevelState()
	{
		levelStateManager.checkIfGameOver();
		levelStateManager.checkIfLevelCompleted();
	}

	protected void winGame()
	{
		timeline.stop();
		mainController.showWinScreen();
	}

	protected void loseGame()
	{
		timeline.stop();
		mainController.showLoseScreen();
	}

	protected UserPlane getUser()
	{
		return user;
	}

	public Group getRoot()
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

	protected boolean userIsDestroyed()
	{
		return user.isDestroyed();
	}

	protected GameActorManager getGameActorManager()
	{
		return gameActorManager;
	}

}
