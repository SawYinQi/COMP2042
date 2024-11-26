package com.example.demo.displays;

import com.example.demo.entities.Boss;
import javafx.scene.Group;

public class LevelTwoView extends LevelView
{

	private final Group root;
	private final ShieldImage shieldImage;
	private final Boss boss;
	
	public LevelTwoView(Group root, int heartsToDisplay, int kills, Boss boss)
	{
		super(root, heartsToDisplay, kills);
		this.root = root;
		this.shieldImage = new ShieldImage();
		this.boss = boss;
	}

	@Override
	public void showGameDisplays()
	{
		super.showGameDisplays();
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
		updateShieldVisibility();
		updateShieldPosition();
	}

	private void updateShieldVisibility()
	{
		if (boss.getBossShield().shielded())
		{
			showShield();
		}
		else
		{
			hideShield();
		}
	}

	private void updateShieldPosition()
	{
		shieldImage.setLayoutX(boss.getCurrentXPosition() + 50);
		shieldImage.setLayoutY(boss.getCurrentYPosition() + 15);
	}

}
