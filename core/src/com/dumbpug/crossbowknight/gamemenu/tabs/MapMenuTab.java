package com.dumbpug.crossbowknight.gamemenu.tabs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * In-Game menu tab for the map.
 * @author nikolas.howard
 */
public class MapMenuTab implements GameMenuTab {

	@Override
	public void update() {
		System.out.println("UPDATE_MAP");
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		System.out.println("DRAW_MAP");
	}
	
	@Override
	public GameMenuTabType getMenuTabType() { return GameMenuTabType.MAP; }
}
