package com.example.demo.displays;

import com.example.demo.entities.Boss;
import javafx.scene.Group;

public class LevelBossView extends LevelView
{
	private static final double HP_BAR_X_POSITION = 795;
	private static final double HP_BAR_Y_POSITION = 10;
	private static final double SHIELD_X_OFFSET = 50;
	private static final double SHIELD_Y_OFFSET = 15;
	private final Group root;
	private final ShieldImage shieldImage;
	private final BossHealthBarDisplay bossHealthBarDisplay;
	private final Boss boss;
	
	public LevelBossView(Group root, int heartsToDisplay, int kills, Boss boss)
	{
		super(root, heartsToDisplay, kills);
		this.root = root;
		this.shieldImage = new ShieldImage();
		this.bossHealthBarDisplay = new BossHealthBarDisplay(HP_BAR_X_POSITION, HP_BAR_Y_POSITION, boss.getHealth());
		this.boss = boss;
	}

	@Override
	public void showGameDisplays()
	{
		super.showGameDisplays();
		root.getChildren().add(shieldImage);
		root.getChildren().add(bossHealthBarDisplay.getHealthBar());
		root.getChildren().add(bossHealthBarDisplay.getLabel());
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
		updateBossHealth();
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
		shieldImage.setLayoutX(boss.getCurrentXPosition() + SHIELD_X_OFFSET);
		shieldImage.setLayoutY(boss.getCurrentYPosition() + SHIELD_Y_OFFSET);
	}

	private void updateBossHealth()
	{
		bossHealthBarDisplay.updateBossHealth(boss.getHealth());
	}

}
