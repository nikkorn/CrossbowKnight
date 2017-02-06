package com.dumbpug.crossbowknight.gamemenu.tabs.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.CrossbowKnight;
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
	
	/**
	 * Create a new instance of the CharacterMenuTab.
	 * @param player
     */
	public CharacterMenuTab(Player player) {
		this.background  = new Texture("graphics/gamemenu/character/gamemenu_background.png");
		this.player      = player;
		this.xpBar       = new XpBar();
	}
	
	@Override
	public void update() {
		if(CrossbowKnight.getPlayerInput().isLeftButtonPressed()) { 
			
		}
		if(CrossbowKnight.getPlayerInput().isRightButtonPressed()) { 
			
		}
		if(CrossbowKnight.getPlayerInput().isUpButtonPressed()) { 
			
		}
		if(CrossbowKnight.getPlayerInput().isDownButtonPressed()) { 
		
		}
		// Check for accept/cancel button presses.
		if(CrossbowKnight.getPlayerInput().isAcceptButtonPressed()) { 
			
		}
		if(CrossbowKnight.getPlayerInput().isCancelButtonPressed()) { 
		
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		// Draw the tabs background.
		batch.draw(background, C.INGAME_MENU_POS_X, C.INGAME_MENU_POS_Y, C.INGAME_MENU_WIDTH, C.INGAME_MENU_HEIGHT);
		
		// ...
		
		// Draw the XP bar.
		this.xpBar.draw(player.getStats().getLevel(), player.getStats().getLevel());
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.CHARACTER; }
}
