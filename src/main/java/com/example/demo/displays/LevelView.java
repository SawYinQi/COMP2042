package com.example.demo.displays;

import javafx.scene.Group;

public class LevelView
{

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 10;
	private static final double AMMO_DISPLAY_X_POSITION = 15;
	private static final double AMMO_DISPLAY_Y_POSITION = 70;
	private static final double KILL_TARGET_X_POSITION = 530;
	private static final double KILL_TARGET_Y_POSITION = 10;
	private final Group root;
	private final HeartDisplay heartDisplay;
	private final AmmunitionDisplay ammunitionDisplay;
	private final KillTargetDisplay killTargetDisplay;
	private final int killsToAdvance;

	public LevelView(Group root, int heartsToDisplay, int kills)
	{
		this.root = root;
		this.killsToAdvance = kills;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.ammunitionDisplay = new AmmunitionDisplay(AMMO_DISPLAY_X_POSITION, AMMO_DISPLAY_Y_POSITION);
		this.killTargetDisplay = new KillTargetDisplay(KILL_TARGET_X_POSITION, KILL_TARGET_Y_POSITION, killsToAdvance);

	}
	
	public void showGameDisplays()
	{
		root.getChildren().add(heartDisplay.getContainer());
		root.getChildren().add(ammunitionDisplay.getAmmunitionDisplay());
		root.getChildren().add(killTargetDisplay.getKillTargetDisplay());
	}
	
	public void removeHearts(int heartsRemaining)
	{
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++)
		{
			heartDisplay.removeHeart();
		}
	}

	public void updateAmmunitionDisplay(int ammo) {
		ammunitionDisplay.updateAmmunition(ammo);
	}

	public void updateKillTargetDisplay(int kills)
	{
		killTargetDisplay.updateKillTarget(kills);
	}

}
