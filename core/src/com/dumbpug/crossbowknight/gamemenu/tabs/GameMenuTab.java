package com.dumbpug.crossbowknight.gamemenu.tabs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Interface for in-game menu tabs.
 * @author nikolas.howard
 */
public interface GameMenuTab {
	
	/**
	 * Get the type of this game menu tab.
	 * @return type.
	 */
	public GameMenuTabType getMenuTabType();
	
	/**
	 * Update this menu tab.
	 */
	public void update();
	
	/**
	 * Draw this tab.
	 * @param batch
	 */
	public void draw(SpriteBatch batch);
}
