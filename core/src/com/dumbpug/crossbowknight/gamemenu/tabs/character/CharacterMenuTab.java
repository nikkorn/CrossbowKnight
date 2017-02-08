package com.dumbpug.crossbowknight.gamemenu.tabs.character;

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
	private AttributesBoxContainer attributesBox;

	/**
	 * Create a new instance of the CharacterMenuTab.
	 * @param player
     */
	public CharacterMenuTab(Player player) {
		this.background    = new Texture("graphics/gamemenu/character/gamemenu_background.png");
		this.player        = player;
		this.xpBar         = new XpBar();
		this.attributesBox = new AttributesBoxContainer(player.getStats());
	}
	
	@Override
	public void update() {
		if(CrossbowKnight.getPlayerInput().isUpButtonPressed()) { 
			attributesBox.selectionUp();
		}
		if(CrossbowKnight.getPlayerInput().isDownButtonPressed()) { 
			attributesBox.selectionDown();
		}
		if(CrossbowKnight.getPlayerInput().isAcceptButtonPressed()) { 
			attributesBox.onSelection();
		}
		
		// TODO Remove!!!!
		this.player.getStats().addXp(1);
	}

	@Override
	public void draw(SpriteBatch batch) {
		// Draw the tabs background.
		batch.draw(background, C.INGAME_MENU_POS_X, C.INGAME_MENU_POS_Y, C.INGAME_MENU_WIDTH, C.INGAME_MENU_HEIGHT);
		
		// ...
		
		// Draw the attributes box.
		attributesBox.draw(batch);
		// Draw the XP bar.
		Stats stats      = player.getStats();
		int currentLevel = stats.getLevel();
		this.xpBar.draw(batch, currentLevel, stats.getXp(), stats.getXpRequiredForLevel(currentLevel-1), stats.getXpRequiredForLevel(currentLevel));
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.CHARACTER; }
}
