package com.dumbpug.crossbowknight.gamemenu.tabs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * In-Game menu tab for system options.
 * @author nikolas.howard
 */
public class SystemMenuTab implements GameMenuTab {
	
	@Override
	public void update() {
		System.out.println("UPDATE_SYSTEM");
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		System.out.println("DRAW_SYSTEM");
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.SYSTEM; }
}
