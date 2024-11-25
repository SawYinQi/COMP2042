package com.example.demo.displays;

import com.example.demo.entities.BossShield;
import javafx.scene.Group;

public class LevelTwoView extends LevelView
{

	private static final int SHIELD_X_POSITION = 400;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;
	private final BossShield bossShield;
	
	public LevelTwoView(Group root, int heartsToDisplay, int kills)
	{
		super(root, heartsToDisplay, kills);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		this.bossShield = new BossShield();
		addImagesToRoot();
	}

	private void addImagesToRoot()
	{
		root.getChildren().add(shieldImage);
	}
	
	private void showShield()
	{
		shieldImage.showShield();
	}

	private void hideShield()
	{
		shieldImage.hideShield();
	}

	public void updateLevelTwoView()
	{
		if (bossShield.shielded())
		{
			showShield();
		}
		else
		{
			hideShield();
		}
	}

}
