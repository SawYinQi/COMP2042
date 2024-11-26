package com.example.demo.levels;

import java.util.*;
import com.example.demo.Managers.CollisionManager;
import com.example.demo.Managers.GameActorManager;
import com.example.demo.Managers.LevelStateManager;
import com.example.demo.Managers.UserInputHandler;
import com.example.demo.controller.MainController;
import com.example.demo.displays.LevelTutorialView;
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
	private final CollisionManager collisionManager;
	private final UserInputHandler userInputHandler;
	private final GameActorManager gameActorManager;
	private final LevelStateManager levelStateManager;
	private final MainController mainController;
	private LevelView levelView;

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

	protected void initializeLevelView()
	{
		this.levelView = instantiateLevelView();
	}

	public Scene initializeScene()
	{
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showGameDisplays();
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
		handleAllCollisions();
		updateAllActors();
		updateLevelView();
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
		background.setOnKeyPressed(e -> userInputHandler.onKeyPressed(e, user, root, gameActorManager.getUserProjectiles(), timeline, levelView));
		background.setOnKeyReleased(e -> userInputHandler.onKeyReleased(e, user));
		root.getChildren().add(background);
	}

	private void updateLevelView()
	{
		levelView.removeHearts(user.getHealth());
		levelView.updateAmmunitionDisplay(user.getAmmunition());
		levelView.updateKillTargetDisplay(user.getNumberOfKills());
		if (levelView instanceof LevelTwoView) {
			((LevelTwoView) levelView).updateLevelTwoView();
		}
		else if(levelView instanceof LevelTutorialView)
		{
			((LevelTutorialView) levelView).showInstructions();
		}
	}

	private void updateAllActors()
	{
		gameActorManager.updateAllActors(user, screenWidth);
	}

	private void handleAllCollisions()
	{
		collisionManager.handleAllCollisions(gameActorManager, user, screenWidth);
	}

	private void checkLevelState()
	{
		levelStateManager.checkLevelState();
	}

	public void winGame()
	{
		timeline.stop();
		user.getTimeline().stop();
		mainController.showWinScreen();
	}

	public void loseGame()
	{
		timeline.stop();
		user.getTimeline().stop();
		mainController.showLoseScreen();
	}

	public void backToMenu()
	{
		timeline.stop();
		user.getTimeline().stop();
		mainController.showMainMenu();
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

	public boolean userIsDestroyed()
	{
		return user.isDestroyed();
	}

	protected GameActorManager getGameActorManager()
	{
		return gameActorManager;
	}
}
