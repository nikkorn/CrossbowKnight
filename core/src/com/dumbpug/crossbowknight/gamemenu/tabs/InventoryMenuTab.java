package com.dumbpug.crossbowknight.gamemenu.tabs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;

/**
 * In-Game menu tab for the player inventory.
 * @author nikolas.howard
 */
public class InventoryMenuTab implements GameMenuTab {
	/** The background for this tab. */
	private Texture background;
	
	/**
	 * Create a new instance of the InventoryMenuTab.
	 */
	public InventoryMenuTab() {
		this.background = new Texture("graphics/gamemenu/inventory/gamemenu_background.png"); 
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		// Draw the tabs background.
		batch.draw(background, C.INGAME_MENU_POS_X, C.INGAME_MENU_POS_Y, C.INGAME_MENU_WIDTH, C.INGAME_MENU_HEIGHT);
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.INVENTORY; }
}
