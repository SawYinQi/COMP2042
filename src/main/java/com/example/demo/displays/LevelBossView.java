package com.example.demo.displays;

import com.example.demo.entities.planes.Boss;
import javafx.scene.Group;

/**
 * The LevelBossView class extends LevelView to include managing UI elements for LevelBoss.
 */
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

	/**
	 * Constructs a LevelBossView with the specified root, hearts, kills and boss.
	 *
	 * @param root            the root group to which UI elements will be added.
	 * @param heartsToDisplay the number of hearts to display for the player.
	 * @param kills           the number of kills required to advance the level.
	 * @param boss            the boss entity for the level.
	 */
	public LevelBossView(Group root, int heartsToDisplay, int kills, Boss boss)
	{
		super(root, heartsToDisplay, kills);
		this.root = root;
		this.shieldImage = new ShieldImage();
		this.bossHealthBarDisplay = new BossHealthBarDisplay(HP_BAR_X_POSITION, HP_BAR_Y_POSITION, boss.getHealth());
		this.boss = boss;
	}

	/**
	 * Displays all game UI elements, including the shield image, boss health bar, and boss label.
	 */
	@Override
	public void showGameDisplays()
	{
		super.showGameDisplays();
		root.getChildren().add(shieldImage);
		root.getChildren().add(bossHealthBarDisplay.getHealthBar());
		root.getChildren().add(bossHealthBarDisplay.getBossLabel());
	}

	/**
	 * Display the shield image on the screen.
	 */
	private void showShield()
	{
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image on the screen.
	 */
	private void hideShield()
	{
		shieldImage.hideShield();
	}

	/**
	 * Updates the level view to reflect changes in the boss's state,
	 * such as shield visibility, shield position, and boss health.
	 */
	public void updateLevelBossView()
	{
		updateShieldVisibility();
		updateShieldPosition();
		updateBossHealth();
	}

	/**
	 * Updates the visibility of shield image based on boss shield's current state.
	 */
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

	/**
	 * Updates the position of the shield image to match the boss's current position.
	 */
	private void updateShieldPosition()
	{
		shieldImage.setLayoutX(boss.getCurrentXPosition() + SHIELD_X_OFFSET);
		shieldImage.setLayoutY(boss.getCurrentYPosition() + SHIELD_Y_OFFSET);
	}

	/**
	 * Updates the boss's health bar to base on its current health.
	 */
	private void updateBossHealth()
	{
		bossHealthBarDisplay.updateBossHealth(boss.getHealth());
	}

}
