package com.example.demo.levels;

import java.util.*;

import com.example.demo.displays.LevelTwoView;
import com.example.demo.displays.LevelView;
import com.example.demo.entities.ActiveActorDestructible;
import com.example.demo.entities.FighterPlane;
import com.example.demo.entities.UserPlane;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;

public abstract class LevelParent extends Observable
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

	private int currentNumberOfEnemies;
	private final LevelView levelView;
	private final CollisionManager collisionManager;
	private final UserInputHandler userInputHandler;
	private final GameActorManager gameActorManager;
	private final LevelStateManager levelStateManager;


	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth)
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
		this.currentNumberOfEnemies = 0;
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
	//new method for encapsulation
	public void stopGame()
	{
		timeline.stop();
	}

	public void goToNextLevel(String levelName)
	{
		setChanged();
		notifyObservers(levelName);
	}

	private void updateScene()
	{
		spawnEnemyUnits();
		gameActorManager.updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		collisionManager.handleEnemyPenetration(user, gameActorManager.getEnemyUnits(), screenWidth);
		collisionManager.handleUserProjectileCollisions(gameActorManager.getUserProjectiles(), gameActorManager.getEnemyUnits());
		collisionManager.handleEnemyProjectileCollisions(gameActorManager.getEnemyProjectiles(), gameActorManager.getFriendlyUnits());
		collisionManager.handlePlaneCollisions(gameActorManager.getFriendlyUnits(), gameActorManager.getEnemyUnits());

		gameActorManager.removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		if (levelView instanceof LevelTwoView) {
			((LevelTwoView) levelView).updateLevelTwoView();
		}
		levelStateManager.checkIfGameOver();
		levelStateManager.checkIfLevelCompleted();
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

	private void updateKillCount()
	{
		for (int i = 0; i < currentNumberOfEnemies - gameActorManager.getEnemyUnits().size(); i++) {
			user.incrementKillCount();
		}
	}

	protected void winGame()
	{
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame()
	{
		timeline.stop();
		levelView.showGameOverImage();
	}

	protected UserPlane getUser()
	{
		return user;
	}

	protected Group getRoot()
	{
		return root;
	}

	protected int getCurrentNumberOfEnemies()
	{
		return gameActorManager.getEnemyUnits().size();
	}

	protected void generateEnemyFire()
	{
		gameActorManager.getEnemyUnits().forEach(enemy -> gameActorManager.spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy)
	{
		gameActorManager.getEnemyUnits().add(enemy);
		root.getChildren().add(enemy);
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

	private void updateNumberOfEnemies()
	{
		currentNumberOfEnemies = gameActorManager.getEnemyUnits().size();
	}

}
