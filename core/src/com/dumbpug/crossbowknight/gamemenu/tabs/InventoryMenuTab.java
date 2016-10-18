package com.dumbpug.crossbowknight.gamemenu.tabs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * In-Game menu tab for the player inventory.
 * @author nikolas.howard
 */
public class InventoryMenuTab implements GameMenuTab {

	@Override
	public void update() {
		System.out.println("UPDATE_INVENTORY");
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		System.out.println("DRAW_INVENTORY");
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.INVENTORY; }
}
