package com.example.demo.displays;

import javafx.scene.Group;

/**
 * The LevelView class manages and displays the UI elements for a game level.
 */
public class LevelView
{
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 10;
	private static final double AMMO_DISPLAY_X_POSITION = 15;
	private static final double AMMO_DISPLAY_Y_POSITION = 70;
	private static final double KILL_TARGET_X_POSITION = 530;
	private static final double KILL_TARGET_Y_POSITION = 10;
	private static final double PAUSE_IMAGE_X_POSITION = 590;
	private static final double PAUSE_IMAGE_Y_POSITION = 300;
	private final Group root;
	private final HeartDisplay heartDisplay;
	private final AmmunitionDisplay ammunitionDisplay;
	private final KillTargetDisplay killTargetDisplay;
	private final PauseImage pauseImage;
	private final int killsToAdvance;

	/**
	 * Constructs a LevelView with the specified root, number of hearts, and kill target.
	 *
	 * @param root            the root group to which the level's UI elements will be added.
	 * @param heartsToDisplay the initial number of hearts to display.
	 * @param kills           the number of kills required to advance to the next level.
	 */
	public LevelView(Group root, int heartsToDisplay, int kills)
	{
		this.root = root;
		this.killsToAdvance = kills;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.ammunitionDisplay = new AmmunitionDisplay(AMMO_DISPLAY_X_POSITION, AMMO_DISPLAY_Y_POSITION);
		this.killTargetDisplay = new KillTargetDisplay(KILL_TARGET_X_POSITION, KILL_TARGET_Y_POSITION, killsToAdvance);
		this.pauseImage = new PauseImage(PAUSE_IMAGE_X_POSITION, PAUSE_IMAGE_Y_POSITION);
	}

	/**
	 * Adds all game UI elements (hearts, ammunition, kill target, and pause image) to the root group for display.
	 */
	public void showGameDisplays()
	{
		root.getChildren().add(heartDisplay.getContainer());
		root.getChildren().add(ammunitionDisplay.getAmmunitionDisplay());
		root.getChildren().add(killTargetDisplay.getKillTargetDisplay());
		root.getChildren().add(pauseImage);
	}

	/**
	 * Updates the heart display to match the specified number of remaining hearts.
	 *
	 * @param heartsRemaining the number of hearts remaining to display.
	 */
	public void removeHearts(int heartsRemaining)
	{
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++)
		{
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Updates the ammunition display with the current number of ammo count.
	 *
	 * @param ammo the current ammunition count.
	 */
	public void updateAmmunitionDisplay(int ammo) {
		ammunitionDisplay.updateAmmunition(ammo);
	}

	/**
	 * Updates the kill target display with the current number of kills.
	 *
	 * @param kills the current number of kills achieved.
	 */
	public void updateKillTargetDisplay(int kills)
	{
		killTargetDisplay.updateKillTarget(kills);
	}

	/**
	 * Displays the pause image on the screen.
	 */
	public void showPauseImage()
	{
		pauseImage.showPauseImage();
	}

	/**
	 * Hides the pause image from the screen.
	 */
	public void hidePauseImage()
	{
		pauseImage.hidePauseImage();
	}

}
