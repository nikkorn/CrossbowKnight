package com.dumbpug.crossbowknight.gamemenu.tabs.character;

import java.text.DecimalFormat;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
import com.dumbpug.crossbowknight.entities.characters.Stats;
import com.dumbpug.crossbowknight.entities.characters.player.Player;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTab;
import com.dumbpug.crossbowknight.gamemenu.tabs.GameMenuTabType;

/**
 * In-Game menu tab for the player stats.
 * @author nikolas.howard
 */
public class CharacterMenuTab implements GameMenuTab {
	/** The background for this tab. */
	private Texture background;
	/** The player. */
	private Player player;
	/** The XP bar. */
	private XpBar xpBar;
	/** The attributes container. */
	private SelectableAttributeBoxContainer selectableAttributesBox;
	/** The non-selectable Stat attributes. */
	private AttributeBox hpStatBox;
	private AttributeBox staminaStatBox;
	private AttributeBox staminaRefillRateStatBox;
	private AttributeBox criticalHitPercentageStatBox;

	/**
	 * Create a new instance of the CharacterMenuTab.
	 * @param player
     */
	public CharacterMenuTab(Player player) {
		this.background              = new Texture("graphics/gamemenu/character/gamemenu_background.png");
		this.player                  = player;
		this.xpBar                   = new XpBar();
		this.selectableAttributesBox = new SelectableAttributeBoxContainer(player.getStats());
		createStatAttributeBoxes();
	}
	
	/**
	 * Create the Stat attribute boxes.
	 */
	private void createStatAttributeBoxes() {
		final Stats stats = player.getStats();
		// Figure out the position on the screen from where to start positioning the attribute boxes.
		float containerPositionX = C.INGAME_MENU_POS_X + (C.INGAME_MENU_WIDTH * 0.525f);
		float containerPositionY = C.INGAME_MENU_POS_Y + (C.INGAME_MENU_HEIGHT * 0.85f);
		// Create the HP stat box.
		hpStatBox = new AttributeBox("HP", containerPositionX, containerPositionY - C.MENU_CHARACTER_ATTRIBUTE_HEIGHT) {
			@Override
			public String getDisplayValue() { return player.getHealthStatus().getHealth() + "/" + stats.getMaxHealth(); }
		};
		// Create the Stamina stat box.
		staminaStatBox = new AttributeBox("STAMINA", containerPositionX, containerPositionY - (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT*2)) {
			@Override
			public String getDisplayValue() { return player.getStaminaStatus().getStamina() + "/" + stats.getMaxStamina(); }
		};
		// Create the StaminaRefill stat box.
		staminaRefillRateStatBox = new AttributeBox("STAMINA REFILL", containerPositionX, containerPositionY - (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT*3)) {
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			@Override
			public String getDisplayValue() { return Float.valueOf(twoDForm.format(1000 / stats.getStaminaRefillRate())) + "pps"; }
		};
		// Create the Critical Hit % stat box.
		criticalHitPercentageStatBox = new AttributeBox("CRITICAL HIT", containerPositionX, containerPositionY - (C.MENU_CHARACTER_ATTRIBUTE_HEIGHT*4)) {
			@Override
			public String getDisplayValue() { return stats.getCriticalShotChance() + "%"; }
		};
	}

	@Override
	public void update() {
		if(CrossbowKnight.getPlayerInput().isUpButtonPressed()) { 
			selectableAttributesBox.selectionUp();
		}
		if(CrossbowKnight.getPlayerInput().isDownButtonPressed()) { 
			selectableAttributesBox.selectionDown();
		}
		if(CrossbowKnight.getPlayerInput().isAcceptButtonPressed()) { 
			selectableAttributesBox.onSelection();
		}
		
		// TODO Remove!!!!
		this.player.getStats().addXp(1);
	}

	@Override
	public void draw(SpriteBatch batch) {
		// Draw the tabs background.
		batch.draw(background, C.INGAME_MENU_POS_X, C.INGAME_MENU_POS_Y, C.INGAME_MENU_WIDTH, C.INGAME_MENU_HEIGHT);
		// Draw the selectable attributes box container.
		selectableAttributesBox.draw(batch);
		// Draw the non-selectable attributes box container.
		hpStatBox.draw(batch);
		staminaStatBox.draw(batch);
		staminaRefillRateStatBox.draw(batch);
		criticalHitPercentageStatBox.draw(batch);
		// Draw the XP bar.
		Stats stats      = player.getStats();
		int currentLevel = stats.getLevel();
		this.xpBar.draw(batch, currentLevel, stats.getXp(), stats.getXpRequiredForLevel(currentLevel-1), stats.getXpRequiredForLevel(currentLevel));
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.CHARACTER; }
}
